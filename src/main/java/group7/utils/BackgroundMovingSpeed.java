package group7.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BackgroundMovingSpeed {
    static ArrayList<ArrayList<Double>> ParallelMovingSpeeds = new ArrayList<>();

    public static void setParallelMovingSpeeds(){
        Double[] mainMenuBackgroundSpeed ={0.0,0.0,0.5,0.5,1.5,0.0,0.0,2.0};
        ArrayList<Double> mainMenuSpeedArrayList = new ArrayList<>();

        Double[] topMenuBackgroundSpeed ={0.0,0.5,0.25,0.5};
        ArrayList<Double> topMenuBackgroundSpeedArrayList = new ArrayList<>();

        Collections.addAll(mainMenuSpeedArrayList,mainMenuBackgroundSpeed);
        Collections.addAll(topMenuBackgroundSpeedArrayList,topMenuBackgroundSpeed);
        ParallelMovingSpeeds.add(mainMenuSpeedArrayList);
        ParallelMovingSpeeds.add(topMenuBackgroundSpeedArrayList);
    }
    public static ArrayList<Double> returnBackgroundSpeeds(int Size){
        for (ArrayList<Double> i : ParallelMovingSpeeds){
            if (i.size() == Size){
                return i;
            }
        }
        return null;
    }
}
