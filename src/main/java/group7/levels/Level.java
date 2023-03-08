package group7.levels;

import group7.entities.*;
import group7.Graphics.GraphicsGrid;
import group7.utils.AssetLoader;
import group7.utils.Direction;

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

    private ArrayList<Entity> entities;

    /** 
     * Loads everything about the level
     */
    public void loadLevel(int dinoNumber) {
        importSprites();
        setLevelData();
        GraphicsGrid.setGridSize(width, height);

        player = new Player(1, 1, pathfinding, dinoNumber);

        entities = new ArrayList<Entity>();
        entities.add(new Enemy(1, 1, pathfinding));
    }

    public Pathfinding getLevelData() { // TODO: remove this
        return pathfinding;
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

    public void setDirection(Direction direction) {
        player.setDirection(direction);
    }

    public void removeDirection(Direction direction) {
        player.removeDirection(direction);
    }

    public void checkInteractions() {
        Rectangle2D playerHitbox = player.getHitbox();

        for (Entity entity : entities) {
            Rectangle2D entityHitbox = entity.getHitbox();

            if (playerHitbox.intersects(entityHitbox)) {
                player.onInteraction(entity);
                System.out.println("Interacted with entity");
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
}
