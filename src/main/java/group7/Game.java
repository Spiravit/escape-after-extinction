package group7;

import group7.Graphics.GraphicsPanel;
import group7.Graphics.GraphicsWindow;
import group7.Graphics.GraphicsGrid;
import group7.gameStates.*;
import group7.gameStates.LevelSelectionState;
import group7.gameStates.playerSelectionState;

/** 
* The class Game implements @see <a href=”https://docs.oracle.com/javase/7/docs/api/java/lang/Runnable.html”>label</a>.
* Renders and starts the game. Allows user to use in game menus to make selections and play or exit the game.
*
* @author  Mohammad Parsaei
* @author  Salman Ayaz
* @author  Karmen Yung
* @author  Chen Min
* @version 1.0
* @since 2023-03-13
*/
public class Game implements Runnable {
    public GraphicsWindow graphicsWindow;
    public GraphicsPanel graphicsPanel;

    // The current running state of the game
    public gameStates gameCurrentState;
    // State object in order to render state of gameCurrentState
    private State currentState;

    private GraphicsGrid graphicsGrid;

    int playerDinoNumber;
    int levelSelected;


    /**
     * Constructor for Game class. It launches the game by creating GraphicsPanel,GraphicsGrid,GraphicWindow objects to
     * set up game's window. The default initial stage of game is set to be main menu so main menu will be rendered initially.
     */
    public Game() {

        this.graphicsGrid = new GraphicsGrid(graphicsPanel, 20, 18);

        // The first stage of game is main menu once game is launched
        // so a mainMenuState stage object is initialized
        currentState = new MainMenuState(this);
        // setting initial state of game to be mainMenu
        gameCurrentState = gameStates.IN_MENU;

        levelSelected=1;
        playerDinoNumber=1;

        // Since the initial state of game is main menu, we pass a mainMenuState object as a gameState object
        // to graphicsPanel so that graphicsPanel will render the main menu until.
        this.graphicsPanel =  new GraphicsPanel(currentState);

        this.graphicsWindow = new GraphicsWindow(this.graphicsPanel);

        // Giving input focus to graphicsPanel
        graphicsPanel.requestFocus();
        startGameLoop();
    }

    /**
     * Starts the game loop by making a thread.
     */
    private void startGameLoop(){
        Thread thread = new Thread(this);
        thread.start();
    }


