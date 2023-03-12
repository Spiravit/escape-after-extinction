package group7.entities.inanimate;

import java.awt.image.BufferedImage;

import group7.entities.animate.Player;
import group7.utils.AssetLoader;

public class Trap extends Collectable {
    private int damage = 50;

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

        entityAnimations[INTERACTION_ANIMATION] = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            entityAnimations[INTERACTION_ANIMATION][i] = trap.getSubimage((i + 1) * 32, 0, 32, 32);
        }
    }

    @Override
    protected void updateAnimation() {
        if (currentAnimation == INTERACTION_ANIMATION) {
            if (aniIndex >= getSpriteAmount(currentAnimation) - 1) {
                visible = false;
            }
        } else {
            super.updateAnimation();
        }
    }

    @Override
    public void onInteraction(Player player) {
        player.takeDamage(damage);
        super.onInteraction(player);
        visible = true;
        setAnimation(INTERACTION_ANIMATION);
    }
}
