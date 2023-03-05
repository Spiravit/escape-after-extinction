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
    // enums for the pathfinding
    public final int INVALID = 0;
    public final int VALID = 1;
    public final int PLAYER = 2;

    private int levelData[][];

    public LevelData(int width, int height) {
        levelData = new int[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                levelData[i][j] = this.INVALID; // TODO: change this to false when collision is implemented
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
     * 0 for invalid, 1 for valid, 2 for where the player is
     */
    public void set(int x, int y, int value) {
        if (value == this.INVALID || value == this.VALID) {
            levelData[x][y] = value;
        }

        // player case is special because we need to remove the player from the surrounding tiles
        if (value == this.PLAYER && levelData[x][y] == this.VALID) {
            levelData[x][y] = value;

            // remove player from surrounding tiles
            // TODO: in rare cases, this may cause a bug where the player is not removed from the tile
            // if the player moved diagonally
            if (levelData[x + 1][y] == this.PLAYER) {
                levelData[x + 1][y] = this.VALID;
            }
            if (levelData[x - 1][y] == this.PLAYER) {
                levelData[x - 1][y] = this.VALID;
            }
            if (levelData[x][y + 1] == this.PLAYER) {
                levelData[x][y + 1] = this.VALID;
            }
            if (levelData[x][y - 1] == this.PLAYER) {
                levelData[x][y - 1] = this.VALID;
            }
        }
    }

    public Direction findPlayer(int x, int y, int range) {
        for (int i = 0; i < range; i++) {
            if (this.canMove(x + 1, y)) {
                if (x + i < levelData.length) {
                    if (levelData[x + i][y] == this.PLAYER) {
                        return Direction.RIGHT;
                    }
                }
            }
            else {
                break;
            }
        }
        for (int i = 0; i < range; i++) {
            if (this.canMove(x - 1, y)) {
                if (x - i >= 0) {
                    if (levelData[x - i][y] == this.PLAYER) {
                        return Direction.LEFT;
                    }
                }
            }
            else {
                break;
            }
        }
        for (int i = 0; i < range; i++) {
            if (this.canMove(x, y + 1)) {
                if (y + i < levelData[0].length) {
                    if (levelData[x][y + i] == this.PLAYER) {
                        return Direction.DOWN;
                    }
                }
            }
            else {
                break;
            }
        }
        for (int i = 0; i < range; i++) {
            if (this.canMove(x, y - 1)) {
                if (y - i >= 0) {
                    if (levelData[x][y - i] == this.PLAYER) {
                        return Direction.UP;
                    }
                }
            }
            else {
                break;
            }
        }

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
        if (!(levelData[x][y] == this.INVALID)) {
            return true;
        }
        return false;
    }

} // End of LevelData.java
