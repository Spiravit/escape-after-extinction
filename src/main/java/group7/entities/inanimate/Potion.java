package group7.entities.inanimate;

import group7.entities.animate.Player;
import group7.helperClasses.AssetLoader;

import java.awt.image.BufferedImage;


/** 
* The class Potion extends abstract class Inanimate.
* The class creates an object of Potion type, and renders a bottle in the game window.
* There are two types of potions: health and speed.  
* When collected Potions increase a players health or speed.
*
* @author  Mohammad Parsaei
* @author  Salman Ayaz
* @author  Karmen Yung
* @author  Chen Min
* @version 1.0
* @since 2023-03-13
*/
public class Potion extends Inanimate {
    private int potionNumber; // the number of the potion sprite to use
    public static final int GREEN_POTION_BOOST_VALUE = 25;	
    public static final float PURPLE_POTION_BOOST_VALUE = 0.01f;
    
    protected String potionType;

    public static final int GREEN_HEALTH_POTION = 1;
	public static final int PURPLE_SPEED_POTION = 2;

    /**
     * Constructor: Create a new specified potion object.
     * @param tileX (the x tile position of this potion)
     * @param tileY (the y tile position of this potion)
     * @param objectType (the type of potion: health or speed)
     */
    public Potion(double tileX, double tileY, int objectType) {
        super(tileX, tileY);
        this.potionNumber = objectType;

        setHitboxHeight(0.7, tileY);
        setHitboxWidth(0.73, tileX);

        loadAnimations();
    }

    @Override
    protected void loadAnimations() {
        switch ( potionNumber ) {
            default:
            case GREEN_HEALTH_POTION:         
                potionType = AssetLoader.HEALTH_POTION;
                break;
            case PURPLE_SPEED_POTION:
                potionType = AssetLoader.SPEED_POTION;
                break;
        }

        BufferedImage potion = AssetLoader.getSpriteAtlas( potionType ); 
        entityAnimations[DEFAULT_ANIMATION] = extractSprite(potion, 0, 0, 16, 16, 2);
    }

    @Override
    public void onInteraction( Player player ) {
        if ( this.potionNumber == GREEN_HEALTH_POTION ) {
            player.gainHealth( GREEN_POTION_BOOST_VALUE );
        } else if ( this.potionNumber == PURPLE_SPEED_POTION ) {
            player.increaseSpeed( PURPLE_POTION_BOOST_VALUE );
        }
        super.onInteraction( player );
    }
}
