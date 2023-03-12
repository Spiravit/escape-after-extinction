package group7.entities;

import group7.levels.Pathfinding;
import group7.Graphics.GraphicsGrid;
import group7.entities.animate.Player;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.geom.Rectangle2D;


public abstract class Entity {
    // stores the position of the top left corner of the hitbox rectangle
    protected double hitboxX;
    protected double hitboxY;

    protected double hitboxWidth = 1;
    protected double hitboxHeight = 1;

    // entity sprite information
    protected BufferedImage[][] entityAnimations; // all animations of the entity
    protected int currentAnimation = DEFAULT_ANIMATION; // current animation to be rendered (first dimension of entityAnimations)
    
    // used to scale the image size relative to the hitbox
    protected double imageScaleX = 1;
    protected double imageScaleY = 1;

    protected boolean reverseImage = false; // if the image should be flipped horizontally

    // animation information
    protected int aniIndex = 0; // current index of the animation (second dimension of entityAnimations)
    protected int aniSpeed = 12; // how fast the animation changes
    protected int aniTick = 15; // how long the current animation has been playing

    // entity animation options
    protected final static int DEFAULT_ANIMATION = 0;
    protected final static int MOVING_ANIMATION = 1;
    protected final static int INTERACTION_ANIMATION = 2;
    protected final static int SPAWN_ANIMATION = 3;
    protected final static int DEATH_ANIMATION = 4;
    protected final static int DAMAGE_TAKEN_ANIMATION = 5;
    protected final static int SPECIAL_IDLE_ANIMATION = 6;
    protected final static int TRACKING_PLAYER_ANIMATION = 7;
    // stores the amount of possible animations, update this if you add more animations
    protected final static int ANIMATION_COUNT = 8;

    public Entity(double posX, double posY) {
        hitboxX = posX;
        hitboxY = posY;

        entityAnimations = new BufferedImage[ANIMATION_COUNT][];
    }

    /**
     * @return
     * returns the hitbox of the entity as a rectangle
     */
    public Rectangle2D getHitbox() {
        return new Rectangle2D.Double(hitboxX, hitboxY, hitboxWidth, hitboxHeight);
    }

    /**
     * @return
     * returns the x position where the middle of the hitbox lies
     */
    protected double getPosX() {
        return hitboxX + hitboxWidth / 2;
    }

    /**
     * @return
     * returns the y position where the middle of the hitbox lies
     */
    protected double getPosY() {
        return hitboxY + hitboxHeight / 2;
    }

    /**
     * manages the animation of the entity
     * including changing the sprite of the entity
     * and how fast the sprite changes
     */
    protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex ++;
            if (aniIndex >= getSpriteAmount(currentAnimation)) {
                // If aniIndex went out of range, then make it 0.
                aniIndex = 0;
            }
        }
    }

    protected void setAnimation(int animation) {
        if (!(currentAnimation == animation)) {
            currentAnimation = animation;
            aniIndex = 0;
            aniTick = 0;
        }
    }

    /**
     * @param entityAction
     * enum of entity action
     * @return
     * amount of sprites for the action
     */
    protected int getSpriteAmount(int entityAction){
        return entityAnimations[entityAction].length;
    }

    /**
     * loads the animations of the entity 
     * and puts them into the entityAnimations array
     */
    protected abstract void loadAnimations();

    /**
     * updates the current animation of the entity
     */
    protected abstract void updateAnimation();

    /**
     * what happens when the entity is interacted with
     * @param player
     * the player that is interacting with the entity
     */
    public abstract void onInteraction(Player player);

    public void update() {
        updateAnimationTick();
        updateAnimation();
    }

    /**
     * draws the currentEntityImage on the screen
     * @param g
     * the graphics object to draw on
     */
    public void render(Graphics g) {
        if (reverseImage) {
            GraphicsGrid.render(
                g,
                entityAnimations[currentAnimation][aniIndex],
                hitboxX + hitboxWidth * imageScaleX, // offset the image by the width of the hitbox 
                hitboxY, 
                -(hitboxWidth * imageScaleX), 
                hitboxHeight * imageScaleY
            );
        } else {
            GraphicsGrid.render(
                g,
                entityAnimations[currentAnimation][aniIndex],
                hitboxX, 
                hitboxY, 
                hitboxWidth * imageScaleX, 
                hitboxHeight * imageScaleY
            );
        }
        // TODO: debugging purposes only, remove later
        g.setColor(Color.RED);
        GraphicsGrid.drawRect(g, hitboxX, hitboxY, hitboxWidth, hitboxHeight); 
        drawPositionDot(g);
    }

    /**
     * debugging purposes only
     * draws a dot at getPosX() and getPosY() of the entity
     * @param g
     * the graphics object to draw on
     */
    public void drawPositionDot(Graphics g) {
        g.setColor(Color.ORANGE);
        g.drawRect((int)(GraphicsGrid.getScaleX() * getPosX()), (int) (GraphicsGrid.getScaleY() * getPosY()), 2, 2);
    }
}
