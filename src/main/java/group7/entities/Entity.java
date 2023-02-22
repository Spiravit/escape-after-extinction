package group7.entities;

import java.awt.Rectangle;
import java.awt.Graphics;
import group7.Graphics.Render;


public abstract class Entity {
    double posX;
    double posY;
    Render render;

    public Entity(double positionX, double positionY, Render render) {
        this.posX = positionX;
        this.posY = positionY;
        this.render = render;
    }

    /**
     * @return
     * returns the hitbox of the entity as a rectangle
     */
    public Rectangle getHitbox() {
        return new Rectangle((int)posX, (int)posY, 1, 1);
    }

    abstract void renderEntity(Graphics g);
}
