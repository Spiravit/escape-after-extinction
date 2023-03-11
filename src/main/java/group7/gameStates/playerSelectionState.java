package group7.gameStates;

import group7.Game;
import group7.Graphics.GraphicsGrid;
import group7.helperClasses.AssetLoader;
import group7.userInterface.UiButtons;

import java.awt.*;
import java.awt.image.BufferedImage;
import static group7.Graphics.GraphicsPanel.*;

public class playerSelectionState extends MainMenuState{
    private static int numberOfCharacters = 5;
    private BufferedImage[] characterDemos ;
    protected int indexCharacterDemo =0;
    public playerSelectionState(Game game) {
        super(game);
        menuButtons = new UiButtons[4];
        characterDemos = new BufferedImage[numberOfCharacters];
        menuButtons[0] = new UiButtons(game,(int)(panelWidth/2 - 3*GraphicsGrid.getScaleX()), (int)(0.5*panelHeight), 7, gameStates.PERV);
        menuButtons[1] = new UiButtons(game,(int)(panelWidth/2 + 3*GraphicsGrid.getScaleX()), (int)(0.5*panelHeight), 6, gameStates.NEXT);
        menuButtons[2] = new UiButtons(game,panelWidth / 2 - GraphicsGrid.getScaleX(), (int)(0.2*panelHeight), 8, gameStates.IN_LEVEL);
        menuButtons[3] = new UiButtons(game,panelWidth / 2 + GraphicsGrid.getScaleX(), (int)(0.2*panelHeight), 9, gameStates.IN_MENU);
        loadCharacterDemos();
    }

    private void loadCharacterDemos() {
        String i_Th_player_demo_sprite = "characterSelectionMenu/player_";
        for (int i=0;i<numberOfCharacters;i++){
            characterDemos[i]= AssetLoader.getSpriteAtlas(i_Th_player_demo_sprite + (i+1) +".png");
        }
    }

    @Override
    public void render(Graphics g){
        mainPageParallelBG.renderParallelBackground(g);
        g.drawImage(characterDemos[indexCharacterDemo],(int)(panelWidth/2 - GraphicsGrid.getScaleX()),(int)(0.4*panelHeight), 2*GraphicsGrid.getScaleX(),2*GraphicsGrid.getScaleX(),null);
        for (UiButtons buttons : menuButtons) {
            buttons.render(g);
        }
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
