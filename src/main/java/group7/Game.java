package group7;

import group7.Graphics.GraphicsPanel;
import group7.Graphics.GraphicsWindow;
import group7.Graphics.GraphicsGrid;
import group7.entities.Player;
import group7.levels.LevelData;
import group7.levels.LevelManager;


public class Game implements Runnable {
    public GraphicsWindow gameWindow;
    public GraphicsPanel gamePanel;
    public Player player; // TODO: this will be removed !!
    private LevelManager levelManager;
    private GraphicsGrid graphicsGrid;

    public Game() {
        this.graphicsGrid = new GraphicsGrid(gamePanel, 15, 10);
        this.levelManager = new LevelManager(graphicsGrid);
        this.levelManager.loadLevel(1);
        this.player = new Player(0, 0, this.graphicsGrid, this.levelManager.getLevelData());
        this.gamePanel =  new GraphicsPanel(this.player, this.levelManager);
        this.gameWindow = new GraphicsWindow(this.gamePanel);
        
        // Giving input focus to gamePanel
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop(){
        Thread thread = new Thread(this);
        thread.start();
    }


    @Override
    public void run(){
        while(true){
            player.update();
            gamePanel.repaint();

            try {
                Thread.sleep(10); // TODO: change this back to 10
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
