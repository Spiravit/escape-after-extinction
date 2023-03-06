package group7.entities;

import group7.levels.LevelData;
import group7.Graphics.GraphicsGrid;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.geom.Rectangle2D;


public abstract class Entity {
    double posX; // deprecated
    double posY; // deprecated

    GraphicsGrid graphicsGrid;

    BufferedImage currentSprite;
    
    // stores the position of the top left corner of the hitbox rectangle
    protected double hitboxX;
    protected double hitboxY;

    protected double hitboxWidth = 0.8;
    protected double hitboxHeight = 0.8;

    protected Rectangle hitBox; // not related to the hitboxX, hitboxY, hitboxWidth, hitboxHeight
    private int xScale = GraphicsGrid.getScaleX();
    private int yScale = GraphicsGrid.getScaleY();

    public Entity(double positionX, double positionY) {
        this.posX = positionX;
        this.posY = positionY;
        System.out.println("updateHitbox x: " + posX + " y: " + posY + " xscale: " + xScale + " yscale: " + yScale);
        initHitbox();

        hitboxX = positionX;
        hitboxY = positionY;
    }

    /**
     * deprecated
     * initializes the hitbox of the entity
     */
    private void initHitbox() {
        hitBox = new Rectangle( (int)(posX), (int)(posY), 56, 56 ); 
    }

    // For Debugging Purpose
    protected void drawHitbox( Graphics g ) {
        g.setColor(Color.BLUE);
        g.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }

    /**
     * deprecated
     * updates the hitbox of the entity with the current position
     */
    protected void updateHitbox() {
        //System.out.println("updateHitbox x: " + posX + " y: " + posY);            // TEST
        hitBox.x = (int) ((posX + 0.2) * xScale);
        hitBox.y = (int) ((posY + 0.1) * yScale);
    }

    /**
     * should be deprecated
     * @return
     * returns the hitbox of the entity as a rectangle
     */
    public Rectangle getHitbox() {
        //return new Rectangle((int)posX, (int)posY, 1, 1);
        return hitBox;
    }

    /**
     * @return
     * returns the x position where the middle of the hitbox lies
     */
    protected double getPosX() {
        return hitboxX + hitboxWidth / 2;
    }

    /**
     * @return
     * returns the y position where the middle of the hitbox lies
     */
    protected double getPosY() {
        return hitboxY + hitboxHeight / 2;
    }

    /**
     * 
     * @return
     * returns a Rectangle2D object of the hitbox
     */
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(hitboxX, hitboxY, hitboxWidth, hitboxHeight);
    }

    
    public void render(Graphics g) {
        g.setColor(Color.RED);
        GraphicsGrid.drawRect(g, hitboxX, hitboxY, hitboxWidth, hitboxHeight);
    }

    public void drawPositionDot(Graphics g) {
        g.setColor(Color.ORANGE);
        g.drawRect((int)(xScale * getPosX()), (int) (yScale * getPosY()), 2, 2);
    }

}
