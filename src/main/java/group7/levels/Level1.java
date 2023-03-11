package group7.levels;

import group7.Graphics.GraphicsGrid;
import group7.helperClasses.AssetLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Level1 extends Level {

    public Level1(int dinoNumber) {
        super(dinoNumber);
        addPlayer(1, 3, dinoNumber);
        addEnemy(5, 5);
        addKey(1, 3);
        addEgg(1, 4, 100);
        addPotion(1, 5, 0);
        addTrap(1, 6);
    }
    @Override
    /**
     * Set the level data
     * this includes the data in the pathfinding object and the levelSpriteData array
     */
    protected void setLevelData() { //TEST MULTIPLE LEVELS
        BufferedImage img = AssetLoader.getSpriteAtlas("levels/level_maps/level_" + 1 + ".png"); //TEST MULTIPLE LEVELS, changed from AssetLoader.LEVEL_1 to filename

        this.width = img.getWidth();
        this.height = img.getHeight();

        levelSpriteData = new int[width][height];
        pathfinding = new Pathfinding(width, height);

        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                Color color = new Color(img.getRGB(x, y));
                int value = color.getRed() % 74; //73 is the clear tile

                levelSpriteData[x][y] = value;
                pathfinding.set(x, y, value != 13 ? false:true);
            }
        }
    }
}
