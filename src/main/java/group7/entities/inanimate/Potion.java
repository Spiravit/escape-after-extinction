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

    /**
     * Constructor: Create a new specified potion object.
     * @param posX (the x position of this potion)
     * @param posY (the y position of this potion)
     * @param objectType (the type of potion: health or speed)
     */
    public Potion(double posX, double posY, int objectType) {
        super(posX, posY);
        this.potionNumber = objectType;

        setHitboxHeight(0.7, posY);
        setHitboxWidth(0.73, posX);

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

        entityAnimations[DEFAULT_ANIMATION] = new BufferedImage[1];
        entityAnimations[DEFAULT_ANIMATION][0] = potion.getSubimage( 0, 0, 16, 16 );
    }

    @Override
    public void onInteraction( Player player ) {
        //System.out.println("collecting a potion: " + this.potionNumber );           // ***TEST REMOVE***
        if ( this.potionNumber == GREEN_HEALTH_POTION ) {
            //System.out.println("health potion");                                    // ***TEST REMOVE***
            player.gainHealth( GREEN_POTION_BOOST_VALUE );
        } else if ( this.potionNumber == PURPLE_SPEED_POTION ) {
            //System.out.println("i am speed");                                       // ***TEST REMOVE***
            player.increaseSpeed( PURPLE_POTION_BOOST_VALUE );
        }
        super.onInteraction( player );
    }
}
