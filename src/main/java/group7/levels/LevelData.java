package group7.levels;
import group7.Game;
import group7.utils.AssetLoader;
import group7.utils.Direction;

import static group7.Game.*;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * LevelData
 * - stores whether a tile is valid or invalid (can be moved on or not) 
 */
public class LevelData {
    private int playerX;
    private int playerY;

    private boolean levelData[][];

    public LevelData(int width, int height) {
        levelData = new boolean[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                levelData[i][j] = false;
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

    public void setPlayer(int x, int y) {
        playerX = x;
        playerY = y;
    }

    public Direction findPlayer(int x, int y, int range) {
        // check if player is in range
        if (x - range <= playerX && playerX <= x + range && y - range <= playerY && playerY <= y + range) {
            // check if player is in the same row
            if (y == playerY) {
                if (x < playerX) {
                    return Direction.RIGHT;
                } else if (x > playerX) {
                    return Direction.LEFT;
                }
            }

            // check if player is in the same column
            if (x == playerX) {
                if (y < playerY) {
                    return Direction.DOWN;
                } else if (y > playerY) {
                    return Direction.UP;
                }
            }
        }

        System.out.println("Player not found");
        return Direction.NONE;
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
            return false;
        }
        return levelData[x][y];
    }

} // End of LevelData.java
