package com.example.ridhwaan.prayertimes;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.os.Handler;


/**
 * Created by Ridhwaan on 1/15/2017.
 */
public class InfoProcess {

    public static float sProgress;

    private  ProgressBar mProgressBar;
    private TextView infoText;
    private String[] times;
    public String mNextPrayer;



    public InfoProcess(ProgressBar progressBar, TextView infoText,String[] times ) {
        this.mProgressBar = progressBar;
        this.infoText = infoText;
        this.times = times;

        calcNextPrayer();

        UpdateProgressBar updateProgressBar = new UpdateProgressBar();
        updateProgressBar.execute();



    }


        public Integer calcNextPrayer(){

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String[] rawTimeString = sdf.format(calendar.getTime()).split(":");




           // String time = sdf.format(calendar.getTime());


            int currentRawTime24hr = Integer.parseInt(rawTimeString[0] + rawTimeString[1]);
            int currentTimeHourValue = Integer.parseInt(rawTimeString[0]);
            int currentTimeMinuteValue = Integer.parseInt(rawTimeString[1]);

            int fajr = Utils.getRawTime24hrINT(times,0);
            Log.d("TAG","  "  + fajr);
            int duhr = Utils.getRawTime24hrINT(times,1);
            int asr = Utils.getRawTime24hrINT(times,2);
            int maghrib = Utils.getRawTime24hrINT(times,3);
            int isha = Utils.getRawTime24hrINT(times,4);



            int fajr2 = Utils.getRawTimeHourINT(times, 0);
            int duhr2 = Utils.getRawTimeHourINT(times, 1);
            int asr2 = Utils.getRawTimeHourINT(times, 2);
            int maghrib2 = Utils.getRawTimeHourINT(times,3);
            int isha2 = Utils.getRawTimeHourINT(times, 4);




            if((currentRawTime24hr > fajr) && (currentRawTime24hr < duhr)){

                int currentTimeHourValueStandard = (currentTimeHourValue -12);


                int hourUntilNxtPrayer = (duhr2 + 12) - (currentTimeHourValueStandard);
                int timeUntilNxtPrayer = Math.abs( duhr - currentRawTime24hr);

                float progress = (currentTimeHourValueStandard - duhr2);
                float progress2 = progress/ hourUntilNxtPrayer;
                float progress3 = progress2 * 100;
                sProgress = progress3;

                mNextPrayer = "Duhr";

                // Log.d("TIME" , "   "  + hourUntilNxtPrayer);
                //    Log.d("TIME", "  " + currentTimeHourValue + "   " + "Fajr Time Hour value" + fajr2 + "Isha hour time value" + isha2);
                //return progress3;
                return hourUntilNxtPrayer;
       }  if((currentRawTime24hr > duhr) && (currentRawTime24hr <asr)){
                int currentTimeHourValueStandard = (currentTimeHourValue -12);


                int hourUntilNxtPrayer = (asr2 + 12) - (currentTimeHourValueStandard);
                int timeUntilNxtPrayer = Math.abs( asr - currentRawTime24hr);

                float progress = (currentTimeHourValueStandard - asr2);
                float progress2 = progress/ hourUntilNxtPrayer;
                float progress3 = progress2 * 100;
                sProgress = progress3;

                mNextPrayer = "Asr";

                // Log.d("TIME" , "   "  + hourUntilNxtPrayer);
                //    Log.d("TIME", "  " + currentTimeHourValue + "   " + "Fajr Time Hour value" + fajr2 + "Isha hour time value" + isha2);
                //return progress3;
                return hourUntilNxtPrayer;



            }  if ((currentRawTime24hr > asr ) && (currentRawTime24hr < maghrib)){
                int currentTimeHourValueStandard = (currentTimeHourValue -12);


                int hourUntilNxtPrayer = (maghrib2 + 12) - (currentTimeHourValueStandard);
                int timeUntilNxtPrayer = Math.abs( maghrib - currentRawTime24hr);

                float progress = (currentTimeHourValueStandard - maghrib2);
                float progress2 = progress/ hourUntilNxtPrayer;
                float progress3 = progress2 * 100;
                Log.d("TIME", "PROGRESS VALUE  " + currentTimeHourValueStandard + "  "  + fajr2 +  "   " + hourUntilNxtPrayer + "  " + progress3);
                Log.d("TIME", "PROGRESS VALUE RND" + Math.round(progress3) );
                sProgress = progress3;

                mNextPrayer = "Maghrib";

                // Log.d("TIME" , "   "  + hourUntilNxtPrayer);
                //    Log.d("TIME", "  " + currentTimeHourValue + "   " + "Fajr Time Hour value" + fajr2 + "Isha hour time value" + isha2);
                //return progress3;
                return hourUntilNxtPrayer;



            }  if((currentRawTime24hr > maghrib)&&(currentRawTime24hr<isha)){
                int currentTimeHourValueStandard = (currentTimeHourValue -12);


                int hourUntilNxtPrayer = (fajr2 + 12) - (currentTimeHourValueStandard);
                int timeUntilNxtPrayer = Math.abs( fajr - currentRawTime24hr);

                float progress = (currentTimeHourValueStandard - fajr2);
                float progress2 = progress/ hourUntilNxtPrayer;
                float progress3 = progress2 * 100;
                Log.d("TIME", "PROGRESS VALUE  " + currentTimeHourValueStandard + "  "  + fajr2 +  "   " + hourUntilNxtPrayer + "  " + progress3);
                Log.d("TIME", "PROGRESS VALUE RND" + Math.round(progress3) );
                sProgress = progress3;

                mNextPrayer = "Isha";

                // Log.d("TIME" , "   "  + hourUntilNxtPrayer);
                //    Log.d("TIME", "  " + currentTimeHourValue + "   " + "Fajr Time Hour value" + fajr2 + "Isha hour time value" + isha2);
                //return progress3;
                return hourUntilNxtPrayer;

            }



            if((currentRawTime24hr > isha)){



                int currentTimeHourValueStandard = (currentTimeHourValue -12);


                int hourUntilNxtPrayer = (fajr2 + 12) - (currentTimeHourValueStandard);
                int timeUntilNxtPrayer = Math.abs( fajr - currentRawTime24hr);

                float progress = Math.abs((currentTimeHourValueStandard -  (isha2 -12)));
                float progress2 = progress/ hourUntilNxtPrayer;
                float progress3 = progress2 * 100;
                float progress4 = (currentTimeHourValueStandard/hourUntilNxtPrayer) * 100;
                Log.d("TIME", "PROGRESS VALUE  " + currentTimeHourValueStandard + "  "  + isha2 +  "   " + hourUntilNxtPrayer + "  " + progress3);
                Log.d("TIME", "PROGRESS VALUE RND" + Math.round(progress3) );
                sProgress = progress3;

                mNextPrayer = "Fajr";

               // Log.d("TIME" , "   "  + hourUntilNxtPrayer);
            //    Log.d("TIME", "  " + currentTimeHourValue + "   " + "Fajr Time Hour value" + fajr2 + "Isha hour time value" + isha2);
                //return progress3;
                return hourUntilNxtPrayer;


            }


return  null;
        }


    public String getNextPrayer(){
        return mNextPrayer;
    }

   //To-do encapsulate calculation










     class UpdateProgressBar extends AsyncTask<Void,Integer,String>{

        @Override
        protected String doInBackground(Void... params) {

            publishProgress(Math.round(sProgress));
             Log.d("THREAD", " progress" + sProgress);
            return "Completed";
        }


         @Override
         protected void onProgressUpdate(Integer... values) {
                    mProgressBar.setProgress(values[0]);


         }


     }







}














































