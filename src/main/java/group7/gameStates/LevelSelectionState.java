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

public class LevelSelectionState extends MainMenuState{
    private static int numberOfLevels = 3;
    private BufferedImage[] levelNumbers;
    protected int indexLevelNumbers = 0;

    public LevelSelectionState(Game game) {
        super(game);
        mainMenuButtons = new GraphicsButtons[4];
        levelNumbers = new BufferedImage[numberOfLevels];
        mainMenuButtons[0] = new GraphicsButtons(game,(int)(panelWidth/2 - 3*GraphicsGrid.getScaleX()), (int)(0.5*panelHeight), 7, gameStates.PERV);
        mainMenuButtons[1] = new GraphicsButtons(game,(int)(panelWidth/2 + 3*GraphicsGrid.getScaleX()), (int)(0.5*panelHeight), 6, gameStates.NEXT);
        mainMenuButtons[2] = new GraphicsButtons(game,panelWidth / 2 - GraphicsGrid.getScaleX(), (int)(0.2*panelHeight), 8, gameStates.IN_LEVEL);
        mainMenuButtons[3] = new GraphicsButtons(game,panelWidth / 2 + GraphicsGrid.getScaleX(), (int)(0.2*panelHeight), 9, gameStates.IN_MENU);
        loadCharacterDemos();
    }

    private void loadCharacterDemos() {
        String level_select_sprite = "levelSelectMenu/lvl";
        for ( int i = 0; i < numberOfLevels; i++ ) {
            levelNumbers[i] = AssetLoader.getSpriteAtlas( level_select_sprite + (i + 1) + ".png" );
        }
    }

    @Override
    public void render(Graphics g) {
        mainPageParallelBG.renderParallelBackground(g);

        g.drawImage( levelNumbers[indexLevelNumbers],
                    (int)(panelWidth / 2 - GraphicsGrid.getScaleX()),
                    (int)(0.4 * panelHeight), 
                    2 * GraphicsGrid.getScaleX(),
                    2 * GraphicsGrid.getScaleX(),
                    null);

        for (GraphicsButtons buttons : mainMenuButtons) {
            buttons.render(g);
        }
    }

    @Override
    public int incrementIndexCharacterDemo() {
        indexLevelNumbers += 1;
        if (indexLevelNumbers >= numberOfLevels){
            indexLevelNumbers = 0;
        }
        return indexLevelNumbers;
    }

    @Override
    public int decrementIndexCharacterDemo() {
        indexLevelNumbers = indexLevelNumbers - 1;
        if (indexLevelNumbers <= -1){
            indexLevelNumbers = numberOfLevels - 1;
        }
        return indexLevelNumbers;
    }
}

