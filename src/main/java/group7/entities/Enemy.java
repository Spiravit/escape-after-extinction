package group7.entities;

import group7.levels.LevelData;
import group7.utils.Direction;

public class Enemy extends Animate {
    int directionUpdateInterval = 100;

    public Enemy(double posX, double posY, LevelData levelData) {
        super(posX, posY, levelData);
    }

    public void setAnimation() {

    }

    public void update() {
        updateDirection();
        super.update();
    }

    public void updateDirection() {
        if (directionUpdateInterval > 0) {
            directionUpdateInterval--;
            return;
        }
        directionUpdateInterval = 100;
        switch ((int) (Math.random() * 5)) {
            case 0:
                toggleDirection(Direction.UP);
                break;
            case 1:
                toggleDirection(Direction.DOWN);
                break;
            case 2:
                toggleDirection(Direction.LEFT);
                break;
            case 3:
                toggleDirection(Direction.RIGHT);
                break;
            case 4: // stop moving
                removeDirection(Direction.UP);
                removeDirection(Direction.DOWN);
                removeDirection(Direction.LEFT);
                removeDirection(Direction.RIGHT);
                break;
        }
    }

    public void loadAnimations() {

    }
}
