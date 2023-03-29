package group7.levels;

import group7.entities.*;
import group7.entities.animate.*;
import group7.entities.inanimate.*;
import group7.Graphics.GraphicsGrid;
import group7.helperClasses.AssetLoader;
import group7.helperClasses.Direction;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/** 
* The abstract class Level defines all methods needed to loads all graphics 
* such as animate and inanimate objects, as well as, the map into the game. 
*
* @author  Mohammad Parsaei
* @author  Salman Ayaz
* @author  Karmen Yung
* @author  Chen Min
* @version 1.0
* @since 2023-03-13
*/
public abstract class Level {
    protected int width;
    protected int height;
    protected Pathfinding pathfinding;
    protected BufferedImage[] levelSprites;
    protected int levelSpriteData[][];
    protected Player player;

    protected ArrayList<Entity> entities = new ArrayList<Entity>();

    protected int numberOfEggs = 0;
    protected int numberOfKeys = 0;


    /**
     * Constructor: Loads everything about the level.
     * @param dinoNumber (player chosen dino type)
     * @param levelNumber (level number which we will import its sprite)
     */
    public Level(int dinoNumber,int levelNumber) {
        importSprites();
        setLevelData(levelNumber);
        GraphicsGrid.setGridSize(width, height);
        addPlayer(1, 3, dinoNumber);
    }

    /**
     * Wrapper Method: Spawn a player in the level.
     * @param x (position x of this player)
     * @param y (position y of this player)
     * @param dinoNumber (associated number of the chosen dinosaur sprite to be use)
     */
    protected void addPlayer(int x, int y, int dinoNumber) {
        player = new Player(x, y, pathfinding, dinoNumber);
    }

    /**
     * Wrapper Method: Spawn an Enemy in the level.
     * @param x 
     * position x of this enemy
     * @param y 
     * position y of this enemy
     * @param detectionRange 
     * detection range of this enemy
     * @param afterDetectionRange 
     * the range this enemy will continue to detect the player after it has been detected
     */
    protected void addEnemy(int x, int y, int detectionRange, int afterDetectionRange, int enemyNumber) {
        entities.add(
            new Enemy(
                x, 
                y, 
                pathfinding, 
                detectionRange, 
                afterDetectionRange, 
                enemyNumber
            )
        );
    }

    /**
     * Wrapper Method: Spawn a key in the level.
     * @param x (position x of this key)
     * @param y (position y of this key)
     */
    protected void addKey(int x, int y) {
        entities.add(new Key(x, y));
        numberOfKeys++;
    }

    /**
     * Wrapper Method: Spawn an egg in the level.
     * @param x (position x of this egg)
     * @param y (position y of this egg)
     * @param timeout (time in seconds before this egg disappears)
     */
    protected void addEgg(int x, int y, int timeout) {
        entities.add(new Egg(x, y, timeout));
        numberOfEggs++;
    }

    /**
     * Wrapper Method: Spawn a potion in the level.
     * @param x (position x of this potion)
     * @param y (position y of this potion)
     * @param potionType (type of potion: Health or Speed)
     */
    protected void addPotion(int x, int y, int potionType) {
        entities.add(new Potion(x, y, potionType));
    }

    /**
     * Wrapper Method: Spawn a trap in the level.
     * @param x (position x of this tarp)
     * @param y (position y of this trap)
     */
    protected void addTrap(int x, int y) {
        entities.add(new Trap(x, y));
    }

    /**
     * Wrapper Method: returns number of keys the player collected.
     * @return int keys collected
     */
    public int getKeysCollected() {
        return player.getKeysCollected();
    }

    /**
     * Wrapper Method: returns number of eggs the player collected.
     * @return int eggs collected
     */
    public int getEggsCollected() {
        return player.getEggsCollected();
    }

    /**
     * Wrapper Method: returns the player's health.
     * @return int player's health
     */
    public int getHealth() {
        return player.getHealth();
    }

    /**
     * Returns number of all eggs to be used in a level
     * @return int number of egg items in a level
     */
    public int getNumberOfEggs() {
        return numberOfEggs;
    }

    /**
     * Returns number of all keys to be used in a level
     * @return int number of keys items in a level
     */
    public int getNumberOfKeys() {
        return numberOfKeys;
    }


    /**
     * Set the level data,
     * this includes the data in the pathfinding object and the levelSpriteData array.
     * @param levelNumber the level number which we want to import its sprite
     */
    protected void setLevelData(int levelNumber){
        BufferedImage img = AssetLoader.getSpriteAtlas("levels/level_maps/level_" + levelNumber + ".png");

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
     * Import the sprites from the sprite atlas and store them in the levelSprites array.
     */
    private void importSprites() {
        BufferedImage img = AssetLoader.getSpriteAtlas(AssetLoader.LEVELS_SPRITES);
        levelSprites = new BufferedImage[75];

        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 5; y++) {
                int index = y * 15 + x;
                levelSprites[index] = img.getSubimage(x * 32, y * 32, 32, 32);
            }
        }
    }

    /**
     * Wrapper Method: sets the direction to be moved in.
     * @param direction
     */
    public void setDirection(Direction direction) {
        player.setDirection(direction);
    }

    /**
     * Wrapper Method: removes current direction that is being moving in.
     * @param direction
     */
    public void removeDirection(Direction direction) {
        player.removeDirection(direction);
    }

    /**
     * Return the current state of the player in this game.
     * @return LOST if players health is at zero.
     * @return WON if all the keys in the level are collected.
     * @return PLAYING otherwise.
     */
    public LevelState checkLevelState() {
        if (player.getHealth() <= 0) {
            return LevelState.LOST;
        } else if (numberOfKeys == getKeysCollected()) {
            return LevelState.WON;
        } else {
            return LevelState.PLAYING;
        }
    }

    /**
     * Determine if any of the entity hitboxex intersect with the player hitbox.
     */
    private void checkInteractions() {
        for (Entity entity : entities) {
            if (entity.getHitbox().intersects(player.getHitbox())) {
                entity.onInteraction(player);
            }
        }
    }

    /**
     * Determine if any of the entities loaded on the map interact with the player or not.
     */
    public void update() {
        player.update();
        for (Entity entity : entities) {
            entity.update();
        }
        checkInteractions();
    }

    /**
     * render the level, player and entities
     * @param g (the graphics object to draw on)
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
