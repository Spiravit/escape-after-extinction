package group7.levels;

import group7.entities.*;
import group7.entities.animate.*;
import group7.entities.inanimate.*;
import group7.Graphics.GraphicsGrid;
import group7.helperClasses.AssetLoader;
import group7.helperClasses.Direction;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.geom.Rectangle2D;

public abstract class Level {
    private int width;
    private int height;
    private Pathfinding pathfinding;
    private BufferedImage[] levelSprites;
    private int levelSpriteData[][];
    private Player player;

    private ArrayList<Entity> entities = new ArrayList<Entity>();

    private int numberOfEggs = 0;
    private int numberOfKeys = 0;

    private int eggsCollected = 0;
    private int keysCollected = 0;

    /** 
     * Loads everything about the level
     * @param dinoNumber 
     * player dino type
     */
    public void loadLevel(String filename, int dinoNumber) {
        //levelSpriteData = getLevelData(level);      // TEST: MULTIPLE LEVLES
        importSprites();
        setLevelData(filename); // TEST: MULTIPLE LEVLES 
        GraphicsGrid.setGridSize(width, height);
        
        numberOfEggs = 0;
        numberOfKeys = 0;

        addPlayer(1, 3, dinoNumber);
        addEnemy(5, 5);
        addKey(1, 3);
        addEgg(1, 4, 100);
        addPotion(1, 5, 0);
        addTrap(1, 6);
    }

    /**
     * spawn the player in the level
     * @param x
     * position x
     * @param y
     * position y
     * @param dinoNumber
     * the number of the dinosaur sprite to use
     */
    private void addPlayer(int x, int y, int dinoNumber) {
        player = new Player(x, y, pathfinding, dinoNumber);
    }

    /**
     * Add an enemy to the level
     * @param x
     * position x
     * @param y
     * position y
     */
    private void addEnemy(int x, int y) {
        entities.add(new Enemy(x, y, pathfinding));
    }

    /**
     * Add a key to the level
     * @param x
     * position x
     * @param y
     * position y
     */
    private void addKey(int x, int y) {
        entities.add(new Key(x, y));
        numberOfKeys++;
    }

    /**
     * Add an egg to the level
     * @param x
     * position x
     * @param y
     * position y
     * @param timeout
     * time in seconds before egg disappears
     */
    private void addEgg(int x, int y, int timeout) {
        entities.add(new Egg(x, y, timeout));
        numberOfEggs++;
    }

    /**
     * Add a potion to the level
     * @param x
     * position x
     * @param y
     * position y
     * @param potionType
     * type of potion
     */
    private void addPotion(int x, int y, int potionType) {
        entities.add(new Potion(x, y, potionType));
    }

    /**
     * Add a trap to the level
     * @param x
     * position x
     * @param y
     * position y
     */
    private void addTrap(int x, int y) {
        entities.add(new Trap(x, y));
    }

    public int getKeysCollected() {
        return player.getKeysCollected();
    }

    public int getEggsCollected() {
        return player.getEggsCollected();
    }
    
    /** 
     * Set the level data
     * this includes the data in the pathfinding object and the levelSpriteData array
     */
    private void setLevelData( String filename) { //TEST MULTIPLE LEVELS
        BufferedImage img = AssetLoader.getSpriteAtlas(filename); //TEST MULTIPLE LEVELS, changed from AssetLoader.LEVEL_1 to filename

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

    /** 
     * Import the sprites from the sprite atlas and store them in the levelSprites array
     */
    private void importSprites() {
        BufferedImage img = AssetLoader.getSpriteAtlas("levels/levelssprites.png");
        levelSprites = new BufferedImage[75];
        
        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 5; y++) {
                int index = y * 15 + x;
                levelSprites[index] = img.getSubimage(x * 32, y * 32, 32, 32);
            }
        }
    }

    public void setDirection(Direction direction) {
        player.setDirection(direction);
    }

    public void removeDirection(Direction direction) {
        player.removeDirection(direction);
    }

    public LevelState checkLevelState() {
        if (player.getHealth() < 0) {
            return LevelState.LOST;
        } else if (numberOfKeys == keysCollected) {
            return LevelState.WON;
        } else {
            return LevelState.PLAYING;
        }
    }

    private void checkInteractions() {
        for (Entity entity : entities) {
            if (entity.getHitbox().intersects(player.getHitbox())) {
                entity.onInteraction(player);
            }
        }
    }

    public void update() {
        player.update();
        for (Entity entity : entities) {
            entity.update();
        }
        checkInteractions();
    }

    /**
     * render the level, player and entities
     * @param g
     * the graphics object to draw on
     */
    public void render(Graphics g) {
        // remember to render the level first or you will end up spending a long time debugging like me :D
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
 
        player.render(g);
    }
}
