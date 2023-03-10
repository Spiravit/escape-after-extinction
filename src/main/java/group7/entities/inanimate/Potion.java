package group7.entities.inanimate;


import java.awt.Graphics;

import group7.Graphics.GraphicsGrid;
import group7.levels.Pathfinding;
import group7.utils.AssetLoader;
import static group7.utils.AssetLoader.*;
import java.awt.image.BufferedImage;


public class Potion extends Collectables {
    public static final int GREEN_POTION_BOOST_VALUE = 15;	
    public static final int PURPLE_POTION_BOOST_VALUE = 10;
    
    protected String potionType;

    public Potion(int positionX, int positionY, int objectType, Pathfinding pathfinding ) {
        super(positionX, positionY, objectType, pathfinding);
        setObject( objectType );
        loadAnimations();
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
        BufferedImage potion = AssetLoader.getSpriteAtlas( SPEED_POTION ); // TODO: Change this to potionType
        entityAnimations = new BufferedImage[1][];

        entityAnimations[0] = new BufferedImage[1];
        entityAnimations[0][0] = potion.getSubimage( 0, 0, 16, 16 );
        currentEntityImage = entityAnimations[0][0];
    }
}
