package group7.entities.animate;

import group7.levels.*;
import group7.entities.inanimate.Potion;
import group7.helperClasses.AssetLoader;
import java.awt.image.BufferedImage;


/**
 * The Player class extends abstract Animate class, which in turn extends the abstract Entity class.
 * The class creates an object of Player type, which is rendered on screen as a Dinosaur.
 * Player has properties: health, number of keys collected, and the number of dinosaur eggs collected.
 *
 * @author  Salman Ayaz
 * @author  Karmen Yung
 * @author  Mohammad Parsaei
 * @author  Chen Min
 * @version 1.0
 * @since 2023-03-13
 */
public class Player extends Animate {
    private int health = 100; // player health, default value is 100
    private int keysCollected = 0; // the number of keys collected
    private int eggsCollected = 0; // the number of dinosaur eggs collected

    private int dinoNumber; // the number of the dinosaur sprite to use
    private boolean canMove = false; // whether the player can mover

    /**
     * Constructor: Create a new player object.
     * @param posX (the x position of this player)
     * @param posY (the y position of this player)
     * @param pathfinding (the pathfinding of the level this player is in)
     */
    public Player(double posX, double posY, Pathfinding pathfinding, int dinoNumber) {
        super(posX, posY, pathfinding);
        this.dinoNumber = dinoNumber;
        currentAnimation = SPAWN_ANIMATION; // the default animation is "hatching"
        loadAnimations(); // load animation resources
        imageScaleX = 1.25; // X-axis scaling
        imageScaleY = 1.5; // Y-axis scaling
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
        String asset;
        switch (dinoNumber) {
        default:
        case 1:
            asset = AssetLoader.DINO_1;
            break;
        case 2:
            asset = AssetLoader.DINO_2;
            break;
        case 3:
            asset = AssetLoader.DINO_3;
            break;
        case 4:
            asset = AssetLoader.DINO_4;
            break;
        case 5:
            asset = AssetLoader.DINO_5;
            break;
        }
        BufferedImage dinosaur = AssetLoader.getSpriteAtlas(asset);
        
        int prevAnimations = 0;
        int curAnimation = 15;

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
     * @return The health of the player
     */
    public int getHealth() {
        return health;
    }

    
    /**
     * Decreases Health
     * @param damage The amount of damage taken
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
     * Increase Health
     * Players MAX health is 100. If player has full health do nothing, 
     * else if player has low health add the health gained from the potion. 
     * @param hp
     */
    public void gainHealth( int hp ) {
        if ( health != 0 && health < 100 && health + Potion.GREEN_POTION_BOOST_VALUE <= 100 ) {
            health += hp;
            //System.out.println("health boosted: " + health );               // ***TEST REMOVE***
        } else if ( health < 100 && health + Potion.GREEN_POTION_BOOST_VALUE > 100 ) {
            //System.out.println("boost to full health" + health);            // ***TEST REMOVE***
            health = 100;   // player restored to full health
        }
    }

    /**
     * Increase movement speed.
     * Players MAX speed is 0.04f. If player has full health do nothing, 
     * else if player has low health add the health gained from the potion.
     * @param speedBoost 
     */
    public void increaseSpeed( float speedBoost ) {
        if ( entitySpeed < 0.04f && entitySpeed + Potion.PURPLE_POTION_BOOST_VALUE <= 0.04f ) {
            entitySpeed += speedBoost;
        } 
    }

    /**
     * Get the number of keys collected.
     * @return The number of keys collected.
     */
    public int getKeysCollected() {
        return keysCollected;
    }

    /**
     * Increment the number of keys collected.
     */
    public void incrementKeysCollected() {
        keysCollected++;
    }

    /**
     * Get the number of eggs collected.
     * @return The number of eggs collected.
     */
    public int getEggsCollected() {
        return eggsCollected;
    }

    /**
     * Increment the number of eggs collected
     */
    public void incrementEggsCollected() {
        eggsCollected++;
    }

    public void onInteraction(Player player) {
        // No Interaction
    }
}
