package com.example.hp.pictconnect.Upload;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.pictconnect.Download.RequestHandler;
import com.example.hp.pictconnect.MainActivity;
import com.example.hp.pictconnect.NavDrawerYearSelected;
import com.example.hp.pictconnect.NoticeLab;
import com.example.hp.pictconnect.R;

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
import java.util.HashMap;


public class Login_authenticate extends AppCompatActivity {
    int REQUEST_CAMERA=0;

    private RequestHandler requestHandler;

    JSONArray peoples = null;
    String myJSON;

    public static final String Register_url = "http://pictconnect.16mb.com/uploaduser.php";
    int selectedPosition =-1;
    final CharSequence[] uploadType = {"Text","Camera"};
    AlertDialog alertDialog;
    Button login_btn,register_btn;
    EditText ed1, ed2;
    private Toolbar mtoolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

        login_btn = (Button) findViewById(R.id.login);
        register_btn=(Button)findViewById(R.id.register_user);


        ed1 = (EditText) findViewById(R.id.username);
        ed2 = (EditText) findViewById(R.id.password);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_chk_target();
                           }
        });

    }

//-----------------------------------------------------------------------------------------------


    public void go_back_to_login()
    {

        super.onBackPressed();
    }
    //------------------------------------------------------------------------------------
   /* public void selection_camera()
    {
        Intent j=new Intent(Login_authenticate.this,camera.class);
        startActivity(j);
    }*/

    //------------------------------------------------------------------------------------
   /* public void selection_text()
    {
        Intent i=new Intent(Login_authenticate.this,Teacher_upload.class);
        startActivity(i);

    }*/

    //-------------------------------------
    private void show_uploadtype_dialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select the Upload Type");
        //  builder.setItems(classes,null);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                go_back_to_login();
            }
        });
        builder.setPositiveButton("Ok",null);
        builder.setSingleChoiceItems(uploadType, selectedPosition , new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Upload via "+ uploadType[which], Toast.LENGTH_SHORT).show();
            }
        });
       /* builder.setMultiChoiceItems(classes, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
               Toast.makeText(getApplicationContext(),classes[which],Toast.LENGTH_SHORT).show();
            }
        });*/
        //alertDialog = builder.create();
        //alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Boolean wantToCloseDialog = false;
                selectedPosition=(alertDialog).getListView().getCheckedItemPosition();

                if(selectedPosition==-1){
                    Toast.makeText(getApplicationContext(),"Select something ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    switch (selectedPosition)
                    {
                        case 0:
                            //selection_text();
                            break;
                        case 1:
                            //selection_camera();
                            break;
                        default:break;

                    }
                    wantToCloseDialog=true;
                }
                if(wantToCloseDialog)
                    alertDialog.dismiss();
                //else dialog stays open. Make sure you have an obvious way to close the dialog especially if you set cancellable to false.
            }
        });
    }


    public void user_register(View view) {
        String user=ed1.getText().toString();
        int length=user.length();
        if(length==6)
        {
            Register_target();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"User id of incorrect type",Toast.LENGTH_LONG).show();
        }

    }

    private void Register_target() {
        class Set_Target_DB extends AsyncTask<String,Void,String > {

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Login_authenticate.this, "Registering...", null,true,true);
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
                data.put("user",strings[0]);
                data.put("password",strings[1]);
                //---------------------------------

                String result=rh.sendPostRequest(Register_url,data);
                //------------------------------------
                return result;
            }
        }
        Set_Target_DB ui = new Set_Target_DB();
        ui.execute(ed1.getText().toString(),ed2.getText().toString());
    }

//---------------------------------------------------------------------------
public void login_chk_target()

{
    requestHandler=new RequestHandler();
    class fetchDetail extends AsyncTask<String,Void,String> {
        ProgressDialog loading;
        int flag=0;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = ProgressDialog.show(Login_authenticate.this, "Authenticating...", null, true, true);
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
                     String user=c.getString("user");
                    String password  = c.getString("password");

                   if(user.equals(ed1.getText().toString()) && password.equals(ed2.getText().toString()))
                   {
                       flag=1;
                       break;
                   }
                }

                if(flag==1){
                    flag=0;
                    Intent authenticate=new Intent(Login_authenticate.this,Teacher_upload.class);
                    startActivity(authenticate);
                }
                else
                {
                    flag=0;
                    Toast.makeText(getApplicationContext(),"Wrong Credentials",Toast.LENGTH_LONG).show();
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
                URL url = new URL("http://pictconnect.16mb.com/downUser.php");
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
}
