package com.example.hp.pictconnect;

import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentActivity;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentStatePagerAdapter;
        import android.support.v4.view.ViewPager;

        import java.util.List;
        import java.util.UUID;

/**
 * Created by HP on 08-08-2016.
 */
public class  NoticePagerActivity extends FragmentActivity {

    public static final String EXTRA_NOTICE_ID="com.example.hp.criminalintent";
    private ViewPager mViewPager;
    private List<Notice_info>mNotice;

    public static Intent newIntent(Context packageContext, UUID noticeId) {
        Intent intent = new Intent(packageContext, NoticePagerActivity.class);
        intent.putExtra(EXTRA_NOTICE_ID, noticeId);
        return intent;
    }
    //--------------------------------------------------------------------------
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_pager);
        UUID notice_id=(UUID)getIntent().getSerializableExtra(EXTRA_NOTICE_ID);

        mViewPager=(ViewPager)findViewById(R.id.activity_notice_pager_view_pager);
        mNotice=NoticeLab.getNotices();
        FragmentManager fragmentManager=getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) { //this Adapter clears the memory as soon as next fragment is opened via swipe
            @Override
            public Fragment getItem(int position) {
                Notice_info notice=mNotice.get(position);
                return NoticeFragment.newInstance(notice.getId());
            }
            @Override
            public int getCount() {
                return mNotice.size();
            }
        });

        for (int i = 0; i < mNotice.size(); i++) {              //for opening that particular crime in view pager
            if (mNotice.get(i).getId().equals(notice_id)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
