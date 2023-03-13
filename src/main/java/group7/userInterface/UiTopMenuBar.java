package group7.userInterface;

import group7.Game;
import group7.Graphics.GraphicsGrid;
import group7.Graphics.GraphicsPanel;
import group7.gameStates.gameStates;
import group7.helperClasses.AssetLoader;
import group7.levels.LevelManager;

import static group7.helperClasses.buttonSpriteRow.*;

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
    protected UiButtons pauseMenuButton;

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

    // retroFont is used to draw time counter at in-game menu
    private LevelManager levelManager ;

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
    public UiTopMenuBar(int levelNumber, LevelManager levelManager, Game game){
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
        pauseMenuButton = new UiButtons(game, (int)(panelWidth-2.5*GraphicsGrid.scaleX), (int)(0.5*GraphicsGrid.getScaleY()), PAUSED_BUTTON, gameStates.PAUSE);

        loadFont(); // loading the custom font at assets/font directory

        // Loading the image of grass required for in-game menu into topMenuLandBackground
        topMenuLandBackground = AssetLoader.getSpriteAtlas("menu/inLevelTopMenu/landTopMenu.png");

        // Loading the images for health bar
        healthBarBoundary = AssetLoader.getSpriteAtlas(AssetLoader.HEALTH_BAR_BOUNDARY);
        healthBarInside = AssetLoader.getSpriteAtlas(AssetLoader.HEALTH_BAR_INSIDE);

        this.levelManager = levelManager;
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
    public void renderTopMenuBar (Graphics g, boolean isPaused, int health,int eggCollected, int keyCollected){
        topMenuSkyParallelBackground.renderParallelBackground(g);
        g.drawImage(topMenuLandBackground,0,(int)(1.25*GraphicsGrid.scaleY),GraphicsPanel.panelWidth,(int)(0.75*GraphicsGrid.scaleY),null);
        renderTime(g,isPaused);
        renderHealth(g,health);
        renderScore(g,100);
        renderEggCount(g,eggCollected);
        renderKeyCount(g,keyCollected);
        pauseMenuButton.render(g);
    }


    /**
     * renders remaining health left for player in current level that player is playing.
     *
     * @param g          Graphic g is passed as parameter in order to draw items on game window.
     * @param health    current health left for player as a percentage.
     */
    private void renderHealth(Graphics g,int health) {
        int healthBarInsideHeight = healthBarInside.getHeight();
        double healthBarInsideWidth = healthBarInside.getWidth();
        double remainingPercentage = (((double)health)/100);

        int remainingHealthBarWidth = (int) (((healthBarInsideWidth)) * (remainingPercentage));
        
        BufferedImage remainingHealthBar = healthBarInside.getSubimage(0,0,remainingHealthBarWidth,healthBarInsideHeight);
        g.drawImage(healthBarBoundary,(int)(0.2*GraphicsGrid.scaleX),(int)(0.3*GraphicsGrid.scaleY),(int)(2.5*GraphicsGrid.scaleX),(int)(1*GraphicsGrid.scaleY),null);
        g.drawImage(remainingHealthBar,(int)(0.2*GraphicsGrid.scaleX + 30),(int)(0.3*GraphicsGrid.scaleY),(int)(2*GraphicsGrid.scaleX *(remainingHealthBarWidth/healthBarInsideWidth)),(int)(1*GraphicsGrid.scaleY),null);
    }

    /**
     *  Loads custom font from assets folder in order to be used later in drawing strings on game window.
     *
     */
    private void loadFont(){
        InputStream is;
        try{
            is = getClass().getResourceAsStream("/assets/font/ThaleahFat.ttf");
            // Set size of font to be 30
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
     * renders the time that player is spending playing the current level.
     *
     * @param g             Graphic g is passed as parameter in order to draw items on game window.
     * @param isPaused      This boolean is passed in order if the game is paused
     *                     then stop updating time counter.
     */
    private void renderTime(Graphics g, boolean isPaused){
        // Get current time is milliseconds
        Long currentTime = System.currentTimeMillis();

        if (System.currentTimeMillis() - startTime >= 1000){
            // If current time is more or equal to 1 second in comparison to start
            // then set start time to current time

            startTime = currentTime;

            // If game is not paused then increment
            // the second_counter since a second has passed
            if (!isPaused)
                second_counter ++;
        }
        if (!isPaused && second_counter >= 60){
            // If the game was not paused and second counter is passing 60
            // then increment minute_counter since 60 seconds is a minute
            // and reset the second_counter to start from 0
            second_counter =0;
            minute_counter ++;
        }

        // Preparing second_counter to write it as string on window
        String secondTimeString = Integer.toString(second_counter);

        if (secondTimeString.length() == 1){
            // If second_counter was less than 10, then we need to add a 0 to its
            // string, For instance instead of having 9 , it is going to be 09
            // In order to make sure that string has always 2 characters
            secondTimeString = "0"+secondTimeString;
        }
        // Using Graphic2D in order to change the Font for drawing a string
        Graphics2D g2D = (Graphics2D) g;
        // Set the font to retroFont in order to draw the Key count on in-game top menu
        g.setFont(retroFont);

        // Drawing Time : minute : second on the game window
        g.drawString("TIME : " + Integer.toString(minute_counter)+" : "+secondTimeString, 3*GraphicsGrid.scaleX,GraphicsGrid.scaleY);
    }

    /**
     * draws the score of player while playing a level in in-game top menu.
     *
     * @param g         Graphic g is passed as parameter in order to draw items on game window.
     * @param score     score of player in current level is passed in order to be drawn by Graphic g.
     */
    private void renderScore(Graphics g,int score){
        // Using Graphic2D in order to change the Font for drawing a string
        Graphics2D g2D = (Graphics2D) g;
        // Set the font to retroFont in order to draw the Key count on in-game top menu
        g.setFont(retroFont);
        g.drawString("SCORE: " + score,7*GraphicsGrid.scaleX,GraphicsGrid.scaleY);
    };

    /**
     * renders number of eggs a player has collected and number of all eggs in current level player is playing.
     *
     * @param g                 Graphic g is passed as parameter in order to draw items on game window.
     * @param eggCollected      Number of eggs a player has collected currently.
     */
    private void renderEggCount(Graphics g, int eggCollected){
        // Using Graphic2D in order to change the Font for drawing a string
        Graphics2D g2D = (Graphics2D) g;
        // Set the font to retroFont in order to draw the Key count on in-game top menu
        g.setFont(retroFont);
        g.drawString("Egg: " + eggCollected +"/" + levelManager.getEggInCurrentLevel(),10*GraphicsGrid.scaleX,GraphicsGrid.scaleY);

    }

    /**
     * renders number of keys a player has collected and number of all keys in current level player is playing.
     *
     * @param g                 Graphic g is passed as parameter in order to draw items on game window.
     * @param keyCollected      Number of Keys a player has collected currently.
     */
    private void renderKeyCount(Graphics g, int keyCollected){
        // Using Graphic2D in order to change the Font for drawing a string
        Graphics2D g2D = (Graphics2D) g;
        // Set the font to retroFont in order to draw the Key count on in-game top menu
        g.setFont(retroFont);
        g.drawString("Keys: " + keyCollected +"/" + levelManager.getKeyInCurrentLevel(),12*GraphicsGrid.scaleX,GraphicsGrid.scaleY);
    }

}
