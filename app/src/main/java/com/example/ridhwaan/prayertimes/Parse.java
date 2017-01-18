package com.example.ridhwaan.prayertimes;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ridhwaan on 1/14/2017.
 */
public class Parse {



    private JSONObject mHeader;
    private JSONObject mData;
    private JSONObject mTimings;

    private List<PrayerObject> prayerObjectList;



    public Parse (String text ){
        prayerObjectList = new ArrayList<>();
        try{

            mHeader = new JSONObject(text);
            mData = mHeader.getJSONObject("data");
            mTimings = mData.getJSONObject("timings");



            String fajr = mTimings.getString("Fajr");
            String duhr = mTimings.getString("Dhuhr");
            String asr = mTimings.getString("Asr");
            String maghrib = mTimings.getString("Maghrib");
            String isha = mTimings.getString("Isha");

            String[] prayerTimes = new String[5];

            prayerTimes[0] = fajr;
            prayerTimes[1] = duhr;
            prayerTimes[2] = asr;
            prayerTimes[3] = maghrib;
            prayerTimes[4] = isha;




            for(int i= 0; i<prayerTimes.length; i++){
              //  Log.d("PARSE TAG", "Prayer Time for prayer number" + i + "is   " + prayerTimes[i]);
            }



            prayerObjectList.add(new PrayerObject("ISNA","H", prayerTimes));

        }catch (JSONException e){
            e.printStackTrace();
        }



    }


public List<PrayerObject> getPrayerObjectList(){
    return prayerObjectList;
}


}
