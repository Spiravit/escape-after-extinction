package group7.levels;

import group7.entities.*;
import group7.Graphics.GraphicsGrid;
import group7.utils.AssetLoader;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;

public abstract class Level {
    private int width;
    private int height;
    private LevelData levelData;
    private BufferedImage[] levelSprites;
    private int levelSpriteData[][];

    public void loadLevel() {
        importSprites();
        setLevelData();
        GraphicsGrid.setGridSize(width, height);
    }

    public LevelData getLevelData() { // TODO: remove this
        return levelData;
    }

    public void setLevelData() {
        BufferedImage img = AssetLoader.getSpriteAtlas(AssetLoader.LEVELONEMAP);

        this.width = img.getWidth();
        this.height = img.getHeight();

        levelSpriteData = new int[width][height];
        levelData = new LevelData(width, height);

        for (int x = 0; x < this.width; x++) {
            System.out.println(img.getWidth());
            for (int y = 0; y < this.height; y++) {
                Color color = new Color(img.getRGB(x, y));
                int value = color.getRed() % 57;

                // TODO: add the logic to set the level data
                // levelData.set(x, y, true/false); <- get the true or false value
                levelSpriteData[x][y] = value;
            }
        }
    }

    private void importSprites() {
        BufferedImage img = AssetLoader.getSpriteAtlas(AssetLoader.LEVELS_SPRITES);
        levelSprites = new BufferedImage[60];
        
        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 4; y++) {
                int index = y * 15 + x;
                levelSprites[index] = img.getSubimage(x * 32, y * 32, 32, 32);
            }
        }
    }

    public void render(Graphics g) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                GraphicsGrid.render(
                    g, 
                    levelSprites[levelSpriteData[x][y]], 
                    x, 
                    y, 
                    1, 
                    1
                );
            }
        }
    }
}