    /**
     * Changes state of game based on argument passed.
     * @param gameStateParameter    (The desired next state of game {@link gameState})
     */
    public void changeGameStates(gameStates gameStateParameter){

        // if the current stage of game is the same as desired one passed as argument
        // then we don't need to change the stage of game since we are already in it .
        if (gameCurrentState == gameStateParameter){
            return;
        }

        // If gameStateParameter is pause then set the isPause boolean in inLevel stage to true
        // in order to spawn pause menu
        // but still keep the gameStage to be inLevel stage since we are still in levels but paused
        else if (gameCurrentState==gameStates.IN_LEVEL && gameStateParameter == gameStates.PAUSE){
            currentState.isPaused=true;
            return;
        }

        // If gameStateParameter is resume then set the isPause boolean in inLevel stage to false
        // in order to continue playing level and stop seeing pause menu
        // but still keep the gameStage to be inLevel stage since we are still in levels
        else if (gameCurrentState==gameStates.IN_LEVEL && gameStateParameter == gameStates.RESUME){
            currentState.isPaused=false;
            return;
        }

        // If gameStateParameter is Next_level then we need to make the next level
        // to play it.
        else if (gameCurrentState==gameStates.IN_LEVEL && gameStateParameter == gameStates.Next_Level){
            currentState.isPaused=false;
            levelSelected ++;
            if (levelSelected > 3){
                levelSelected = levelSelected % 3;
            }
            currentState = new InLevelState(this, playerDinoNumber, levelSelected);
            graphicsPanel.changeGameStates(currentState);
            return;
        }

        // If gameStateParameter is new_game then we need to make a new InLevel object in order
        // to restart the game.
        else if (gameCurrentState==gameStates.IN_LEVEL && gameStateParameter == gameStates.NEW_GAME){
            currentState.isPaused=false;
            currentState = new InLevelState(this, playerDinoNumber, levelSelected);
            graphicsPanel.changeGameStates(currentState);
            return;
        }


        // If game stage was not in main menu and the desired stage passed as parameter is main menu
        // then change gameCurrentState to be same as parameter
        // then initialize currentState object
        // and pass it to graphicPanel in order to render MainMenuState
        else if (gameStateParameter == gameStates.IN_MENU ) {

            this.gameCurrentState = gameStateParameter;
            currentState = new MainMenuState(this);
            graphicsPanel.changeGameStates(currentState);
        }

        // If game stage was not in level selection menu and the desired stage passed as parameter is level selection menu
        // then change gameCurrentState to be level selection menu
        // then initialize currentState to be a  level selection state object
        // and pass it to graphicPanel in order to render level selection menu
        else if (gameStateParameter == gameStates.LEVEL_SELECTION_SUB_MENU ) {
            this.gameCurrentState = gameStateParameter;
            currentState = new LevelSelectionState(this);
            graphicsPanel.changeGameStates(currentState);
        }

        // If game stage was not in player selection menu and the desired stage passed as parameter is player selection menu
        // then change gameCurrentState to be player selection menu
        // then initialize currentState to be a  player selection state object
        // and pass it to graphicPanel in order to render player selection menu
        else if (gameStateParameter == gameStates.PLAYER_SELECTION_SUB_MENU ) {
            this.gameCurrentState = gameStateParameter;
            currentState = new playerSelectionState(this);
            graphicsPanel.changeGameStates(currentState);

        }


        // If current state of game is in level selection menu but the desired next stage is playing game stage
        // then change the current stage of game to be inLevel stage in order to render and setting up game level
        if (gameStateParameter == gameStates.IN_LEVEL && gameCurrentState == gameStates.LEVEL_SELECTION_SUB_MENU){
            this.gameCurrentState = gameStates.IN_LEVEL;
            currentState = new InLevelState(this, playerDinoNumber, levelSelected);
            graphicsPanel.changeGameStates(currentState);
        }

        // If current state of game is in player selection menu but the desired next stage is playing game stage
        // then change the current stage of game to be inLevel stage in order to render and setting up game level
        if (gameStateParameter == gameStates.IN_LEVEL && gameCurrentState == gameStates.PLAYER_SELECTION_SUB_MENU){
            this.gameCurrentState = gameStates.IN_LEVEL;
            currentState = new InLevelState(this, playerDinoNumber, levelSelected);
            graphicsPanel.changeGameStates(currentState);
        }


        // If a next button was clicked then :
        else if (gameStateParameter == gameStates.NEXT ) {
            if (gameCurrentState== gameStates.LEVEL_SELECTION_SUB_MENU){
                // Then we need to increase index of Level Number Sprites array
                levelSelected = currentState.incrementSpriteArrayIndex() + 1;

            }
            else if (gameCurrentState== gameStates.PLAYER_SELECTION_SUB_MENU){
                // Then we need to increase index of dinosaur demo Sprites array
                playerDinoNumber = currentState.incrementSpriteArrayIndex() + 1;
            }
        }

        // If a previous button was clicked then :
        else if (gameStateParameter == gameStates.PERV ) {

            if (gameCurrentState== gameStates.LEVEL_SELECTION_SUB_MENU){
                // Then we need to decrease index of Level Number Sprites array
                levelSelected = currentState.decrementSpriteArrayIndex() + 1;
            }
            else if (gameCurrentState== gameStates.PLAYER_SELECTION_SUB_MENU){
                // Then we need to decrease index of  dinosaur demo Sprites array
                playerDinoNumber = currentState.decrementSpriteArrayIndex() + 1;
            }

        }

        // If Restart gameState was passed as argument, then we need to reinitialize the inLevelState
        // in order to restart setting up the level again.
        if (gameStateParameter == gameStates.RESTART ) {
            this.gameCurrentState = gameStates.IN_LEVEL;
            currentState = new InLevelState(this, playerDinoNumber, levelSelected);
            graphicsPanel.changeGameStates(currentState);
        }

        // If the desired state passed as argument is QUIT, then we need to exit from the game, terminating it
        else if (gameStateParameter == gameStates.QUIT) {
            // if the current state of game is changed to be quit, then terminate the program
            this.gameCurrentState = gameStateParameter;
            System.exit(0);
        }
    }


    /**
     * Renders all graphics required to run and play the game.
     *
     */
    @Override
    public void run() {
        while(true) {
            currentState.update();
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
