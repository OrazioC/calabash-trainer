package com.blinkboxmusic.android_sample_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oraziocotroneo on 16/01/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    static final String _ID = "_id";
    static final String NAME = "name";
    static final String AFFILIATION = "affiliation";
    static final String DESCRIPTION = "description";
    static final String IMAGE = "image";

    static final String DATABASE_NAME = "Bbm";
    static final String SPACECRAFT_TABLE_NAME = "spacecraft";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE =
                    " CREATE TABLE " + SPACECRAFT_TABLE_NAME +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " name TEXT NOT NULL, " +
                    " affiliation TEXT NOT NULL, " +
                    " description TEXT NOT NULL, " +
                    " image TEXT NOT NULL);";

    Context context = null;

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL(CREATE_DB_TABLE);

        List<Spacecraft> spacecrafts = new ArrayList<Spacecraft>();
        XmlPullSpacecraftParserHandler parserHandler = new XmlPullSpacecraftParserHandler();

        try {
            InputStream inputStream = this.context.getAssets().open("spacecraft.xml");

            spacecrafts = parserHandler.parseXML(inputStream);
        } catch (XmlPullParserException e) {
            Log.d("PARSER", e.getStackTrace().toString());
        } catch (IOException e) {
            Log.d("PARSER", e.getStackTrace().toString());
        }

        //Read the info from a file
        //Populate the tables
        for (Spacecraft spacecraft: spacecrafts) {
            db.execSQL("INSERT INTO " + SPACECRAFT_TABLE_NAME + " (name, affiliation, description, image) VALUES ('"+spacecraft.getName()+"', '"+spacecraft.getAffiliation()+"', '"+spacecraft.getDescription()+"', '"+spacecraft.getImageName()+"')");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SPACECRAFT_TABLE_NAME);
        onCreate(db);
    }
}
