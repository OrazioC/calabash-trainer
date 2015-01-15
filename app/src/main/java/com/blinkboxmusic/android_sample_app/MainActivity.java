package com.blinkboxmusic.android_sample_app;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

        // Bind the Adapter with the ListView
        // Instantiate the Adapter obj
        final MainListViewAdapter mainListViewAdapter = new MainListViewAdapter(getApplicationContext(), getDataForListView());
        // Take the reference of the ListView
        ListView mainListView = (ListView)findViewById(R.id.main_list_view);
        // Bind the Adapter with ListView
        mainListView.setAdapter(mainListViewAdapter);

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainListItem item = mainListViewAdapter.getItem(position);

                Intent intent = new Intent(getApplicationContext(), ShowItemActivity.class);
                intent.putExtra(EXTRAS_KEY_ITEM_NAME_MESSAGE, item.getName());
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

    private List<MainListItem> getDataForListView() {
        List<MainListItem> mainList = new ArrayList<MainListItem>();

        MainListItem item1 = new MainListItem();
        item1.setName("Death Star");
        item1.setAffiliation(Contants.Faction.EMPIRE.toString());

        MainListItem item2 = new MainListItem();
        item2.setName("Mon Calamari cruiser");
        item2.setAffiliation(Contants.Faction.REBELLION.toString());

        MainListItem item3 = new MainListItem();
        item3.setName("Tantive IV");
        item3.setAffiliation(Contants.Faction.REBELLION.toString());

        MainListItem item4 = new MainListItem();
        item4.setName("Star Destroyer");
        item4.setAffiliation(Contants.Faction.EMPIRE.toString());

        mainList.add(item1);
        mainList.add(item2);
        mainList.add(item3);
        mainList.add(item4);

        return mainList;
    }
}
