package group7.entities;

import group7.utils.Direction;

public class Player extends Animate {
    private int health = 100;
    private int stamina = 100;

    private boolean movingUp = false;
    private boolean movingDown = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    

    public Player(double positionX, double positionY, double height, double width) {
        super(positionX, positionY, height, width);
    }

    public void updatePlayer(){
        if(this.movingUp){
            positionY -= 1;
        }
        if(this.movingDown){
            positionY += 1;
        }
        if(this.movingLeft){
            positionX -= 1;
        }
        if(this.movingRight){
            positionX += 1;
        }
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
