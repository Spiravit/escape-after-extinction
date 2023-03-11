package group7.entities.inanimate;

import java.awt.image.BufferedImage;

import group7.entities.animate.Player;
import group7.utils.AssetLoader;

public class Egg extends Collectable {

    public Egg(int x, int y) {
        super(x, y);
        loadAnimations();
    }

    @Override
    protected void loadAnimations() {
        entityAnimations[DEFAULT_ANIMATION] = new BufferedImage[1];
        BufferedImage img = AssetLoader.getSpriteAtlas(AssetLoader.EGG);
        entityAnimations[DEFAULT_ANIMATION][0] = img.getSubimage(0, 0, 24, 24);
    }
}
