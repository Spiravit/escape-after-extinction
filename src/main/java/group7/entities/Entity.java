package group7.entities;

import java.awt.Rectangle;
import java.awt.Graphics;
import group7.Graphics.RenderGrid;
import java.awt.image.BufferedImage;


public abstract class Entity {
    double posX;
    double posY;
    RenderGrid renderGrid;

    BufferedImage currentSprite; 

    public Entity(double positionX, double positionY, RenderGrid render) {
        this.posX = positionX;
        this.posY = positionY;
        this.renderGrid = render;
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
