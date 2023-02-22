package group7.levels;
import group7.main.Game;
import group7.utils.AssetLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

import static group7.main.Game.*;

public class LevelData {
    private int levelData[][];

    public LevelData() {
        setLevelData();
    }

    public void setLevelData() {
        levelData = new int[NUMBER_OF_TILES_IN_HEIGHT][NUMBER_OF_TILES_IN_WIDTH];
        BufferedImage img = AssetLoader.getSpriteAtlas(AssetLoader.LEVELONEMAP);
        for (int j = 0; j < img.getHeight(); j++)
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed() % 57;
                levelData[j][i] = value;
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
    public void set(int x, int y, int value) {
        levelData[x][y] = value;
    }

    public int getLevelDataSprite(int x, int y) {
        return levelData[x][y];
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
        int gameWidth = (int) GAME_SIZE_SCALE * NUMBER_OF_TILES_IN_WIDTH * TILES_SIZE;
        int gameHeight = (int) GAME_SIZE_SCALE * NUMBER_OF_TILES_IN_HEIGHT * TILES_SIZE;
        if (x < 0 || x >= gameWidth || y < 0 || y >= gameHeight) {
            return false;
        }
/*
        int tileX = (int) (x/(TILES_SIZE*GAME_SIZE_SCALE));
        int tileY = (int) (y/(TILES_SIZE*GAME_SIZE_SCALE));
        if (levelData[tileX][tileY] !=13){
            return false;
        }
        */
        return true;
    }
}
