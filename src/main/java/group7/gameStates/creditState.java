package group7.gameStates;

import group7.Game;
import static group7.helperClasses.buttonSpriteRow.*;
import group7.Graphics.GraphicsGrid;
import static group7.Graphics.GraphicsGrid.*;
import group7.helperClasses.AssetLoader;
import group7.userInterface.UiButtons;
import group7.userInterface.UiParallelBackground;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import static group7.Graphics.GraphicsPanel.*;

/**
 * is responsible for creating and rendering Credit page. Credit page is extending MainMenuState class.
 * This class has overridden render method in order to render content of credit page.
 *
 * @author  Salman Ayaz
 * @author  Karmen Yung
 * @author  Mohammad Parsaei
 * @author  Chen Min
 */
public class creditState extends State{

    /**
     * Constructor for creditState class
     *
     * @param game  it takes a Gama parameter, in order to change its stage
     *              if any of the button in credit page was clicked.
     */
    public creditState(Game game) {
        // Calling constructor of State to set the animated background
        super(game);

        // Initializing Return button
        initializeMainMenuButtons();
    }

    /**
     * Initialize the button in creditState class to a return button located at (13th tile, 14th tile)
     *
     */
    protected void initializeMainMenuButtons(){
        stateButton= new UiButtons[1];
        // There will be only one button in credit page, and it is a return button
        stateButton[0] = new UiButtons(game,
                13*scaleX,
                14*scaleY,
                RETURN_BUTTON,
                gameStates.IN_MENU);
    }


    /**
     * renders credit page which includes credit text and a return button to main menu.
     *
     * @param g     Graphics g is passed to function in order
     *              draw on game window.
     */
    @Override
    public void render(Graphics g){
        // render the animated background
        super.render(g);

        // TODO Adding Credit text

        // Render the return button in credit page
        stateButton[0].render(g);
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
