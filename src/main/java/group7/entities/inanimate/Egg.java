package group7.entities.inanimate;

import java.awt.image.BufferedImage;

import group7.entities.animate.Player;
import group7.utils.AssetLoader;

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

        aniSpeed = 30;
    }

    @Override
    protected void loadAnimations() {
        BufferedImage img = AssetLoader.getSpriteAtlas(AssetLoader.EGG);

        entityAnimations[DEFAULT_ANIMATION] = new BufferedImage[4];
        for (int i = 0; i < 4; i++) {
            entityAnimations[DEFAULT_ANIMATION][i] = img.getSubimage(i * 24, 0, 24, 24);
        }
    }

    public void update() {
        if ((System.currentTimeMillis() - startTime) / 1000 > timeout) {
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
