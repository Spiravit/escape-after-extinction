package group7.entities.animate;

import group7.entities.Entity;
import group7.levels.Pathfinding;
import group7.utils.Direction;


public abstract class Animate extends Entity {
    protected Pathfinding pathfinding;

    protected boolean movingUp = false;
    protected boolean movingDown = false;
    protected boolean movingLeft = false;
    protected boolean movingRight = false;

    // Moving speed of entity to change position of entity on map
    protected float entitySpeed = 0.02f;

    public Animate(double posX, double posY, Pathfinding pathfinding) {
        super(posX, posY);
        this.pathfinding = pathfinding;

        // change hitbox size to 0.8 to allow for movement
        hitboxWidth = 0.8;
        hitboxHeight = 0.8;
    }

    /**
     * updates the entity, including position, hitbox, and animation
     */
    public void update() {
        updatePosition();
        super.update();
    }

    /**
     * updates the position of the entity based on the directions it is moving in
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
        }
        // When moving right check both top left and bottom left
        if(this.movingRight && checkDirection(Direction.RIGHT)) {
            hitboxX += entitySpeed;
        }
    }



    /**
    * updates the current animation on the entity based on its movement state and direction
    * if the entity is currently moving and can move in a certain direction, the motion animation is played.
    *previous animations are stored in a variable for comparison.
    **/
    protected void updateAnimation(){
        int prevAnimation = currentAnimation;
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
            case NONE:
                this.movingUp = false;
                this.movingDown = false;
                this.movingLeft = false;
                this.movingRight = false;
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
            case NONE:
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
            case NONE:
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
}
