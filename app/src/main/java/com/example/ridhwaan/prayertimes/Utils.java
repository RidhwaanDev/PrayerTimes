package com.example.ridhwaan.prayertimes;

import java.util.ArrayList;

/**
 * Created by Ridhwaan on 1/15/2017.
 */
public class Utils {

    static ArrayList<String[]> sListWithTimeFormatted = new ArrayList<>();

    public static ArrayList<String[]> convertToHourMin(String[] s){


        for(int i= 0; i<s.length; i++) {
            sListWithTimeFormatted.add(s[i].split(":"));
        }
        return sListWithTimeFormatted;

    }
    public static int getRawTime24hrINT(String[] s, int index ){
        String[] peiceWiseTime = s[index].split(":");
        int time = Integer.parseInt(peiceWiseTime[0] + peiceWiseTime[1]);
        return time;

    }

    public static int getRawTimeHourINT(String[]s, int index){
        String[] peiceWiseTime = s[index].split(":");
        int hourTime = Integer.parseInt(peiceWiseTime[0]);

        return hourTime;
    }
    public static int getRawTimeMinuteINT(String[]s, int index){
        String[] peiceWiseTime = s[index].split(":");
        int minuteTime = Integer.parseInt(peiceWiseTime[1]);

        return minuteTime;
    }




    public static String convertToTimeString(ArrayList<String[]> aList, int index){
        String[] convertArray = aList.get(index);
        int time = Integer.parseInt(convertArray[0] + convertArray[1]);


        //  StringBuilder sb = new StringBuilder();
        //  sb.append(convertArray[0] + ":" +convertArray[1]);
        //  System.out.println( " TIME WAS AS INT " + "\n" + time);
        //  System.out.println(sb);


        int timeUnParsed1 = Integer.parseInt(convertArray[0]);
        int timeUnParsed2 = Integer.parseInt(convertArray[1]);

        if(timeUnParsed1 > 12){
            timeUnParsed1 = timeUnParsed1 - 12;

        }if(timeUnParsed2 == 60){
            timeUnParsed1++;
            timeUnParsed2 = Integer.parseInt("00");
        }

        StringBuilder sb = new StringBuilder();


        if(timeUnParsed2 == 0){
            sb.append(timeUnParsed1 + ":" + timeUnParsed2 + "0");
        } else{
            sb.append(timeUnParsed1 + ":" + timeUnParsed2);
        }

        return sb.toString();

    }


}


