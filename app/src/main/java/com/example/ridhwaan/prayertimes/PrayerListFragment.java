package com.example.ridhwaan.prayertimes;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ridhwaan on 1/14/2017.
 */
public class PrayerListFragment extends Fragment implements AsyncResponse {

        private TextView mFajr,mDuhr,mAsr,mMaghrib,mIsha;
        private TextView infoTextView;



        private PrayerObject prayerOBJ;

        private Button mRevealConfig;
        private Button mBackReveal;

        private ProgressBar prayerProgressBar;

        private CardView mCardView;

        private boolean isRevealed = false;

    Parse parser;

View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

         v = inflater.inflate(R.layout.prayer_list_fragment_layout,container,false);



        executeTask();




        mFajr =(TextView) v.findViewById(R.id.farjTextView);
        mDuhr = (TextView) v.findViewById(R.id.duhrTextView);
        mAsr = (TextView) v.findViewById(R.id.AsrTextView);
        mMaghrib = (TextView) v.findViewById(R.id.maghribTextView);
        mIsha = (TextView)v.findViewById(R.id.ishaTextView);

        infoTextView = (TextView)v.findViewById(R.id.infoTextView);



        mRevealConfig = (Button)v.findViewById(R.id.revealConfigurationsButton);
        mBackReveal = (Button)v.findViewById(R.id.backFromRevealID);

            prayerProgressBar = (ProgressBar) v.findViewById(R.id.progressView);


        mCardView = (CardView)v.findViewById(R.id.configCardViewID);









        mRevealConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!isRevealed) {

                    if (Build.VERSION.SDK_INT >= 21) {

                        // previously invisible view


                        // get the center for the clipping circle
                        int cx = mCardView.getWidth() / 2;
                        int cy = mCardView.getHeight() / 2;
                        // get the final radius for the clipping circle
                        float finalRadius = (float) Math.hypot(cx, cy);

                        // create the animator for this view (the start radius is zero)
                        Animator anim =
                                ViewAnimationUtils.createCircularReveal(mCardView, cx, cy, 0, finalRadius);
                        anim.setDuration(400);

                        // make the view visible and start the animation
                        mCardView.setVisibility(View.VISIBLE);
                        anim.start();
                        isRevealed = true;


                    } else {
                        Snackbar.make(v, "Phone Outdated", Snackbar.LENGTH_LONG);
                    }

                }
            }
        });




        mBackReveal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= 21) {
                    if(isRevealed) {
                            int cx = mCardView.getWidth() / 2;
                        int cy = mCardView.getHeight() / 2;

// get the initial radius for the clipping circle
                            float initialRadius = (float) Math.hypot(cx, cy);

// create the animation (the final radius is zero)
                            Animator anim =
                                    ViewAnimationUtils.createCircularReveal(mCardView, cx, cy, initialRadius, 0);
                                anim.setDuration(400);
// make the view invisible when the animation is done
                            anim.addListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    mCardView.setVisibility(View.INVISIBLE);
                                }
                            });

// start the animation
                            anim.start();
                            isRevealed = false;

                    }
                }
            }
        });




        return v;
    }






    public void executeTask(){
        DownloadRawData downloadRawData = new DownloadRawData();
        downloadRawData.delegate = this;
        downloadRawData.execute("http://api.aladhan.com/timingsByCity?city=Dubai&country=AE&method=2");
    }


    @Override
    public void onProcessFinish(String result) {

        Log.d("TAG", "result was   " + result);

        parser = new Parse(result);
       this.prayerOBJ = parser.getPrayerObjectList().get(0);

        String[] times = prayerOBJ.getmPrayerTimes();


        InfoProcess info = new InfoProcess(prayerProgressBar,infoTextView, times);
        infoTextView.setText(info.getNextPrayer() + " prayer in" + " " + info.calcNextPrayer() + " Hours");


        ArrayList<String[]> a = Utils.convertToHourMin(times);
        for (int i = 0; i < a.size(); i++) {
            //System.out.println(Arrays.toString(a.get(i)));


            mFajr.setText(Utils.convertToTimeString(a,0) +" "+ "A.M.");
            mDuhr.setText(Utils.convertToTimeString(a,1) +" " + "A.M.");
            mAsr.setText(Utils.convertToTimeString(a,2) +" " + "P.M");
            mMaghrib.setText(Utils.convertToTimeString(a,3)+" " + "P.M.");
            mIsha.setText(Utils.convertToTimeString(a,4)+ " " + "P.M.");


        }






    }
}
