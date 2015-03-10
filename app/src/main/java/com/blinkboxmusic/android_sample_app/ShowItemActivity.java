package com.blinkboxmusic.android_sample_app;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class ShowItemActivity extends ActionBarActivity {

    public static String PACKAGE_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PACKAGE_NAME = this.getPackageName();

        /*
         * Getting the parcelable object from intent extras(bundle)
         */
        Bundle bundle = getIntent().getExtras();
        Spacecraft spacecraft = bundle.getParcelable(MainActivity.EXTRAS_KEY_ITEM);

        Log.d("Parcelable spacecraft: ", spacecraft.getName());


        Resources res = getResources();
        String text = String.format(res.getString(R.string.title_activity_show_item), spacecraft.getName());
        setTitle(text);


        PlaceholderFragment itemfrag = new PlaceholderFragment();
        /*
         * Sending data from Activity to Fragment
         */
        itemfrag.setArguments(bundle);

        setContentView(R.layout.activity_show_item);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, itemfrag)
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_item, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            Spacecraft spacecraft = getArguments().getParcelable(MainActivity.EXTRAS_KEY_ITEM);

            View rootView = inflater.inflate(R.layout.fragment_show_item, container, false);

            TextView spacecraftClass = (TextView) rootView.findViewById(R.id.class_description);
            spacecraftClass.setText(spacecraft.getSpacecraftClass());

            TextView armament = (TextView) rootView.findViewById(R.id.armament_description);
            armament.setText(spacecraft.getArmaments());

            TextView defence = (TextView) rootView.findViewById(R.id.defence_description);
            defence.setText(spacecraft.getDefences());

            TextView size = (TextView) rootView.findViewById(R.id.size_description);
            size.setText(spacecraft.getSize());

            ImageView image = (ImageView) rootView.findViewById(R.id.item_large_image);
            String uri = "@drawable/" + spacecraft.getImageName() + "_large";

            Log.d("PACKAGE", PACKAGE_NAME);
            int imageResource = getResources().getIdentifier(uri, null, PACKAGE_NAME);
            Drawable res = getResources().getDrawable(imageResource);
            image.setImageDrawable(res);

            return rootView;
        }
    }
}
