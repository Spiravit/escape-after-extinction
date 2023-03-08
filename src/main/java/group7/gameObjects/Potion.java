package group7.gameObjects;

public class Potion extends Collectables {

    public static final int POTION_WIDTH_DEFAULT = 16;
	public static final int POTION_HEIGHT_DEFAULT = 16;
	public static final int POTION_WIDTH = (int) ( xScale * POTION_WIDTH_DEFAULT);
	public static final int POTION_HEIGHT = (int) ( yScale * POTION_HEIGHT_DEFAULT);
    
    public static final int GREEN_POTION_BOOST_VALUE = 15; // TODO: change value if needed
	public static final int PURPLE_POTION_BOOST_VALUE = 10; // TODO: change value

    public Potion(int positionX, int positionY, int objectType) {
        super(positionX, positionY, objectType);
        // doAnimation = true;
        // initHitbox(); // move hitbox here from collectables
    }
/* 
    public void update() {
        updateAnimationTick();
    }
*/

}
