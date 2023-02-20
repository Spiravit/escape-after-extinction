package group7.entities;

import group7.utils.AssetLoader;
import static group7.utils.AssetLoader.*;
import group7.utils.Direction;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends Animate {


    private int health = 100;
    private int stamina = 100;

    private BufferedImage[][] playerAnimations;
    private boolean isMoving=false;
    private boolean isIdle = true;

    private boolean movingUp,movingDown,movingLeft,movingRight = false;

    

    public Player(double positionX, double positionY, double height, double width) {
        super(positionX, positionY, height, width,PLAYER_IDLEACTION);
        loadAnimations();
    }
    @Override
    void loadAnimations() {
        BufferedImage dinosaurMoving = AssetLoader.getSpriteAtlas(PLAYERMOVING);
        BufferedImage dinosaurIdle = AssetLoader.getSpriteAtlas(PLAYERIDLE);
        playerAnimations = new BufferedImage[2][6]; // 2 x 6 2D array
        for (int i=0;i<=5;i++){
            // loading moving animations into 2D array
            playerAnimations[0][i]=dinosaurMoving.getSubimage(i*24,0,24,24);
        }
        for (int j=0;j<=2;j++){
            // loading idle animations into 2D array
           playerAnimations[1][j]=dinosaurIdle.getSubimage(j*24,0,24,24);
        }
    }
    public void update() {
        updatePlayer();
        updateAnimationTick();
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
            aniIndex=0;
            aniTick=0;
        }
    }
    @Override
    public void renderEntity(Graphics g){
        System.out.println(currentAnimateAction);
        g.drawImage(playerAnimations[currentAnimateAction][aniIndex],(int)positionX,(int)positionY,(int) width,(int) height,null);
    }

    public void updatePlayer(){
        if (currentAnimateAction==PLAYER_IDLEACTION){
            return; // no need to update position when player is in idle condition
        }
        if(this.movingUp){
            positionY -= animatedEntitySpeed;
        }
        else if(this.movingDown){
            positionY += animatedEntitySpeed;
        }
        else if(this.movingLeft){
            positionX -= animatedEntitySpeed;
        }
        else if(this.movingRight){
            positionX += animatedEntitySpeed;
        }
        System.out.println(positionX);
    }

    public void setDirection(Direction direction){
        isMoving=true;
        isIdle=false;
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

    public void removeDirection(Direction direction){
        isMoving=false;
        isIdle=true;
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
}
