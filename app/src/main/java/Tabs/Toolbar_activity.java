package com.example.hp.pictconnect.Tabs;

        import android.content.Context;
        import android.support.design.widget.TabLayout;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentPagerAdapter;
        import android.support.v4.view.ViewPager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.Toolbar;
        import android.view.View;

        import com.example.hp.pictconnect.R;
        import com.example.hp.pictconnect.TabListFragments.CompListFragment;
        import com.example.hp.pictconnect.TabListFragments.ENTCListFragment;
        import com.example.hp.pictconnect.TabListFragments.ITListFragment;


public class Toolbar_activity extends AppCompatActivity {
    Toolbar mToolbar;
    TabLayout mTabLayout;
    ViewPager mViewPager;

    int limitNumberOfPages = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_toolbar);

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

        mViewPager = (ViewPager) findViewById(R.id.viewPager); // Get the ViewPager and set it's PagerAdapter so that it can display items

        mViewPager.setOffscreenPageLimit(limitNumberOfPages); //before setAdapter
        mViewPager.setAdapter(new CustomAdapter(getSupportFragmentManager(), getApplicationContext()));

        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private class CustomAdapter extends FragmentPagerAdapter {
        private String tabNames[] = {"Comp", "IT", "ENTC"};

        public CustomAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
        }

        @Override
        public int getCount() {
            return tabNames.length;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:{
                    TabYearSelected.isCompSelected = 1;
                    TabYearSelected.isITSelected=0;
                    TabYearSelected.isEntcSelected=0;
                    return new CompListFragment();}
                case 1:{
                    TabYearSelected.isCompSelected = 0;
                    TabYearSelected.isITSelected=1;
                    TabYearSelected.isEntcSelected=0;
                    return new ITListFragment();}
                case 2:{
                    TabYearSelected.isCompSelected = 0;
                    TabYearSelected.isITSelected=0;
                    TabYearSelected.isEntcSelected=1;
                    return new ENTCListFragment();}
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabNames[position];
        }
    }
}