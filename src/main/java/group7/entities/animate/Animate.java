package group7.entities.animate;

import group7.Graphics.GraphicsGrid;
import group7.entities.Entity;
import group7.levels.Pathfinding;
import group7.helperClasses.Direction;
import java.awt.Graphics;
import java.awt.Color;

/**
 * The Animation class is an abstract class of all animated entities and inherits from the Entity class
 * it implements basic function such as entity movement and animation update, 
 * and provides auxiliary methods such as setting and removing movement direction 
 * and checking whether going a certain direction is feasible or not.
 * 
 * @author  Salman Ayaz
 * @author  Karmen Yung
 * @author  Mohammad Parsaei
 * @author  Chen Min
 * @version 1.0
 * @since 2023-03-13
 */
public abstract class Animate extends Entity {
    // a pathfinding object that checks whether a tile is accessible
    protected Pathfinding pathfinding;

    // these four Boolean variables indicate whether the object is moving in a particular direction
    protected boolean movingUp = false;
    protected boolean movingDown = false;
    protected boolean movingLeft = false;
    protected boolean movingRight = false;

    // Moving speed of entity to change position of entity on map
    protected float entitySpeed = 0.02f;

    /**
    * Constructor to initialize the properties of the animate instance, such as location and pathfinding
    * @param posX
    * the x coordinates of animate instance
    * @param posY
    * the y coordinates of animate instance
    * @param pathfinding
    * pathfinding object that helps animate find its way around the map
    */
    public Animate(double posX, double posY, Pathfinding pathfinding) {
        super(posX, posY);
        this.pathfinding = pathfinding;

        // change hitbox size to 0.8 to allow for movement
        setHitboxWidth(0.8, posX);
        setHitboxHeight(0.8, posY);
    }

    /**
     * Updates the entity, including position, hitbox, and animation.
     */
    public void update() {
        updatePosition();
        super.update();
    }

    /**
     * Updates the position of the entity based on the directions it is moving in.
     */
    protected void updatePosition() {
        // Exit if not moving in any direction
        if( !movingUp && !movingDown && !movingLeft && !movingRight ) {
            return;
        }
        
        // When moving up check both top left and right corners
        if(this.movingUp && checkDirection(Direction.UP)){
            hitboxY -= entitySpeed;
        }
        // When moving down check both bottom left and right corners
        if(this.movingDown && checkDirection(Direction.DOWN)){
            hitboxY += entitySpeed;
        }
        // When moving left check both top left and bottom left
        if(this.movingLeft && checkDirection(Direction.LEFT)){
            hitboxX -= entitySpeed;
            reverseImage = true;
        }
        // When moving right check both top left and bottom left
        if(this.movingRight && checkDirection(Direction.RIGHT)) {
            hitboxX += entitySpeed;
            reverseImage = false;
        }
    }



    /**
     * Updates the current animation on the entity based on its movement state and direction.
     * If the entity is currently moving and can move in a certain direction, the motion animation is played.
     * Previous animations are stored in a variable for comparison.
     */
    protected void updateAnimation() {
        // store the current animation
        int prevAnimation = currentAnimation;

        // determine which animation to play based on the moving state and direction of the target
        if (this.isMoving()){
            if (this.movingUp && checkDirection(Direction.UP)){
                currentAnimation = MOVING_ANIMATION;
            }
            else if (this.movingDown && checkDirection(Direction.DOWN)){
                currentAnimation = MOVING_ANIMATION;
            }
            else if (this.movingLeft && checkDirection(Direction.LEFT)){
                currentAnimation = MOVING_ANIMATION;
            }
            else if (this.movingRight && checkDirection(Direction.RIGHT)){
                currentAnimation = MOVING_ANIMATION;
            }
            else {
                currentAnimation = DEFAULT_ANIMATION;
            }
        }
        else if (!this.isMoving()) {
            currentAnimation = DEFAULT_ANIMATION;
        }
        if (prevAnimation != currentAnimation){
            // if the action of a player was changed, then
            // we need to reset the aniIndex and aniTick
            // in order to start from beginning of sprites for new action
            aniIndex = 0;
            aniTick = 0;
        }
    }

