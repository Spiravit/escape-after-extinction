package group7.entities.animate;

import group7.entities.Entity;
import group7.levels.*;
import group7.utils.AssetLoader;
import java.awt.image.BufferedImage;


public class Player extends Animate {
    private int health = 100;
    private int stamina = 100;
    private int dinoNumber; // the number of the dinosaur sprite to use

    /**
     * Create a new player
     * @param posX
     * the x position of the player
     * @param posY
     * the y position of the player
     * @param pathfinding
     * the pathfinding of the level the player is in
     */
    public Player(double posX, double posY, Pathfinding pathfinding, int dinoNumber) {
        super(posX, posY, pathfinding);
        this.dinoNumber = dinoNumber;
    }

   
    @Override
    protected void loadAnimations() {
        BufferedImage dinosaur = AssetLoader.getSpriteAtlas("playerSprites/dino_"+ 1 +".png");
        System.out.println("playerSprites/dino_"+ dinoNumber +".png done");
        entityAnimations = new BufferedImage[2][];
        
        // place idle animations into 2d array
        entityAnimations[0] = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            entityAnimations[0][i] = dinosaur.getSubimage(i * 24 + 12 * 24, 0, 24, 24);
        }

        // place moving animations into 2d array
        entityAnimations[1] = new BufferedImage[6];
        for (int i = 0; i < 6; i++) {
            entityAnimations[1][i] = dinosaur.getSubimage(i * 24, 0, 24, 24);
        }

        currentEntityImage = entityAnimations[0][0];
    }

   
    protected void updatePosition() {
        super.updatePosition();
        pathfinding.setPlayer((int) getPosX(), (int) getPosY());
    }

    void onInteraction(Entity entity) {
        if (entity instanceof Enemy) {
            health -= health; // end game instantly
        }
    }
}
