package group7.userInterface;

import group7.Game;
import group7.gameStates.gameStates;
import static group7.Graphics.GraphicsGrid.*;
import static group7.helperClasses.buttonSpriteRow.*;
import static group7.helperClasses.buttonSpriteRow.EXIT_BUTTON;


/**
 * is responsible for creating a pause menu.
 *
 *  * @author Karmen Yung
 *  * @author Salman Ayaz
 *  * @author Mohammad Parsaei
 */
public class UiPauseMenu extends UiMenu {

    /**
     * constructor for pause menu
     * @param game  in order to be passed to button of pause menu
     */
    public UiPauseMenu(Game game) {
        super(game);

        // There are 4 buttons in Pause menu : Continue, restart, main menu and exit
        initializeMenuButtons();
    }

    /**
     * It initializes the button for pause menu.
     */
    private void initializeMenuButtons() { //changed from protected to private
        menuButtons = new UiButtons[4];
        // Continue button
        menuButtons[0] = new UiButtons(game,
                mainMenuButtonsPosX,
                5*scaleY,
                CONTINUE_BUTTON,
                gameStates.RESUME);

        // Restart game button
        menuButtons[1] = new UiButtons(game,
                mainMenuButtonsPosX,
                7*scaleY,
                NEW_GAME_BUTTON,
                gameStates.NEW_GAME);

        // Main Menu button
        menuButtons[2] = new UiButtons(game,
                mainMenuButtonsPosX,
                9*scaleY,
                MAIN_MENU_BUTTON,
                gameStates.IN_MENU);

        // Exit button
        menuButtons[3] = new UiButtons(game,
                mainMenuButtonsPosX,
                11*scaleY,
                EXIT_BUTTON,
                gameStates.QUIT);
    }
}
