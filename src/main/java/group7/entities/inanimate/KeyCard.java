package group7.entities.inanimate;

import group7.utils.AssetLoader;
import static group7.utils.AssetLoader.*;
import java.awt.image.BufferedImage;


public class KeyCard extends Collectables {
    
    // Constructor
    public KeyCard(int x, int y) {
        super(x, y);
    }

    @Override
    protected void loadAnimations() {
        BufferedImage key = AssetLoader.getSpriteAtlas( KEY_CARD );
        entityAnimations = new BufferedImage[1][];

        entityAnimations[0] = new BufferedImage[1];
        entityAnimations[0][0] = key.getSubimage(0, 0, 32, 24);
    }
}
