package com.example.ridhwaan.prayertimes;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ridhwaan on 1/14/2017.
 */
public class DownloadRawData extends AsyncTask<String,Void,String> {

    private static final String TAG = DownloadRawData.class.getCanonicalName();

    private String mFileContents;
    public AsyncResponse delegate = null;


    @Override
    protected String doInBackground(String... params) {

        mFileContents = downloadJSONData(params[0]);

        return mFileContents;
        // TODO: 1/14/2017 begin download and set content to local var. Then fire off async response


    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        delegate.onProcessFinish(result);

    }

    private String downloadJSONData(String urlSpec){

        Log.d(TAG,"beginnning download");
        StringBuilder sb = new StringBuilder();



        try {
            URL url = new URL(urlSpec);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream is = urlConnection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);

            int charRead;
            char[] buffer = new char[500];

            while (true){

                charRead = isr.read(buffer);
            if(charRead<=0){

                Log.d(TAG,"Completed input stream read" + "   now breaking");
                break;

            }
                sb.append(String.copyValueOf(buffer,0,charRead));

            }
            return sb.toString();

        }catch (IOException e){
            e.printStackTrace();
        }



        return null;
    }


}