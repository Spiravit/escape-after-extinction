package group7.entities;

public abstract class Animate extends Entity {
    // Player Actions
    protected final static int PLAYER_IDLEACTION = 1;
    protected final static int PLAYER_MOVINGACTION =0;

    protected int currentAnimateAction ;

    protected int aniTick, aniIndex, aniSpeed = 15;

    protected float animatedEntitySpeed = 4.0f;
    public Animate(double positionX, double positionY, double height, double width,int currentAnimateAction) {
        super(positionX, positionY, height, width);
        this.currentAnimateAction=currentAnimateAction;
    }
    abstract void loadAnimations();

    abstract void setAnimation();

    protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(currentAnimateAction)) {
                aniIndex = 0;
            }
        }
    }
    public int GetSpriteAmount(int animatedEntityAction){
        if (animatedEntityAction==PLAYER_IDLEACTION){
            return 3;
        }
        else if (animatedEntityAction==PLAYER_MOVINGACTION){
            return 6;
        }
        return 0;
    }

}
