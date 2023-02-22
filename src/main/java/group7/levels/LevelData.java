package group7.levels;
import group7.Game;
import group7.utils.AssetLoader;

import static group7.Game.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelData {
    private int levelData[][];

    public LevelData() {
        setLevelData();
    }

    public void setLevelData() {
        levelData = new int[15][20];
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
        if (x < 0 || x >= levelData[0].length || y < 0 || y >= levelData.length) {
            return false;
        }
        int tileX = (int) (x/(TILES_SIZE*GAME_SIZE_SCALE));
        int tileY = (int) (y/(TILES_SIZE*GAME_SIZE_SCALE));
        if (levelData[tileX][tileY] !=13){
            return false;
        }
        */
        return true;
    }
}
