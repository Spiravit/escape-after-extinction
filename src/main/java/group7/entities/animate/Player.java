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
    private boolean canMove = false; // whether the player can move

    /**
     * Constructor: Create a new player object.
     * @param tileX (the x tile of this player)
     * @param tileY (the y tile of this player)
     * @param pathfinding (the pathfinding of the level this player is in)
     */
    public Player(double tileX, double tileY, Pathfinding pathfinding, int dinoNumber) {
        super(tileX, tileY, pathfinding);
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
        entityAnimations[SPAWN_ANIMATION] = extractSprite(dinosaur, 0, 0, 24, 24, curAnimation);

        // default animation
        prevAnimations += curAnimation - 3;
        curAnimation = 3;
        entityAnimations[DEFAULT_ANIMATION] = extractSprite(dinosaur, prevAnimations * 24, 0, 24, 24, curAnimation);
        
        // moving animation
        prevAnimations += curAnimation;
        curAnimation = 6;
        entityAnimations[MOVING_ANIMATION] = extractSprite(dinosaur, prevAnimations * 24, 0, 24, 24, curAnimation);

        prevAnimations += curAnimation + 6;
        curAnimation = 4;
        entityAnimations[DAMAGE_TAKEN_ANIMATION] = extractSprite(dinosaur, prevAnimations * 24, 0, 24, 24, curAnimation);

        prevAnimations += curAnimation;
        curAnimation = 5;
        entityAnimations[DEATH_ANIMATION] = extractSprite(dinosaur, prevAnimations * 24, 0, 24, 24, curAnimation);
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
     * the amount of health to be gained
     */
    public void gainHealth( int hp ) {
        if ( health != 0 && health < 100 && health + Potion.GREEN_POTION_BOOST_VALUE <= 100 ) {
            health += hp;
        } else if ( health < 100 && health + Potion.GREEN_POTION_BOOST_VALUE > 100 ) {
            health = 100;   // player restored to full health
        }
    }

    /**
     * Increase movement speed.
     * Players MAX speed is 0.04f. If player is at max speed do nothing, 
     * else if player has default speed 0.02f add the 0.01f speed increase from the potion.
     * @param speedBoost 
     * the amount of speed to be gained
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

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
}
