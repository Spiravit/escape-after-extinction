package group7.gameStates;

import group7.Game;
import group7.Graphics.GraphicsButtons;
import group7.Graphics.GraphicsGrid;
import group7.utils.AssetLoader;
import static group7.Graphics.GraphicsPanel.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MainMenuState extends State {
    private GraphicsButtons[] mainMenuButtons = new GraphicsButtons[3]; // 3 since there are 3 buttons in Main menu
    private BufferedImage mainMenuBackground;
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

    // The Heigh of main Menu background
    private static final int MAIN_MENU_BACKGROUND_HEIGH=80+4*GraphicsGrid.getScaleY()+20;
    private static final int MAIN_MENU_BACKGROUND_WIDTH=4*GraphicsGrid.getScaleX(); // The width of main Menu is 4 Grids

    public MainMenuState(Game game) {
        super(game);
        mainPageBackground_Layer_1 = AssetLoader.getSpriteAtlas(AssetLoader.MAINPAGE_Layer_1);
        mainPageBackground_Layer_2 = AssetLoader.getSpriteAtlas(AssetLoader.MAINPAGE_Layer_2);
        mainPageBackground_Layer_3 = AssetLoader.getSpriteAtlas(AssetLoader.MAINPAGE_Layer_3);
        mainPageBackground_Layer_4 = AssetLoader.getSpriteAtlas(AssetLoader.MAINPAGE_Layer_4);
        mainPageBackground_Layer_5 = AssetLoader.getSpriteAtlas(AssetLoader.MAINPAGE_Layer_5);
        mainPageBackground_Layer_6 = AssetLoader.getSpriteAtlas(AssetLoader.MAINPAGE_Layer_6);
        mainPageBackground_Layer_7 = AssetLoader.getSpriteAtlas(AssetLoader.MAINPAGE_Layer_7);
        mainPageBackground_Layer_8 = AssetLoader.getSpriteAtlas(AssetLoader.MAINPAGE_Layer_8);

        mainMenuBackground = AssetLoader.getSpriteAtlas(AssetLoader.MAIN_MENU_BACKGROUND);
        mainMenuButtons[0] = new GraphicsButtons(game,1280 / 2, 180, 0, gameStates.IN_LEVEL);
        mainMenuButtons[1] = new GraphicsButtons(game,1280 / 2, 180 + 40 + GraphicsGrid.getScaleY(), 1, gameStates.IN_LEVEL);
        mainMenuButtons[2] = new GraphicsButtons(game,1280 / 2, 180 + 80 + 2 * GraphicsGrid.getScaleY(), 2, gameStates.QUIT);
    }

    @Override
    public void update() {
        for (GraphicsButtons button : mainMenuButtons)
            button.update();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, 1280, 720);
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
        // Draw Main menu background
        g.drawImage(mainMenuBackground,
                panelWidth / 2 - 2*GraphicsGrid.getScaleX(),
                140,
                MAIN_MENU_BACKGROUND_WIDTH,
                MAIN_MENU_BACKGROUND_HEIGH,
                null);

        for (GraphicsButtons buttons : mainMenuButtons) {
            buttons.render(g);
        }
        //g.setColor(Color.black);
        //g.drawString("MENU", 100, 200);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            game.changeGameStates(gameStates.IN_LEVEL);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (GraphicsButtons button : mainMenuButtons) {
            if (button.isMouseInButton(e)) {
                button.setMousePressed(true);
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (GraphicsButtons button : mainMenuButtons) {
            if (button.isMouseInButton(e)) {
                if (button.isMousePressed())
                    button.applyGamestate();
                break;
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for (GraphicsButtons button : mainMenuButtons) {
            button.resetBools();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (GraphicsButtons button : mainMenuButtons)
            button.setMouseOver(false);

        for (GraphicsButtons button : mainMenuButtons) {
            if (button.isMouseInButton(e)) {
                button.setMouseOver(true);
                break;
            }

        }
    }
}
