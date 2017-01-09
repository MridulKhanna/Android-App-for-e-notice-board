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
import com.example.hp.pictconnect.TabListFragments.FEListActivity;
import com.example.hp.pictconnect.R;


/**
 * Created by HP on 22-07-2016.
 */
public class FE_MainPage_Frgmnt extends ListFragment {

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

        NavDrawerYearSelected.setIsNavFESelected(1);
        NavDrawerYearSelected.setIsNavSESelected(0);
        NavDrawerYearSelected.setIsNavTESelected(0);
        NavDrawerYearSelected.setIsNavBESelected(0);
        if(position==0)
        {
            Intent i=new Intent(getActivity().getApplicationContext(),FEListActivity.class);
            startActivity(i);
        }
        else if(position==1){
            Intent weblink= new Intent(Intent.ACTION_VIEW);
            weblink.setData(Uri.parse("http://pict.edu/wp-content/uploads/2015/12/FE_Syllabus_2012_Course_10-7-12.pdf?0c760f"));
            startActivity(weblink);
        }
        else if (position==2){
            Intent weblink= new Intent(Intent.ACTION_VIEW);
            weblink.setData(Uri.parse("http://pict.edu/wp-content/uploads/2015/09/All_DIVISION_TT_Sem2__AY_16-19-2.pdf?0c760f"));
            startActivity(weblink);        }
    }
}







