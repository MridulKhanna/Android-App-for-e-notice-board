package com.example.hp.pictconnect.Upload;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hp.pictconnect.R;
import com.example.hp.pictconnect.Download.RequestHandler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import okhttp3.Request;


public class Teacher_upload extends AppCompatActivity {

    public static final String UPLOAD_URL = "http://pictconnect.16mb.com/testUpNotice.php";
    public static final String NOTIFICATION_URL = "http://minimilitia.16mb.com/push_notification.php";

    private Date noticeDate;
    private String Notice_UID;
    private String str_Notice_UID, notice_dept="0";
    private String fe_flag="0",se_flag="0",te_flag="0",be_flag="0",is_placement="0";
    private EditText notice_detail, notice_title, notice_UploadBy;
    private CheckBox select_all_chk,fe_chk,se_chk,te_chk,be_chk,comp_dept,it_dept,entx_dept;

    private String str_not_det,str_not_tit,str_not_upBy,str_date;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_upload);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationIcon(R.drawable.ic_back_navigate);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        notice_detail=(EditText)findViewById(R.id.notice_detail);
        notice_title=(EditText)findViewById(R.id.notice_title);
        notice_UploadBy=(EditText)findViewById(R.id.teacher_name);
        select_all_chk=(CheckBox)findViewById(R.id.all_select_box);
        fe_chk=(CheckBox)findViewById(R.id.fe_box);
        se_chk=(CheckBox)findViewById(R.id.se_box);
        te_chk=(CheckBox)findViewById(R.id.te_box);
        be_chk=(CheckBox)findViewById(R.id.be_box);

        comp_dept=(CheckBox)findViewById(R.id.comp_box);
        it_dept=(CheckBox)findViewById(R.id.it_box);
        entx_dept=(CheckBox)findViewById(R.id.entc_box);
    }

    public void select_all(View view) {         //select all checkboz listener
        if(select_all_chk.isChecked()){

            notice_dept ="7";
            fe_flag="1";se_flag="1";te_flag="1";be_flag="1";

            Toast.makeText(Teacher_upload.this,"Selected all",Toast.LENGTH_SHORT).show();
            fe_chk.setChecked(true);
            se_chk.setChecked(true);
            te_chk.setChecked(true);
            be_chk.setChecked(true);
            comp_dept.setChecked(true);
            it_dept.setChecked(true);
            entx_dept.setChecked(true);

            fe_chk.setEnabled(false);
            se_chk.setEnabled(false);
            te_chk.setEnabled(false);
            be_chk.setEnabled(false);
            comp_dept.setEnabled(false);
            it_dept.setEnabled(false);
            entx_dept.setEnabled(false);
        }
        else{
                notice_dept ="0";
                fe_flag="0";se_flag="0";te_flag="0";be_flag="0";

                Toast.makeText(Teacher_upload.this,"Selected all",Toast.LENGTH_SHORT).show();
                fe_chk.setChecked(false);
                se_chk.setChecked(false);
                te_chk.setChecked(false);
                be_chk.setChecked(false);
                comp_dept.setChecked(false);
                it_dept.setChecked(false);
                entx_dept.setChecked(false);

                fe_chk.setEnabled(true);
                se_chk.setEnabled(true);
                te_chk.setEnabled(true);
                be_chk.setEnabled(true);
                comp_dept.setEnabled(true);
                it_dept.setEnabled(true);
                entx_dept.setEnabled(true);
        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo==null)return false;
        else return true;
    }

    //-------------------------------------------------
    //------------------------------------
    public void upload_tch_on_clk(View view) {              //Upload button onclick listener

        if (!isNetworkAvailable()) {
            Toast.makeText(Teacher_upload.this, "Please Connect to a Network", Toast.LENGTH_LONG).show();
        } else {
            Notice_UID = UUID.randomUUID().toString();                               //set a UID for each notice
            noticeDate=new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yy   hh:mm");

/*
            radiogrp= (RadioGroup) findViewById(R.id.radiogroup);
            int selected_radio_btn=radiogrp.getCheckedRadioButtonId();                  //see what radio button is selected
            selectedRadioButton=(RadioButton)findViewById(selected_radio_btn);

            if(selectedRadioButton.getText()=="Placement"){
                Toast.makeText(Teacher_upload.this,"in placemcnts",Toast.LENGTH_LONG).show();
            }
            else if(selectedRadioButton.getText()=="Others"){
                Toast.makeText(Teacher_upload.this,"in others",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(Teacher_upload.this,"Select Notice Type",Toast.LENGTH_LONG).show();
            }*/

            str_date= sdf.format(noticeDate);
            str_Notice_UID = Notice_UID;
            str_not_tit = notice_title.getText().toString();
            str_not_det = notice_detail.getText().toString().trim();
            str_not_upBy = notice_UploadBy.getText().toString().trim();


            if(str_not_tit.isEmpty()){
                Toast.makeText(Teacher_upload.this,"Enter a title",Toast.LENGTH_LONG).show();
            }
            else if(str_not_det.isEmpty()){
                Toast.makeText(Teacher_upload.this,"Enter notice text",Toast.LENGTH_LONG).show();
            }
            else if(str_not_upBy.isEmpty()){
                Toast.makeText(Teacher_upload.this,"Enter your name",Toast.LENGTH_LONG).show();
            }

            else if(!fe_chk.isChecked() && !se_chk.isChecked() && !te_chk.isChecked()  && !be_chk.isChecked()  ){
                Toast.makeText(Teacher_upload.this,"Select a year",Toast.LENGTH_LONG).show();
            }

            else if((se_chk.isChecked()  || te_chk.isChecked()  || be_chk.isChecked()  )&&(!comp_dept.isChecked() && !it_dept.isChecked() &&!entx_dept.isChecked())){
                Toast.makeText(Teacher_upload.this,"Select a department",Toast.LENGTH_LONG).show();
            }


            else {
                if (fe_chk.isChecked()) fe_flag = "1";
                else if (se_chk.isChecked()) se_flag = "1";
                else if (te_chk.isChecked()) te_flag = "1";
                else if (be_chk.isChecked()) be_flag = "1";

                if ((fe_chk.isChecked()) && (se_chk.isChecked())) {
                    fe_flag = "1";  se_flag = "1";
                }
                if ((fe_chk.isChecked()) && (te_chk.isChecked())) {
                    fe_flag = "1";  te_flag = "1";
                }
                if ((fe_chk.isChecked()) && (be_chk.isChecked())) {
                    fe_flag = "1";   be_flag = "1";
                }
                if ((se_chk.isChecked()) && (te_chk.isChecked())) {
                    se_flag = "1";    te_flag = "1";
                }
                if ((se_chk.isChecked()) && (be_chk.isChecked())) {
                    se_flag = "1";     be_flag = "1";
                }
                if ((fe_chk.isChecked()) && (se_chk.isChecked()) && (te_chk.isChecked())) {
                    fe_flag = "1";
                    se_flag = "1";
                    te_flag = "1";
                }
                if ((se_chk.isChecked()) && (te_chk.isChecked()) && (be_chk.isChecked())) {
                    se_flag = "1";
                    te_flag = "1";
                    be_flag = "1";
                }
                if ((fe_chk.isChecked()) && (te_chk.isChecked()) && (be_chk.isChecked())) {
                    fe_flag = "1";
                    te_flag = "1";
                    be_flag = "1";
                }

                if (!comp_dept.isChecked() && !entx_dept.isChecked() && !it_dept.isChecked()) {         //for FE
                    notice_dept ="0";
                }
                else if (comp_dept.isChecked()) notice_dept ="1";
                else if (it_dept.isChecked()) notice_dept ="2";
                else if (entx_dept.isChecked()) notice_dept ="3";

                if (comp_dept.isChecked() && it_dept.isChecked()) {
                    notice_dept ="4";
                }
                if (comp_dept.isChecked() && entx_dept.isChecked()) {
                    notice_dept ="5";
                }
                if (entx_dept.isChecked() && it_dept.isChecked()) {
                    notice_dept ="6";
                }
                if (comp_dept.isChecked() && entx_dept.isChecked() && it_dept.isChecked()) {            //All selected
                    notice_dept ="7";
                }
            //------------------------
                    Set_Target();
            }
        }
    }
    private void Set_Target() {
        class Set_Target_DB extends AsyncTask<String,Void,String > {

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Teacher_upload.this, "Uploading...", null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... strings) {

                HashMap<String,String> data = new HashMap<>();
                data.put("Title",strings[0]);
                data.put("NoticeText",strings[1]);
                data.put("UploadBy", strings[2]);
                data.put("NoticeDept",strings[3]);
                data.put("IsFE",strings[4]);
                data.put("IsSE",strings[5]);
                data.put("IsTE",strings[6]);
                data.put("IsBE",strings[7]);
                data.put("NoticeID",strings[8]);
                data.put("NoticeDate",strings[9]);
                data.put("Placement",strings[10]);
               //---------------------------------


                HashMap<String,String> blank_data= new HashMap<>();
                blank_data.put("NoticeDept",strings[3]);
                blank_data.put("IsFE",strings[4]);
                blank_data.put("IsSE",strings[5]);
                blank_data.put("IsTE",strings[6]);
                blank_data.put("IsBE",strings[7]);


                String notifResult=rh.sendPostRequest(NOTIFICATION_URL,blank_data);
                //------------------------------------
                String result = rh.sendPostRequest(UPLOAD_URL,data);
                return result;
            }
        }
        Set_Target_DB ui = new Set_Target_DB();
        ui.execute(str_not_tit,str_not_det,str_not_upBy, notice_dept, fe_flag,se_flag,te_flag,be_flag,   str_Notice_UID  ,str_date, is_placement);
    }


    public void fe_chkbox(View view) {      //on fe checkbox click listener

        if((se_chk.isChecked() || te_chk.isChecked() ||  be_chk.isChecked())||(!fe_chk.isChecked() &&!se_chk.isChecked() && !te_chk.isChecked() &&  !be_chk.isChecked())) {
            comp_dept.setEnabled(true);
            it_dept.setEnabled(true);
            entx_dept.setEnabled(true);
        }
        else{
            comp_dept.setEnabled(false);
            it_dept.setEnabled(false);
            entx_dept.setEnabled(false);
        }
    }

    public void se_te_be_chkbox(View view) {        //on se te be checkbox click listener
        if(fe_chk.isChecked() && !se_chk.isChecked() && !te_chk.isChecked() &&  !be_chk.isChecked()){
            comp_dept.setEnabled(false);
            it_dept.setEnabled(false);
            entx_dept.setEnabled(false);
        }
        else {
            comp_dept.setEnabled(true);
            it_dept.setEnabled(true);
            entx_dept.setEnabled(true);

        }
    }

    public void placement_radio_btn(View view) {
            select_all_chk.setChecked(false);
            fe_chk.setChecked(false);
            se_chk.setChecked(false);
            te_chk.setChecked(false);
            be_chk.setChecked(true);
            comp_dept.setChecked(true);
            it_dept.setChecked(true);
            entx_dept.setChecked(true);

            select_all_chk.setEnabled(false);
            fe_chk.setEnabled(false);
            se_chk.setEnabled(false);
            te_chk.setEnabled(false);
            be_chk.setEnabled(false);
            comp_dept.setEnabled(false);
            it_dept.setEnabled(false);
            entx_dept.setEnabled(false);
            is_placement="1";
    }

    public void others_radio_btn(View view) {
        select_all_chk.setChecked(false);
        fe_chk.setChecked(false);
        se_chk.setChecked(false);
        te_chk.setChecked(false);
        be_chk.setChecked(false);
        comp_dept.setChecked(false);
        it_dept.setChecked(false);
        entx_dept.setChecked(false);

        select_all_chk.setEnabled(true);
        fe_chk.setEnabled(true);
        se_chk.setEnabled(true);
        te_chk.setEnabled(true);
        be_chk.setEnabled(true);
        comp_dept.setEnabled(true);
        it_dept.setEnabled(true);
        entx_dept.setEnabled(true);
        is_placement="0";

    }


}
