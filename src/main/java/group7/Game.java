package group7;

import group7.Graphics.GraphicsPanel;
import group7.Graphics.GraphicsWindow;
import group7.Graphics.GraphicsGrid;
import group7.gameStates.*;


public class Game implements Runnable {
    public GraphicsWindow graphicsWindow;
    public GraphicsPanel graphicsPanel;


    // Two different states of game
    private InLevelState inLevelState;
    private  MainMenuState mainMenuState;


    private GraphicsGrid graphicsGrid;
    public gameStates gameCurrentState; // The current running state of the game

    private boolean playerSelectFlag = false;                                           // **TEST: MULTIPLE LEVELS **
    private boolean levelSelectFlag = false;                                            // **TEST: MULTIPLE LEVELS **

    int playerDinoNumber = 1;
    int levelSelected = 1;                                                              // **TEST: MULTIPLE LEVELS **

    public Game() {
        gameCurrentState = gameStates.IN_MENU; // setting initial state of game to be mainMenu
        this.graphicsGrid = new GraphicsGrid(graphicsPanel, 20, 18);
       // inLevelState = new InLevelState(this);
        mainMenuState = new MainMenuState(this);
        inLevelState = new InLevelState(this, playerDinoNumber, levelSelected);            // **TEST: MULTIPLE LEVELS **
        // Since the initial state of game is main menu, we pass a mainMenuState object as a gameState object
        // to graphicsPanel so that graphicsPanel will render the main menu until.
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

    public void changeGameStates(gameStates gameStateParameter){
        // changing the gameCurrentState to indicate the current running state of game in game class
        if (gameStateParameter==gameStates.IN_LEVEL && gameCurrentState==gameStates.IN_LEVEL){
            if (inLevelState.isPaused==true){
                inLevelState.isPaused=false;
            }
        }
        if (gameCurrentState == gameStateParameter ){
            return;
        }
        if (gameStateParameter == gameStates.PAUSE){
            inLevelState.isPaused=true;
        }
        if (gameStateParameter == gameStates.IN_MENU ) {
            // changing the gameStates field in graphicsPanel so that
            // the graphicsPanel will use the rendering methods of current running state to render the game
            // Here, once the game state is changed to main menu in game class,
            // then we change game states in graphicsPanel to render the main menu.
            this.gameCurrentState = gameStateParameter;
            mainMenuState = new MainMenuState(this);
            graphicsPanel.changeGameStates(mainMenuState);
            playerSelectFlag = false;
            levelSelectFlag = false;
        }
        if (gameStateParameter == gameStates.CREDIT_SUB_MENU ) {
            this.gameCurrentState = gameStateParameter;
            mainMenuState = new creditState(this);
            graphicsPanel.changeGameStates(mainMenuState);
        }
        if (gameStateParameter == gameStates.PLAYER_SELECTION_SUB_MENU ) {
            this.gameCurrentState = gameStateParameter;
            mainMenuState = new playerSelectionState(this);
            graphicsPanel.changeGameStates(mainMenuState);
            playerSelectFlag = true;
        }
        if (gameStateParameter == gameStates.LEVEL_SELECTION_SUB_MENU ) {
            this.gameCurrentState = gameStateParameter;
            mainMenuState = new LevelSelectionState(this);
            graphicsPanel.changeGameStates(mainMenuState);
            playerSelectFlag = true;
        }
        if (gameStateParameter == gameStates.NEXT ) {
            if ( playerSelectFlag == true ) {
                playerDinoNumber = mainMenuState.incrementIndexCharacterDemo() + 1;
            } else if ( levelSelectFlag == true ) {
                levelSelected = mainMenuState.incrementIndexCharacterDemo() + 1;
            }
        }
        if (gameStateParameter == gameStates.PERV ) {
            if ( playerSelectFlag == true ) {
                playerDinoNumber = mainMenuState.decrementIndexCharacterDemo() + 1;
            } else if ( levelSelectFlag == true ) {
                levelSelected = mainMenuState.decrementIndexLevelNumber() + 1;
            }        
        }
        if (gameStateParameter == gameStates.RESTART ) {
            this.gameCurrentState = gameStates.IN_LEVEL;
            inLevelState = new InLevelState(this, playerDinoNumber, levelSelected);
            graphicsPanel.changeGameStates(inLevelState);
        }
        else if (gameStateParameter == gameStates.IN_LEVEL ) {
            // Here, once the game state is changed to in Level state (the state, where player is playing) in game class,
            // then we change game states in graphicsPanel to render the level state (rendering levels,players,...) .
            this.gameCurrentState = gameStateParameter;
            inLevelState = new InLevelState(this, playerDinoNumber, levelSelected);
            graphicsPanel.changeGameStates(inLevelState);
        } else if (gameStateParameter == gameStates.QUIT) {
            // if the current state of game is changed to be quit, then terminate the program
            this.gameCurrentState = gameStateParameter;
            System.exit(0);
        }
    }

    @Override
    public void run() {
        while(true) {
            if (gameCurrentState == gameStates.IN_MENU) {
                // If the current state of game is in main menu state,
                // then use the update method of inLevelState
                // TODO writing comments here
                mainMenuState.update();
            }
            else if (gameCurrentState == gameStates.IN_LEVEL ) {
                // If the current state of game is in playing state,
                // then use the update method of inLevelState
                // where it updates the player, ...
                inLevelState.update();
            }

            // The repaint will render the game corresponding to a gameState field, holding current running state
            // of game, in graphicsPanel.
            graphicsPanel.repaint();
            try {
                Thread.sleep(10); // TODO: change this back to 10
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
