package group7.entities.inanimate;

import java.awt.image.BufferedImage;


import group7.entities.animate.Player;
import group7.helperClasses.AssetLoader;


public class Egg extends Collectable {
    private double startTime;
    private int timeout;

    /**
     * Creates an egg that will disappear after a certain amount of time
     * @param x
     * position x
     * @param y
     * position y
     * @param timeout
     * time in seconds before egg disappears
     */
    public Egg(int x, int y, int timeout) {
        super(x, y);
        loadAnimations();
        this.timeout = timeout;
        startTime = System.currentTimeMillis();
    }

    @Override
    protected void loadAnimations() {
        entityAnimations[DEFAULT_ANIMATION] = new BufferedImage[1];
        BufferedImage img = AssetLoader.getSpriteAtlas(AssetLoader.EGG);
        entityAnimations[DEFAULT_ANIMATION][0] = img.getSubimage(0, 0, 24, 24);
    }

    public void update() {
        if ((System.currentTimeMillis() - startTime) / 1000 > timeout) {
            visible = false;
        }
        super.update();
    }

    @Override
    public void onInteraction(Player player) {
        player.incrementEggsCollected();
        super.onInteraction(player);
    }
}
