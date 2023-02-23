package group7.entities;

import java.awt.Rectangle;
import java.awt.Graphics;
import group7.Graphics.GraphicsGrid;
import java.awt.image.BufferedImage;


public abstract class Entity {
    double posX;
    double posY;
    GraphicsGrid graphicsGrid;

    BufferedImage currentSprite; 

    public Entity(double positionX, double positionY) {
        this.posX = positionX;
        this.posY = positionY;
    }

    /**
     * @return
     * returns the hitbox of the entity as a rectangle
     */
    public Rectangle getHitbox() {
        return new Rectangle((int)posX, (int)posY, 1, 1);
    }

    abstract void render(Graphics g);
}
