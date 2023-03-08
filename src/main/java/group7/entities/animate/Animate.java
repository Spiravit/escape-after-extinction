package group7.entities.animate;

import group7.entities.Entity;
import group7.levels.Pathfinding;
import group7.utils.Direction;
import group7.Graphics.GraphicsGrid;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;

/**
 * will be added later
 */
public abstract class Animate extends Entity {
    protected Pathfinding pathfinding;

    protected boolean movingUp = false;
    protected boolean movingDown = false;
    protected boolean movingLeft = false;
    protected boolean movingRight = false;

    protected final static int IDLE_ACTION = 1;
    protected final static int MOVING_ACTION = 0;

    // the current condition of animated entity,
    // for instance if currentAction=0, then the entity is a player that is moving
    protected int currentAction;

    // a 2D array, where each row of it is holding stripes of one entity's actions
    // for instance, the first row can be sprites of moving sprites
    protected BufferedImage[][] entityAnimations;

    //aniIndex is used to iterate through entityAnimations to change sprites of a condition
    // ariSpeed is the speed of changing sprites in a condition
    protected int aniTick = 15;
    protected int aniIndex = 0;
    protected int aniSpeed = 15;

    // Moving speed of entity to change position of entity on map
    protected float entitySpeed = 0.02f;

    public Animate(double posX, double posY, Pathfinding pathfinding) {
        super(posX, posY);
        this.currentAction = IDLE_ACTION;
        this.pathfinding = pathfinding;
    }

    /**
     * loads the animations of the entity 
     * and puts them into the entityAnimations array
     */
    abstract void loadAnimations();

    /**
     * updates the current action of the entity
     */
    abstract void setAnimation();

    /**
     * updates the entity, including position, hitbox, and animation
     */
    public void update() {
        // update position of a entity based on player current action
        updatePosition();
        updateHitbox();
        
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
            if (aniIndex >= GetSpriteAmount(currentAction)) {
                // If aniIndex went out of range, then make it 0.
                aniIndex = 0;
            }
        }
    }

    /**
     * @param entityAction
     * enum of entity action
     * @return
     * amount of sprites for the action
     */
    public int GetSpriteAmount(int entityAction){
        return entityAnimations[entityAction].length;
    }

    /**
     * updates the position of the entity based on the directions it is moving in
     */
    protected void updatePosition() {
        // Exit if not moving in any direction
        if( !movingUp && !movingDown && !movingLeft && !movingRight ) {
            return;
        }
        
        // floor in the canMove function insures the entity doesn't move into a negative position between 0 and -1
        // When moving up check both top left and right corners
        if(this.movingUp && pathfinding.canMove((int)Math.floor(hitboxX), (int)Math.floor(hitboxY - entitySpeed))){
            if ((pathfinding.canMove((int)Math.floor(hitboxX + hitboxWidth), (int)Math.floor(hitboxY - entitySpeed)))){
                posY -= entitySpeed;
                hitboxY -= entitySpeed;
            }
        }
        // When moving down check both bottom left and right corners
        if(this.movingDown && pathfinding.canMove((int)Math.floor(hitboxX), (int)Math.floor(hitboxY + hitboxHeight + entitySpeed))){
            if ((pathfinding.canMove((int)Math.floor(hitboxX + hitboxWidth), (int)Math.floor(hitboxY + hitboxHeight + entitySpeed)))){
                posY += entitySpeed;
                hitboxY += entitySpeed;
            }
        }
        // When moving left check both top left and bottom left
        if(this.movingLeft && pathfinding.canMove((int)Math.floor(hitboxX - entitySpeed), (int)Math.floor(hitboxY))){
            if ((pathfinding.canMove((int)Math.floor(hitboxX - entitySpeed), (int)Math.floor(hitboxY + hitboxHeight)))){
                posX -= entitySpeed;
                hitboxX -= entitySpeed;
            }
        }
        // When moving right check both top left and bottom left
        if(this.movingRight && pathfinding.canMove((int)Math.floor(hitboxX + hitboxWidth + entitySpeed), (int)Math.floor(hitboxY))){
            if ( (pathfinding.canMove((int)Math.floor(hitboxX + hitboxWidth + entitySpeed), (int)Math.floor(hitboxY + hitboxHeight)))){
                posX += entitySpeed;
                hitboxX += entitySpeed;
            }
        }
    } 


    /**
     * adds a direction to the list of directions the entity is moving in
     * @param direction
     * the direction to start moving in
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
        }
    }


    /**
     * removes a direction from the list of directions the entity is moving in
     * @param direction
     * the direction to stop moving in
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
        }
    }

    /**
     * toggles a direction in the list of directions the entity is moving in
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
        }
    }

    /**
     * @return
     * true if the entity is moving in any direction
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

    public void render(Graphics g) {
        drawPositionDot(g);
        super.render(g);
    }

}
