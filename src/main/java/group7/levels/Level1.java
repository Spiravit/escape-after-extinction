package group7.levels;

import group7.Graphics.GraphicsGrid;
import group7.utils.AssetLoader;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;

public class Level1 extends Level {
    private int width = 15;
    private int height = 10;
    private LevelData levelData;
    private BufferedImage[] levelSprites;
    private int levelSpriteData[][]; // stores the sprite index for each tile

    private GraphicsGrid graphicsGrid;

    public void loadLevel(GraphicsGrid graphicsGrid) {
        graphicsGrid.setGridSize(width, height);
        levelData = new LevelData(width, height);
        levelSpriteData = new int[height][width];
        importSprites();
    }

    public LevelData getLevelData() { // TODO: remove this
        return levelData;
    }

    public void setLevelData(BufferedImage levelImg) {
        BufferedImage img = AssetLoader.getSpriteAtlas(AssetLoader.LEVELONEMAP);
        for (int i = 0; i < img.getHeight(); i++) {
            System.out.println(img.getHeight());
            for (int j = 0; j < img.getWidth(); j++) {
                Color color = new Color(img.getRGB(j, i));
                int value = color.getRed() % 57;
                // TODO: add the logic to set the level data
                // levelData.set(x, y, true/false); <- get the true or false value
                levelSpriteData[j][i] = value;
            }
        }
    }

    public void render(Graphics g) {
        System.out.println("Rendering level");
        System.out.println("Width: " + levelSpriteData[0].length + " Height: " + levelSpriteData.length);
        System.out.println("levelSprite length: " +  levelSprites.length);  
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.println(levelSpriteData[j][i]);

                graphicsGrid.render(
                    g, 
                    levelSprites[levelSpriteData[j][i]], 
                    i, 
                    j, 
                    width, 
                    height
                );
            }
        }
    }

    private void importSprites() {
        BufferedImage img = AssetLoader.getSpriteAtlas(AssetLoader.LEVELS_SPRITES);
        levelSprites = new BufferedImage[60];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 15; j++) {
                int index = i * 15 + j;
                levelSprites[index] = img.getSubimage(j * 32, i * 32, 32, 32);
            }
        }
    }
}
