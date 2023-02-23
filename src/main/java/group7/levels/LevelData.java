package group7.levels;
import group7.Game;
import group7.utils.AssetLoader;

import static group7.Game.*;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * LevelData
 * - stores whether a tile is valid or invalid (can be moved on or not) 
 */
public class LevelData {
    private boolean levelData[][];

    public LevelData(int width, int height) {
        levelData = new boolean[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                levelData[i][j] = true; // TODO: change this to false when collision is implemented
            }
        }
    }

    /**
     * Set a tile valid or invalid
     * @param x 
     * x coordinate
     * @param y
     * y coordinate
     * @param value
     * true if valid, false if invalid
     */
    public void set(int x, int y, boolean value) {
        levelData[x][y] = value;
    }

    /**
     * Check if a tile is valid
     * @param x
     * x coordinate
     * @param y
     * y coordinate
     * @return
     * true if valid, false if invalid
     */
    public boolean canMove(int x, int y) {
        if (x < 0 || x >= levelData.length || y < 0 || y >= levelData[0].length) {
            System.out.println("Out of bounds");
            return false;
        }
        System.out.println("x: " + x + "y: " + y);
        return levelData[x][y];
    }
}
