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
    private Level[] levels = {
        new Level1(),
        new Level2(),
        new Level3()
    };
    private int dinoNumber;

    public LevelManager(int dinoNumber) {
        this.dinoNumber = dinoNumber;
    }

    /** 
     * Load a level
     * @param level
     * level number starting from 1
     */
    public void loadLevel(int level) {
        currentLevel = levels[level - 1];
        currentLevel.loadLevel("levels/level_maps/level_" + level + ".png", dinoNumber); //TEST MULTIPLE LEVELS, chnged from (dinoNumber) to ("Level_" + level, dinoNumber)
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
}
