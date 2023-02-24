package group7.levels;

import group7.Graphics.GraphicsGrid;
import group7.utils.AssetLoader;

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
        new Level1()
    };

    /** 
     * Load a level
     * @param level
     * level number starting from 1
     */
    public void loadLevel(int level) {
        currentLevel = levels[level - 1];
        currentLevel.loadLevel();
    }

    public LevelData getLevelData() { // TODO: remove this
        return currentLevel.getLevelData();
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
