package group7;

import group7.Graphics.GraphicsPanel;
import group7.Graphics.GraphicsWindow;
import group7.Graphics.RenderGrid;
import group7.entities.Player;
import group7.levels.LevelData;
import group7.levels.LevelManager;


public class Game implements Runnable {
    public GraphicsWindow gameWindow;
    public GraphicsPanel gamePanel;
    public Player player; // this will be removed !!
    private LevelManager levels;
    private RenderGrid renderGrid;

    public Game(){
        LevelManager levels = new LevelManager();
        RenderGrid renderGrid = new RenderGrid(gamePanel, 15, 10);
        player = new Player(100, 200, renderGrid, levels.getLevel(1));
        gamePanel =  new GraphicsPanel(player,levels);
        gameWindow = new GraphicsWindow(gamePanel);
        
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
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
