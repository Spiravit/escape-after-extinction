package group7.gameStates;

import group7.Game;
import static group7.helperClasses.buttonSpriteRow.*;
import group7.Graphics.GraphicsGrid;
import group7.helperClasses.AssetLoader;
import group7.userInterface.UiButtons;
import java.awt.*;
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
public class creditState extends MainMenuState{
    // TODO This will be removed
    private BufferedImage creditText;

    /**
     * Constructor for creditState class
     *
     * @param game  it takes a Gama parameter, in order to change its stage
     *              if any of the button in credit page was clicked.
     */
    public creditState(Game game) {
        // Calling constructor of MainMenuState to set game field
        super(game);

        // There will be only one button in credit page, and it is a return button
        menuButtons = new UiButtons[1];

        // TODO The line bellow will be removed !!
        creditText = AssetLoader.getSpriteAtlas(AssetLoader.CreditMenu);

        // Initializing Return button
        initializeMainMenuButtons();
    }

    /**
     * Initialize the button in creditState class to a return button located at (13th tile, 14th tile)
     *
     */
    @Override
    protected void initializeMainMenuButtons(){
        menuButtons[0] = new UiButtons(game,
                13*GraphicsGrid.scaleX,
                14*GraphicsGrid.scaleY,
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
        // render the same animated background of main menu in credit page
        mainPageParallelBG.renderParallelBackground(g);

        // TODO The line bellow will be removed
        g.drawImage(creditText, (int)(0.5*(panelWidth-creditText.getWidth())),100,creditText.getWidth(),creditText.getHeight(),null);

        // Render the return button in credit page
        menuButtons[0].render(g);
    }
}
