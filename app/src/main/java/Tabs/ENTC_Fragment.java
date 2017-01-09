package com.example.hp.pictconnect.Tabs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.pictconnect.R;

public class ENTC_Fragment extends Fragment {

    public ENTC_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top_paid, container, false);
    }

}
