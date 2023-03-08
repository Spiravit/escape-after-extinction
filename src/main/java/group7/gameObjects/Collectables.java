package group7.gameObjects;


import group7.Graphics.GraphicsGrid;
import group7.levels.Pathfinding;

import java.awt.Rectangle;
import java.awt.Graphics;


public abstract class Collectables extends Enti {
    protected Pathfinding pathfinding;

    protected static int xScale = GraphicsGrid.getScaleX();
    protected static int yScale = GraphicsGrid.getScaleY();
    protected Rectangle hitBox;

    protected java.awt.image.BufferedImage[][] entityAnimations;

    protected int x, y, objectType;
    //protected Rectangle2D.Float hitBox;
    protected boolean active = true; // If object hasn't been picked up TRUE, else FALSE 
    protected boolean doAnimate; // use if animating potions/eggs 
    protected int xDrawOffset, yDrawOffset;

    //aniIndex is used to iterate through entityAnimations to change sprites of a condition
    // ariSpeed is the speed of changing sprites in a condition
    protected int aniTick = 15; // use if animating potions/eggs
    protected int aniIndex = 0;
    protected int aniSpeed = 15;

    // TYPES OF COLLECTABLES
    public static final int ESCAPE_KEYCARD = 0;
    public static final int GREEN_HEALTH_POTION = 1;
	public static final int PURPLE_SPEED_POTION = 2;
    public static final int EGG_POINT_BONUS = 3;
    
    // Constructor
    public Collectables( int x, int y, int objectType, Pathfinding pathfinding ) {
        super(x, y);
        this.pathfinding = pathfinding;
        this.objectType = objectType;
    }

    /**
     * loads the animations of the entity 
     * and puts them into the entityAnimations array
     */
    abstract void loadAnimations();

    public void reset() {
        aniIndex = 0;     // implement if you animate the potion/keys/eggs
        aniTick = 0;    // implement if you animate the potion/keys/eggs
        active = true;
        doAnimate = true;     // implement if you animate the potion/keys/eggs
    }

    // implement if you animate the potion/keys/eggs    
    protected void updateAnimationTick() {
		aniTick++;
		if ( aniTick >= aniSpeed ) {
			aniTick = 0;
			aniIndex++;
			if ( aniIndex >= getObjectSpriteAmount(objectType) ) {
				aniIndex = 0;
			}
		}
	}

    public void render(Graphics g) {
        super.render(g);
    }

    // GETTERS and SETTERS
    // implement if you animate the potion/keys/eggs
    public static int getObjectSpriteAmount( int object_type ) {
        switch ( object_type ) {
        case GREEN_HEALTH_POTION:
        case PURPLE_SPEED_POTION:
            return 8;
        case EGG_POINT_BONUS:
            return 3; //TODO change value when sprite is aquired!!!
        }
        return 1;
    }
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
