package com.example.ridhwaan.prayertimes;

/**
 * Created by Ridhwaan on 1/14/2017.
 */
public class PrayerObject {

    private String mPrayerName;
    private String mPrayerTime;
    private String mMethod;
    private String mSchool;
    private String[] mPrayerTimes;


    public PrayerObject(String mMethod, String mSchool, String[] prayerTimes) {
        this.mMethod = mMethod;
        this.mPrayerName = mPrayerName;
        this.mPrayerTime = mPrayerTime;
        this.mSchool = mSchool;
        this.mPrayerTimes = prayerTimes;
    }

    public String getmMethod() {
        return mMethod;
    }

    public void setmMethod(String mMethod) {
        this.mMethod = mMethod;
    }

    public String getmPrayerName() {
        return mPrayerName;
    }

    public void setmPrayerName(String mPrayerName) {
        this.mPrayerName = mPrayerName;
    }

    public String getmPrayerTime() {
        return mPrayerTime;
    }

    public void setmPrayerTime(String mPrayerTime) {
        this.mPrayerTime = mPrayerTime;
    }

    public String getmSchool() {
        return mSchool;
    }

    public void setmSchool(String mSchool) {
        this.mSchool = mSchool;
    }

    public String[] getmPrayerTimes() {
        return mPrayerTimes;
    }
}































