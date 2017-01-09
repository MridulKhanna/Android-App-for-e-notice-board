package com.example.hp.pictconnect;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.hp.pictconnect.Download.RequestHandler;
import com.example.hp.pictconnect.MainPage_Fragment.BE_MainPage_Frgmnt;
import com.example.hp.pictconnect.MainPage_Fragment.FE_MainPage_Frgmnt;
import com.example.hp.pictconnect.MainPage_Fragment.SE_MainPage_Frgmnt;
import com.example.hp.pictconnect.MainPage_Fragment.TE_MainPage_Frgmnt;
import com.example.hp.pictconnect.Tabs.Toolbar_activity;
import com.example.hp.pictconnect.Upload.Login_authenticate;
import com.example.hp.pictconnect.Upload.Teacher_upload;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
    private String str_Notice_UID;
    private String notice_dept="0";
    private int fe_flag=0,se_flag=0,te_flag=0,be_flag=0,placement_flag=0;
    private String str_not_det,str_not_tit,str_not_upBy,str_date;
    private RequestHandler requestHandler;
    private static final String TAG="MainActivity";


    JSONArray peoples = null;
    String myJSON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String token= FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"Token: "+token);
        //Toast.makeText(MainActivity.this,token, Toast.LENGTH_SHORT).show();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_first_layout) {
            NavDrawerYearSelected.setIsNavFESelected(1);
            NavDrawerYearSelected.setIsNavSESelected(0);
            NavDrawerYearSelected.setIsNavTESelected(0);
            NavDrawerYearSelected.setIsNavBESelected(0);
            getSingleNotice();  //asynctask function

            fragmentManager.beginTransaction().replace(R.id.content_frame,new FE_MainPage_Frgmnt()).addToBackStack(null).commit();

        } else if (id == R.id.nav_second_layout) {
            NavDrawerYearSelected.setIsNavFESelected(0);
            NavDrawerYearSelected.setIsNavSESelected(1);
            NavDrawerYearSelected.setIsNavTESelected(0);
            NavDrawerYearSelected.setIsNavBESelected(0);
            getSingleNotice();  //asynctask function

            fragmentManager.beginTransaction().replace(R.id.content_frame,new SE_MainPage_Frgmnt()).addToBackStack(null).commit();
        } else if (id == R.id.nav_third_layout) {
            NavDrawerYearSelected.setIsNavFESelected(0);
            NavDrawerYearSelected.setIsNavSESelected(0);
            NavDrawerYearSelected.setIsNavTESelected(1);
            NavDrawerYearSelected.setIsNavBESelected(0);
            getSingleNotice();  //asynctask function

            fragmentManager.beginTransaction().replace(R.id.content_frame,new TE_MainPage_Frgmnt()).addToBackStack(null).commit();

        }  else if (id == R.id.nav_fourth_layout) {
            NavDrawerYearSelected.setIsNavFESelected(0);
            NavDrawerYearSelected.setIsNavSESelected(0);
            NavDrawerYearSelected.setIsNavTESelected(0);
            NavDrawerYearSelected.setIsNavBESelected(1);
            getSingleNotice();  //asynctask function

            fragmentManager.beginTransaction().replace(R.id.content_frame,new BE_MainPage_Frgmnt()).addToBackStack(null).commit();

        }
        else if (id == R.id.download_notice) {
            Intent i=new Intent(MainActivity.this,Login_authenticate.class);
            startActivity(i);
        }

        else if(id==R.id.pdf){
            Intent weblink= new Intent(Intent.ACTION_VIEW);
            weblink.setData(Uri.parse("http://connectpict.16mb.com/PDFs"));
            startActivity(weblink);
        }

        else if(id==R.id.presentations){
            Intent weblink= new Intent(Intent.ACTION_VIEW);
            weblink.setData(Uri.parse("http://connectpict.16mb.com/Presentations"));
            startActivity(weblink);
        }

        else if(id==R.id.documents){
            Intent weblink= new Intent(Intent.ACTION_VIEW);
            weblink.setData(Uri.parse("http://connectpict.16mb.com/Documents"));
            startActivity(weblink);
        }

        else if(id==R.id.images){
            Intent weblink= new Intent(Intent.ACTION_VIEW);
            weblink.setData(Uri.parse("http://connectpict.16mb.com/Images"));
            startActivity(weblink);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void getSingleNotice()

    {
        NoticeLab.intialize_notice();

        requestHandler=new RequestHandler();
        class fetchDetail extends AsyncTask<String,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Downloading...", null, true, true);
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                myJSON=result;
                try {
                    JSONObject jsonObj = new JSONObject(myJSON);
                    peoples = jsonObj.getJSONArray("result");

                    for(int i=0;i<peoples.length();i++) {
                        JSONObject c = peoples.getJSONObject(i);
                        str_Notice_UID=c.getString("NoticeID");
                        str_not_tit  = c.getString("Title");
                        str_not_det = c.getString("NoticeText");
                        str_not_upBy  = c.getString("UploadBy");
                        notice_dept=c.getString("NoticeDept");
                        fe_flag=c.getInt("IsFE");
                        se_flag=c.getInt("IsSE");
                        te_flag=c.getInt("IsTE");
                        be_flag=c.getInt("IsBE");
                        str_date=c.getString("NoticeDate");
                        placement_flag=c.getInt("IsPlacement");


                        if((NavDrawerYearSelected.getIsNavFESelected()==1)&&fe_flag==1){
                            sendDetail(str_Notice_UID,str_not_tit,str_not_det,str_not_upBy,notice_dept,fe_flag,se_flag,te_flag,be_flag,str_date,placement_flag);
                        }
                        else if ((NavDrawerYearSelected.getIsNavSESelected()==1)&&se_flag==1){
                            sendDetail(str_Notice_UID,str_not_tit,str_not_det,str_not_upBy,notice_dept,fe_flag,se_flag,te_flag,be_flag,str_date,placement_flag);
                        }
                        else if ((NavDrawerYearSelected.getIsNavTESelected()==1)&&te_flag==1){
                            sendDetail(str_Notice_UID,str_not_tit,str_not_det,str_not_upBy,notice_dept,fe_flag,se_flag,te_flag,be_flag,str_date,placement_flag);
                        }
                        else if ((NavDrawerYearSelected.getIsNavBESelected()==1)&&be_flag==1){
                            sendDetail(str_Notice_UID,str_not_tit,str_not_det,str_not_upBy,notice_dept,fe_flag,se_flag,te_flag,be_flag,str_date,placement_flag);
                        }
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                }
                loading.dismiss();
            }

            @Override
            protected String doInBackground(String... strings) {

                HttpURLConnection connection = null;
                BufferedReader reader = null;
                StringBuffer buffer = null;
                try {
                    URL url = new URL("http://pictconnect.16mb.com/downNotice.php");
                    connection = (HttpURLConnection) url.openConnection();

                    InputStream stream = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(stream));
                    buffer = new StringBuffer();
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line+"\n");
                    }
                    return buffer.toString();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    e.printStackTrace();
                } catch (IOException e) {
                }finally {
                    if (connection != null)
                        connection.disconnect();
                    try {
                        if (reader != null)
                            reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        }
        fetchDetail obj=new fetchDetail();
        obj.execute("101");
    }


    public void sendDetail(String str_Notice_UID, String str_not_tit, String str_not_det, String str_not_upBy, String notice_dept,int fe_flag,int se_flag,int te_flag,int be_flag,String str_date,int placement_flag)
    {
        NoticeLab.Notice_info_store_from_download(str_Notice_UID, str_not_tit,  str_not_det, str_not_upBy, notice_dept,fe_flag, se_flag, te_flag,be_flag,str_date,placement_flag);
    }





}
