package com.blinkboxmusic.android_sample_app;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import java.util.HashMap;

public class SpacecraftContentProvider extends ContentProvider {


    static final String PROVIDER_NAME = "bbm";
    static final String URL = "content://" + PROVIDER_NAME + "/spacecrafts";
    static final Uri CONTENT_URI = Uri.parse(URL);

    private static HashMap<String, String> SPACECRAFT_PROJECTION_MAP = null;

    static final int SPACECRAFT = 1;
    static final int SPACECRAFT_ID = 2;

    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "spacecrafts", SPACECRAFT);
        uriMatcher.addURI(PROVIDER_NAME, "spacecraft/#", SPACECRAFT_ID);
    }

    private SQLiteDatabase db = null;

    public SpacecraftContentProvider() {
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return (db == null)? false:true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        /**
         * Add a new spacecraft record
         */
        long rowId = db.insert(DatabaseHelper.SPACECRAFT_TABLE_NAME, "", values);
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
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(DatabaseHelper.SPACECRAFT_TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case SPACECRAFT:
                qb.setProjectionMap(SPACECRAFT_PROJECTION_MAP);
                break;
            case SPACECRAFT_ID:
                qb.appendWhere( DatabaseHelper._ID + "=" + uri.getPathSegments().get(1));
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if (sortOrder == null || sortOrder.equals("")){
            /**
             * By default sort on spacecraft names
             */
            sortOrder = DatabaseHelper.NAME;
        }
        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        /**
         * Set Uri for cursor to respond to on notify events
         */
        c.setNotificationUri(getContext().getContentResolver(), uri);

        return c;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;

        switch (uriMatcher.match(uri)) {
        case SPACECRAFT:
            count = db.update(DatabaseHelper.SPACECRAFT_TABLE_NAME, values, selection, selectionArgs);
            break;
        case SPACECRAFT_ID:
            count = db.update(DatabaseHelper.SPACECRAFT_TABLE_NAME, values, DatabaseHelper._ID + "= " +
                    uri.getPathSegments().get(1) +
                    (!TextUtils.isEmpty(selection) ? " AND (" +
                    selection + ')' : ""), selectionArgs);
        default:
            throw new IllegalArgumentException("Unknown URI " + uri );
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
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
}
