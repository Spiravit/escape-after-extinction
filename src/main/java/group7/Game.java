package group7;

import group7.Graphics.GraphicsPanel;
import group7.Graphics.GraphicsWindow;
import group7.Graphics.GraphicsGrid;
import group7.entities.Player;
import group7.levels.LevelData;
import group7.levels.LevelManager;


public class Game implements Runnable {
    public GraphicsWindow graphicsWindow;
    public GraphicsPanel graphicsPanel;
    public Player player; // TODO: this will be removed !!
    private LevelManager levelManager;
    private GraphicsGrid graphicsGrid;

    public Game() {
        this.graphicsGrid = new GraphicsGrid(graphicsPanel, 15, 10);
        this.levelManager = new LevelManager();
        this.levelManager.loadLevel(1);
        this.player = new Player(1, 3, this.levelManager.getLevelData());  
        this.graphicsPanel =  new GraphicsPanel(this.player, this.levelManager);
        this.graphicsWindow = new GraphicsWindow(this.graphicsPanel);
        
        // Giving input focus to graphicsPanel
        graphicsPanel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop(){
        Thread thread = new Thread(this);
        thread.start();
    }


    @Override
    public void run() {
        while(true) {
            player.update();
            graphicsPanel.repaint();

            try {
                Thread.sleep(10); // TODO: change this back to 10
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
