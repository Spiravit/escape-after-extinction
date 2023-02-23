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

    public Player(double posX, double posY, LevelData levelData) {
        super(posX, posY, levelData);
        loadAnimations();
    }

    @Override
    void loadAnimations() {
        BufferedImage dinosaur = AssetLoader.getSpriteAtlas(PINKPLAYER);
        entityAnimations = new BufferedImage[2][];
        
        // place moving animations into 2d array
        entityAnimations[0] = new BufferedImage[6];
        for (int i = 0; i < entityAnimations[0].length; i++) { 
            entityAnimations[0][i] = dinosaur.getSubimage(i * 24, 0, 24, 24);
        }
        
        // place idle animations into 2d array
        entityAnimations[1] = new BufferedImage[3];
        for (int i = 0; i < entityAnimations[1].length; i++) {
            entityAnimations[1][i] = dinosaur.getSubimage(i * 24 + 13 * 24, 0, 24, 24);
        }
    }

    public void update() {
        // update position of a player based on player current action
        updatePosition();
        // change sprite of the player in sprites of current actions
        updateAnimationTick();
        // check the action of player, if the action was changed, then change currentAction
        setAnimation();
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

    @Override
    public void render(Graphics g){
        // draw the player, with the current animation and sprite in the current positions
        GraphicsGrid.render(
            g,
            entityAnimations[currentAction][aniIndex], 
            (posX - 0.5), // slight offset to make the player look more centered on the tile
            (posY - 0.7), // TODO: change this to be more accurate and maybe place it somewhere else
            1,
            1
        );
    }
}
