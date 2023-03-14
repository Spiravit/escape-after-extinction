package group7.levels;

import java.awt.*;
import java.awt.image.BufferedImage;

import group7.helperClasses.AssetLoader;
import static group7.entities.inanimate.Collectable.*;


public class Level1 extends Level {

    public Level1(int dinoNumber) {
        super(dinoNumber);
        addPlayer(1, 3, dinoNumber);

       // addEnemy(5, 14, 1);
       // addEnemy(9, 4, 2);
      //  addEnemy(13, 9, 3);
      //  addEnemy(10, 15, 4);

        addTrap(3, 9);
        addTrap(11, 10);
        addTrap(16, 10);
        addTrap(8, 14);

        addKey(2, 14);
        addKey(17, 5);
        addKey(18, 16);

        addEgg(3, 15, 200);
        addEgg(16, 4, 100);
        addEgg(18, 13, 300);

        addPotion(10, 3, PURPLE_SPEED_POTION );    
        addPotion(7, 16, PURPLE_SPEED_POTION );    
        addPotion(9, 10, GREEN_HEALTH_POTION );    
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
