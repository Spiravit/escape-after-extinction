package group7.gameStates;

import group7.Game;

import static group7.Graphics.GraphicsGrid.*;

import group7.helperClasses.AssetLoader;
import group7.userInterface.UiButtons;

import static group7.helperClasses.buttonSpriteRow.*;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import static group7.Graphics.GraphicsPanel.*;

/**
 *
 *
 *
 * @author Karmeny Yung
 * @author Mohammad Parsaei
 * @author Salaman Ayaz
 */

public class LevelSelectionState extends State {

    // There are maximum 3 levels considered
    private static int numberOfLevels = 3;



    // Sprites for level selection number
    private BufferedImage[] levelNumbersSprites;

    // used in indexing of levelNumbersSprites array
    protected int indexLevelNumbers = 0;


    /**
     *  Constructor for creating a level selection page
     *
     * @param game passed to constructor of super class, in order change gameStates in game when
     *             any button was pressed in Level selection page.
     */
    public LevelSelectionState(Game game) {
        super(game);

        // Loading buttons
        loadButtons();

        // Loading level demo number sprites
        loadLevelDemoSprites();
    }

    @Override
    public void update() {

    }

    /**
     * loads and initialize the previous, next, lets play and return button on level selection page.
     *
     */
    private void loadButtons(){
        // There will be 4 buttons on level selection page
       stateButton = new UiButtons[4];

        // Previous button
        stateButton[0] = new UiButtons(game,
                6*scaleX,
                7*scaleY,
                PERV_BUTTON,
                gameStates.PERV);

        // Next button
        stateButton[1] = new UiButtons(game,
                12*scaleX,
                7*scaleY,
                NEXT_BUTTON,
                gameStates.NEXT);

        // Let's play button
        stateButton[2] = new UiButtons(game,
                panelWidth / 2 - 2*scaleX ,
                3*scaleY,
                LETS_PLAY_BUTTON,
                gameStates.IN_LEVEL);

        // Return button
        stateButton[3] = new UiButtons(game,
                panelWidth / 2 ,
                3*scaleY,
                RETURN_BUTTON,
                gameStates.IN_MENU);

    }


    /**
     * Loading level Number sprites into levelNumbersSprites array
     *
     */
    private void loadLevelDemoSprites() {
        levelNumbersSprites = new BufferedImage[numberOfLevels];
        String level_select_sprite = "levelSelectMenu/lvl";
        for ( int i = 0; i < numberOfLevels; i++ ) {
            levelNumbersSprites[i] = AssetLoader.getSpriteAtlas( level_select_sprite + (i + 1) + ".png" );
        }
    }


    /**
     * renders the level selection (load game) page in game windows. It draws the animated background, four button
     * (next,previous,return and lets play button) and draw level selection number sprites.
     *
     * @param g     is used to draw items on game's window
     */
    @Override
    public void render(Graphics g) {
        // render the animated background
        super.render(g);

        // Render Level number sprites
        g.drawImage( levelNumbersSprites[indexLevelNumbers],
                    (int)(panelWidth / 2 - scaleX),
                    6*scaleY,
                    2 * scaleX,
                    2 *scaleY,
                    null);

        for (UiButtons buttons : stateButton) {
            buttons.render(g);
        }
    }

    /**
     *  Increments the index of levelNumbersSprites. It is used when a player press on next button
     *  , the index is incremented in order to show next sprite of the array on levelSelection page.
     *
     * @return index of levelNumbersSprites array that is are currently at.
     */
    @Override
    public int incrementSpriteArrayIndex() {
        // Increment the index
        indexLevelNumbers += 1;

        // If indexing was going to be out of bound, being bigger or equal to size of array then
        //  set index to be 0.
        if (indexLevelNumbers >= numberOfLevels){
            indexLevelNumbers = 0;
        }
        return indexLevelNumbers;
    }

    /**
     *  decrements the index of levelNumbersSprites. It is used when a player press on previous button
     *  , the index is decremented in order to show previous sprite of the array on levelSelection page.
     *
     * @return index of levelNumbersSprites array that is are currently at.
     */
    @Override
    public int decrementSpriteArrayIndex() {
        // Decrement the index
        indexLevelNumbers = indexLevelNumbers - 1;

        // If indexing was going to be out of bound, be negative
        // then set index to be the index of last element in levelNumbersSprites
        if (indexLevelNumbers <= -1){
            indexLevelNumbers = numberOfLevels - 1;
        }
        return indexLevelNumbers;
    }

    /**
     *  gets a key that was pressed and if that key was escape key on keyboard, then it
     *  changes gameState to be in mainMenuState in order to get back to main menu page.
     *
     * @param e the keyboard key that player has pressed while being in credit page
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // If the pressed escape button, then go back to main menu page
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            game.changeGameStates(gameStates.IN_MENU);
    }

}

