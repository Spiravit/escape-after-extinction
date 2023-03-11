package group7.entities.inanimate;

import group7.helperClasses.AssetLoader;
import static group7.helperClasses.AssetLoader.*;
import java.awt.image.BufferedImage;


public class Key extends Collectable {
    
    // Constructor
    public Key(int x, int y) {
        super(x, y);
        loadAnimations();
    }

    @Override
    protected void loadAnimations() {
        BufferedImage key = AssetLoader.getSpriteAtlas(KEY);

        entityAnimations[DEFAULT_ANIMATION] = new BufferedImage[12];
        for (int i = 0; i < 12; i++) {
            entityAnimations[DEFAULT_ANIMATION][i] = key.getSubimage(i * 32, 0, 32, 32);
        }
    }
}
