package group7.userInterface;

import group7.Game;

import static group7.Graphics.GraphicsGrid.*;
import static group7.helperClasses.buttonSpriteRow.*;
import group7.gameStates.gameStates;
import group7.helperClasses.AssetLoader;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static group7.Graphics.GraphicsPanel.panelWidth;

public class UiMenu {
    protected Font retroFont;
    // Position x of buttons in menu, they are centered at middle of width
    int mainMenuButtonsPosX = panelWidth/ 2 - scaleX ;

    //dedicated for four buttons in main menu page (New game, Load game, credit and exit buttons)
    protected UiButtons[] menuButtons;

    // TODO Add comments
    public Game game;

    //MenuBoxBackground: dedicated for blue box image behind main menu buttons
    private BufferedImage menuBoxBackground;
    //MENU_BOX_BACKGROUND_HEIGHT: The Height of blue box behind  menu buttons
    private static final int MENU_BOX_BACKGROUND_HEIGHT =9*scaleY;
    //MENU_BOX_BACKGROUND_WIDTH: The Width of blue box behind  menu buttons
    private static final int MENU_BOX_BACKGROUND_WIDTH =5*scaleX;

    public UiMenu(Game game){
        menuBoxBackground = AssetLoader.getSpriteAtlas(AssetLoader.MAIN_MENU_BACKGROUND);
        this.game = game;
        initializeMenuButtons();
    }
    private void initializeMenuButtons(){ //from protected to private
        menuButtons = new UiButtons[3];
        // New Game button
        menuButtons[0] = new UiButtons(game,
                mainMenuButtonsPosX,
                5*scaleY,
                NEW_GAME_BUTTON,
                gameStates.PLAYER_SELECTION_SUB_MENU);

        // Load game button
        menuButtons[1] = new UiButtons(game,
                mainMenuButtonsPosX,
                8*scaleY,
                LOAD_GAME_UNSAVED_BUTTON,
                gameStates.LEVEL_SELECTION_SUB_MENU);


        // Exit button
        menuButtons[2] = new UiButtons(game,
                mainMenuButtonsPosX,
                11*scaleY,
                EXIT_BUTTON,
                gameStates.QUIT);
    }

    public void render(Graphics g){
        // Draw blue box behind buttons background
        renderMenuBox(g);

        // Render each main menu buttons
        for (UiButtons buttons : menuButtons) {
            buttons.render(g);
        }
    }

    /**
     * renders the blue box background behind main menu buttons. It is rendered at center of the window.
     *
     * @param g     Graphic object passed as parameter in order
     *              to draw the blue box behind buttons
     */
    private void renderMenuBox(Graphics g){
        // Draw image at x position of (width of window - width of box )
        // In order to make sure that it is centered.
        g.drawImage(menuBoxBackground,
                (int)(panelWidth / 2 - MENU_BOX_BACKGROUND_WIDTH /2),
                4*scaleY,
                MENU_BOX_BACKGROUND_WIDTH,
                MENU_BOX_BACKGROUND_HEIGHT,
                null);
    }

    private void renderButtons(Graphics g){
        for (UiButtons buttons : menuButtons) {
            buttons.render(g);
        }
    }
    public void update() {
        for (UiButtons button : menuButtons)
            button.update();
    }



    public void mousePressed(MouseEvent e) {
        for (UiButtons button : menuButtons) {
            if (button.isMouseInButton(e)) {
                button.setIsMousePressedButton(true);
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        for (UiButtons button : menuButtons) {
            if (button.isMouseInButton(e)) {
                if (button.getIsMousePressed())
                    button.applyGameState();
                break;
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for (UiButtons button : menuButtons) {
            button.resetMouseBooleans();
        }
    }

    public void mouseMoved(MouseEvent e) {
        for (UiButtons button : menuButtons)
            button.setIsMouseOverButton(false);

        for (UiButtons button : menuButtons) {
            if (button.isMouseInButton(e)) {
                button.setIsMouseOverButton(true);
                break;
            }

        }
    }
}
