package com.example.hp.pictconnect.MainPage_Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hp.pictconnect.NavDrawerYearSelected;
import com.example.hp.pictconnect.Syllabus_TT.Syllabus;
import com.example.hp.pictconnect.Syllabus_TT.Timetable;
import com.example.hp.pictconnect.TabListFragments.FEListActivity;
import com.example.hp.pictconnect.R;
import com.example.hp.pictconnect.Tabs.Toolbar_activity;


/**
 * Created by HP on 22-07-2016.
 */
public class TE_MainPage_Frgmnt extends ListFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ArrayAdapter adapter=ArrayAdapter.createFromResource(getActivity(), R.array.fe_se_te_list,android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        NavDrawerYearSelected.setIsNavFESelected(0);
        NavDrawerYearSelected.setIsNavSESelected(0);
        NavDrawerYearSelected.setIsNavTESelected(1);
        NavDrawerYearSelected.setIsNavBESelected(0);

        if(position==0)
        {
            Intent i=new Intent(getActivity().getApplicationContext(),Toolbar_activity.class);
            startActivity(i);
        }
        else if (position==1){
            Intent i=new Intent(getActivity().getApplicationContext(),Syllabus.class);
            startActivity(i);
        }
        else if(position==2)
        {
            /*
            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("http://www.google.com"));
            startActivity(intent);*/
            Intent i=new Intent(getActivity().getApplicationContext(),Timetable.class);
            startActivity(i);
        }
    }
}







