package group7.levels;

import group7.helperClasses.Direction;

import java.awt.*;

/**
 * LevelManager
 * - loads levels
 * - renders levels
 * - saves level data
 */
public class LevelManager {
    private Level currentLevel;
    private int dinoNumber;

    public LevelManager(int dinoNumber, int levelNumber) {
        this.dinoNumber = dinoNumber;
        switch (levelNumber){
            case 1:
                System.out.println("Made level 1");
                currentLevel = new Level1(dinoNumber);
                break;
            case 2:
                System.out.println("Made level 2");
                currentLevel = new Level2(dinoNumber);
                break;
            case 3:
                System.out.println("Made level 3");
                currentLevel = new Level3(dinoNumber);
                break;
            default:
                System.out.println("Made level default 1");
                currentLevel = new Level1(dinoNumber);
                break;
        }
    }

    public void update() {
        currentLevel.update();
    }

    public void setDirection(Direction direction) {
        currentLevel.setDirection(direction);
    }

    public void removeDirection(Direction direction) {
        currentLevel.removeDirection(direction);
    }

    /**
     * render the level
     * @param g
     * the graphics object to draw on
     */
    public void render(Graphics g) {
        currentLevel.render(g);
    }

    public LevelState getLevelState(){
        return currentLevel.checkLevelState();
    }
    public int getEggInCurrentLevel(){
        return currentLevel.getNumberOfEggs();
    }
    public int getKeyInCurrentLevel(){
        return currentLevel.getNumberOfKeys();
    }
    public int getEggCollectedCurrentLevel(){
        return currentLevel.getEggsCollected();
    }
    public int getKeyCollectedCurrentLevel(){
        return currentLevel.getKeysCollected();
    }
}
