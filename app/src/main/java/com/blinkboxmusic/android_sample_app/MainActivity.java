package com.blinkboxmusic.android_sample_app;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {


    public final static String EXTRAS_KEY_ITEM_NAME_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String username = intent.getStringExtra(SignInActivity.EXTRAS_KEY_SIGN_IN_MESSAGE);

        Resources res = getResources();
        String text = String.format(res.getString(R.string.welcome_message), username);
        TextView welcome_message = (TextView)findViewById(R.id.welcome_label);
        welcome_message.setText(text);


        /**
         *
         */
//        ContentValues values = new ContentValues();
//
//        values.put(SpacecraftContentProvider.NAME, "Death Star");
//        values.put(SpacecraftContentProvider.AFFILIATION, MainListViewAdapter.Faction.EMPIRE.toString());
//        Uri uri = getContentResolver().insert(SpacecraftContentProvider.CONTENT_URI, values);
//
//        Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();

        // get a cursor from the ContentProvider
        // iterate using the cursor to populate the list
        // (in the future use cursor adapter

        String URL = "content://bbm/spacecraft";
        Uri spacecrafts = Uri.parse(URL);
        Cursor cursor = getContentResolver().query(spacecrafts, null, null, null, "name");

        Log.d("CURSOR", String.valueOf(cursor.getCount()));
        startManagingCursor(cursor);

        final MainListCursorAdapter cursorAdapter = new MainListCursorAdapter(this,cursor,false);

        // Bind the Adapter with the ListView
        // Instantiate the Adapter obj
        //final MainListViewAdapter mainListViewAdapter = new MainListViewAdapter(getApplicationContext());
        // Take the reference of the ListView
        ListView mainListView = (ListView)findViewById(R.id.main_list_view);
        // Bind the Adapter with ListView
        mainListView.setAdapter(cursorAdapter);


        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor cursor = ((MainListCursorAdapter) parent.getAdapter()).getCursor();
                cursor.moveToPosition(position);

                String itemName = cursor.getString(cursor.getColumnIndex(SpacecraftContentProvider.NAME));

                Intent intent = new Intent(getApplicationContext(), ShowItemActivity.class);
                intent.putExtra(EXTRAS_KEY_ITEM_NAME_MESSAGE, itemName);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
