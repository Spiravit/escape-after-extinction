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
import static group7.Graphics.GraphicsPanel.*;

public class playerSelectionState extends State {

    private static int numberOfCharacters = 5;
    private BufferedImage[] characterDemos ;
    protected int indexCharacterDemo =0;
    public playerSelectionState(Game game) {
        super(game);
        stateButton = new UiButtons[4];
        characterDemos = new BufferedImage[numberOfCharacters];
        stateButton[0] = new UiButtons(game,(int)(panelWidth/2 - 3*GraphicsGrid.getScaleX()), (int)(0.5*panelHeight), 7, gameStates.PERV);
        stateButton[1] = new UiButtons(game,(int)(panelWidth/2 + 3*GraphicsGrid.getScaleX()), (int)(0.5*panelHeight), 6, gameStates.NEXT);
        stateButton[2] = new UiButtons(game,panelWidth / 2 - GraphicsGrid.getScaleX(), (int)(0.2*panelHeight), 8, gameStates.IN_LEVEL);
        stateButton[3] = new UiButtons(game,panelWidth / 2 + GraphicsGrid.getScaleX(), (int)(0.2*panelHeight), 9, gameStates.IN_MENU);
        loadCharacterDemos();
    }

    @Override
    public void update() {

    }

    private void loadCharacterDemos() {
        String i_Th_player_demo_sprite = "characterSelectionMenu/player_";
        for (int i=0;i<numberOfCharacters;i++){
            characterDemos[i]= AssetLoader.getSpriteAtlas(i_Th_player_demo_sprite + (i+1) +".png");
        }
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

    @Override
    public int incrementSpriteArrayIndex() {
        this.indexCharacterDemo += 1;
        if (indexCharacterDemo >= numberOfCharacters){
            indexCharacterDemo=0;
        }
        return indexCharacterDemo;
    }
    @Override
    public int decrementSpriteArrayIndex() {
        this.indexCharacterDemo = this.indexCharacterDemo -1;
        if (indexCharacterDemo <= -1){
            indexCharacterDemo=numberOfCharacters-1;
        }
        return indexCharacterDemo;
    }
}
