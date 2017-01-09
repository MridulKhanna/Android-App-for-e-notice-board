package com.example.hp.pictconnect;

/**
 * Created by HP on 13-08-2016.
 */

import java.util.UUID;


public class Notice_info {
    private UUID mId;
    private String mNoticeTitle,mText,mUploadBy,mNoticeDept;
    private int mIsFE,mIsSE,mIsTE,mIsBE,mIsPlacement;
    private String mDate;
    private boolean isSeen;

    public void setId(UUID id) {
        mId = id;
    }

    public Notice_info(){

    }

    public int getIsPlacement() {
        return mIsPlacement;
    }

    public void setIsPlacement(int isPlacement) {
        mIsPlacement = isPlacement;
    }

    public void setText(String text) {
        mText = text;
    }

    public String getText() {
        return mText;
    }

    public String getUploadBy() {
        return mUploadBy;
    }

    public String getNoticeDept() {
        return mNoticeDept;
    }

    public void setNoticeTitle(String noticeTitle) {
        mNoticeTitle = noticeTitle;
    }

    public void setIsSE(int isSE) {
        mIsSE = isSE;
    }

    public void setIsTE(int isTE) {
        mIsTE = isTE;
    }

    public void setIsBE(int isBE) {
        mIsBE = isBE;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public void setUploadBy(String uploadBy) {
        mUploadBy = uploadBy;
    }

    public void setNoticeDept(String noticeDept) {
        mNoticeDept = noticeDept;
    }

    public void setIsFE(int isFE) {
        mIsFE = isFE;
    }

    public int getIsFE() {
        return mIsFE;
    }

    public int getIsSE() {
        return mIsSE;
    }

    public int getIsTE() {
        return mIsTE;
    }

    public int getIsBE() {
        return mIsBE;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mNoticeTitle;
    }

    public void setTitle(String title) {
        mNoticeTitle = title;
    }

    public String getDate() {
        return mDate;
    }

    /*public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }*/
}
