package group7.helperClasses;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/** 
* The class AssetLoader defines all Animate and Inanimate Objects resource file paths,
* and retrieves the correct images from file as a BufferedImage.
*
* @author  Mohammad Parsaei
* @author  Salman Ayaz
* @author  Karmen Yung
* @author  Chen Min
* @version 1.0
* @since 2023-03-13
*/
public class AssetLoader {
    
    public static final String LEVELS_SPRITES = "levels/levelsSprites.png";

    // The main menu buttons sprite image
    public static final String MAIN_MENU_BUTTONS = "menu/mainMenuButtons.png";
    // The main menu background sprite image
    public static final String MAIN_MENU_BACKGROUND = "menu/mainMenuBackground.png";


    public static final String LOADING_1 = "menu/parallexBG/loading1.png";
    public static final String LOADING_2 = "menu/parallexBG/loading2.png";
    public static final String LOADING_3 = "menu/parallexBG/loading3.png";


    public static final String DINO_1 = "player/dino_1.png";
    public static final String DINO_2 = "player/dino_2.png";
    public static final String DINO_3 = "player/dino_3.png";
    public static final String DINO_4 = "player/dino_4.png";
    public static final String DINO_5 = "player/dino_5.png";

    public static final String SCIENTIST = "enemies/Scientists.png";

    public static final String EGG = "collectable/egg.png";
    public static final String KEY = "collectable/key.png";
    public static final String TRAP = "collectable/trap.png";


    public static final String HEALTH_POTION = "collectable/greenPotion.png";
    public static final String SPEED_POTION = "collectable/purplePotion.png";


    public static final String HEALTH_BAR_BOUNDARY = "menu/inLevelTopMenu/health_bar_outside.png";
    public static final String HEALTH_BAR_INSIDE = "menu/inLevelTopMenu/health_bar_inside.png";

    /**
     * Load a sprite atlas from the assets folder.
     * @param filename
     * The name of the file to loaded. Must be one of the constants defined in this class.
     * @return The sprite atlas as a BufferedImage
     */
    public static BufferedImage getSpriteAtlas(String filename) {
        BufferedImage img = null;
        InputStream is = AssetLoader.class.getResourceAsStream("/assets/" + filename);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();

        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }
}
