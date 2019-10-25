package com.example.mymp3;

class SongInfo {
    public String mSongName;
    public String mSinger;
    public int mImageID;
    public String mSongID;


    public SongInfo(String mSongName, String mSinger, int mImageID, String songID) {
        this.mSongName = mSongName;
        this.mSinger = mSinger;
        this.mImageID = mImageID;
        this.mSongID = songID;
    }

    public String getmSongName() {
        return mSongName;
    }

    public void setmSongName(String mSongName) {
        this.mSongName = mSongName;
    }

    public String getmSinger() {
        return mSinger;
    }

    public void setmSinger(String mSinger) {
        this.mSinger = mSinger;
    }



    public String getmSongID() {
        return mSongID;
    }

    public int getmImageID() {
        return mImageID;
    }

    public void setmSongID(String mSongID) {
        this.mSongID = mSongID;
    }



    public void setmImageID(int mImageID) {
        this.mImageID = mImageID;
    }
}
