package group7.userInterface;

import group7.Game;
import group7.Graphics.GraphicsGrid;
import group7.gameStates.gameStates;
import group7.helperClasses.AssetLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This class is implemented to render
 *
 *
 */
public class UiButtons {

    //buttonGameStates: current stage of game will be changed to buttonGameStates when button is clicked
    // for instance a pause button will have PAUSE as its gameState
    private gameStates buttonGameStates;

    // An array of bufferedImage holding 2 sprites for a buttons
    // normal sprite which is a button is not clicked nor hovered
    // One sprite is for once a button is clicked
    private BufferedImage[] buttonSprites;

    //buttonCollision: The boundaries for a button
    private Rectangle buttonCollision;

    // If a button is pressed then IsMousePressedButton is set to true
    private boolean IsMousePressedButton;
    // If a button is hovered then IsMouseOverButton is set to true
    private boolean IsMouseOverButton;

    //buttonPosX : position X of where button is located
    private int buttonPosX;

    //buttonPosY : position Y of where button is located
    private int buttonPosY;

    public UiButtons(Game game, int buttonPositionX, int buttonPositionY, int row, gameStates buttonGameStates) {
        this.game=game;
        this.buttonGameStates = buttonGameStates;
        this.buttonPosX = buttonPositionX;
        this.buttonPosY = buttonPositionY;
        this.row = row;

        bounds = new Rectangle(buttonPositionX,buttonPositionY,2* GraphicsGrid.getScaleX(),GraphicsGrid.getScaleY());
        loadButtonsSprites();
    }


    /**
     *
     *
     */
    private void loadButtonsSprites() {
        // for each button there are two conditions, hovered and not hovered
        buttonSprites = new BufferedImage[2];

        // Loading mainMenuButtons.png, the image that has sprites of all buttons
        // Each row of mainMenuButtons.png is for a different button
        // but in each i-th row, there are two sprites for a same button
        // the left one is for when a button is in normal state (not clicked/hovered)
        // the right one is for when a button is clicked.
        BufferedImage temp = AssetLoader.getSpriteAtlas(AssetLoader.MAIN_MENU_BUTTONS);


        // Since mainMenuButtons.png has 2 buttons in each row
        // Hence width of a single button is half of width of mainMenuButtons.png
        int singleButtonSpriteHeight = temp.getHeight()/15;

        // Since mainMenuButtons.png has 2 buttons in each row
        // Hence width of a single button is half of width of mainMenuButtons.png
        int singleButtonSpriteWidth = temp.getWidth() / 2;

        //
        for (int i = 0; i < 2; i++)
            imgs[i] = temp.getSubimage(i * menuButtonSpriteWidth, row * menuButtonSpriteHeight, singleButtonSpriteWidth, singleButtonSpriteHeight);
    }

    /**
     * renders a button at (buttonPositionX , buttonPositionY) with width of 2 tile and height of 1 tile.
     *
     * @param g     g is a Graphics object passed as parameter to draw the button on game window.
     */
    public void render(Graphics g) {
        g.drawImage(imgs[index],buttonPositionX,buttonPositionY,2*GraphicsGrid.getScaleX(),GraphicsGrid.getScaleY(),null);
    }

}
