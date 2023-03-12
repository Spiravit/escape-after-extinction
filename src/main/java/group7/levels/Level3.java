package group7.levels;

import java.awt.*;
import java.awt.image.BufferedImage;

import group7.helperClasses.AssetLoader;
import static group7.entities.inanimate.Collectable.*;

public class Level3 extends Level {

    public Level3(int dinoNumber) {
        super(dinoNumber);
        addPlayer(1, 3, dinoNumber);

        addEnemy(4, 6, 1);
        addEnemy(3, 14, 2);
        addEnemy(13, 15, 3);
        addEnemy(18, 5, 4);

        addTrap(3, 7);
        addTrap(3, 10);
        addTrap(7, 3);
        addTrap(8, 6);
        addTrap(8, 12);
        addTrap(9, 15);
        addTrap(11, 9);
        addTrap(17, 14);
        addTrap(14, 4);
        addTrap(15, 9);

        addKey(2, 9); 
        addKey(2, 16);
        addKey(8, 3);
        addKey(9, 10);
        addKey(13, 3);
        addKey(18, 16);

        addEgg(2, 6, 300);
        addEgg(9, 8, 400);
        addEgg(4, 16, 100);
        addEgg(9, 16, 200);
        addEgg(16, 9, 200);

        addPotion(6, 10, PURPLE_SPEED_POTION );    
        addPotion(18, 11, PURPLE_SPEED_POTION );    
        addPotion(18, 3, PURPLE_SPEED_POTION );    
        addPotion(6, 6, GREEN_HEALTH_POTION );    
        addPotion(10, 6, GREEN_HEALTH_POTION );    
        addPotion(11, 11, GREEN_HEALTH_POTION ); 
        addPotion(17, 7, GREEN_HEALTH_POTION );    
    }

    @Override
    /**
     * Set the level data
     * this includes the data in the pathfinding object and the levelSpriteData array
     */
    protected void setLevelData() { //TEST MULTIPLE LEVELS
        BufferedImage img = AssetLoader.getSpriteAtlas("levels/level_maps/level_" + 3 + ".png"); //TEST MULTIPLE LEVELS, changed from AssetLoader.LEVEL_1 to filename

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
