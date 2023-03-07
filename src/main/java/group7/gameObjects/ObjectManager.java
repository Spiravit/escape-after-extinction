package group7.gameObjects;

import group7.levels.LevelData;
import group7.utils.AssetLoader;
import static group7.utils.AssetLoader.*;
import group7.Graphics.GraphicsGrid;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ObjectManager {
    private LevelData currentLevel;
    private BufferedImage potionImage;
    private ArrayList<KeyCard> keys;

    public ObjectManager (LevelData levelData) {
        this.currentLevel = levelData;
        loadImages();
        keys = new ArrayList<>();
        keys.add(new KeyCard(3, 3, 0));
    }

    private void loadImages() {
        BufferedImage potionSprite = AssetLoader.getSpriteAtlas(KEY_CARD);
        BufferedImage potionImage = potionSprite.getSubimage(0, 0, 32, 24); //x,y,w,h
    }
    
    public void render(Graphics g){
        // draw the player, with the current animation and sprite in the current positions
        for(KeyCard key : keys) {
            GraphicsGrid.render(
                g,
                potionImage,
                ( key.getHitbox().x ),
                ( key.getHitbox().y ), // TODO: maybe place it somewhere else.
                1,
                1
            );
            //drawHitbox(g);
        }    
    }
}
