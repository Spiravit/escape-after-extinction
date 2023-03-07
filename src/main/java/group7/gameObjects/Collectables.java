package group7.gameObjects;

//import java.awt.geom.Rectangle2D;

import group7.Graphics.GraphicsGrid;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;


public class Collectables {
    GraphicsGrid graphicsGrid;

    protected int xScale = GraphicsGrid.getScaleX();
    protected int yScale = GraphicsGrid.getScaleY();
    protected Rectangle hitBox;

    protected int x, y, objectType;
    //protected Rectangle2D.Float hitBox;
    protected boolean doAnimate;
    protected boolean active = true;
    protected int aniTick, aniIndex;
    protected int xDrawOffset, yDrawOffset;
    
    // Constructor
    public Collectables( int x, int y, int objectType ) {
        this.x = x;
        this.y = y;
        this.objectType = objectType;
        initHitbox();
    }
/* 
    protected void initHitbox( int width, int height ) {
        hitBox = new Rectangle2D.Float(x, y, , );
    } */
    protected void initHitbox() {
        hitBox = new Rectangle( x, y, 56, 56 );
    }

    // For Debugging Purpose
    protected void drawHitbox( Graphics g ) {
        g.setColor(Color.RED);
        g.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }

    protected void updateHitbox() {
        //System.out.println("updateHitbox x: " + posX + " y: " + posY);            // TEST
        hitBox.x = (int) ((x + 0.2) * xScale);
        hitBox.y = (int) ((y + 0.1) * yScale);
    }

    public void reset() {
        aniIndex = 0;
        aniTick = 0;
        active = true;
        doAnimate = true;
    }

    // Getters and Setters
    public void setActive( boolean active ) {
        this.active = active;
    }
    public boolean getActive() {
        return active;
    }
    public int getxDrawOffset() {
        return xDrawOffset;
    }
    public int getyDrawOffset() {
        return yDrawOffset;
    }
    public int getObjectType() {
        return objectType;
    }
    public Rectangle getHitbox() {
        return hitBox;
    }
    public int getAniIndex() {
        return aniIndex;
    }
}
