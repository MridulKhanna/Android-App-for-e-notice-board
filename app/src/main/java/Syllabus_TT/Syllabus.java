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
public class Syllabus extends AppCompatActivity {

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

                    weblink.setData(Uri.parse("http://pict.edu/wp-content/uploads/2015/09/SPPU_SE_Computer_Engg_2015_Course_Syllabus_June2016-1-27-6-16.pdf?0c760f"));
                    startActivity(weblink);
                }
            });
            Button seit=(Button) findViewById(R.id.seit);
            seit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent weblink= new Intent(Intent.ACTION_VIEW);
                    weblink.setData(Uri.parse("http://pict.edu/wp-content/uploads/2015/09/SEIT-2015-Course-Syllabus-Final-19-06-2016-24-6-16.pdf?0c760f"));
                    startActivity(weblink);
                }
            });
            Button seentc=(Button) findViewById(R.id.seentc);
            seentc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent weblink= new Intent(Intent.ACTION_VIEW);
                    weblink.setData(Uri.parse("http://pict.edu/wp-content/uploads/2015/09/Revised_SE_E_TC_Syllabus_2016_14__June_2016-27-6-16.pdf?0c760f"));
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
                    weblink.setData(Uri.parse("http://pict.edu/wp-content/uploads/2015/12/Syllabus-TE-Computer-Engineering-2012Course.pdf?0c760f"));
                    startActivity(weblink);
                }
            });
            Button seit=(Button) findViewById(R.id.seit);
            seit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent weblink= new Intent(Intent.ACTION_VIEW);
                    weblink.setData(Uri.parse("http://pict.edu/wp-content/uploads/2015/09/TEIT-Syllabus-2012-Course-29-10-20151.pdf?0c760f"));
                    startActivity(weblink);
                }
            });
            Button seentc=(Button) findViewById(R.id.seentc);
            seentc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent weblink= new Intent(Intent.ACTION_VIEW);
                    weblink.setData(Uri.parse("http://pict.edu/wp-content/uploads/2015/12/TE-Electronics-Telecommunication.pdf?0c760f"));
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
                    weblink.setData(Uri.parse("http://pict.edu/wp-content/uploads/2015/09/BE-Computer-2012-course-27-8-15.pdf?0c760f"));
                    startActivity(weblink);
                }
            });
            Button seit=(Button) findViewById(R.id.seit);
            seit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent weblink= new Intent(Intent.ACTION_VIEW);
                    weblink.setData(Uri.parse("http://pict.edu/wp-content/uploads/2015/09/BE-IT-Syllabus-2012-Course-Final-15-6-15.pdf?0c760f"));
                    startActivity(weblink);
                }
            });
            Button seentc=(Button) findViewById(R.id.seentc);
            seentc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent weblink= new Intent(Intent.ACTION_VIEW);
                    weblink.setData(Uri.parse("http://pict.edu/wp-content/uploads/2015/12/BE-ETC-Syllabus-2012-course-revised-syllabus.pdf?0c760f"));
                    startActivity(weblink);
                }
            });
        }

    }
}
