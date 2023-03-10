package group7.entities.animate;

import group7.Graphics.GraphicsGrid;
import group7.levels.Pathfinding;
import group7.utils.AssetLoader;
import group7.utils.Direction;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Enemy extends Animate {
    int directionUpdateInterval = 200;

    int detectionWidth = 5;
    int detectionHeight = 5;

    public Enemy(double posX, double posY, Pathfinding pathfinding) {
        super(posX, posY, pathfinding);
    }

    /**
     * Update the direction the enemy is moving in, the position, and the animation
     */
    public void update() {
        updateDirection();
        super.update();
    }

    /**
     * Update the direction the enemy is moving in
     * If the player is in range, move towards the player
     * If the player is not in range, move randomly
     */
    public void updateDirection() {
        Direction playerDirection = pathfinding.findPlayer((int) getPosX(), (int) getPosY(), detectionWidth);
        if (!(playerDirection == Direction.NONE)) {
            // remove all directions
            removeDirection(Direction.UP);
            removeDirection(Direction.DOWN);
            removeDirection(Direction.LEFT);
            removeDirection(Direction.RIGHT);

            // move to towards the center of the tile to prevent getting stuck
            if (getPosX() % 1 - (hitboxWidth / 2) > 0 && playerDirection != Direction.RIGHT) {
                setDirection(Direction.LEFT);
            } else if (getPosX() % 1 < (hitboxWidth / 2) && playerDirection != Direction.LEFT) {
                setDirection(Direction.RIGHT);
            }
            if (getPosY() % 1 - (hitboxHeight / 2) > 0 && playerDirection != Direction.DOWN) {
                setDirection(Direction.UP);
            } else if (getPosY() % 1 < (hitboxHeight / 2) && playerDirection != Direction.UP) {
                setDirection(Direction.DOWN);
            } 

            setDirection(playerDirection);
            return;
        }

        // if the player is not in range, move randomly
        if (directionUpdateInterval > 0) {
            directionUpdateInterval--;
            return;
        }
        directionUpdateInterval = (int) (Math.random() * 100);
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
        BufferedImage dinosaur = AssetLoader.getSpriteAtlas("playerSprites/dino_"+ 1 +".png");
        System.out.println("playerSprites/dino_"+ 1 +".png done");
        entityAnimations = new BufferedImage[2][];
        
        // place idle animations into 2d array
        entityAnimations[0] = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            entityAnimations[0][i] = dinosaur.getSubimage(i * 24 + 12 * 24, 0, 24, 24);
        }

        // place moving animations into 2d array
        entityAnimations[1] = new BufferedImage[6];
        for (int i = 0; i < 6; i++) {
            entityAnimations[1][i] = dinosaur.getSubimage(i * 24, 0, 24, 24);
        }
    }

    /**
     * debugging only
     * Draw rectangles in the direction the enemy is moving in
     * @param g
     * the graphics object to draw to
     */
    private void drawMovementDirections(Graphics g) {
        // debugging the direction the enemy is moving in
        g.setColor(Color.ORANGE);
        if (movingDown) {
            GraphicsGrid.drawRect(g, getPosX(), getPosY() + 1, 0.1, 0.1);
        }
        if (movingLeft) {
            GraphicsGrid.drawRect(g, getPosX() - 1, getPosY(), 0.1, 0.1);
        }
        if (movingRight) {
            GraphicsGrid.drawRect(g, getPosX() + 1, getPosY(), 0.1, 0.1);
        }
        if (movingUp) {
            GraphicsGrid.drawRect(g, getPosX(), getPosY() - 1, 0.1, 0.1);
        }
    }

    public void render(Graphics g) {
        super.render(g);
        drawMovementDirections(g);
    }
}

