package group7.entities.animate;

import group7.entities.Entity;
import group7.levels.*;
import group7.utils.AssetLoader;
import java.awt.image.BufferedImage;

public class Player extends Animate {
    private int health = 100;
    private int dinoNumber; // the number of the dinosaur sprite to use

    private int keysCollected = 0;
    private int eggsCollected = 0;

    private boolean canMove = false;

    /**
     * Create a new player
     * @param posX
     * the x position of the player
     * @param posY
     * the y position of the player
     * @param pathfinding
     * the pathfinding of the level the player is in
     */
    public Player(double posX, double posY, Pathfinding pathfinding, int dinoNumber) {
        super(posX, posY, pathfinding);
        this.dinoNumber = dinoNumber;
        currentAnimation = SPAWN_ANIMATION;
        loadAnimations();
    }

    @Override
    protected void updateAnimation() {
        if (!canMove) {
            if (aniIndex >= getSpriteAmount(currentAnimation) - 1) {
                currentAnimation = DEFAULT_ANIMATION;
                aniIndex = 0;
                canMove = true;
            }
        } else {
            super.updateAnimation();
        }
    }

    @Override
    protected void loadAnimations() {
        BufferedImage dinosaur = AssetLoader.getSpriteAtlas("playerSprites/dino_"+ dinoNumber +".png");
        
        int prevAnimations = 0;
        int curAnimation = 11;

        // spawn animation + idle animation to make the spawn animation a bit longer
        entityAnimations[SPAWN_ANIMATION] = new BufferedImage[curAnimation];
        for (int i = 0; i < curAnimation; i++) {
            entityAnimations[SPAWN_ANIMATION][i] = dinosaur.getSubimage(i * 24, 0, 24, 24);
        }

        prevAnimations += curAnimation - 3;
        curAnimation = 3;

        entityAnimations[DEFAULT_ANIMATION] = new BufferedImage[curAnimation];
        for (int i = 0; i < curAnimation; i++) {
            entityAnimations[DEFAULT_ANIMATION][i] = dinosaur.getSubimage(i * 24 + prevAnimations * 24, 0, 24, 24);
        }

        prevAnimations += curAnimation;
        curAnimation = 6;

        entityAnimations[MOVING_ANIMATION] = new BufferedImage[curAnimation];
        for (int i = 0; i < curAnimation; i++) {
            entityAnimations[MOVING_ANIMATION][i] = dinosaur.getSubimage(i * 24 + prevAnimations * 24, 0, 24, 24);
        }

        prevAnimations += curAnimation + 6;
        curAnimation = 4;

        entityAnimations[DAMAGE_TAKEN_ANIMATION] = new BufferedImage[curAnimation];
        for (int i = 0; i < curAnimation; i++) {
            entityAnimations[DAMAGE_TAKEN_ANIMATION][i] = dinosaur.getSubimage(i * 24 + prevAnimations * 24, 0, 24, 24);
        }

        prevAnimations += curAnimation;
        curAnimation = 5;

        entityAnimations[DEATH_ANIMATION] = new BufferedImage[curAnimation];
        for (int i = 0; i < curAnimation; i++) {
            entityAnimations[DEATH_ANIMATION][i] = dinosaur.getSubimage(i * 24 + prevAnimations * 24, 0, 24, 24);
        }
    }

    /**
     * Update the position of the player
     * and update the its position in the pathfinding class
     */
    @Override
    protected void updatePosition() {
        if (canMove) {
            super.updatePosition();
        }
        pathfinding.setPlayer((int) getPosX(), (int) getPosY());
    }

    /**
     * Get the health of the player
     * @return
     * the health of the player
     */
    public int getHealth() {
        return health;
    }

    
    /**
     * Take damage
     * @param damage
     * the amount of damage to take
     */
    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            setAnimation(DEATH_ANIMATION);
        } else {
            setAnimation(DAMAGE_TAKEN_ANIMATION);
        }
        canMove = false;
    }

    /**
     * Get the number of keys collected
     * @return
     * the number of keys collected
     */
    public int getKeysCollected() {
        return keysCollected;
    }

    /**
     * Increment the number of keys collected
     */
    public void incrementKeysCollected() {
        keysCollected ++;
    }

    /**
     * Get the number of eggs collected
     * @return
     * the number of eggs collected
     */
    public int getEggsCollected() {
        return eggsCollected;
    }

    /**
     * Increment the number of eggs collected
     */
    public void incrementEggsCollected() {
        eggsCollected ++;
    }

    public void onInteraction(Player player) {
        // no interaction
    }
}
