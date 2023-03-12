package group7.userInterface;

import group7.Game;
import group7.gameStates.gameStates;

import static group7.Graphics.GraphicsGrid.scaleY;
import static group7.helperClasses.buttonSpriteRow.*;
import static group7.helperClasses.buttonSpriteRow.EXIT_BUTTON;

public class UiFinishedGameMenu extends UiMenu{
    int currentLevel;
    public UiFinishedGameMenu(Game game, int currentLevel) {
        super(game);
        this.currentLevel=currentLevel;
        System.out.println(currentLevel);
       // initializeMenuButtons();
    }

    @Override
    protected void initializeMenuButtons() {
        menuButtons = new UiButtons[3];
        // If we are at level 2 or 1, we will have next level button
        if (currentLevel < 3){
            menuButtons[0] = new UiButtons(game,
                    mainMenuButtonsPosX,
                    7*scaleY,
                    NEXT_LEVEL_BUTTON,
                    gameStates.Next_Level);
        }
        // if we are at level 3, we have main menu button
        else{
            menuButtons[0] = new UiButtons(game,
                    mainMenuButtonsPosX,
                    7*scaleY,
                    MAIN_MENU_BUTTON,
                    gameStates.IN_MENU);
        }

        // Restart button
        menuButtons[1] = new UiButtons(game,
                mainMenuButtonsPosX,
                9*scaleY,
                RESTART_BUTTON,
                gameStates.IN_LEVEL);

        // Main Menu button
        menuButtons[2] = new UiButtons(game,
                mainMenuButtonsPosX,
                11*scaleY,
                MAIN_MENU_BUTTON,
                gameStates.IN_MENU);
    }
}
