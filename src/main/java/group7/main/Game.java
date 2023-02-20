package group7.main;

import group7.entities.Player;

public class Game implements Runnable{
    public GameWindow gameWindow;
    public GamePanel gamePanel;
    public Player player; // this will be removed !!
    public Game(){
        player = new Player(100,200,10,10);
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


    public void run(){
        while(true){
            player.updatePlayer();
            gamePanel.repaint();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
