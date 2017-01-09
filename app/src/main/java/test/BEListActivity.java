package com.example.hp.pictconnect.test;

import android.support.v4.app.Fragment;

import com.example.hp.pictconnect.SingleFragmentActivity;

public class BEListActivity extends SingleFragmentActivity{
    protected Fragment createFragment() {
        return new BEPlacementListFragment();
    }

}
