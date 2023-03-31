package group7.helperClasses;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static group7.helperClasses.AssetLoader.getSpriteAtlas;
import static org.junit.Assert.*;

public class AssetLoaderTest {

    @Test(expected = NullPointerException.class)
    public void getSpriteAtlasNonExistingImageTest() {
        // This test make sure, if we tried to load an image
        // that doesn't exists in our asset folder then
        // it should throw nullpointer
        getSpriteAtlas("aribitrary file name that doesn't exist");
    }
    @Test
    public void getSpriteAtlasExistingImageTest(){
        // This test make sure when we pass name of the images that are in our asset folder
        // and they exist , getSpriteAtlas can load those image
        // we make sure by getSpriteAtlas works by checking if image returned is not null
        assertNotNull(getSpriteAtlas("enemies/Scientists.png"));
        assertNotNull(getSpriteAtlas("player/dino_1.png"));
        assertNotNull(getSpriteAtlas("menu/inLevelTopMenu/health_bar_outside.png"));
    }
}