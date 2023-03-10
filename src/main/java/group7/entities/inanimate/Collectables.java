package group7.entities.inanimate;

import group7.levels.Pathfinding;

public abstract class Collectables extends Inanimate {
    protected Pathfinding pathfinding;

    protected int objectType;

    protected boolean active = true; // If object hasn't been picked up TRUE, else FALSE 

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

    protected void updateAnimation() {
        // implement if you animate the potion/keys/eggs
    }
    
    public void setActive( boolean active ) {
        this.active = active;
    }

    public boolean getActive() {
        return active;
    }
    
    public int getObjectType() {
        return objectType;
    }
}
