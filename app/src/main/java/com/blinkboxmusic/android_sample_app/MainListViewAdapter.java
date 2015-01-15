package com.blinkboxmusic.android_sample_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by orazio on 15/12/14.
 * This class is not used anymore, but keeping it as an example
 */
public class MainListViewAdapter extends BaseAdapter{

    private Context mContext;
    private List<Spacecraft> mMainList;


    public MainListViewAdapter(final Context context, final List<Spacecraft> mainList){

        this.mContext = context;
        this.mMainList = mainList;
    }

    @Override
    public int getCount() {
        return mMainList.size();
    }

    @Override
    public Spacecraft getItem(int position) {
        return mMainList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolderMainListItem viewHolderMainListItem;

        // ListView recycles the views
        if (convertView==null) {
            // Inflate the layout
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.main_list_item, parent, false);

            // Set up the ViewHolder
            viewHolderMainListItem = new ViewHolderMainListItem();
            viewHolderMainListItem.textViewItem = (TextView) convertView.findViewById(R.id.item_name);
            viewHolderMainListItem.imageViewItem = (ImageView) convertView.findViewById(R.id.item_affiliation_logo);

            // store the holder with the view.
            convertView.setTag(viewHolderMainListItem);

        } else {
            // we've just avoided calling findViewById() on resource everytime
            // just use the viewHolder
            viewHolderMainListItem = (ViewHolderMainListItem) convertView.getTag();

        }

        Spacecraft mainListItem = mMainList.get(position);

        if (mainListItem != null) {

            if (mainListItem.getAffiliation().equals(Constants.Faction.REBELLION.toString())) {
                viewHolderMainListItem.imageViewItem.setImageResource(R.drawable.rebellion);
            } else {
                viewHolderMainListItem.imageViewItem.setImageResource(R.drawable.empire);
            }

            // get the TextView from the ViewHolder and then set the text (item name) and tag (item ID) values
            viewHolderMainListItem.textViewItem.setText(mainListItem.getName());

        }

        return convertView;
    }

    static class ViewHolderMainListItem {
        TextView textViewItem;
        ImageView imageViewItem;
    }

}
