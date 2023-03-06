package group7.entities;

import group7.utils.AssetLoader;
import static group7.utils.AssetLoader.*;
import group7.utils.Direction;
import group7.Graphics.GraphicsGrid;
import group7.levels.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends Animate {
    private int health = 100;
    private int stamina = 100;
    private int DinoNumber ;
    /**
     * Create a new player
     * @param posX
     * the x position of the player
     * @param posY
     * the y position of the player
     * @param levelData
     * the levelData of the level the player is in
     */
    public Player(double posX, double posY, LevelData levelData, int DinoNumber) {
        super(posX, posY, levelData);
        this.DinoNumber = DinoNumber;
        loadAnimations();
    }

    public void update() {
        super.update();
        // move these functions to update() in Animate once Enemy sprites are added
        // change sprite of the entity in sprites of current actions
        updateAnimationTick();
        // check the action of entity, if the action was changed, then change currentAction
        setAnimation();
    }

   
    @Override
    void loadAnimations() {
        BufferedImage dinosaur = AssetLoader.getSpriteAtlas("playerSprites/dino_"+DinoNumber+".png");
        entityAnimations = new BufferedImage[2][];
        
        // place moving animations into 2d array
        entityAnimations[0] = new BufferedImage[6];
        for (int i = 0; i < 6; i++) {
            entityAnimations[0][i] = dinosaur.getSubimage(i * 24, 0, 24, 24);
        }
        
        // place idle animations into 2d array
        entityAnimations[1] = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            entityAnimations[1][i] = dinosaur.getSubimage(i * 24 + 12 * 24, 0, 24, 24);
        }
    }

   
    protected void updatePosition() {
        super.updatePosition();
        levelData.setPlayer((int) getPosX(), (int) getPosY());
    }

    @Override
    void setAnimation(){
        int prevAction = currentAction;
        if (this.isMoving()) {
            currentAction = MOVING_ACTION;
        }
        else if (!this.isMoving()) {
            currentAction = IDLE_ACTION;
        }
        if (prevAction != currentAction){
            // if the action of a player was changed, then
            // we need to reset the aniIndex and aniTick
            // in order to start from beginning of sprites for new action
            aniIndex = 0;
            aniTick = 0;
        }
    }

    void onInteraction(Entity entity) {
        if (entity instanceof Enemy) {
            health -= health; // end game instantly
        }
    }

    /**
     * Render the player
     * @param g
     * the graphics object to draw on
     */
    @Override
    public void render(Graphics g){
        g.setColor(Color.RED);
        // draw the player, with the current animation and sprite in the current positions
        GraphicsGrid.render(
            g,
            entityAnimations[currentAction][aniIndex], 
            hitboxX, 
            hitboxY,
            hitboxWidth,
            hitboxHeight
        );
        super.render(g);
    }
}
