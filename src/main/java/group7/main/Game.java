package group7.main;

import group7.entities.Player;
import group7.levels.LevelData;

public class Game implements Runnable{
    public GameWindow gameWindow;
    public GamePanel gamePanel;
    public Player player; // this will be removed !!

    public final static int TILES_SIZE = 32;
    public final static float GAME_SIZE_SCALE = 1.0f;
    public final static int NUMBER_OF_TILES_IN_WIDTH = 15;
    public final static int NUMBER_OF_TILES_IN_HEIGHT = 10 ;


    public Game(){
        player = new Player(100, 200, 24*5, 24*5, new LevelData(1000, 1000));
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
