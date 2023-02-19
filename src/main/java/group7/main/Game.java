package group7.main;

import group7.entities.Entity;

public class Game {
    public GameWindow gameWindow;
    public GamePanel gamePanel;
    public Entity Player; // this will be removed !!
    public Game(){
        Entity Player= new Entity(100,200,10,10);
        gamePanel =  new GamePanel(Player);
        gameWindow = new GameWindow(gamePanel);
        // Giving input focus to gamePanel
        gamePanel.requestFocus();
    }
}
