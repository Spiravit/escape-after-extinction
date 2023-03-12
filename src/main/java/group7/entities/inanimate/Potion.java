package group7.entities.inanimate;

import group7.helperClasses.AssetLoader;
import static group7.helperClasses.AssetLoader.*;
import java.awt.image.BufferedImage;


public class Potion extends Collectable {
    public static final int GREEN_POTION_BOOST_VALUE = 15;	
    public static final int PURPLE_POTION_BOOST_VALUE = 10;
    
    protected String potionType;

    public Potion(double posX, double posY, int objectType) {
        super(posX, posY);
        setObject( objectType );

        hitboxHeight = 0.7;
        hitboxWidth = 0.73;
        setPosX(posX);
        setPosY(posY);
    }

    protected void setObject( int objectType ) {
        potionType = SPEED_POTION;
        if( objectType == GREEN_HEALTH_POTION ) {
            potionType = HEALTH_POTION;
        }
    }

    @Override
    protected void loadAnimations() {
        BufferedImage potion = AssetLoader.getSpriteAtlas( SPEED_POTION ); // TODO: Change this to potionType
        //BufferedImage potion = AssetLoader.getSpriteAtlas( potionType ); 

        entityAnimations[DEFAULT_ANIMATION] = new BufferedImage[1];
        entityAnimations[DEFAULT_ANIMATION][0] = potion.getSubimage( 0, 0, 16, 16 );
    }
}
