package group7;

import group7.Graphics.GraphicsPanel;
import group7.Graphics.GraphicsWindow;
import group7.Graphics.GraphicsGrid;
import group7.entities.Player;
import group7.gameStates.InLevelState;
import group7.gameStates.MainMenuState;
import group7.gameStates.State;
import group7.gameStates.gameStates;
import group7.levels.LevelData;
import group7.levels.LevelManager;


public class Game implements Runnable {
    public GraphicsWindow graphicsWindow;
    public GraphicsPanel graphicsPanel;
    //public Player player; // TODO: this will be removed !!
    //private LevelManager levelManager;
    private InLevelState inLevelState;
    private  MainMenuState mainMenuState;
    private GraphicsGrid graphicsGrid;
    public gameStates gameCurrentState;

    public Game() {
        gameCurrentState = gameStates.IN_MENU;
        this.graphicsGrid = new GraphicsGrid(graphicsPanel, 15, 10);
        inLevelState = new InLevelState(this);
        mainMenuState = new MainMenuState(this);
        //this.levelManager = new LevelManager();
        //this.levelManager.loadLevel(1);
        //this.player = new Player(1, 1, this.levelManager.getLevelData());
        this.graphicsPanel =  new GraphicsPanel(mainMenuState);
        this.graphicsWindow = new GraphicsWindow(this.graphicsPanel);
        
        // Giving input focus to graphicsPanel
        graphicsPanel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop(){
        Thread thread = new Thread(this);
        thread.start();
    }

    public void changeGameStates(gameStates gameCurrentState){
        this.gameCurrentState = gameCurrentState;
        if (gameCurrentState == gameStates.IN_MENU ) {
            graphicsPanel.changeGameStates(mainMenuState);
        }
        else if (gameCurrentState == gameStates.IN_LEVEL ) {
            graphicsPanel.changeGameStates(inLevelState);
        } else if (gameCurrentState == gameStates.QUIT) {
            System.exit(0);
        }
    }



    @Override
    public void run() {
        while(true) {
            if (gameCurrentState == gameStates.IN_MENU ) {
                mainMenuState.update();
            }
            else if (gameCurrentState == gameStates.IN_LEVEL ) {
                inLevelState.update();
            }
            graphicsPanel.repaint();
            try {
                Thread.sleep(10); // TODO: change this back to 10
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
