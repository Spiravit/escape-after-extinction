package group7.Graphics;

import group7.Game;
import group7.gameStates.gameStates;
import group7.helperClasses.AssetLoader;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class GraphicsButtons {
    private int buttonPositionX, buttonPositionY, row, index;
    private gameStates buttonGameStates;
    private Game game;
    private BufferedImage[] imgs;
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;

    public GraphicsButtons(Game game, int buttonPositionX, int buttonPositionY, int row, gameStates buttonGameStates) {
        index = 0;
        this.game=game;
        this.buttonPositionX = buttonPositionX;
        this.buttonPositionY = buttonPositionY;
        this.row = row;
        this.buttonGameStates = buttonGameStates;
        bounds = new Rectangle(buttonPositionX,buttonPositionY,2*GraphicsGrid.getScaleX(),GraphicsGrid.getScaleY());
        loadButtonsSprites();
    }

    private void loadButtonsSprites() {
        imgs = new BufferedImage[5]; // for each button there are two conditions, hovered and not hovered
        BufferedImage temp = AssetLoader.getSpriteAtlas(AssetLoader.MAIN_MENU_BUTTONS);
        int menuButtonSpriteHeight = 70;
        int menuButtonSpriteWidth = temp.getWidth() / 2;
        //System.out.println(menuButtonSpriteWidth);
        for (int i = 0; i < 2; i++)
            imgs[i] = temp.getSubimage(i * menuButtonSpriteWidth, row * menuButtonSpriteHeight, menuButtonSpriteWidth, menuButtonSpriteHeight);
    }

    public void render(Graphics g) {
        g.drawImage(imgs[index],buttonPositionX,buttonPositionY,2*GraphicsGrid.getScaleX(),GraphicsGrid.getScaleY(),null);
    }

    public void update() {
        if (mouseOver || mousePressed)
            index = 1;
        else {
            index = 0;
        }
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public void applyGamestate() {
        game.changeGameStates(buttonGameStates);
    }

    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

    public boolean isMouseInButton (MouseEvent e) {
        return bounds.contains(e.getX(), e.getY());
    }
    public gameStates getButtonGameStates() {
        return buttonGameStates;
    }

}
