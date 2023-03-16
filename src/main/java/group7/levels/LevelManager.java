package group7.levels;

import group7.helperClasses.Direction;

import java.awt.*;


/**
* The class LevelManager
* - Loads levels
* - Renders levels
* - Saves level data sush as what character and what level the player selected to play.
*
* @author  Mohammad Parsaei
* @author  Salman Ayaz
* @author  Karmen Yung
* @author  Chen Min
* @version 1.0
* @since 2023-03-13
*/
public class LevelManager {
    private Level currentLevel;
    private int dinoNumber;

    /**
     * Constructor: create the selected level.
     * @param dinoNumber
     * @param levelNumber
     */
    public LevelManager(int dinoNumber, int levelNumber) {
        this.dinoNumber = dinoNumber;
        switch (levelNumber){
            case 1:
                //System.out.println("Made level 1");
                currentLevel = new Level1(dinoNumber);
                break;
            case 2:
                //System.out.println("Made level 2");
                currentLevel = new Level2(dinoNumber);
                break;
            case 3:
                //System.out.println("Made level 3");
                currentLevel = new Level3(dinoNumber);
                break;
            default:
                //System.out.println("Made level default 1");
                currentLevel = new Level1(dinoNumber);
                break;
        }
    }

    /**
     * Wrapper Method: determine if the player interacts with any other entities on the map.
     */
    public void update() {
        currentLevel.update();
    }

    /**
     * Wrapper Method: set the new direction the player should move in.
     * @param direction
     */
    public void setDirection(Direction direction) {
        currentLevel.setDirection(direction);
    }

    /**
     * Wrapper Method: remove the current direction the player is moving in.
     * @param direction
     */
    public void removeDirection(Direction direction) {
        currentLevel.removeDirection(direction);
    }

    /**
     * Render the graphics of this level
     * @param g (the graphics object to draw on)
     */
    public void render(Graphics g) {
        currentLevel.render(g);
    }

    /**
     * Wrapper Method: Return the current state of the player in this game {@link LevelState}.
     * @return LOST
     * @return WON
     * @return PLAYING
     */
    public LevelState getLevelState(){
        return currentLevel.checkLevelState();
    }

    /**
     * Wrapper Method: get the total number of eggs for this current level
     * @return int total number of eggs
     */
    public int getEggInCurrentLevel(){
        return currentLevel.getNumberOfEggs();
    }

    /**
     * Wrapper Method: get the total number of keys for this current level
     * @return int total numbers of keys 
     */
    public int getKeyInCurrentLevel(){
        return currentLevel.getNumberOfKeys();
    }

    /**
     * Wrapper Method: get the number of eggs the player collected
     * @return int number of eggs
     */
    public int getEggCollectedCurrentLevel(){
        return currentLevel.getEggsCollected();
    }

    /**
     * Wrapper Method: get the number of keys the player collected
     * @return int number of keys
     */
    public int getKeyCollectedCurrentLevel(){
        return currentLevel.getKeysCollected();
    }
    
    /**
     * Wrapper Method: get the health of the player in the current level
     * @return the health of the player
     */
    public int getHealth() {
        return currentLevel.getHealth();
    }
}
