package group7.gameStates;

import group7.Game;
import group7.Graphics.GraphicsButtons;
import group7.Graphics.GraphicsGrid;
import group7.Graphics.GraphicsPanel;
import group7.entities.animate.Player;
import group7.utils.AssetLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import static group7.Graphics.GraphicsPanel.*;

public class playerSelectionState extends MainMenuState{
    private static int numberOfCharacters = 5;
    private BufferedImage[] characterDemos ;
    protected int indexCharacterDemo =0;
    public playerSelectionState(Game game) {
        super(game);
        mainMenuButtons = new GraphicsButtons[4];
        characterDemos = new BufferedImage[numberOfCharacters];
        mainMenuButtons[0] = new GraphicsButtons(game,(int)(panelWidth/2 - 3*GraphicsGrid.getScaleX()), (int)(0.5*panelHeight), 7, gameStates.PERV);
        mainMenuButtons[1] = new GraphicsButtons(game,(int)(panelWidth/2 + 3*GraphicsGrid.getScaleX()), (int)(0.5*panelHeight), 6, gameStates.NEXT);
        mainMenuButtons[2] = new GraphicsButtons(game,panelWidth / 2 - GraphicsGrid.getScaleX(), (int)(0.2*panelHeight), 8, gameStates.IN_LEVEL);
        mainMenuButtons[3] = new GraphicsButtons(game,panelWidth / 2 + GraphicsGrid.getScaleX(), (int)(0.2*panelHeight), 9, gameStates.IN_MENU);
        loadCharacterDemos();
    }

    private void loadCharacterDemos() {
        characterDemos[0] = AssetLoader.getSpriteAtlas(AssetLoader.DINO_1).getSubimage(11 * 24, 0, 24, 24);
        characterDemos[1] = AssetLoader.getSpriteAtlas(AssetLoader.DINO_2).getSubimage(11 * 24, 0, 24, 24);
        characterDemos[2] = AssetLoader.getSpriteAtlas(AssetLoader.DINO_3).getSubimage(11 * 24, 0, 24, 24);
        characterDemos[3] = AssetLoader.getSpriteAtlas(AssetLoader.DINO_4).getSubimage(11 * 24, 0, 24, 24);
        characterDemos[4] = AssetLoader.getSpriteAtlas(AssetLoader.DINO_5).getSubimage(11 * 24, 0, 24, 24);
    }

    @Override
    public void render(Graphics g){
        mainPageParallelBG.renderParallelBackground(g);
        g.drawImage(characterDemos[indexCharacterDemo],(int)(panelWidth/2 - GraphicsGrid.getScaleX()),(int)(0.4*panelHeight), 2*GraphicsGrid.getScaleX(),2*GraphicsGrid.getScaleX(),null);
        for (GraphicsButtons buttons : mainMenuButtons) {
            buttons.render(g);
        }
    }
    @Override
    public int incrementIndexCharacterDemo() {
        this.indexCharacterDemo += 1;
        if (indexCharacterDemo >= numberOfCharacters){
            indexCharacterDemo=0;
        }
        return indexCharacterDemo;
    }
    @Override
    public int decrementIndexCharacterDemo() {
        this.indexCharacterDemo = this.indexCharacterDemo -1;
        if (indexCharacterDemo <= -1){
            indexCharacterDemo=numberOfCharacters-1;
        }
        return indexCharacterDemo;
    }
}
