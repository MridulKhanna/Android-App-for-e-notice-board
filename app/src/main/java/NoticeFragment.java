package com.example.hp.pictconnect;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.UUID;

//when user selects a crime from a set of crimes...this file is operated on..(line 32-35)
//it firstly accepts UUID from intent...matches that particular crime..then displays default title,date and checkbox(line 43..59.63)
//also..if user changes any of the details..those are reflected back and are changed permanently(line 44 and 64)

/**
 * Created by HP on 28-07-2016.
 */
public class NoticeFragment extends Fragment {
    public static final String ARG_NOTICE_ID="notice_id";
    private Notice_info mNotice;
    private TextView mTitleField,mNoticeDetail,mDate,mUploadBy;
    private Toolbar mtoolbar;

    public static NoticeFragment newInstance(UUID crimeID) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_NOTICE_ID, crimeID);
        NoticeFragment fragment = new NoticeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {     //receive the intent call..to search for the UUID of crime
        super.onCreate(savedInstanceState);

        UUID crimeId = (UUID) getArguments().getSerializable(ARG_NOTICE_ID);
         mNotice = NoticeLab.getNotice(crimeId); //set mNotice as matched obj's context

    }
    //---------------------------------------------------
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_notice,container,false);

        //-------------------BACK NAVIGATION----------
        //-------------------BACK NAVIGATION----------


        mTitleField=(TextView)v.findViewById(R.id.notice_title);
        mTitleField.setText(mNotice.getTitle());     //set Title of that particular crime..(when a crime is clicked upon..ie.new activity is created)

        mNoticeDetail=(TextView) v.findViewById(R.id.notice_detail) ;
        mNoticeDetail.setText(mNotice.getText());

        mDate=(TextView)v.findViewById(R.id.notice_date_textview);
        mDate.setText(mNotice.getDate());

        mUploadBy= (TextView) v.findViewById(R.id.notice_upload_by);
        mUploadBy.setText(mNotice.getUploadBy());


        return v;   //always type in end..
    }
}
