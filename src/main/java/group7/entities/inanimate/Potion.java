package group7.entities.inanimate;


import java.awt.Graphics;

import group7.Graphics.GraphicsGrid;
import group7.levels.Pathfinding;
import group7.utils.AssetLoader;
import static group7.utils.AssetLoader.*;
import java.awt.image.BufferedImage;


public class Potion extends Collectables {

    public static final int POTION_WIDTH_DEFAULT = 16;
	public static final int POTION_HEIGHT_DEFAULT = 16;
	public static final int POTION_WIDTH = (int) ( xScale * POTION_WIDTH_DEFAULT);
	public static final int POTION_HEIGHT = (int) ( yScale * POTION_HEIGHT_DEFAULT);
    
    public static final int GREEN_POTION_BOOST_VALUE = 15; // TODO: change value if needed
	public static final int PURPLE_POTION_BOOST_VALUE = 10; // TODO: change value
    protected String potionType;

    // Constructor
    public Potion(int positionX, int positionY, int objectType, Pathfinding pathfinding ) {
        super(positionX, positionY, objectType, pathfinding);
        setObject( objectType );
        loadAnimations();
        // doAnimation = true;
        // initHitbox(); // move hitbox here from collectables
    }

    protected void setObject( int objectType ) {
        potionType = SPEED_POTION;
        if( objectType == GREEN_HEALTH_POTION ) {
            potionType = HEALTH_POTION;
        }
    }

    @Override
    protected void loadAnimations() {
        //BufferedImage potion = AssetLoader.getSpriteAtlas( SPEED_POTION );
        BufferedImage potion = AssetLoader.getSpriteAtlas( potionType );
        entityAnimations = new BufferedImage[1][];

        entityAnimations[0] = new BufferedImage[1];
        entityAnimations[0][0] = potion.getSubimage( 0, 0, 16, 16 ); //x,y,w,h
    }

    public void render(Graphics g) {
        // draw the current animation and sprite in the current positions
        GraphicsGrid.render(
            g,
            entityAnimations[0][0],
            hitboxX + 0.2,
            hitboxY + 0.1, // TODO: maybe change centering 0.1 adjustment.
            0.6,
            0.8
        ); 
        super.render(g); //draws hitbox
        // hitboxWidth,
        //  hitboxHeight
    }

    @Override
    public void update() {
        
    }
   
}
