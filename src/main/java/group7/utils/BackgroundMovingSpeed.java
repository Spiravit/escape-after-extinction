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
        Collections.addAll(mainMenuSpeedArrayList,mainMenuBackgroundSpeed);
        ParallelMovingSpeeds.add(mainMenuSpeedArrayList);
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
