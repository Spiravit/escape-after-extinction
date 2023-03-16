package group7.entities.inanimate;

import group7.entities.animate.Player;
import group7.helperClasses.AssetLoader;
import static group7.helperClasses.AssetLoader.*;
import java.awt.image.BufferedImage;


/** 
* The class creates an object of type Key, that renders a key in the game window.
* When a Player collects a key their key count increases.
*
* @author  Mohammad Parsaei
* @author  Salman Ayaz
* @author  Karmen Yung
* @author  Chen Min
* @version 1.0
* @since 2023-03-13
*/
public class Key extends Inanimate {
    
    /**
     * Constructor: Create a new key object.
     * @param posX (the x position of this key)
     * @param posY (the y position of this key)
     */
    public Key(double posX, double posY) {
        super(posX, posY);
    }

    @Override
    protected void loadAnimations() {
        BufferedImage key = AssetLoader.getSpriteAtlas(KEY);

        entityAnimations[DEFAULT_ANIMATION] = new BufferedImage[12];
        for (int i = 0; i < 12; i++) {
            entityAnimations[DEFAULT_ANIMATION][i] = key.getSubimage(i * 32, 0, 32, 32);
        }
    }

    @Override
    public void onInteraction(Player player) {
        player.incrementKeysCollected();
        super.onInteraction(player);
    }
}
