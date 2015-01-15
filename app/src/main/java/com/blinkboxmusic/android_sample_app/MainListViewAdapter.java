package com.blinkboxmusic.android_sample_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by orazio on 15/12/14.
 */
public class MainListViewAdapter extends BaseAdapter{

    List<Spacecraft> mainList = getDataForListView();
    Context context;
    enum Faction {EMPIRE, REBELLION};

    public MainListViewAdapter(Context pContext){
        context = pContext;
    }

    private List<Spacecraft> getDataForListView() {
        mainList = new ArrayList<Spacecraft>();

        Spacecraft item1 = new Spacecraft();
        item1.setName("Death Star");
        item1.setAffiliation(Faction.EMPIRE.toString());

        Spacecraft item2 = new Spacecraft();
        item2.setName("Mon Calamari cruiser");
        item2.setAffiliation(Faction.REBELLION.toString());

        Spacecraft item3 = new Spacecraft();
        item3.setName("Tantive IV");
        item3.setAffiliation(Faction.REBELLION.toString());

        Spacecraft item4 = new Spacecraft();
        item4.setName("Star Destroyer");
        item4.setAffiliation(Faction.EMPIRE.toString());

        mainList.add(item1);
        mainList.add(item2);
        mainList.add(item3);
        mainList.add(item4);

        return mainList;
    }

    @Override
    public int getCount() {
        return mainList.size();
    }

    @Override
    public Spacecraft getItem(int position) {
        return mainList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // ListView recycles the views
        if (convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.main_list_item, parent, false);
        }

        TextView shipNameView = (TextView)convertView.findViewById(R.id.item_name);
        ImageView affiliationLogoImageView = (ImageView)convertView.findViewById(R.id.item_affiliation_logo);

        String shipName = mainList.get(position).getName();
        String affiliation = mainList.get(position).getAffiliation();

        if (affiliation.equals(Faction.REBELLION.toString())) {
            affiliationLogoImageView.setImageResource(R.drawable.rebellion);
        } else {
            affiliationLogoImageView.setImageResource(R.drawable.empire);
        }


        shipNameView.setText(shipName);

        return convertView;
    }
}
