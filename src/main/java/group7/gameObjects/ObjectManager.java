package group7.gameObjects;

import group7.utils.AssetLoader;
import static group7.utils.AssetLoader.*;
import group7.Graphics.GraphicsGrid;
import group7.levels.Pathfinding;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ObjectManager {
    private Pathfinding currentLevel;
    private BufferedImage keycardImage;
    private ArrayList<KeyCard> keys;
 
    public ObjectManager (Pathfinding pathfinding) {
        this.currentLevel = pathfinding;
        loadImages();
        keys = new ArrayList<>();
        keys.add(new KeyCard(3, 3, 0));
    }

    private void loadImages() {
        BufferedImage keycardSprite = AssetLoader.getSpriteAtlas(KEY_CARD);
        BufferedImage keycardImage = keycardSprite.getSubimage(0, 0, 32, 24); //x,y,w,h
    }
    
    public void render(Graphics g) {
        // draw the current animation and sprite in the current positions
        for(KeyCard key : keys) {
            GraphicsGrid.render(
                g,
                keycardImage,
                ( key.getHitbox().x ),
                ( key.getHitbox().y ), // TODO: maybe place it somewhere else.
                1,
                1
            ); 
            //drawHitbox(g);
        }    
    }
}
