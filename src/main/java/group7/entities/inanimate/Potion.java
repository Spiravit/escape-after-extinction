package group7.entities.inanimate;

import group7.helperClasses.AssetLoader;
import static group7.helperClasses.AssetLoader.*;
import java.awt.image.BufferedImage;


public class Potion extends Collectable {
    private int potionNumber; // the number of the potion sprite to use
    public static final int GREEN_POTION_BOOST_VALUE = 15;	
    public static final int PURPLE_POTION_BOOST_VALUE = 10;
    
    protected String potionType;

    public Potion(double posX, double posY, int objectType) {
        super(posX, posY);
        this.potionNumber = objectType;

        hitboxHeight = 0.7;
        hitboxWidth = 0.73;
        setPosX(posX);
        setPosY(posY);
        
        loadAnimations();
    }

    @Override
    protected void loadAnimations() {
        switch ( potionNumber ) {
            default:
            case 1:         
                potionType = AssetLoader.SPEED_POTION;
                break;
            case 2:
                potionType = AssetLoader.HEALTH_POTION;
                break;
        }
        BufferedImage potion = AssetLoader.getSpriteAtlas( potionType ); 

        entityAnimations[DEFAULT_ANIMATION] = new BufferedImage[1];
        entityAnimations[DEFAULT_ANIMATION][0] = potion.getSubimage( 0, 0, 16, 16 );
    }
}
