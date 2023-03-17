// TODO : need no more editing
package group7.userInterface;

import group7.Game;
import group7.Graphics.GraphicsGrid;
import group7.gameStates.gameStates;
import group7.helperClasses.AssetLoader;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * This class is implemented to render Buttons, implements their interaction with mouse input
 * and associate them with a gameState. That is if they are clicked then they change the state of game based on
 *  their associated button state.
 *
 * @author Karmen Yung
 * @author Salman Ayaz
 * @author Mohammad Parsaei
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
     * Constructor for Button
     *
     * @param game  passed game to a button in order for a button to change game state
     * @param buttonPositionX   position x of where button is going to be located
     * @param buttonPositionY   position y of where button is going to be located
     * @param buttonSpriteRowNumber the i-th row of menubuttons png where sprite of button is located
     * @param buttonGameStates  The state associated with button, hence once it is clicked
     *                         it changes game state based on buttonState
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
     * resets the value of IsMouseOverButton to false indicating that mouse is not on button anymore.
     *
     */
    public void resetMouseBooleans() {

        IsMouseOverButton = IsMousePressedButton =  false;
    }

    /**
     * passes the buttonGameStates to changeGameStates method in game class when the button is clicked in order
     * to change state of the game.
     *
     */
    public void applyGameState() {
        // Change the current stage of Game to be the same stage of button
        game.changeGameStates(buttonGameStates);
    }

    /**
     *  TODO mIGHT be removed !
     */
    public void update() {
        // TODO ADD :???
    }


    /**
     * returns true if the mouse is on the button else it returns false.
     *
     * @param e     MouseEvent e passed in order to see if the mouse position falls in where button is located
     * @return      returns boolean indicating if the mouse is on the button or not
     */
    public boolean isMouseInButton (MouseEvent e) {
        return buttonCollision.contains(e.getX(), e.getY());
    }

    /**
     * Returns the state that button is associated with.
     *
     * @return  return buttonGameStates
     */
    public gameStates getButtonGameStates() {

        return buttonGameStates;
    }

}
