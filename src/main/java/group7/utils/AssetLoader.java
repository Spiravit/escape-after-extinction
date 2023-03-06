package group7.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class AssetLoader {
    public static final String LEVELONEMAP = "levels/level_one/levelonesprites.png";
    public static final String LEVELS_SPRITES = "levels/levelssprites.png";

    // The main menu buttons sprite image
    public static final String MAIN_MENU_BUTTONS = "menu/mainMenuButtons.png";
    // The main menu background sprite image
    public static final String MAIN_MENU_BACKGROUND = "menu/mainMenuBackground.png";

    public static final String MAINPAGE_Layer_1 = "menu/parallexBG/1.png";
    public static final String MAINPAGE_Layer_2 = "menu/parallexBG/2.png";
    public static final String MAINPAGE_Layer_3 = "menu/parallexBG/3.png";
    public static final String MAINPAGE_Layer_4 = "menu/parallexBG/4.png";
    public static final String MAINPAGE_Layer_5 = "menu/parallexBG/5.png";
    public static final String MAINPAGE_Layer_6 = "menu/parallexBG/6.png";
    public static final String MAINPAGE_Layer_7 = "menu/parallexBG/7.png";
    public static final String MAINPAGE_Layer_8 = "menu/parallexBG/8.png";
    public static final String LOADING_1 = "menu/parallexBG/loading1.png";
    public static final String LOADING_2 = "menu/parallexBG/loading2.png";
    public static final String LOADING_3 = "menu/parallexBG/loading3.png";

    public static final String PINKPLAYER = "playerSprites/pink_dino_ani.png";

    public static final String SCIENTIST = "scientist_sprites.png";
    public static final String SPIKE = "spike_sprites.png";

    public static final String EGG = "egg_sprites.png";
    public static final String KEY_CARD = "key_card_sprites.png";

    public static final String INVISIBILITY_POTION = "invisibility_potion_sprites.png";
    public static final String SPEED_POTION = "speed_potion_sprites.png";

    public static final String LAB_TILE = "lab_tile.png";
    public static final String DOOR = "door_sprites.png";
    

    /**
     * Load a sprite atlas from the assets folder
     * @param filename
     * The name of the file to load, which must be one of the constants defined in this class
     * @return
     * The sprite atlas as a BufferedImage
     */
    public static BufferedImage getSpriteAtlas(String filename) {
        BufferedImage img = null;
        InputStream is = AssetLoader.class.getResourceAsStream("/assets/" + filename);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }
}
