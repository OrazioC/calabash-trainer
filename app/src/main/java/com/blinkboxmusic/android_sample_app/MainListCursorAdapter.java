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

    public MainListCursorAdapter(final Context context, final Cursor c, final boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(final Context context, final Cursor cursor, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.main_list_item, parent, false);

        // Set up the ViewHolder
        // CursorAdapter is not going to call the newView each time it needs a new row.
        // If it already has a View it will reuse it and call the bindView method.
        // But we can still increase the performances avoiding calling the findViewById on each bindView invocation
        ViewHolderMainListItem viewHolderMainListItem = new ViewHolderMainListItem();
        viewHolderMainListItem.textViewItem = (TextView) view.findViewById(R.id.item_name);
        viewHolderMainListItem.imageViewItem = (ImageView) view.findViewById(R.id.item_affiliation_logo);

        view.setTag(viewHolderMainListItem);

        return view;
    }

    @Override
    public void bindView(final View view, final Context context, final Cursor cursor) {

        ViewHolderMainListItem viewHolderMainListItem = (ViewHolderMainListItem)view.getTag();

        // Getting the indexes for the columns
        int nameColumnIndex = cursor.getColumnIndexOrThrow(DatabaseHelper.NAME);
        int affiliationColumnIndex = cursor.getColumnIndexOrThrow(DatabaseHelper.AFFILIATION);

        // Getting the values (x row x column)
        String name = cursor.getString(nameColumnIndex);
        String affiliation = cursor.getString(affiliationColumnIndex);

        // Setting the values in the view using the view holder
        viewHolderMainListItem.textViewItem.setText(name);

        if (affiliation.equals(Constants.Faction.REBELLION.toString())) {
            viewHolderMainListItem.imageViewItem.setImageResource(R.drawable.rebellion);
        } else {
            viewHolderMainListItem.imageViewItem.setImageResource(R.drawable.empire);
        }
    }

    static class ViewHolderMainListItem {
        TextView textViewItem;
        ImageView imageViewItem;
    }
}
