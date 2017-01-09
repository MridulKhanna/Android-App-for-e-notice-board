package com.example.hp.pictconnect.Syllabus_TT;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.hp.pictconnect.NavDrawerYearSelected;
import com.example.hp.pictconnect.R;

/**
 * Created by HP on 30-09-2016.
 */
public class Timetable extends AppCompatActivity {

    private Toolbar mtoolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable);

        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mtoolbar.setNavigationIcon(R.drawable.ic_back_navigate);

        mtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //----------------------------------------------------------------------
     if (NavDrawerYearSelected.getIsNavSESelected()==1){
        Button secomp=(Button) findViewById(R.id.secomp);
        secomp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent weblink= new Intent(Intent.ACTION_VIEW);
                weblink.setData(Uri.parse("http://pict.edu/wp-content/uploads/2015/09/SE_COMP_ALL_16-17.pdf?0c760f"));
                startActivity(weblink);
            }
        });
        Button seit=(Button) findViewById(R.id.seit);
        seit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent weblink= new Intent(Intent.ACTION_VIEW);
                weblink.setData(Uri.parse("http://pict.edu/wp-content/uploads/2015/09/SE_All_IT_1617.pdf?0c760f"));
                startActivity(weblink);
            }
        });
        Button seentc=(Button) findViewById(R.id.seentc);
        seentc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent weblink= new Intent(Intent.ACTION_VIEW);
                weblink.setData(Uri.parse("http://pict.edu/wp-content/uploads/2015/09/SE_All_ETC_16-17.pdf?0c760f"));
                startActivity(weblink);
            }
        });

    }
    //----------------------------------------------------------------------
    else if (NavDrawerYearSelected.getIsNavTESelected()==1){
        Button secomp=(Button) findViewById(R.id.secomp);
        secomp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent weblink= new Intent(Intent.ACTION_VIEW);
                weblink.setData(Uri.parse("http://pict.edu/wp-content/uploads/2015/09/TE_Comp_ALL_16-17.pdf?0c760f"));
                startActivity(weblink);
            }
        });
        Button seit=(Button) findViewById(R.id.seit);
        seit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent weblink= new Intent(Intent.ACTION_VIEW);
                weblink.setData(Uri.parse("http://pict.edu/wp-content/uploads/2015/09/TE_All_IT1617.pdf?0c760f"));
                startActivity(weblink);
            }
        });
        Button seentc=(Button) findViewById(R.id.seentc);
        seentc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent weblink= new Intent(Intent.ACTION_VIEW);
                weblink.setData(Uri.parse("http://pict.edu/wp-content/uploads/2015/09/TE_All_ETC_16-17.pdf?0c760f"));
                startActivity(weblink);
            }
        });
    }
    //----------------------------------------------------------------------
    else if (NavDrawerYearSelected.getIsNavBESelected()==1){
        Button secomp=(Button) findViewById(R.id.secomp);
        secomp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent weblink= new Intent(Intent.ACTION_VIEW);
                weblink.setData(Uri.parse("http://pict.edu/wp-content/uploads/2015/09/BE_Comp_ALL_16-17.pdf?0c760f"));
                startActivity(weblink);
            }
        });
        Button seit=(Button) findViewById(R.id.seit);
        seit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent weblink= new Intent(Intent.ACTION_VIEW);
                weblink.setData(Uri.parse("http://pict.edu/wp-content/uploads/2015/09/BE_All_IT_16-17.pdf?0c760f"));
                startActivity(weblink);
            }
        });
        Button seentc=(Button) findViewById(R.id.seentc);
        seentc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent weblink= new Intent(Intent.ACTION_VIEW);
                weblink.setData(Uri.parse("http://pict.edu/wp-content/uploads/2015/09/BE_All_ETC16-17.pdf?0c760f"));
                startActivity(weblink);
            }
        });
    }

}
}

