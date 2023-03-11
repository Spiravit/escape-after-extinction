package group7;

import group7.Graphics.GraphicsPanel;
import group7.Graphics.GraphicsWindow;
import group7.Graphics.GraphicsGrid;
import group7.gameStates.*;


public class Game implements Runnable {


    public GraphicsWindow graphicsWindow;
    public GraphicsPanel graphicsPanel;

    // The current running state of the game
    public gameStates gameCurrentState;
    // State object in order to render state of gameCurrentState
    private State currentState;



    private GraphicsGrid graphicsGrid;



    int playerDinoNumber = 1;
    int levelSelected = 1;


    /**
     * Constructor for Game class. It launches the game by creating GraphicsPanel,GraphicsGrid,GraphicWindow objects to
     * set up game's window. The default initial stage of game is set to be main menu so main menu will be rendered initially.
     *
     */
    public Game() {

        this.graphicsGrid = new GraphicsGrid(graphicsPanel, 20, 18);

       // The first stage of game is main menu once game is launched
        // so a mainMenuState stage object is initialized
        currentState = new MainMenuState(this);
        // setting initial state of game to be mainMenu
        gameCurrentState = gameStates.IN_MENU;

        // Since the initial state of game is main menu, we pass a mainMenuState object as a gameState object
        // to graphicsPanel so that graphicsPanel will render the main menu until.
        this.graphicsPanel =  new GraphicsPanel(currentState);

        this.graphicsWindow = new GraphicsWindow(this.graphicsPanel);
        
        // Giving input focus to graphicsPanel
        graphicsPanel.requestFocus();
        startGameLoop();
    }

    /**
     * starts the game loop by making a thread.
     *
     */
    private void startGameLoop(){
        Thread thread = new Thread(this);
        thread.start();
    }

    public void changeGameStates(gameStates gameStateParameter){

        // if the current stage of game is the same as desired one passed as argument
        // then we don't need to change the stage of game since we are already in it .
        if (gameCurrentState == gameStateParameter){
            return;
        }

        // If gameStateParameter is pause then set the isPause boolean in inLevel stage to true
        // in order to spawn pause menu
        // but still keep the gameStage to be inLevel stage since we are still in levels but paused
        if (gameCurrentState==gameStates.IN_LEVEL && gameStateParameter == gameStates.PAUSE){
            currentState.isPaused=true; // TODO fix it
            return;
        }



        // If game stage was not in main menu and the desired stage passed as parameter is main menu
        // then change gameCurrentState to be same as parameter
        // then initialize currentState object
        // and pass it to graphicPanel in order to render MainMenuState
        if (gameStateParameter == gameStates.IN_MENU ) {

            this.gameCurrentState = gameStateParameter;
            currentState = new MainMenuState(this);
            graphicsPanel.changeGameStates(currentState);
        }

        // If game stage was not in credit menu and the desired stage passed as parameter is credit menu
        // then change gameCurrentState to be CREDIT_SUB_MENU
        // then initialize currentState to be a credit menu state object
        // and pass it to graphicPanel in order to render level selection menu
        if (gameStateParameter == gameStates.CREDIT_SUB_MENU ) {
            this.gameCurrentState = gameStateParameter;
            currentState = new creditState(this);
            graphicsPanel.changeGameStates(currentState);
        }

        // If game stage was not in level selection menu and the desired stage passed as parameter is level selection menu
        // then change gameCurrentState to be level selection menu
        // then initialize currentState to be a  level selection menu object
        // and pass it to graphicPanel in order to render level selection menu
        if (gameStateParameter == gameStates.LEVEL_SELECTION_SUB_MENU ) {
            this.gameCurrentState = gameStateParameter;
            currentState = new LevelSelectionState(this);
            graphicsPanel.changeGameStates(currentState);
        }


        // If current state of game is in level selection menu but the desired next stage is playing game stage
        // then change the current stage of game to be inLevel stage in order to render and setting up game level
        if (gameStateParameter == gameStates.IN_LEVEL && gameCurrentState == gameStates.LEVEL_SELECTION_SUB_MENU){
            this.gameCurrentState = gameStates.IN_LEVEL;
            currentState = new InLevelState(this, playerDinoNumber, levelSelected);
            graphicsPanel.changeGameStates(currentState);
        }


        if (gameStateParameter == gameStates.PLAYER_SELECTION_SUB_MENU ) {
            this.gameCurrentState = gameStateParameter;
            currentState = new playerSelectionState(this);
            graphicsPanel.changeGameStates(currentState);

        }
        if (gameStateParameter == gameStates.NEXT ) {
            if (gameCurrentState== gameStates.LEVEL_SELECTION_SUB_MENU){

            }
            else if (gameCurrentState== gameStates.PLAYER_SELECTION_SUB_MENU){

            }
        }
        if (gameStateParameter == gameStates.PERV ) {

            if (gameCurrentState== gameStates.LEVEL_SELECTION_SUB_MENU){

            }
            else if (gameCurrentState== gameStates.PLAYER_SELECTION_SUB_MENU){

            }

        }
        if (gameStateParameter == gameStates.RESTART ) {
            this.gameCurrentState = gameStates.IN_LEVEL;
            currentState = new InLevelState(this, playerDinoNumber, levelSelected);
            graphicsPanel.changeGameStates(currentState);
        }
        else if (gameStateParameter == gameStates.IN_LEVEL ) {
            // Here, once the game state is changed to in Level state (the state, where player is playing) in game class,
            // then we change game states in graphicsPanel to render the level state (rendering levels,players,...) .
            this.gameCurrentState = gameStateParameter;
            currentState = new InLevelState(this, playerDinoNumber, 2);
            graphicsPanel.changeGameStates(currentState);
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
                currentState.update();
            }
            else if (gameCurrentState == gameStates.IN_LEVEL ) {
                // If the current state of game is in playing state,
                // then use the update method of inLevelState
                // where it updates the player, ...
                currentState.update();
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
