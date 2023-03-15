package group7.userInterface;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import group7.Game;
import group7.gameStates.gameStates;
import static group7.Graphics.GraphicsPanel.*;
import static group7.Graphics.GraphicsGrid.scaleX;
import static group7.Graphics.GraphicsGrid.scaleY;
import static group7.helperClasses.buttonSpriteRow.*;


public class UiDeathScreen extends UiMenu {
    int currentLevel;

    public UiDeathScreen( Game game, int currentLevel ) {
        super(game);
        this.currentLevel = currentLevel;
        initialiseMenuButtons();
        loadFont();
    }

    private void initialiseMenuButtons() { 
        menuButtons = new UiButtons[3];

        // Restart button
        menuButtons[0] = new UiButtons( game,
                mainMenuButtonsPosX,
                9 * scaleY,
                RESTART_BUTTON,
                gameStates.NEW_GAME );
        // Main Menu button
        menuButtons[1] = new UiButtons( game,
                mainMenuButtonsPosX,
                10 * scaleY,
                MAIN_MENU_BUTTON,
                gameStates.IN_MENU );
        // Exit Game button
        menuButtons[2] = new UiButtons( game,
                mainMenuButtonsPosX,
                11 * scaleY,
                EXIT_BUTTON,
                gameStates.QUIT );
    }

    @Override
    public void render( Graphics g ){
        super.render(g);
        Graphics2D g2D = ( Graphics2D ) g;
        g2D.setFont( retroFont );
        g2D.setColor( Color.white );
        g2D.drawString( "Better", (panelWidth/2 - scaleX/2)-2, (5 * scaleY) ); 
        g2D.drawString( "Luck", (panelWidth/2 - scaleX/2), (6 * scaleY) ); 
        g2D.drawString( "Next", (panelWidth/2 - scaleX/2), (7 * scaleY) ); 
        g2D.drawString( "Time!", (panelWidth/2 - scaleX/2), (8 * scaleY) ); 
    }
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
