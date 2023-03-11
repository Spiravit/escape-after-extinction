package group7.entities.animate;

import group7.entities.Entity;
import group7.levels.*;
import group7.utils.AssetLoader;
import java.awt.image.BufferedImage;

public class Player extends Animate {
    private int health = 100;
    private int stamina = 100;
    private int dinoNumber; // the number of the dinosaur sprite to use

    private int eggsCollected = 0;
    private int keysCollected = 0;

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
        loadAnimations();
    }

    @Override
    protected void loadAnimations() {
        BufferedImage dinosaur = AssetLoader.getSpriteAtlas("playerSprites/dino_"+ dinoNumber +".png");
        
        // place idle animations into 2d array
        entityAnimations[DEFAULT_ANIMATION] = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            entityAnimations[DEFAULT_ANIMATION][i] = dinosaur.getSubimage(i * 24 + 12 * 24, 0, 24, 24);
        }

        // place moving animations into 2d array
        entityAnimations[MOVING_ANIMATION] = new BufferedImage[6];
        for (int i = 0; i < 6; i++) {
            entityAnimations[MOVING_ANIMATION][i] = dinosaur.getSubimage(i * 24, 0, 24, 24);
        }
    }

    protected void updatePosition() {
        super.updatePosition();
        pathfinding.setPlayer((int) getPosX(), (int) getPosY());
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getKeysCollected() {
        return keysCollected;
    }

    public void incrementKeysCollected() {
        keysCollected ++;
    }
    
    public void onInteraction(Player player) {
        // no interaction
    }
}
