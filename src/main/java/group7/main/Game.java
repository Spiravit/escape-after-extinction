package group7.main;

import group7.entities.Player;
import group7.levels.LevelData;

public class Game implements Runnable {
    public GameWindow gameWindow;
    public GamePanel gamePanel;
    public Player player; // this will be removed !!
    public Game(){
        player = new Player(100, 200, new LevelData(1000, 1000));
        gamePanel =  new GamePanel(player);
        gameWindow = new GameWindow(gamePanel);
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
