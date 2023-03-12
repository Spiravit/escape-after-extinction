package group7.entities.animate;

import group7.Graphics.GraphicsGrid;
import group7.levels.Pathfinding;
import group7.utils.AssetLoader;
import group7.utils.Direction;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Scanner;

public class Enemy extends Animate {
    int directionUpdateInterval = 200;

    int detectionWidth = 5;
    int detectionHeight = 5;

    // prevents the enemy from refreshing the player death animation when the enemy continues to interact
    boolean interactable = true;

    boolean trackingPlayer = false;
    boolean specialIdleAnimation = false;

    public Enemy(double posX, double posY, Pathfinding pathfinding) {
        super(posX, posY, pathfinding);
        entitySpeed = (float)(0.75 * entitySpeed); // 0.75 the speed of regular animate
        loadAnimations();
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
        Direction playerDirection = pathfinding.findPlayer((int) getPosX(), (int) getPosY(), detectionWidth);
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
            if ((int)(Math.random() * 5) == 0) { // 20% chance of playing the special idle animation
                specialIdleAnimation = true;
            }
            break;
        }
    }

    public void loadAnimations() {
        BufferedImage scientist = AssetLoader.getSpriteAtlas(AssetLoader.SCIENTIST);
        int scientistNumber = 0;

        entityAnimations[DEFAULT_ANIMATION] = new BufferedImage[4];
        for (int i = 0; i < 4; i++) {
            entityAnimations[DEFAULT_ANIMATION][i] = scientist.getSubimage(i * 32, 0, 32, 32);
        }

        entityAnimations[MOVING_ANIMATION] = new BufferedImage[8];
        for (int i = 0; i < 8; i++) {
            entityAnimations[MOVING_ANIMATION][i] = scientist.getSubimage(i * 32, 32, 32, 32);
        }

        entityAnimations[SPECIAL_IDLE_ANIMATION] = new BufferedImage[16];
        for (int i = 0; i < 16; i++) {
            entityAnimations[SPECIAL_IDLE_ANIMATION][i] = scientist.getSubimage(i * 32, 2 * 32, 32, 32);
        }

        entityAnimations[TRACKING_PLAYER_ANIMATION] = new BufferedImage[8];
        for (int i = 0; i < 8; i++) {
            entityAnimations[TRACKING_PLAYER_ANIMATION][i] = scientist.getSubimage(i * 32, 3 * 32, 32, 32);
        }
    }

    protected void updateAnimation() {
        if (specialIdleAnimation) {
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

