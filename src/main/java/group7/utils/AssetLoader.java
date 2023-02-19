package group7.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class AssetLoader {
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
