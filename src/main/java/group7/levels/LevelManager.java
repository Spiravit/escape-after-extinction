package group7.levels;

import group7.Graphics.GraphicsGrid;
import group7.utils.AssetLoader;
import group7.utils.Direction;

import static group7.Game.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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

    public Pathfinding getLevelData() { // TODO: remove this
        return currentLevel.getPathfindingData();
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
