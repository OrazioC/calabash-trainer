package com.blinkboxmusic.android_sample_app;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SpacecraftContentProvider extends ContentProvider {


    static final String PROVIDER_NAME = "bbm";
    static final String URL = "content://" + PROVIDER_NAME + "/spacecrafts";
    static final Uri CONTENT_URI = Uri.parse(URL);

    private static HashMap<String, String> SPACECRAFT_PROJECTION_MAP;

    static final int SPACECRAFT = 1;
    static final int SPACECRAFT_ID = 2;

    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "spacecraft", SPACECRAFT);
        uriMatcher.addURI(PROVIDER_NAME, "spacecraft/#", SPACECRAFT_ID);
    }

    // Name
    // Affiliation
    // Image
    static final String _ID = "_id";
    static final String NAME = "name";
    static final String AFFILIATION = "affiliation";
    static final String Image = "image";

    private SQLiteDatabase db;
    static final String DATABASE_NAME = "Bbm";
    static final String SPACECRAFT_TABLE_NAME = "spacecraft";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE =
            " CREATE TABLE " + SPACECRAFT_TABLE_NAME +
            " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " name TEXT NOT NULL, " +
            " affiliation TEXT NOT NULL);";

    private static class DatabaseHelper extends SQLiteOpenHelper {

        Context context;

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db){

            db.execSQL(CREATE_DB_TABLE);


            //TODO Parse the spacecrafts xml file
            XmlPullParserFactory pullParserFactory;
            List<Spacecraft> spacecrafts = new ArrayList<Spacecraft>();

            try {
                pullParserFactory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = pullParserFactory.newPullParser();

                InputStream in_s = this.context.getAssets().open("spacecraft.xml");

                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(in_s, null);

                parseXML(parser, spacecrafts);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Read the info from a file
            //Populate the tables
            for (Spacecraft spacecraft: spacecrafts) {
                db.execSQL("INSERT INTO " + SPACECRAFT_TABLE_NAME + " (name, affiliation) VALUES ('"+spacecraft.getName()+"', '"+spacecraft.getAffiliation()+"')");
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + SPACECRAFT_TABLE_NAME);
            onCreate(db);
        }

        private void parseXML(final XmlPullParser parser, final List<Spacecraft> spacecrafts) throws XmlPullParserException,IOException
        {

            int eventType = parser.getEventType();

            Spacecraft currentSpacecraft = null;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    Log.d("PARSER", "START_DOCUMENT " + parser.getName());
                    break;
                case XmlPullParser.START_TAG:
                    String tagName = parser.getName();

                    if (tagName.equals("spacecraft")) {
                        currentSpacecraft = new Spacecraft();
                    } else if (tagName.equals("name")) {
                        currentSpacecraft.setName(parser.nextText());
                    } else if (tagName.equals("affiliation")) {
                        currentSpacecraft.setAffiliation(parser.nextText());
                    }


                    Log.d("PARSER", "START_TAG " + parser.getName());
                    break;
                case XmlPullParser.TEXT:
                    Log.d("PARSER", "TEXT " + parser.getText());
                    break;
                case XmlPullParser.END_TAG:
                    String endTagName = parser.getName();
                    if (endTagName.equals("spacecraft")) {
                        spacecrafts.add(currentSpacecraft);
                    }
                    Log.d("PARSER", "END_TAG " + parser.getName());
                    break;
                case XmlPullParser.END_DOCUMENT :
                    Log.d("PARSER", "END_DOCUMENT " + parser.getName());
                    break;
                }
                eventType = parser.next();
            }
        }

    }


    public SpacecraftContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
        /**
         * Get all spacecraft records
         */
        case SPACECRAFT:
            return "vnd.android.cursor.dir/vnd.example.spacecraft";
        /**
         * Get a particular spacecraft
         */
        case SPACECRAFT_ID:
            return "vnd.android.cursor.item/vnd.example.spacecraft";
        default:
            throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        /**
         * Add a new spacecraft record
         */
        long rowId = db.insert(SPACECRAFT_TABLE_NAME, "", values);
        /**
         * If record is added successfully
         */
        if (rowId > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        return null;
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return (db == null)? false:true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(SPACECRAFT_TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case SPACECRAFT:
                qb.setProjectionMap(SPACECRAFT_PROJECTION_MAP);
                break;
            case SPACECRAFT_ID:
                qb.appendWhere( _ID + "=" + uri.getPathSegments().get(1));
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if (sortOrder == null || sortOrder == ""){
            /**
             * By default sort on spacecraft names
             */
            sortOrder = NAME;
        }
        Cursor c = qb.query(db,	projection,	selection, selectionArgs, null, null, sortOrder);
        /**
         * register to watch a content URI for changes
         */
        c.setNotificationUri(getContext().getContentResolver(), uri);

        return c;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;

        switch (uriMatcher.match(uri)) {
        case SPACECRAFT:
            count = db.update(SPACECRAFT_TABLE_NAME, values, selection, selectionArgs);
            break;
        case SPACECRAFT_ID:
            count = db.update(SPACECRAFT_TABLE_NAME, values, _ID + "= " +
                    uri.getPathSegments().get(1) +
                    (!TextUtils.isEmpty(selection) ? " AND (" +
                    selection + ')' : ""), selectionArgs);
        default:
            throw new IllegalArgumentException("Unknown URI " + uri );
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
