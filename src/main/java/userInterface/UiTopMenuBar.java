package userInterface;

import group7.Game;
import group7.Graphics.GraphicsButtons;
import group7.Graphics.GraphicsGrid;
import group7.Graphics.GraphicsPanel;
import group7.gameStates.gameStates;
import group7.utils.AssetLoader;
import group7.utils.BackgroundMovingSpeed;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static group7.Graphics.GraphicsPanel.panelWidth;

public class UiTopMenuBar {
    private long startTime;
    private int second_counter;
    private int minute_counter;
    protected GraphicsButtons pauseMenuButton;
    private BufferedImage topMenuLandBackground;
    private BufferedImage backgroundParallelOne;
    private BufferedImage backgroundParallelTwo;
    private BufferedImage backgroundParallelThree;
    private BufferedImage backgroundParallelFour;
    private BufferedImage healthBarBoundary;
    private BufferedImage healthBarInside;
    private Font retroFont =  new Font("TimesRoman", Font.BOLD, 18);
    public UiTopMenuBar(int levelNumber, Game game){
        startTime = System.currentTimeMillis();
        second_counter=0;
        minute_counter=0;
        pauseMenuButton = new GraphicsButtons(game, (int)(panelWidth-2.5*GraphicsGrid.scaleX), (int)(0.5*GraphicsGrid.getScaleY()), 5, gameStates.PAUSE);
        loadFont();
        String topMenuBarLocation = "menu/inLevelTopMenu/level_"+levelNumber+"/";
        backgroundParallelOne = AssetLoader.getSpriteAtlas(topMenuBarLocation+"1"+".png");
        backgroundParallelTwo = AssetLoader.getSpriteAtlas(topMenuBarLocation+"2"+".png");
        backgroundParallelThree = AssetLoader.getSpriteAtlas(topMenuBarLocation+"3"+".png");
        backgroundParallelFour = AssetLoader.getSpriteAtlas(topMenuBarLocation+"4"+".png");
        topMenuLandBackground = AssetLoader.getSpriteAtlas("menu/inLevelTopMenu/landTopMenu.png");
        healthBarBoundary = AssetLoader.getSpriteAtlas(AssetLoader.HEALTH_BAR_BOUNDARY);
        healthBarInside = AssetLoader.getSpriteAtlas(AssetLoader.HEALTH_BAR_INSIDE);
    }
    public void renderTopMenuBar (Graphics g, boolean isPaused, int health){
        g.drawImage(backgroundParallelOne,0,0, GraphicsPanel.panelWidth,2* GraphicsGrid.scaleY,null);
        g.drawImage(backgroundParallelTwo,0,0,GraphicsPanel.panelWidth,2*GraphicsGrid.scaleY,null);
        g.drawImage(backgroundParallelThree,0,0,GraphicsPanel.panelWidth,2*GraphicsGrid.scaleY,null);
        g.drawImage(backgroundParallelFour,0,0,GraphicsPanel.panelWidth,2*GraphicsGrid.scaleY,null);
        g.drawImage(topMenuLandBackground,0,(int)(1.25*GraphicsGrid.scaleY),GraphicsPanel.panelWidth,(int)(0.75*GraphicsGrid.scaleY),null);
        renderTime(g,isPaused);
        renderHealth(g,health);
        pauseMenuButton.render(g);
    }

    private void renderHealth(Graphics g,int health) {
        int healthBarInsideHeight = healthBarInside.getHeight();
        int healthBarInsideWidth = healthBarInside.getWidth();
        int remainingHealthBarWidth = (healthBarInsideWidth-131)*(health/100) + 131;
        BufferedImage remainingHealthBar = healthBarInside.getSubimage(0,0,remainingHealthBarWidth,healthBarInsideHeight);
        g.drawImage(healthBarBoundary,(int)(0.5*GraphicsGrid.scaleX),(int)(0.5*GraphicsGrid.scaleY),2*GraphicsGrid.scaleX,(int)(0.5*GraphicsGrid.scaleY),null);
        g.drawImage(remainingHealthBar,(int)(0.5*GraphicsGrid.scaleX),(int)(0.5*GraphicsGrid.scaleY),(int)(2*GraphicsGrid.scaleX *(remainingHealthBarWidth/healthBarInsideWidth)),(int)(0.5*GraphicsGrid.scaleY),null);
    }

    private void loadFont(){
        InputStream is;
        try{
            is = getClass().getResourceAsStream("/assets/font/ThaleahFat.ttf");
            retroFont = Font.createFont(Font.TRUETYPE_FONT,is).deriveFont(30f);
        }
        catch (FontFormatException f){
            f.printStackTrace();
        }
        catch (IOException a){
            a.printStackTrace();
        }
    }
    private void renderTime(Graphics g, boolean isPaused){
        Long currentTime = System.currentTimeMillis();
        if (System.currentTimeMillis() - startTime >= 1000){
            startTime = currentTime;
            if (!isPaused)
                second_counter ++;
        }
        if (!isPaused && second_counter >= 60){
            second_counter =0;
            minute_counter ++;
        }
        String secondTimeString = Integer.toString(second_counter);
        if (secondTimeString.length() == 1){
            secondTimeString = "0"+secondTimeString;
        }
        Graphics2D g2D = (Graphics2D) g;
        g.setFont(retroFont);
        g.drawString("TIME : " + Integer.toString(minute_counter)+" : "+secondTimeString, 3*GraphicsGrid.scaleX,GraphicsGrid.scaleY);
    }

}
