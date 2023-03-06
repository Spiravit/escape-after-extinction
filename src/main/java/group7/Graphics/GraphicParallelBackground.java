package group7.Graphics;

import group7.utils.AssetLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

import static group7.Graphics.GraphicsPanel.panelHeight;
import static group7.Graphics.GraphicsPanel.panelWidth;

public class GraphicParallelBackground {
    private BufferedImage mainPageBackground_Layer_1,
            mainPageBackground_Layer_2,mainPageBackground_Layer_3,
            mainPageBackground_Layer_4,mainPageBackground_Layer_5,
            mainPageBackground_Layer_6,mainPageBackground_Layer_7,
            mainPageBackground_Layer_8;
    private float increment_background_5 = 0f;
    private float increment_background_2 = 0f;
    private float increment_background_3 = 0f;
    private float increment_background_4 = 0f;
    private float increment_background_6 = 0f;
    private float increment_background_7 = 0f;
    private float increment_background_8 = 0f;
    public GraphicParallelBackground(){
        mainPageBackground_Layer_1 = AssetLoader.getSpriteAtlas(AssetLoader.MAINPAGE_Layer_1);
        mainPageBackground_Layer_2 = AssetLoader.getSpriteAtlas(AssetLoader.MAINPAGE_Layer_2);
        mainPageBackground_Layer_3 = AssetLoader.getSpriteAtlas(AssetLoader.MAINPAGE_Layer_3);
        mainPageBackground_Layer_4 = AssetLoader.getSpriteAtlas(AssetLoader.MAINPAGE_Layer_4);
        mainPageBackground_Layer_5 = AssetLoader.getSpriteAtlas(AssetLoader.MAINPAGE_Layer_5);
        mainPageBackground_Layer_6 = AssetLoader.getSpriteAtlas(AssetLoader.MAINPAGE_Layer_6);
        mainPageBackground_Layer_7 = AssetLoader.getSpriteAtlas(AssetLoader.MAINPAGE_Layer_7);
        mainPageBackground_Layer_8 = AssetLoader.getSpriteAtlas(AssetLoader.MAINPAGE_Layer_8);
    }
    public void renderParallelBackground(Graphics g){
        g.setColor(new Color(0,0,0));
        g.fillRect(0,0,panelWidth,panelHeight);
        increment_background_2 += 0;
        increment_background_3 += 0.5;
        increment_background_4 += 0.5;
        increment_background_5 += 1.5;
        increment_background_6 += 0;
        increment_background_7 += 0;
        increment_background_8 += 2;
        if (increment_background_8 >= panelWidth){
            increment_background_8=0;
        }
        if (increment_background_7 >= panelWidth){
            increment_background_7=0;
        }
        if (increment_background_6 >= panelWidth){
            increment_background_6=0;
        }
        if (increment_background_5 >= panelWidth){
            increment_background_5=0;
        }
        if (increment_background_4 >= panelWidth){
            increment_background_4=0;
        }
        if (increment_background_3 >= panelWidth){
            increment_background_3=0;
        }
        if (increment_background_2 >= panelWidth){
            increment_background_2=0;
        }

        g.drawImage(mainPageBackground_Layer_1,0,(int)(0.125*panelHeight), (int) panelWidth,(int) (0.75*panelHeight),null);
        g.drawImage(mainPageBackground_Layer_2,(int)(0+increment_background_2),(int)(0.125*panelHeight), (int) panelWidth,(int) (0.75*panelHeight),null);
        g.drawImage(mainPageBackground_Layer_2,(int)(0+increment_background_2-1280),(int)(0.125*panelHeight), (int) panelWidth,(int) (0.75*panelHeight),null);
        g.drawImage(mainPageBackground_Layer_3,(int)(0+increment_background_3),(int)(0.125*panelHeight), (int) panelWidth,(int) (0.75*panelHeight),null);
        g.drawImage(mainPageBackground_Layer_3,(int)(0+increment_background_3-1280),(int)(0.125*panelHeight), (int) panelWidth,(int) (0.75*panelHeight),null);
        g.drawImage(mainPageBackground_Layer_4,(int)(0+increment_background_4),(int)(0.125*panelHeight), (int) panelWidth,(int) (0.75*panelHeight),null);
        g.drawImage(mainPageBackground_Layer_4,(int)(0+increment_background_4-1280),(int)(0.125*panelHeight), (int) panelWidth,(int) (0.75*panelHeight),null);
        g.drawImage(mainPageBackground_Layer_5,(int)(0+increment_background_5),(int)(0.125*panelHeight), (int) panelWidth,(int) (0.75*panelHeight),null);
        g.drawImage(mainPageBackground_Layer_5,(int)(0+increment_background_5-1280),(int)(0.125*panelHeight), (int) panelWidth,(int) (0.75*panelHeight),null);
        g.drawImage(mainPageBackground_Layer_6,(int)(0+increment_background_6),(int)(0.125*panelHeight), (int) panelWidth,(int) (0.75*panelHeight),null);
        g.drawImage(mainPageBackground_Layer_6,(int)(0+increment_background_6-1280),(int)(0.125*panelHeight), (int) panelWidth,(int) (0.75*panelHeight),null);
        g.drawImage(mainPageBackground_Layer_7,(int)(0+increment_background_7),(int)(0.125*panelHeight), (int) panelWidth,(int) (0.75*panelHeight),null);
        g.drawImage(mainPageBackground_Layer_7,(int)(0+increment_background_7-1280),(int)(0.125*panelHeight), (int) panelWidth,(int) (0.75*panelHeight),null);
        g.drawImage(mainPageBackground_Layer_8,(int)(0+increment_background_8),(int)(0.125*panelHeight), (int) panelWidth,(int) (0.75*panelHeight),null);
        g.drawImage(mainPageBackground_Layer_8,(int)(0+increment_background_8-1280),(int)(0.125*panelHeight), (int) panelWidth,(int) (0.75*panelHeight),null);
    }
}
