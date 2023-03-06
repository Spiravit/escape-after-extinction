package group7.gameStates;

import group7.Game;
import group7.entities.Player;
import group7.utils.AssetLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import static group7.Graphics.GraphicsPanel.*;

public class playerSelectionState extends MainMenuState{
    private static int numberOfCharacters = 5;
    private BufferedImage[] characterDemos ;
    public playerSelectionState(Game game) {
        super(game);
        characterDemos = new BufferedImage[numberOfCharacters];
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
        int characterDemoSpriteSize = 200;
        g.drawImage(characterDemos[0],0,0,characterDemoSpriteSize,characterDemoSpriteSize,null );
    }
}
