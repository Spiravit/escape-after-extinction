package group7.entities.inanimate;

import group7.utils.AssetLoader;
import static group7.utils.AssetLoader.*;
import java.awt.image.BufferedImage;


public class Potion extends Collectables {
    public static final int GREEN_POTION_BOOST_VALUE = 15;	
    public static final int PURPLE_POTION_BOOST_VALUE = 10;
    
    protected String potionType;

    public Potion(int positionX, int positionY, int objectType) {
        super(positionX, positionY);
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

        entityAnimations[DEFAULT_ANIMATION] = new BufferedImage[1];
        entityAnimations[DEFAULT_ANIMATION][0] = potion.getSubimage( 0, 0, 16, 16 );
    }
}
