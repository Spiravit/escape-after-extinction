package group7.userInterface;

import group7.Game;
import group7.Graphics.GraphicsGrid;
import group7.gameStates.gameStates;
import group7.helperClasses.AssetLoader;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * This class is implemented to render
 *
 *
 */
public class UiButtons {

    //  Button's width is 2 tiles:
    private int buttonWidth = 2* GraphicsGrid.getScaleX();
    //  Button's height is 1 tile:
    private int buttonHeight = GraphicsGrid.getScaleY();


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

    //buttonSpriteRowNumber : The row of mainMenuButtons.png where sprites for the button is located
    private int buttonSpriteRowNumber;

    // game: In order to change the stage of game to buttonGameStates
    private Game game;

    /**
     *
     * @param game
     * @param buttonPositionX
     * @param buttonPositionY
     * @param buttonSpriteRowNumber
     * @param buttonGameStates
     */
    public UiButtons(Game game, int buttonPositionX, int buttonPositionY, int buttonSpriteRowNumber, gameStates buttonGameStates) {
        this.game=game;
        this.buttonGameStates = buttonGameStates;
        this.buttonPosX = buttonPositionX;
        this.buttonPosY = buttonPositionY;
        this.buttonSpriteRowNumber = buttonSpriteRowNumber;
        buttonCollision = new Rectangle(buttonPositionX,buttonPositionY,buttonWidth,buttonHeight);
        loadButtonsSprites();
    }

    // ********** Getters ****************
    /**
     *  gets value of IsMouseOverButton and returns it.
     *
     * @return it returns value of IsMouseOverButton
     */
    public boolean getIsMouseOver() {

        return IsMouseOverButton;
    }
    /**
     *  gets value of IsMousePressedButton and returns it.
     *
     * @return it returns value of IsMouseOverButton
     */
    public boolean getIsMousePressed() {
        return IsMousePressedButton;
    }


    // **********Setters****************

    /**
     *  sets boolean value of IsMouseOverButton to mouseOverButton( parameter).
     *
     * @param mouseOverButton     is a boolean passed as parameter in order to
     *                            change value of IsMouseOverButton.
     */
    public void setIsMouseOverButton(boolean mouseOverButton) {
        this.IsMouseOverButton = mouseOverButton;
    }


    /**
     *  sets boolean value of IsMousePressedButton to mousePressedButton( parameter).
     *
     * @param mousePressedButton    is a boolean passed as parameter in order to
     *                              change value of IsMousePressedButton.
     */
    public void setIsMousePressedButton(boolean mousePressedButton) {
        this.IsMousePressedButton = mousePressedButton;
    }



    /**
     * Loads the ButtonSprite array with the two sprites of a button. The first sprite is
     * the default sprite for a button, the second sprite added to array is the sprite
     * used for when the button is hovered or clicked.
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

        // Loading the buttonSprites array with the two sprite of the button
        // located at buttonSpriteRowNumber -th row of mainMenuButtons.png
        for (int i = 0; i < 2; i++)
            buttonSprites[i] = temp.getSubimage(i * singleButtonSpriteWidth,
                    buttonSpriteRowNumber * singleButtonSpriteHeight,
                    singleButtonSpriteWidth, singleButtonSpriteHeight);
    }



    /**
     * renders a button at (buttonPositionX , buttonPositionY) with width of 2 tile and height of 1 tile.
     *
     * @param g     g is a Graphics object passed as parameter to draw the button on game window.
     */
    public void render(Graphics g) {
        if (IsMouseOverButton || IsMousePressedButton){
            // If the button is hovered or pressed
            // the show the pressed sprite for the button which is at 2nd element of buttonSprites
            g.drawImage(buttonSprites[1],buttonPosX,buttonPosY,buttonWidth,buttonHeight,null);
        }
        else{
            // If the button is not hovered nor pressed
            // then renders its normal sprite
            g.drawImage(buttonSprites[0],buttonPosX,buttonPosY,buttonWidth,buttonHeight,null);
        }
    }



    /**
     *
     *
     */
    public void resetMouseBooleans() {
        IsMouseOverButton = IsMousePressedButton =  false;
    }
    public void applyGameState() {
        // Change the current stage of Game to be the same stage of button
        game.changeGameStates(buttonGameStates);
    }

    public void update() {
        // TODO FIX the update method for buttons
        // TODO MAYBE we don't need it ???
    }

    public boolean isMouseInButton (MouseEvent e) {
        return buttonCollision.contains(e.getX(), e.getY());
    }
    public gameStates getButtonGameStates() {
        return buttonGameStates;
    }

}
