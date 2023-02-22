package group7.entities;

import group7.utils.AssetLoader;
import static group7.utils.AssetLoader.*;
import group7.utils.Direction;
import group7.levels.*;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends Animate {

    private int health = 100;
    private int stamina = 100;

    private boolean isMoving=false;
    private boolean isIdle = true;

    public Player(double positionX, double positionY, double height, double width, LevelData levelData) {
        super(positionX, positionY, height, width, PLAYER_IDLEACTION, levelData);
        loadAnimations();
    }

    @Override
    void loadAnimations() {
        BufferedImage dinosaur = AssetLoader.getSpriteAtlas(PINKPLAYER);
        animatedEntityAnimations = new BufferedImage[2][6]; // 2 x 6 2D array
        for (int i=0;i<=5;i++){
            // loading moving animations into 2D array
            animatedEntityAnimations[0][i]=dinosaur.getSubimage(i*24,0,24,24);
        }
        for (int j=0;j<=2;j++){
            // loading idle animations into 2D array
            animatedEntityAnimations[1][j]=dinosaur.getSubimage(j*24+13*24,0,24,24);
        }
    }

    public void update() {
        // update position of a player based on player current action
        updatePlayer();
        // change sprite of the player in sprites of current actions
        updateAnimationTick();
        // check the action of player, if the action was changed, then change currentAnimateAction
        setAnimation();
    }

    @Override
    void setAnimation(){
        int oldAnimateAction = currentAnimateAction;
        if (isMoving) {
            currentAnimateAction = PLAYER_MOVINGACTION;
        }
        else if (isIdle) {
            currentAnimateAction = PLAYER_IDLEACTION;
        }
        if (oldAnimateAction != currentAnimateAction){
            // if the action of a player was changed, then
            // we need to reset the aniIndex and aniTick
            // in order to start from beginning of sprites for new action
            aniIndex=0;
            aniTick=0;
        }
    }

    @Override
    public void renderEntity(Graphics g){
        // draw the player, with the current animation and sprite in the current positions
        g.drawImage(animatedEntityAnimations[currentAnimateAction][aniIndex],(int)positionX,(int)positionY,(int) width,(int) height,null);
    }

    public void updatePlayer(){
        if (currentAnimateAction==PLAYER_IDLEACTION){
            return; // no need to update position when player is in idle condition
        }
        super.updatePlayer();
    }

    public void setDirection(Direction direction){
        // if one of direction is set to true
        // then player is moving, hence set isMoving to true
        // and set isIdle to false, in order to change currentAnimateAction later
        isMoving=true;
        isIdle=false;
        super.setDirection(direction);
    }

    public void removeDirection(Direction direction){
        isMoving=false;
        isIdle=true;
        
        super.removeDirection(direction);
    }
}
