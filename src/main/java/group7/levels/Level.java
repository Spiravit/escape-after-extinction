package group7.levels;

import group7.entities.*;
import group7.Graphics.GraphicsGrid;
import group7.utils.AssetLoader;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

public abstract class Level {
    private int width;
    private int height;
    private Pathfinding pathfinding;
    private BufferedImage[] levelSprites;
    private int levelSpriteData[][];

    private ArrayList<Entity> entities;

    /** 
     * Loads everything about the level
     */
    public void loadLevel() {
        importSprites();
        setLevelData();
        GraphicsGrid.setGridSize(width, height);

        entities = new ArrayList<Entity>();
        entities.add(new Enemy(1, 1, pathfinding));
    }

    public Pathfinding getLevelData() { // TODO: remove this
        return pathfinding;
    }

    /**
     * render the level
     * @param g
     * the graphics object to draw on
     */
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

        for (Entity entity : entities) {
            entity.render(g);
        }
    }

    /** 
     * Set the level data
     * this includes the data in the pathfinding object and the levelSpriteData array
     */
    private void setLevelData() {
        BufferedImage img = AssetLoader.getSpriteAtlas(AssetLoader.LEVELONEMAP);

        this.width = img.getWidth();
        this.height = img.getHeight();

        levelSpriteData = new int[width][height];
        pathfinding = new Pathfinding(width, height);

        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                Color color = new Color(img.getRGB(x, y));
                int value = color.getRed() % 57;

                // TODO: add the logic to set the level data
                // pathfinding.set(x, y, true/false); <- get the true or false value
                levelSpriteData[x][y] = value;
                pathfinding.set(x, y, value != 13 ? false:true);
            }
        }
    }

    /** 
     * Import the sprites from the sprite atlas and store them in the levelSprites array
     */
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
}
