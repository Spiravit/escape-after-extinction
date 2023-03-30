package group7.userInterface;

import group7.Game;
import group7.gameStates.gameStates;
import static group7.Graphics.GraphicsPanel.*;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import static group7.Graphics.GraphicsGrid.scaleX;
import static group7.Graphics.GraphicsGrid.scaleY;
import static group7.helperClasses.buttonSpriteRow.*;
import static group7.helperClasses.buttonSpriteRow.EXIT_BUTTON;

/**
 * is responsible to create a menu after the player has successfully won a level
 * @author Salman Ayaz
 * @author Karmen Yung
 * @author Mohammad Parsaei
 */
public class UiFinishedGameMenu extends UiMenu{
    int currentLevel;

    /**
     * Constructor for creating won menu
     * @param game  Game object passed in order to being used for buttons in death screen
     * @param currentLevel  The current level where player is playing
     */
    public UiFinishedGameMenu(Game game, int currentLevel) {
        super(game);
        if (currentLevel > 3){
            throw new IndexOutOfBoundsException("No such level number exist");
        }
        this.currentLevel=currentLevel;
        initialiseMenuButtons();
    }

    /**
     * Renders the won menu on game window
     */
    public void render(Graphics g,int time, int eggCollected){
        super.render(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setFont(retroFont);
        g2D.setColor(Color.white);
        g2D.drawString("WON !",panelWidth/2 - scaleX/2,5*scaleY);
        g2D.drawString("collected egg: "+eggCollected,panelWidth/2 - 2*scaleX,6*scaleY);
        g2D.drawString("Time: "+time +" s",panelWidth/2 - 2*scaleX,7*scaleY);
        g2D.drawString("score: "+(time*(-1)+100*eggCollected),panelWidth/2 - 2*scaleX,8*scaleY);
    }

    /**
     * It initializes the buttons on death screen.
     */
    protected void initialiseMenuButtons() {
        menuButtons = new UiButtons[3];
        // If we are at level 2 or 1, we will have next level button
        if (currentLevel < 3){
            System.out.println("Current level is " + currentLevel);
            menuButtons[0] = new UiButtons(game,
                    mainMenuButtonsPosX,
                    9*scaleY,
                    NEXT_LEVEL_BUTTON,
                    gameStates.Next_Level);
        }
        // if we are at level 3, we have main menu button
        else{
            menuButtons[0] = new UiButtons(game,
                    mainMenuButtonsPosX,
                    9*scaleY,
                    MAIN_MENU_BUTTON,
                    gameStates.IN_MENU);
        }

        // Restart button
        menuButtons[1] = new UiButtons(game,
                mainMenuButtonsPosX,
                10*scaleY,
                RESTART_BUTTON,
                gameStates.NEW_GAME);

        // Main Menu button
        menuButtons[2] = new UiButtons(game,
                mainMenuButtonsPosX,
                11*scaleY,
                MAIN_MENU_BUTTON,
                gameStates.IN_MENU);
    }
}
