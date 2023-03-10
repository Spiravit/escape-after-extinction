package group7.entities.inanimate;

import java.awt.image.BufferedImage;

import group7.utils.AssetLoader;

public class Trap extends Collectable {

    public Trap(int positionX, int positionY) {
        super(positionX, positionY);
        loadAnimations();
    }

    @Override
    protected void loadAnimations() {
        BufferedImage trap = AssetLoader.getSpriteAtlas(AssetLoader.TRAP);

        entityAnimations[DEFAULT_ANIMATION] = new BufferedImage[1];
        for (int i = 0; i < 1; i++) {
            entityAnimations[DEFAULT_ANIMATION][i] = trap.getSubimage(i * 32, 0, 32, 32);
        }
    }
    
}
