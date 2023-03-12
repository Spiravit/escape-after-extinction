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

public class UiFinishedGameMenu extends UiMenu{
    int currentLevel;
    private Font retroFont ;

    public UiFinishedGameMenu(Game game, int currentLevel) {
        super(game);
        this.currentLevel=currentLevel;
        initialiseMenuButtons();
        loadFont();
    }

    @Override
    public void render(Graphics g){
        super.render(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setFont(retroFont);
        g2D.setColor(Color.white);
        g2D.drawString("WON !",panelWidth/2 - scaleX/2,5*scaleY);
    }
    @Override
    protected void initializeMenuButtons() {
        // TODO FIx here
    }

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
                gameStates.RESTART);

        // Main Menu button
        menuButtons[2] = new UiButtons(game,
                mainMenuButtonsPosX,
                11*scaleY,
                MAIN_MENU_BUTTON,
                gameStates.IN_MENU);
    }

    /**
     *  Loads custom font from assets folder in order to be used later in drawing strings on game window.
     *
     */
    private void loadFont(){
        InputStream is;
        try{
            is = getClass().getResourceAsStream("/assets/font/ThaleahFat.ttf");
            // Set size of font to be 30
            retroFont = Font.createFont(Font.TRUETYPE_FONT,is).deriveFont(30f);
        }
        catch (FontFormatException f){
            f.printStackTrace();
        }
        catch (IOException a){
            a.printStackTrace();
        }
    }
}
