package group7.entities.inanimate;

import java.awt.image.BufferedImage;

import group7.entities.animate.Player;
import group7.helperClasses.AssetLoader;


/** 
* The class Trap extends abstract class Inanimate.
* The class creates an object of Trap type, and renders a spike trap in the game window.
* When a Player walks over a trap their health decreases.
*
* @author  Mohammad Parsaei
* @author  Salman Ayaz
* @author  Karmen Yung
* @author  Chen Min
* @version 1.0
* @since 2023-03-13
*/
public class Trap extends Inanimate {
    private int damage = 50;

    /**
     * Constructor: Create a new trap object.
     * @param tileX (the x tile position of this trap)
     * @param tileY (the y tile position of this trap)
     */
    public Trap(double tileX, double tileY) {
        super(tileX, tileY);
        loadAnimations();
    }

    @Override
    protected void loadAnimations() {
        BufferedImage trap = AssetLoader.getSpriteAtlas(AssetLoader.TRAP);

        entityAnimations[DEFAULT_ANIMATION] = extractSprite(trap, 0, 0, 32, 32, 1);
        entityAnimations[INTERACTION_ANIMATION] = extractSprite(trap, 32, 0, 32, 32, 3);
    }

    @Override
    protected void updateAnimation() {
        if (currentAnimation == INTERACTION_ANIMATION) {
            if (aniIndex >= getSpriteAmount(currentAnimation) - 1) {
                visible = false;
            }
        } else {
            super.updateAnimation();
        }
    }

    @Override
    public void onInteraction(Player player) {
        player.takeDamage(damage);
        super.onInteraction(player);
        visible = true;
        setAnimation(INTERACTION_ANIMATION);
    }
}
