package group7.levels;

import group7.utils.AssetLoader;

import static group7.Game.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class LevelManager {
    private BufferedImage[] levelSprite;
    private LevelData levelOne;
    private Level[] levels = {
        new Level1()
    };

    public LevelManager() {
        importOutsideSprites();
        levelOne = new LevelData();
    }

    public LevelData getLevel(int level) {
        return levels[level];
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
}
