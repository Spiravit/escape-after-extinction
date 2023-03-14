package group7.entities.animate;

import group7.Graphics.GraphicsGrid;
import group7.levels.Pathfinding;
import group7.helperClasses.AssetLoader;
import group7.helperClasses.Direction;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * this class represents enemies in the game and extends from animate
 * enemies have their own way  of moving and their own behavior
 * when the player interacts with the enemy, the enemy clears the player's health
 */
public class Enemy extends Animate {
    int directionUpdateInterval = 200;

    int detectionRange = 3;

    // prevents the enemy from refreshing the player death animation when the enemy continues to interact
    boolean interactable = true;

    boolean trackingPlayer = false;
    boolean specialIdleAnimation = false;

    // the number sprite to use
    int enemyNumber;


    /**
     * constructor to initialize the properties of the enemy instance, such as location, pathfinding, and numbering
     * @param posX x coordinates of enemy instance
     * @param posY y coordinates of enemy instance
     * @param  pathfinding Objects that help enemy instances move
     * @param enemyNumber number of an enemy instance used to determine which character's animation is locded
     */
    public Enemy(double posX, double posY, Pathfinding pathfinding, int enemyNumber) {
        super(posX, posY, pathfinding);
        entitySpeed = (float)(0.75 * entitySpeed); // 0.75 the speed of regular animate
        this.enemyNumber = enemyNumber;
        loadAnimations();
        imageScaleX = 1.75;
        imageScaleY = 1.75;
        imageOffsetX = 0.05;
        imageOffsetY = -0.1;
    }

    /**
     * Update the direction the enemy is moving in, the position, and the animation
     */
    public void update() {
        updateDirection();
        super.update();
    }

    /**
     * Update the direction the enemy is moving in
     * If the player is in range, move towards the player
     * If the player is not in range, move randomly
     */
    public void updateDirection() {
        Direction playerDirection = pathfinding.findPlayer((int) getPosX(), (int) getPosY(), detectionRange);
        if (!(playerDirection == Direction.NONE)) {
            trackingPlayer = true;
            // remove all directions
            removeDirection(Direction.UP);
            removeDirection(Direction.DOWN);
            removeDirection(Direction.LEFT);
            removeDirection(Direction.RIGHT);

            // move to towards the center of the tile to prevent getting stuck
            if (getPosX() % 1 - (hitboxWidth / 2) > 0 && playerDirection != Direction.RIGHT) {
                setDirection(Direction.LEFT);
            } else if (getPosX() % 1 < (hitboxWidth / 2) && playerDirection != Direction.LEFT) {
                setDirection(Direction.RIGHT);
            }
            if (getPosY() % 1 - (hitboxHeight / 2) > 0 && playerDirection != Direction.DOWN) {
                setDirection(Direction.UP);
            } else if (getPosY() % 1 < (hitboxHeight / 2) && playerDirection != Direction.UP) {
                setDirection(Direction.DOWN);
            } 

            setDirection(playerDirection);
            return;
        }

        trackingPlayer = false;
        // if the player is not in range, move randomly
        if (directionUpdateInterval > 0 || specialIdleAnimation) {
            directionUpdateInterval--;
            return;
        }
        directionUpdateInterval = (int) (Math.random() * 100);
        switch ((int) (Math.random() * 5)) {
        case 0:
            toggleDirection(Direction.UP);
            break;
        case 1:
            toggleDirection(Direction.DOWN);
            break;
        case 2:
            toggleDirection(Direction.LEFT);
            break;
        case 3:
            toggleDirection(Direction.RIGHT);
            break;
        case 4: // stop moving
            removeDirection(Direction.UP);
            removeDirection(Direction.DOWN);
            removeDirection(Direction.LEFT);
            removeDirection(Direction.RIGHT);
            if ((int)(Math.random() * 2) == 0) { // 50% chance of playing the special idle animation
                specialIdleAnimation = true;
            }
            break;
        }
    }

