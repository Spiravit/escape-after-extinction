package group7.entities;

import java.awt.image.BufferedImage;

/**
 * will be added later
 */
public abstract class Animate extends Entity {
    // Constants for each Animated entities' actions

        // Player Actions constants:
        protected final static int PLAYER_IDLEACTION = 1;
        protected final static int PLAYER_MOVINGACTION =0;

    // the current condition of animated entity,
    // for instance if currentAnimateAction=0, then the entity is a player that is moving
    protected int currentAnimateAction ;

    // a 2D array, where each row of it is holding stripes of one entity's actions
    // for instance, the first row can be sprites of moving sprites
    protected BufferedImage[][] animatedEntityAnimations;

    //aniIndex is used to iterate through animatedEntityAnimations to change sprites of a condition
    // ariSpeed is the speed of changing sprites in a condition

    protected int aniTick, aniIndex, aniSpeed = 15;

    // Moving speed of entity to change position of entity on map
    protected float animatedEntitySpeed = 4.0f;

    public Animate(double positionX, double positionY, double height, double width,int currentAnimateAction) {
        super(positionX, positionY, height, width);
        this.currentAnimateAction=currentAnimateAction;
    }

    // this comment will be changed to javaDOC later !!!
    // This function is responsible for
    abstract void loadAnimations();

    // this comment will be changed to javaDOC later !!!
    // This function is responsible for
    abstract void setAnimation();

    // this comment will be changed to javaDOC later !!!
    // This function is responsible for changing sprites for an entity in their current action
    // it goes to next sprite of an action when aniTick > aniSpeed
    protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(currentAnimateAction)) {
                // If aniIndex went out of range, then make it 0.
                aniIndex = 0;
            }
        }
    }

    // this comment will be changed to javaDOC later !!!
    // This function is responsible for
    public int GetSpriteAmount(int animatedEntityAction){
        if (animatedEntityAction==PLAYER_IDLEACTION){
            // there are 3 sprites for player in idle condition
            return 3;
        }
        else if (animatedEntityAction==PLAYER_MOVINGACTION){
            // there are 6 sprites for player in moving condition
            return 6;
        }
        return 0;
    }

}
