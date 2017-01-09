package com.example.hp.pictconnect.Notification;


import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


public class FirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG="FirebaseInsIDService";
    @Override
    public void onTokenRefresh() {
        // Toast.makeText(getApplicationContext(), "Testing", Toast.LENGTH_SHORT).show();
        String refreshedtoken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"NewToken:"+refreshedtoken);
        registerToken(refreshedtoken);
    }

    public void registerToken(String token) {

        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("Token",token)
                .build();

        Request request = new Request.Builder()
                .url("http://minimilitia.16mb.com/register.php")
                .post(body)
                .build();

        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
