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
     * @param tileX (the x tile position of this key)
     * @param tileY (the y tile position of this key)
     */
    public Key(double tileX, double tileY) {
        super(tileX, tileY);
    }

    @Override
    protected void loadAnimations() {
        BufferedImage key = AssetLoader.getSpriteAtlas(KEY);
        entityAnimations[DEFAULT_ANIMATION] = extractSprite(key, 0, 0, 32, 32, 12);
    }

    @Override
    public void onInteraction(Player player) {
        player.incrementKeysCollected();
        super.onInteraction(player);
    }
}
