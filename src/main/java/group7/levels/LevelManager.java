package group7.levels;

import group7.utils.AssetLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import static group7.main.Game.*;

public class LevelManager {

    private BufferedImage[] levelSprite;
    private LevelData levelOne;

    public LevelManager() {
        importOutsideSprites();
        levelOne = new LevelData();
    }

    public LevelData getLevelOne() {
        return levelOne;
    }

    private void importOutsideSprites() {
        BufferedImage img = AssetLoader.getSpriteAtlas(AssetLoader.LEVELS_SPRITES);
        levelSprite = new BufferedImage[60];
        for (int j = 0; j < 4; j++)
            for (int i = 0; i < 15; i++) {
                int index = j * 15 + i;
                levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
            }
    }

    public void draw(Graphics g) {
        for (int j = 0; j < NUMBER_OF_TILES_IN_HEIGHT; j++)
            for (int i = 0; i < NUMBER_OF_TILES_IN_WIDTH; i++) {
                int index = levelOne.getLevelDataSprite(j, i);
                g.drawImage(levelSprite[index], (int) GAME_SIZE_SCALE * TILES_SIZE * i, (int) GAME_SIZE_SCALE * TILES_SIZE * j, (int) GAME_SIZE_SCALE * TILES_SIZE, (int) GAME_SIZE_SCALE * TILES_SIZE, null);
            }
    }

}
