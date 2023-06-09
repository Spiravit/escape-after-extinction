package group7.entities;

import group7.Graphics.GraphicsGrid;
import group7.entities.animate.Player;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
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
     * @param tileX (the x tile of this entity)
     * @param tileY (the y tile of this entity)
     */
    public Entity(double tileX, double tileY) {
        setTileX(tileX);
        setTileY(tileY);

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
     * Sets the x tile position of the entity which sets the postition of the entity to be the center of the given x tile
     * recall this method when hitboxX or hitboxWidth is changed to center the entity
     * @param x (the x tile position to set the entity to)
     */
    protected void setTileX(double x) {
        hitboxX = x + ((1 - hitboxWidth) / 2);
    }

    /**
     * Sets the y tile position of the entity which sets the postition of the entity to be the center of the given y tile
     * recall this method when hitboxY or hitboxHeight is changed to center the entity
     * @param y (the y tile position to set the entity to)
     */
    protected void setTileY(double y) {
        hitboxY = y + ((1 - hitboxHeight) / 2);
    }

    /**
     * Returns the y position where the middle of the hitbox lies
     * @return hitboxY of the middle of the hitbox
     */
    public double getPosY() {
        return hitboxY + hitboxHeight / 2;
    }

    /**
     * Returns the x position where the middle of where the hitbox lies
     * @return hitboxX of the middle of the hitbox
     */
    public double getPosX() {
        return hitboxX + hitboxWidth / 2;
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
     * Called when the entity is interacted with
     * @param player
     * the player that is interacting with the entity
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
     * returns an array of BufferedImages that are extracted from the given image
     * @param img
     * the image to extract the sprites from
     * @param x
     * the x position of the first sprite
     * @param y
     * the y position of the first sprite
     * @param width
     * the width of each sprite
     * @param height
     * the height of each sprite
     * @param numberOfSprites
     * the amount of sprites to extract
     * @return
     * an array of BufferedImages that are extracted from the given image
     */
    protected BufferedImage[] extractSprite(BufferedImage img, int x, int y, int width, int height, int numberOfSprites) {
        BufferedImage[] sprite = new BufferedImage[numberOfSprites];
        for (int i = 0; i < numberOfSprites; i++) {
            sprite[i] = img.getSubimage(x + i * width, y, width, height);
        }
        return sprite;
    }

    /**
     * Draws the currentEntityImage on the screen
     * @param g (the graphics object to draw on)
     */
    public void render(Graphics g) {
        // imageScaleX and imageScaleY are used to increase/decresae the size of the image
        // imageOffsetX and imageOffsetY are used to move the image horizontally and vertically
        if (reverseImage) {
            // if the image should be flipped horizontally
            GraphicsGrid.render(
                g,
                entityAnimations[currentAnimation][aniIndex],
                // hitboxWidth * imageScaleX recenters the image after reversing it ↓
                hitboxX - ((imageScaleX - 1) / 2) + imageOffsetX * imageScaleX + hitboxWidth * imageScaleX, 
                hitboxY - ((imageScaleY - 1) / 2) + imageOffsetY * imageScaleY,
                - (hitboxWidth * imageScaleX), // negative flips the image horizontally
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
    }

    /**
     * updates the hitbox height of the entity
     * @param hitboxHeight
     * the new height of the hitbox
     * @param tileY
     * the y tile to set the entity to
     */
    protected void setHitboxHeight(double hitboxHeight, double tileY){
        this.hitboxHeight = hitboxHeight;
        setTileY(tileY);
    }

    /**
     * updates the hitbox width of the entity
     * @param hitboxWidth
     * the new width of the hitbox
     * @param tileX
     * the x tile to set the entity to
     */
    protected void setHitboxWidth(double hitboxWidth, double tileX){
        this.hitboxWidth = hitboxWidth;
        setTileX(tileX);
    }

    /**
     * @return
     * returns the hitbox height of the entity
     */
    public double getHitboxHeight() {
        return hitboxHeight;
    } 

    /**
     * @return
     * returns the hitbox width of the entity
     */
    public double getHitboxWidth() {
        return hitboxWidth;
    }

}
