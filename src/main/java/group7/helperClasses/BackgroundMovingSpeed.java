package group7.helperClasses;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Holds static ArrayList of ArrayList<Double>. Each ArrayList inside this 2D array, is holding
 * moving speeds for each background used in one animated background. The first Arraylist inside
 * the 2D array is holding moving speeds for backgrounds used in animated main menu background.
 * Since there are 8 backgrounds used for main menu background, there are 8 double-type values inside it.
 * The second ArrayList inside the 2D array is holding moving speeds for backgrounds used in animated
 * background of in-game menu.
 *
 * @author Mohammad Parsaei
 */
public class BackgroundMovingSpeed {

    // A 2D arraylist, where each arraylist inside it is holding moving speed of backgrounds used
    // in one animated background
    static ArrayList<ArrayList<Double>> ParallelMovingSpeeds = new ArrayList<>();

    /**
     *
     *
     */
    public static void setParallelMovingSpeeds(){

        // The speeds used for each background of main menu for instance
        // the 8th image in background of main menu is having 2.0 as its speed
        Double[] mainMenuBackgroundSpeed ={0.0,0.0,0.5,0.5,1.5,0.0,0.0,2.0};
        ArrayList<Double> mainMenuSpeedArrayList = new ArrayList<>();
        // Add values of mainMenuBackgroundSpeed array to an ArrayList declared above
        Collections.addAll(mainMenuSpeedArrayList,mainMenuBackgroundSpeed);

        // The speeds used for each background of in-game top menu
        Double[] topMenuBackgroundSpeed ={0.0,0.5,0.25,0.5};
        ArrayList<Double> topMenuBackgroundSpeedArrayList = new ArrayList<>();
        // Add values of topMenuBackgroundSpeed array to an ArrayList declared above
        Collections.addAll(topMenuBackgroundSpeedArrayList,topMenuBackgroundSpeed);

        // Adding those two arrayList into 2D array
        ParallelMovingSpeeds.add(mainMenuSpeedArrayList);
        ParallelMovingSpeeds.add(topMenuBackgroundSpeedArrayList);
    }

    /**
     *
     *
     *
     * @param Size
     * @return
     */
    public static ArrayList<Double> returnBackgroundSpeeds(int Size){
        for (ArrayList<Double> i : ParallelMovingSpeeds){
            if (i.size() == Size){
                return i;
            }
        }
        return null;
    }
}
