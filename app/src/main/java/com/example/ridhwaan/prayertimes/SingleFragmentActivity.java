package com.example.ridhwaan.prayertimes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Ridhwaan on 1/14/2017.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {

    public abstract Fragment createFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout_generic_frame);


        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container_framelayout);

        if(fragment == null){

            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container_framelayout,fragment)
                    .commit();


        }

    }
}
