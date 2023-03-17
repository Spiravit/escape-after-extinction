package group7.userInterface;

import group7.Graphics.GraphicsGrid;
import group7.helperClasses.AssetLoader;
import group7.helperClasses.BackgroundMovingSpeed;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static group7.Graphics.GraphicsPanel.panelHeight;
import static group7.Graphics.GraphicsPanel.panelWidth;

/**
 * is responsible to create and render animated backgrounds on game window.
 */
public class UiParallelBackground {
    ArrayList<BufferedImage> backgroundImages;
    ArrayList<Double> backgroundsIncrementSpeed;
    ArrayList<Double> backgroundsPositionX;
    int scale1,scale2;

    /**
     * Constructor for creating animated backgrounds
     * @param NumberOfBackgrounds   The number of backgrounds used to create animated background
     * @param AssetAddress  The location where each background is located
     * @param scale1    width scale of backgrounds relative to width of game window
     * @param scale2     height scale of backgrounds relative to height of game window
     */
    public UiParallelBackground(int NumberOfBackgrounds, String AssetAddress, int scale1, int scale2){
        BackgroundMovingSpeed.setParallelMovingSpeeds();
        backgroundImages = new ArrayList<>();
        backgroundsIncrementSpeed = BackgroundMovingSpeed.returnBackgroundSpeeds(NumberOfBackgrounds);
        backgroundsPositionX = new ArrayList<>();
        for (int i=0;i< NumberOfBackgrounds;i++){
            backgroundImages.add(AssetLoader.getSpriteAtlas(AssetAddress+Integer.toString(i+1)+".png"));
            backgroundsPositionX.add(0.0);
        }
        this.scale1=scale1;
        this.scale2=scale2;
    }

    /**
     * Renders the animated background on game window
     * @param g passed as argument in order to draw the background on game window
     */
    public void renderParallelBackground(Graphics g){
        g.setColor(new Color(0,0,0));
        g.fillRect(0,0,panelWidth,panelHeight);
        for (int i = 0; i< backgroundsPositionX.size(); i++){
            Double previousIncrement = backgroundsPositionX.get(i);
            backgroundsPositionX.set(i,previousIncrement+backgroundsIncrementSpeed.get(i));
            if (backgroundsPositionX.get(i) >= panelWidth){
                backgroundsPositionX.set(i,0.0);
            }
        }
        for (int i=0; i< backgroundImages.size();i++){
            g.drawImage(backgroundImages.get(i),(int)(0+ backgroundsPositionX.get(i)), scale1 * GraphicsGrid.scaleY,(int) panelWidth,(int) (scale2*GraphicsGrid.scaleY),null);
            g.drawImage(backgroundImages.get(i),(int)(0+ backgroundsPositionX.get(i)-panelWidth),scale1 * GraphicsGrid.scaleY,(int) panelWidth,(int) (scale2*GraphicsGrid.scaleY),null);
        }
    }
}