    public void loadAnimations() {
        BufferedImage scientist = AssetLoader.getSpriteAtlas(AssetLoader.SCIENTIST);
        
        switch (enemyNumber) {
        case 1:
            entityAnimations[DEFAULT_ANIMATION] = new BufferedImage[4];
            for (int i = 0; i < 4; i++) {
                entityAnimations[DEFAULT_ANIMATION][i] = scientist.getSubimage(i * 32, 2, 32, 32);
            }

            entityAnimations[MOVING_ANIMATION] = new BufferedImage[8];
            for (int i = 0; i < 8; i++) {
                entityAnimations[MOVING_ANIMATION][i] = scientist.getSubimage(i * 32, 34, 32, 32);
            }

            entityAnimations[SPECIAL_IDLE_ANIMATION] = new BufferedImage[16];
            for (int i = 0; i < 16; i++) {
                entityAnimations[SPECIAL_IDLE_ANIMATION][i] = scientist.getSubimage(i * 32, 2 * 32, 32, 32);
            }

            entityAnimations[TRACKING_PLAYER_ANIMATION] = new BufferedImage[8];
            for (int i = 0; i < 8; i++) {
                entityAnimations[TRACKING_PLAYER_ANIMATION][i] = scientist.getSubimage(i * 32, 3 * 32, 32, 32);
            }
            break;
        case 2:
            entityAnimations[DEFAULT_ANIMATION] = new BufferedImage[4];
            for (int i = 0; i < 4; i++) {
                entityAnimations[DEFAULT_ANIMATION][i] = scientist.getSubimage(i * 32, 4 * 32, 32, 32);
            }

            entityAnimations[MOVING_ANIMATION] = new BufferedImage[8];
            for (int i = 0; i < 8; i++) {
                entityAnimations[MOVING_ANIMATION][i] = scientist.getSubimage(i * 32, 5 * 32, 32, 32);
            }

            entityAnimations[SPECIAL_IDLE_ANIMATION] = new BufferedImage[9];
            for (int i = 0; i < 9; i++) {
                entityAnimations[SPECIAL_IDLE_ANIMATION][i] = scientist.getSubimage(i * 32, 6 * 32, 32, 32);
            }

            entityAnimations[TRACKING_PLAYER_ANIMATION] = new BufferedImage[8];
            for (int i = 0; i < 8; i++) {
                entityAnimations[TRACKING_PLAYER_ANIMATION][i] = scientist.getSubimage(i * 32, 7 * 32, 32, 32);
            }
            break;
        case 3:
            entityAnimations[DEFAULT_ANIMATION] = new BufferedImage[4];
            for (int i = 0; i < 4; i++) {
                entityAnimations[DEFAULT_ANIMATION][i] = scientist.getSubimage(i * 32, 8 * 32, 32, 32);
            }

            entityAnimations[MOVING_ANIMATION] = new BufferedImage[8];
            for (int i = 0; i < 8; i++) {
                entityAnimations[MOVING_ANIMATION][i] = scientist.getSubimage(i * 32, 9 * 32, 32, 32);
            }

            entityAnimations[SPECIAL_IDLE_ANIMATION] = new BufferedImage[10];
            for (int i = 0; i < 10; i++) {
                entityAnimations[SPECIAL_IDLE_ANIMATION][i] = scientist.getSubimage(i * 32, 10 * 32, 32, 32);
            }

            entityAnimations[TRACKING_PLAYER_ANIMATION] = new BufferedImage[8];
            for (int i = 0; i < 8; i++) {
                entityAnimations[TRACKING_PLAYER_ANIMATION][i] = scientist.getSubimage(i * 32, 11 * 32, 32, 32);
            }
            break;
        case 4:
        default:
            entityAnimations[DEFAULT_ANIMATION] = new BufferedImage[4];
            for (int i = 0; i < 4; i++) {
                entityAnimations[DEFAULT_ANIMATION][i] = scientist.getSubimage(i * 32, 12 * 32, 32, 32);
            }

            entityAnimations[MOVING_ANIMATION] = new BufferedImage[8];
            for (int i = 0; i < 8; i++) {
                entityAnimations[MOVING_ANIMATION][i] = scientist.getSubimage(i * 32, 13 * 32, 32, 32);
            }

            entityAnimations[SPECIAL_IDLE_ANIMATION] = new BufferedImage[22];
            for (int i = 0; i < 22; i++) {
                entityAnimations[SPECIAL_IDLE_ANIMATION][i] = scientist.getSubimage(i * 32, 14 * 32, 32, 32);
            }

            entityAnimations[TRACKING_PLAYER_ANIMATION] = new BufferedImage[8];
            for (int i = 0; i < 8; i++) {
                entityAnimations[TRACKING_PLAYER_ANIMATION][i] = scientist.getSubimage(i * 32, 15 * 32, 32, 32);
            }
            break;
        }

            
    }

    /**
     * update the animation of the enemy instacne
     */
    protected void updateAnimation() {
        if (specialIdleAnimation && !trackingPlayer) {
            setAnimation(SPECIAL_IDLE_ANIMATION);
            if (aniIndex == getSpriteAmount(SPECIAL_IDLE_ANIMATION) - 1) {
                specialIdleAnimation = false;
            }
        } else
        if (trackingPlayer) {
            setAnimation(TRACKING_PLAYER_ANIMATION);
        } else {
            super.updateAnimation();
        }
    }

    /**
     * debugging only
     * Draw rectangles in the direction the enemy is moving in
     * @param g
     * the graphics object to draw to
     */
    private void drawMovementDirections(Graphics g) {
        // debugging the direction the enemy is moving in
        g.setColor(Color.ORANGE);
        if (movingDown) {
            GraphicsGrid.drawRect(g, getPosX(), getPosY() + 1, 0.1, 0.1);
        }
        if (movingLeft) {
            GraphicsGrid.drawRect(g, getPosX() - 1, getPosY(), 0.1, 0.1);
        }
        if (movingRight) {  
            GraphicsGrid.drawRect(g, getPosX() + 1, getPosY(), 0.1, 0.1);
        }
        if (movingUp) {
            GraphicsGrid.drawRect(g, getPosX(), getPosY() - 1, 0.1, 0.1);
        }
    }

    /**
     * ways to interact with the player
     * when an enemy instance interacts with the player, clears all of the player's health
     * @param player example of player
     * the player that is interacting with the entity
     */
    @Override
    public void onInteraction(Player player) {
        if (interactable) {
            player.takeDamage(player.getHealth()); // remove all health
            interactable = false;
        }
    }

    public void render(Graphics g) {
        super.render(g);
        drawMovementDirections(g);
    }
}

