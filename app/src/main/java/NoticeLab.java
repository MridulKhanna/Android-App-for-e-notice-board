
package com.example.hp.pictconnect;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NoticeLab {                     //singleton class
    public static List<Notice_info> mNotice_info;     // a list of notice_info objects
    public static int noticeCount=0;

//----------------------------------------------

    public NoticeLab() {      //private constructor..set predefined values to the crimes
        mNotice_info = new ArrayList<>();
    }

    //----------------------------------------------
    public static List<Notice_info> getNotices() {

        return mNotice_info;
    }   //return whole list of crimes

    //----------------------------------------------------
    public static void intialize_notice() {
        mNotice_info = new ArrayList<>();
    }
//---------------------------------------------------------
    public static Notice_info getNotice(UUID id) {
        for (Notice_info notice : mNotice_info) {           //enhanced for loop without indices..notice here is variable used of Notice_info data type..operating on mNotice_info
            if (notice.getId().equals(id)) {
                return notice;
            }
        }
        return null;
    }

    //------------------------------------------------------------------
    public static void Notice_info_store_from_download(String str_Notice_UID, String str_not_tit, String str_not_det, String str_not_upBy, String notice_dept, int fe_flag, int se_flag, int te_flag, int be_flag,String str_date,int placement_flag) {
        Notice_info notice = new Notice_info();

        notice.setId(UUID.fromString(str_Notice_UID));
        notice.setTitle(str_not_tit);
        notice.setUploadBy(str_not_upBy);
        notice.setText(str_not_det);
        notice.setNoticeDept(notice_dept);
        notice.setIsFE(fe_flag);
        notice.setIsSE(se_flag);
        notice.setIsTE(te_flag);
        notice.setIsBE(be_flag);
        notice.setDate(str_date);
        notice.setIsPlacement(placement_flag);

        mNotice_info.add(notice);
        noticeCount++;
    }

    //------------------------------------------------------------------------


}