package com.blinkboxmusic.android_sample_app;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
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


public class MainActivity extends ActionBarActivity implements LoaderManager.LoaderCallbacks<Cursor> {


    public final static String EXTRAS_KEY_ITEM_NAME_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";
    private MainListCursorAdapter mCursorAdapter = null;


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

        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        // CursorLoader uses a worker thread to query a content provider and return a Cursor
        getSupportLoaderManager().initLoader(0, null, this);

        // Instantiate the Adapter obj
        // The Cursor Adapter gets initialized with a null cursor
        // When the Loader is created the onLoadFinished method is invoked
        // the actual cursor then gets assigned to the CursorAdapter
        mCursorAdapter = new MainListCursorAdapter(MainActivity.this, null, false);
        // Take the reference of the ListView
        ListView mainListView = (ListView)findViewById(R.id.main_list_view);
        // Bind the Adapter with ListView
        mainListView.setAdapter(mCursorAdapter);


        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor cursor = ((MainListCursorAdapter) parent.getAdapter()).getCursor();
                cursor.moveToPosition(position);

                String itemName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.NAME));

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

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, SpacecraftContentProvider.CONTENT_URI, null, null, null, "name");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }
}
