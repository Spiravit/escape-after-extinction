package group7.gameStates;

import group7.Game;
import group7.Graphics.GraphicsGrid;
import group7.gameStates.State;
import group7.gameStates.gameStates;
import group7.helperClasses.AssetLoader;
import group7.userInterface.UiButtons;
import group7.userInterface.UiParallelBackground;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static group7.Graphics.GraphicsGrid.scaleX;
import static group7.Graphics.GraphicsGrid.scaleY;
import static group7.Graphics.GraphicsPanel.*;
import static group7.helperClasses.buttonSpriteRow.*;
import static group7.helperClasses.buttonSpriteRow.RETURN_BUTTON;

/**
 * The class creates an object of type playerSelectionState, that renders a page
 * where the player can select the character they want to play as.
 * @author  Salman Ayaz
 * @author  Karmen Yung
 * @author  Mohammad Parsaei
 * @author  Chen Min
 * @version 1.0
 * @since 2023-03-13
 */
public class playerSelectionState extends State {
    // number of characters to be displayed
    private static int numberOfCharacters = 5;

    // Sprites for character selection
    private BufferedImage[] characterDemos;

    // current character displayed
    protected int indexCharacterDemo = 0;

    /**
     * Constructor for creating a level selection page
     * @param game
     * the game class 
     */
    public playerSelectionState(Game game) {
        super(game);

        loadButtons();

        loadCharacterDemos();
    }

    /**
     * loads all the buttons for the level selection page
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
    * loads the player type images for the player selection screen
    */
    private void loadCharacterDemos() {
        characterDemos = new BufferedImage[5];
        characterDemos[0] = AssetLoader.getSpriteAtlas(AssetLoader.DINO_1).getSubimage(11 * 24, 0, 24, 24);
        characterDemos[1] = AssetLoader.getSpriteAtlas(AssetLoader.DINO_2).getSubimage(11 * 24, 0, 24, 24);
        characterDemos[2] = AssetLoader.getSpriteAtlas(AssetLoader.DINO_3).getSubimage(11 * 24, 0, 24, 24);
        characterDemos[3] = AssetLoader.getSpriteAtlas(AssetLoader.DINO_4).getSubimage(11 * 24, 0, 24, 24);
        characterDemos[4] = AssetLoader.getSpriteAtlas(AssetLoader.DINO_5).getSubimage(11 * 24, 0, 24, 24);
    }

    @Override
    public void render(Graphics g){
        // render the animated background
        super.render(g);

        g.drawImage(characterDemos[indexCharacterDemo],(int)(panelWidth/2 - GraphicsGrid.getScaleX()),(int)(0.4*panelHeight), 2*GraphicsGrid.getScaleX(),2*GraphicsGrid.getScaleX(),null);
        for (UiButtons buttons : stateButton) {
            buttons.render(g);
        }
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

    /**
     * decreases the index of the current shown character in the character demo array
     */
    @Override
    public int incrementSpriteArrayIndex() {
        this.indexCharacterDemo += 1;
        if (indexCharacterDemo >= numberOfCharacters){
            indexCharacterDemo=0;
        }
        return indexCharacterDemo;
    }

    /**
     * increases the index of the current shown character in the character demo array
     */
    @Override
    public int decrementSpriteArrayIndex() {
        this.indexCharacterDemo = this.indexCharacterDemo -1;
        if (indexCharacterDemo <= -1){
            indexCharacterDemo=numberOfCharacters-1;
        }
        return indexCharacterDemo;
    }
}
