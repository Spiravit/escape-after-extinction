package group7.userInterface;

import group7.Game;
import group7.Graphics.GraphicsButtons;
import group7.Graphics.GraphicsGrid;
import group7.Graphics.GraphicsPanel;
import group7.gameStates.gameStates;
import group7.helperClasses.AssetLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static group7.Graphics.GraphicsPanel.*;

/**
 * This class is responsible for creating and rendering an in-game menu.
 * The in-game menu will be located at top of the game. The in-game menu will have
 * a pause button, time counter, number of eggs and keys collected, health bar indicating health of player.
 *
 * @author      Mohammad Parsaei
 * @author      Karmen Yung
 * @author      Salman Ayaz
 * @author      Chen Min
 * @since       1.0
 */
public class UiTopMenuBar {
    // Holding the time (in millisecond) once constructor of UiTopMenuBar was called
    private long startTime;

    // Used to count the seconds for time counter at in-game menu
    private int second_counter;

    // Used to count the minute for time counter at in-game menu
    private int minute_counter;

    // The pause menu button located at in-game menu in order to spawn pause menu
    protected GraphicsButtons pauseMenuButton;

    // topMenuSkyParallelBackground is used for rendering parallel background for in-game menu
    private UiParallelBackground topMenuSkyParallelBackground;

    //topMenuLandBackground:  BufferedImage for grass land used in in-game menu
    private BufferedImage topMenuLandBackground;

    //healthBarBoundary: Image for health bar shape
    private BufferedImage healthBarBoundary;

    //healthBarInside: Image for liquid inside healthBarBoundary indicating remaining health of player
    private BufferedImage healthBarInside;

    // retroFont is used to draw time counter at in-game menu
    private Font retroFont ;

    /**
     * Constructor for UiTopMenuBar class
     *
     * @param levelNumber   levelNumber is passed to constructor indicating
     *                      which level the player is playing now. Then based on
     *                      Level, pass corresponding in-game's menu backgrounds to UiParallelBackground field
     *                      to render the animated background for in-game menu's background.
     *
     *
     * @param game         a game Object passed to constructor since it
     *                     was required for creating a Pause button. Pause button
     *                     needs the game object. Because once the button is clicked, it changes
     *                     the gameState inside game object.
     *
     */
    public UiTopMenuBar(int levelNumber, Game game){
        // Initialize the topMenuSkyParallelBackground object
        // passed 4 to UiParallelBackground constructor since animated background for in-game menu is using 4 images
        // passed 0 as scale1 meaning that position y of animated background is at 0 and scale2 is indicating
        // that height of animated background is 2 tiles
        topMenuSkyParallelBackground = new UiParallelBackground(4,"menu/inLevelTopMenu/level_"+levelNumber+"/",0,2);

        startTime = System.currentTimeMillis(); // get current time once a UiTopMenuBar object was created

        second_counter=0; // Set the seconds starting at 0

        minute_counter=0; // Set the minutes starting at 0

        // Setting up the pause button, it will be located at right top of game
        // passed 5 as row , since the sprite for Pause button is in 6th row of mainMenuButtons.png
        // passed gameStates.PAUSE as the objective of this button. When the button is clicked , it should change the game
        // to pause mode.
        pauseMenuButton = new GraphicsButtons(game, (int)(panelWidth-2.5*GraphicsGrid.scaleX), (int)(0.5*GraphicsGrid.getScaleY()), 5, gameStates.PAUSE);

        loadFont(); // loading the custom font at assets/font directory

        // Loading the image of grass required for in-game menu into topMenuLandBackground
        topMenuLandBackground = AssetLoader.getSpriteAtlas("menu/inLevelTopMenu/landTopMenu.png");

        // Loading the images for health bar
        healthBarBoundary = AssetLoader.getSpriteAtlas(AssetLoader.HEALTH_BAR_BOUNDARY);
        healthBarInside = AssetLoader.getSpriteAtlas(AssetLoader.HEALTH_BAR_INSIDE);
    }

    /**
     * Calls the update method of pause button at in-game menu in order to check if the button has clicked or no.
     *
     * @return void
     */
    public void update(){
        // By checking update method in pause button
        // we make sure if the button was clicked then we change the gameState
        pauseMenuButton.update();
    }

    /**
     * renders in-game menu on the game window. It will render the background, time counter,
     *  health bar and a pause button on top part of game's window.
     *
     * @param g             g is a Graphics object used to draw on game window.
     * @param isPaused     isPaused is a boolean indicating if the game is paused or not. It is passed
     *                     as parameter in order to pause updating time and rendering it.
     * @param health        Health is an integer indicating health of a player. It is passed in order
     *                      to render player's health on the in-game menu.
     */
    public void renderTopMenuBar (Graphics g, boolean isPaused, int health){
        topMenuSkyParallelBackground.renderParallelBackground(g);
        g.drawImage(topMenuLandBackground,0,(int)(1.25*GraphicsGrid.scaleY),GraphicsPanel.panelWidth,(int)(0.75*GraphicsGrid.scaleY),null);
        renderTime(g,isPaused);
        renderHealth(g,health);
        pauseMenuButton.render(g);
    }


    /**
     *
     * @param g
     * @param health
     */
    private void renderHealth(Graphics g,int health) {
        int healthBarInsideHeight = healthBarInside.getHeight();
        int healthBarInsideWidth = healthBarInside.getWidth();
        int remainingHealthBarWidth = (healthBarInsideWidth-131)*(health/100) + 131;
        BufferedImage remainingHealthBar = healthBarInside.getSubimage(0,0,remainingHealthBarWidth,healthBarInsideHeight);
        g.drawImage(healthBarBoundary,(int)(0.5*GraphicsGrid.scaleX),(int)(0.5*GraphicsGrid.scaleY),2*GraphicsGrid.scaleX,(int)(0.5*GraphicsGrid.scaleY),null);
        g.drawImage(remainingHealthBar,(int)(0.5*GraphicsGrid.scaleX),(int)(0.5*GraphicsGrid.scaleY),(int)(2*GraphicsGrid.scaleX *(remainingHealthBarWidth/healthBarInsideWidth)),(int)(0.5*GraphicsGrid.scaleY),null);
    }

    /**
     *
     */
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

    /**
     *
     * @param g
     * @param isPaused
     */
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
