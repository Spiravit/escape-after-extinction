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
    private Level1 currentLevel; // TODO: change this to a Level
    private Level1[] levels = { // TODO: change this to a Level array
        new Level1()
    };

    private GraphicsGrid graphicsGrid;

    public LevelManager(GraphicsGrid graphicsGrid) {
        this.graphicsGrid = graphicsGrid;
    }
    
    /** 
     * Load a level
     * @param level
     * level number starting from 1
     */
    public void loadLevel(int level) {
        currentLevel = levels[level - 1];
        currentLevel.loadLevel(graphicsGrid);
    }

    public LevelData getLevelData() { // TODO: remove this
        return currentLevel.getLevelData();
    }

    public void render(Graphics g) {
        currentLevel.render(g);
    }
}
