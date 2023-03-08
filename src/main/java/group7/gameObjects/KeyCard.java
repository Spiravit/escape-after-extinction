package group7.gameObjects;

import java.awt.Graphics;

import group7.Graphics.GraphicsGrid;
import group7.levels.Pathfinding;
import group7.utils.AssetLoader;
import static group7.utils.AssetLoader.*;
import java.awt.image.BufferedImage;


public class KeyCard extends Collectables {
    
    // Constructor
    public KeyCard(int x, int y, int objectType, Pathfinding pathfinding ) {
        super( x, y, objectType, pathfinding );
        loadAnimations();
    }

    @Override
    void loadAnimations() {
        BufferedImage key = AssetLoader.getSpriteAtlas( KEY_CARD );
        entityAnimations = new BufferedImage[1][];

        entityAnimations[0] = new BufferedImage[1];
        entityAnimations[0][0] = key.getSubimage(0, 0, 32, 24); //x,y,w,h
        //System.out.println("attempting to load keycard img");
    }

    public void render(Graphics g) {
        // draw the current animation and sprite in the current positions
        //System.out.println("attempting to render key");
        GraphicsGrid.render(
            g,
            entityAnimations[0][0],
            hitboxX,
            hitboxY, // TODO: maybe place it somewhere else.
            1,
            1
        ); 
        super.render(g);
        //drawHitbox(g);
    }
    
}
