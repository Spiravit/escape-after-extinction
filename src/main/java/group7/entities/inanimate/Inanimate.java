package group7.entities.inanimate;

import group7.entities.Entity;
import group7.entities.animate.Player;

import java.awt.geom.Rectangle2D;
import java.awt.Graphics;

/** 
* Inanimate defines all immobile objects in game, that the Player can walkover and pickup, 
* and their shared methods. These game objects affect the Players health, speed, final score, 
* and abiltiy to complete the level.
*
* @author  Mohammad Parsaei
* @author  Salman Ayaz
* @author  Karmen Yung
* @author  Chen Min
* @version 1.0
* @since 2023-03-13
*/
public abstract class Inanimate extends Entity {

    protected int objectType;

    // seperate visibility and interactability variables for objects that can be 
    // continued to be seen after being interacted with
    // an example of this is the trap, which shows an iteraction animation
    protected boolean visible = true; // If object can still be seen TRUE, else FALSE
    protected boolean interactable = true; // If object can be picked up TRUE, else FALSE
    
    /**
     * Constructor: Passes position information onto super constructor in {@link Entity}
     * @param posX (the x position of the player)
     * @param posY (the y position of the player)
     */
    public Inanimate(double posX, double posY) {
        super(posX, posY);
    }

    @Override
    protected void updateAnimation() {
        // Do NOTHING: implement if you animate the potion/keys/eggs
    }
    
    /**
     * Returns the kind of Inanimate object: key, egg, potion, or traps.
     * @return Type of Inanimate Object
     */
    public int getObjectType() {
        return objectType;
    }

    /**
     * Creates a box around the object, used to determine if there are interactions with the object.
     * @return hitbox
     */
    public Rectangle2D getHitbox() {
        if (interactable) {
            return super.getHitbox();
        } else {
            return new Rectangle2D.Double(0, 0, 0, 0);
        }
    }

    /**
     * Removes the graphic of the inatimate object from screen, after the player interacts with it.
     */
    protected void removeCollectable() {
        interactable = false;
        visible = false;
    }

    @Override
    public void onInteraction(Player player) {
        interactable = false;
        visible = false;
    }

    /**
     * Renders the graphics of the Inanimate object in the game screen,
     * depending on if it's supposed to be visibile or not.
     */
    public void render(Graphics g) {
        if (visible) {
            super.render(g);
        }
    }

    /**
     * removes the ability for the player to interact with the object
     */
    public boolean isInteractable() {
        return interactable;
    }

    /**
     * removes the ability for the player to see the object
     */
    public boolean isVisible() {
        return visible;
    }
}
