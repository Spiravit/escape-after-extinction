package group7.entities;

import java.awt.Rectangle;
import java.awt.Graphics;


public abstract class Entity {
    double posX;
    double posY;
    double height;
    double width;

    public Entity(double positionX, double positionY) {
        this.posX = positionX;
        this.posY = positionY;
    }

    /**
     * @return
     * returns the hitbox of the entity as a rectangle
     */
    public Rectangle getHitbox() {
        return new Rectangle((int)posX, (int)posY, (int)width, (int)height);
    }

    abstract void renderEntity(Graphics g);
}
