package group7.entities;

import java.awt.Rectangle;
import java.awt.Graphics;


public abstract class Entity {
    double posX;
    double posY;
    double height;
    double width;

    public Entity(double positionX, double positionY, double width, double height) {
        this.posX = positionX;
        this.posY = positionY;
        this.height = height;
        this.width = width;
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
