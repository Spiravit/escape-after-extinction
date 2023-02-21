package group7.entities;

import group7.levels.LevelData;
import group7.utils.Direction;

public abstract class Animate extends Entity {
    private LevelData levelData;

    private boolean movingUp = false;
    private boolean movingDown = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;


    public Animate(double positionX, double positionY, double height, double width, LevelData levelData) {
        super(positionX, positionY, height, width);
        this.levelData = levelData;
    }

    public void updatePlayer(){
        if(this.movingUp && levelData.canMove((int)positionX, (int)positionY-1)){
            positionY -= 1;
        }
        if(this.movingDown && levelData.canMove((int)positionX, (int)positionY+1)){
            positionY += 1;
        }
        if(this.movingLeft && levelData.canMove((int)positionX-1, (int)positionY)){
            positionX -= 1;
        }
        if(this.movingRight && levelData.canMove((int)positionX+1, (int)positionY)){
            positionX += 1;
        }
        System.out.println("Player position: " + positionX + ", " + positionY);
    }

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
}
