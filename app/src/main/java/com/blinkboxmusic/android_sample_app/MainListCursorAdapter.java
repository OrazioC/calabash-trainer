package com.blinkboxmusic.android_sample_app;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by orazio on 07/01/15.
 */
public class MainListCursorAdapter extends CursorAdapter {

    enum Faction {EMPIRE, REBELLION};

    public MainListCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(R.layout.main_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView shipNameView = (TextView)view.findViewById(R.id.item_name);
        ImageView affiliationLogoImageView = (ImageView)view.findViewById(R.id.item_affiliation_logo);

        String affiliation = cursor.getString(2);

        if (affiliation.equals(Faction.REBELLION.toString())) {
            affiliationLogoImageView.setImageResource(R.drawable.rebellion);
        } else {
            affiliationLogoImageView.setImageResource(R.drawable.empire);
        }

        shipNameView.setText(cursor.getString(1));

    }
}