    /**
     * Checks if the directions the entity wants to move in is possible or not. 
     * @param direction
     * The direction the entity wants to move in.
     * @return 
     * If the direction an entity wants to go is possible or not. 
     */
    protected boolean checkDirection(Direction direction) {
        // floor in the isValidTile function insures the entity doesn't move into a negative position between 0 and -1
        switch(direction) {
            case UP:
                return pathfinding.isValidTile((int)Math.floor(hitboxX), (int)Math.floor(hitboxY - entitySpeed)) &&
                        pathfinding.isValidTile((int)Math.floor(hitboxX + hitboxWidth), (int)Math.floor(hitboxY - entitySpeed));
            case DOWN:
                return pathfinding.isValidTile((int)Math.floor(hitboxX), (int)Math.floor(hitboxY + hitboxHeight + entitySpeed)) &&
                        pathfinding.isValidTile((int)Math.floor(hitboxX + hitboxWidth), (int)Math.floor(hitboxY + hitboxHeight + entitySpeed));
            case LEFT:
                return pathfinding.isValidTile((int)Math.floor(hitboxX - entitySpeed), (int)Math.floor(hitboxY)) &&
                        pathfinding.isValidTile((int)Math.floor(hitboxX - entitySpeed), (int)Math.floor(hitboxY + hitboxHeight));
            case RIGHT:
                return pathfinding.isValidTile((int)Math.floor(hitboxX + hitboxWidth + entitySpeed), (int)Math.floor(hitboxY)) &&
                        pathfinding.isValidTile((int)Math.floor(hitboxX + hitboxWidth + entitySpeed), (int)Math.floor(hitboxY + hitboxHeight));
            default:
                return false;
        }
    }

    /**
     * Adds a direction to the list of directions the entity to start moving in.
     * @param direction
     */
    public void setDirection(Direction direction){
        switch(direction) {
            case UP:
                this.movingUp = true;
                break;
            case DOWN:
                this.movingDown = true;
                break;
            case LEFT:
                this.movingLeft = true;
                break;
            case RIGHT:
                this.movingRight = true;
                break;
            case NONE:
                this.movingUp = false;
                this.movingDown = false;
                this.movingLeft = false;
                this.movingRight = false;
                break;
        }
    }


    /**
     * Removes a direction from the list of directions the entity is moving in.
     * The direction to stop moving in.
     * @param direction
     */
    public void removeDirection(Direction direction){
        switch(direction) {
            case UP:
                this.movingUp = false;
                break;
            case DOWN:
                this.movingDown = false;
                break;
            case LEFT:
                this.movingLeft = false;
                break;
            case RIGHT:
                this.movingRight = false;
                break;
            case NONE:
                break;
        }
    }

    /**
     * Toggles a direction in the list of directions the entity is moving in.
     * @param direction
     */
    public void toggleDirection(Direction direction) {
        switch(direction) {
            case UP:
                this.movingUp = !this.movingUp;
                break;
            case DOWN:
                this.movingDown = !this.movingDown;
                break;
            case LEFT:
                this.movingLeft = !this.movingLeft;
                break;
            case RIGHT:
                this.movingRight = !this.movingRight;
                break;
            case NONE:
                break;
        }
    }

    /**
     * @return
     * Used to detect whether an object is moving.
     * Returns true if the entity is moving in any direction ( when pressing up, down, left or right ).
     * Return true when the UP and Down keys are pressed at the same time 
     * and the left or right buttons are pressed at the same time 
     * and the up or down keys are pressed but not at the same time.
     * Otherwise return false.
     */
    public boolean isMoving() {
        if (movingUp && movingDown) {
            if ((movingLeft || movingRight) && !(movingLeft && movingRight)) { // XOR
                return true;
            }
        } else if (movingLeft && movingRight) {
            if ((movingUp || movingDown) && !(movingUp && movingDown)) {
                return true;
            }
        } else if (movingUp || movingDown || movingLeft || movingRight) {
            return true;
        }
        return false;
    }



    public float getSpeed() {
        return entitySpeed;
    }
}