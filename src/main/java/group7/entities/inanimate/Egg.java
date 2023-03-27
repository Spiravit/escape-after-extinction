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
     * @param posX (position x of this Egg)
     * @param posY (position y of this Egg)
     * @param timeout (time, in seconds, before this egg disappears)
     */
    public Egg(double posX, double posY, int timeout) {
        super(posX, posY);
        this.timeout = timeout;
        startTime = System.currentTimeMillis();

        aniSpeed = 30;
        hitboxWidth = 0.8;
        hitboxHeight = 0.8;
        setPosX(posX);
        setPosY(posY);
    }

    @Override
    protected void loadAnimations() {
        BufferedImage img = AssetLoader.getSpriteAtlas(AssetLoader.EGG);

        entityAnimations[DEFAULT_ANIMATION] = new BufferedImage[4];
        for (int i = 0; i < 4; i++) {
            entityAnimations[DEFAULT_ANIMATION][i] = img.getSubimage(i * 24, 0, 24, 24);
        }
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
