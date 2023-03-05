package group7.entities;

import group7.levels.LevelData;
import group7.Graphics.GraphicsGrid;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Color;


public abstract class Entity {
    double posX;
    double posY;
    GraphicsGrid graphicsGrid;

    BufferedImage currentSprite; 

    protected Rectangle hitBox;
    private int xScale = GraphicsGrid.getScaleX();
    private int yScale = GraphicsGrid.getScaleY();

    public Entity(double positionX, double positionY) {
        this.posX = positionX;
        this.posY = positionY;
        System.out.println("updateHitbox x: " + posX + " y: " + posY + " xscale: " + xScale + " yscale: " + yScale);
        initHitbox();
    }

    private void initHitbox() {
        hitBox = new Rectangle( (int)(posX), (int)(posY), 56, 56 );
    }

    // For Debugging Purpose
    protected void drawHitbox( Graphics g ) {
        g.setColor(Color.RED);
        g.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }

    protected void updateHitbox() {
        //System.out.println("updateHitbox x: " + posX + " y: " + posY);            // TEST
        hitBox.x = (int) ((posX + 0.2) * xScale);
        hitBox.y = (int) ((posY + 0.1) * yScale);
    }

    /**
     * @return
     * returns the hitbox of the entity as a rectangle
     */
    public Rectangle getHitbox() {
        //return new Rectangle((int)posX, (int)posY, 1, 1);
        return hitBox;
    }

    
    public void render(Graphics g) {
        g.setColor(Color.RED);
        GraphicsGrid.drawRect(g, posX, posY, 1, 1);
    }

}
