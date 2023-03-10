package group7.levels;

import group7.entities.*;
import group7.Graphics.GraphicsGrid;
import group7.utils.AssetLoader;
import group7.utils.Direction;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import java.awt.Graphics;
import java.awt.Color;

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
    public void loadLevel(String filename, int dinoNumber) {
        //levelSpriteData = getLevelData(level);      // TEST: MULTIPLE LEVLES
        importSprites();
        setLevelData(filename); // TEST: MULTIPLE LEVLES 
        GraphicsGrid.setGridSize(width, height);

        player = new Player(1, 3, pathfinding, dinoNumber);

        entities = new ArrayList<Entity>();
        entities.add(new Enemy(1, 1, pathfinding));
    }

    public Pathfinding getPathfindingData() { // TODO: remove this
        return pathfinding;
    }

    // Fetch all game level maps
/*    public static BufferedImage[] getAllLevels() {
        URL url = AssetLoader.class.getResource( "/assets/levels/level_maps/" );
        File file = null;

        try {
            file = new File( url.toURI() );
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        
        File[] files = file.listFiles(); // array of all level maps
        File[] levelMaps = new File[files.length]; // sorted array of all levels

        for ( int i = 0; i < levelMaps.length; i++ ) {
            for ( int j = 0; j < files.length; j++ ) {
                if ( files[j].getName().equals( "level_" + (i + 1) + ".png" ) ) {
                    levelMaps[i] = files[j];
                }
            }
        }

        // convert files to usable images, store in array
        BufferedImage[] imgs = new BufferedImage[levelMaps.length];
        for ( int k = 0; k < imgs.length; k++ ) {
            try {
                imgs[k] = ImageIO.read( levelMaps[k] );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return imgs;
    } 
*/
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
    private void setLevelData( String filename) { //TEST MULTIPLE LEVELS
        BufferedImage img = AssetLoader.getSpriteAtlas(filename); //TEST MULTIPLE LEVELS, changed from AssetLoader.LEVEL_1 to filename

        this.width = img.getWidth();
        this.height = img.getHeight();

        levelSpriteData = new int[width][height];
        pathfinding = new Pathfinding(width, height);

        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                Color color = new Color(img.getRGB(x, y));
                int value = color.getRed() % 58; //chaged from 57 b 57 is the clear tile

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

    public void setDirection(Direction direction) {
        player.setDirection(direction);
    }

    public void removeDirection(Direction direction) {
        player.removeDirection(direction);
    }

    public void update() {
        player.update();
        for (Entity entity : entities) {
            entity.update();
        }
    }

}
