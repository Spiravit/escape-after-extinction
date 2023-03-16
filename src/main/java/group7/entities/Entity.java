package group7.entities;

import group7.Graphics.GraphicsGrid;
import group7.entities.animate.Player;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.geom.Rectangle2D;

/** 
* The class Entity encompasses all Animate and Inanimate Objects that can appear in game.
* Entity has properties: hitbox, scale, offset, and animation information. Used for rendering in game 
* objects in the correct location, setting an objects size and how an orject is animated. 
*
* @author  Mohammad Parsaei
* @author  Salman Ayaz
* @author  Karmen Yung
* @author  Chen Min
* @version 1.0
* @since 2023-03-13
*/
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

    // used to offset the image relative to the center of the hitbox
    protected double imageOffsetX = 0;
    protected double imageOffsetY = 0;

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

    /**
     * Constructor: Create the correct Entity at a given location.
     * @param posX (the x position of this entity)
     * @param posY (the y position of this entity)
     */
    public Entity(double posX, double posY) {
        setPosX(posX);
        setPosY(posY);

        entityAnimations = new BufferedImage[ANIMATION_COUNT][];
        loadAnimations();
    }

    /**
     * Returns the hitbox of the entity as a rectangle
     * @return hitbox
     */
    public Rectangle2D getHitbox() {
        return new Rectangle2D.Double(hitboxX, hitboxY, hitboxWidth, hitboxHeight);
    }

    /**
     * Returns the x position where the middle of where the hitbox lies
     * @return hitboxX of the middle of the hitbox
     */
    protected double getPosX() {
        return hitboxX + hitboxWidth / 2;
    }

    /**
     * Sets the x position of the entity to be the center of the given x tile
     * recall this method when hitboxX or hitboxWidth is changed to center the entity
     * @param x (the x position to set the entity to)
     */
    protected void setPosX(double x) {
        hitboxX = x + ((1 - hitboxWidth) / 2);
    }

    /**
     * Sets the y position of the entity to be the center of the given y tile
     * recall this method when hitboxY or hitboxHeight is changed to center the entity
     * @param y (the y position to set the entity to)
     */
    protected void setPosY(double y) {
        hitboxY = y + ((1 - hitboxHeight) / 2);
    }

    /**
     * Returns the y position where the middle of the hitbox lies
     * @return hitboxY of the middle of the hitbox
     */
    protected double getPosY() {
        return hitboxY + hitboxHeight / 2;
    }

    /**
     * Manages the animation of the entity,
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

    /**
     * Sets the current animation of the entity
     * @param animation (the type of animation to set)
     */
    protected void setAnimation(int animation) {
        if (!(currentAnimation == animation)) {
            currentAnimation = animation;
            aniIndex = 0;
            aniTick = 0;
        }
    }

    /**
     * Returns the amount of sprites/frames for a type of action.
     * @param entityAction (enum of entity action)
     * @return length of a type of action
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
     * What happens when the entity is interacted with
     * @param player
     */
    public abstract void onInteraction(Player player);

    /**
     * Update the animation sequence, virtually make an object look like it's moving.
     */
    public void update() {
        updateAnimationTick();
        updateAnimation();
    }

    /**
     * Draws the currentEntityImage on the screen
     * @param g (the graphics object to draw on)
     */
    public void render(Graphics g) {
        if (reverseImage) {
            GraphicsGrid.render(
                g,
                entityAnimations[currentAnimation][aniIndex],
                hitboxX - ((imageScaleX - 1) / 2) + imageOffsetX * imageScaleX + hitboxWidth * imageScaleX, // offset the image by the width of the hitbox 
                hitboxY - ((imageScaleY - 1) / 2) + imageOffsetY * imageScaleY,
                - (hitboxWidth * imageScaleX), 
                hitboxHeight * imageScaleY
            );
        } else {
            GraphicsGrid.render(
                g,
                entityAnimations[currentAnimation][aniIndex],
                hitboxX - ((imageScaleX - 1) / 2) + imageOffsetX * imageScaleX,
                hitboxY - ((imageScaleY - 1) / 2) + imageOffsetY * imageScaleY, 
                hitboxWidth * imageScaleX, 
                hitboxHeight * imageScaleY
            );
        }
        //debugRender(g);
    }

    /**
     * debugging purposes only
     * draws the hitbox of the entity, and a dot at the center of the entity
     * @param g
     * the graphics object to draw on
     */
    protected void debugRender(Graphics g) {
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
    protected void drawPositionDot(Graphics g) {
        g.setColor(Color.ORANGE);
        g.drawRect((int)(GraphicsGrid.getScaleX() * getPosX()), (int) (GraphicsGrid.getScaleY() * getPosY()), 2, 2);
    }
}
