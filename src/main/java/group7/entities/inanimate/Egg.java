package group7.entities.inanimate;

import java.awt.image.BufferedImage;

import group7.entities.animate.Player;
import group7.helperClasses.AssetLoader;


/**
* The class Egg extends Inanimate.
* Creates an Object of type Egg, that renders a dinosaur egg on screen.
* Collecting a dinosaur egg increases a players final score for the level.
* Egg has properties: startTime and timeout, that allows it to disappear from the map after a set time.
*
* @author  Mohammad Parsaei
* @author  Salman Ayaz
* @author  Karmen Yung
* @author  Chen Min
* @version 1.0
* @since 2023-03-13
*/
public class Egg extends Inanimate {
    private double startTime;
    private int timeout;

    /**
     * Creates an egg that will disappear after a certain amount of time
     * @param tileX (x tile position of this Egg)
     * @param tileY (y tile postion of this Egg)
     * @param timeout (time, in seconds, before this egg disappears)
     */
    public Egg(double tileX, double tileY, int timeout) {
        super(tileX, tileY);
        this.timeout = timeout;
        startTime = System.currentTimeMillis();

        aniSpeed = 30;

        setHitboxHeight(0.8, tileY);
        setHitboxWidth(0.8, tileX);
    }

    @Override
    protected void loadAnimations() {
        BufferedImage egg = AssetLoader.getSpriteAtlas(AssetLoader.EGG);
        entityAnimations[DEFAULT_ANIMATION] = extractSprite(egg, 0, 0, 24, 24, 4);
    }

    /**
     * Removes the graphic displayed from the game window, when time is up.
     */
    public void update() {
        if ((System.currentTimeMillis() - startTime) / 1000 >= timeout) {
            removeCollectable();
        }
        super.update();
    }

    @Override
    public void onInteraction(Player player) {
        player.incrementEggsCollected();
        super.onInteraction(player);
    }
}
