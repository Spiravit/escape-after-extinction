package group7.gameStates;

import group7.Game;
import group7.levels.LevelState;
import group7.userInterface.*;
import group7.levels.LevelManager;
import group7.helperClasses.Direction;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


// This class is for when the game is in Playing state
// it initializes a level
// render and update them
// the class is extending the abstract State class, a super class for all states
public class InLevelState extends State {

    protected LevelManager levelManager;
    private UiTopMenuBar topMenu;
    private UiPauseMenu pauseMenu;
    private UiFinishedGameMenu levelFinishedMenu;
    private UiDeathScreen deathScreenMenu;                                                        // ***TEST REMOVE***
    public LevelState isLevelDone = LevelState.PLAYING;

    int currentLevelNumber;

    public InLevelState( Game game, int playerDinoNumber, int levelSelected ) {
        super( game );
        currentLevelNumber = levelSelected;
        this.levelManager = new LevelManager( playerDinoNumber, levelSelected );
        //System.out.println( "All I need is :"+ currentLevelNumber );                      // ***TEST REMOVE***
        deathScreenMenu = new UiDeathScreen( game, currentLevelNumber );                    // ***TEST REMOVE***
        levelFinishedMenu = new UiFinishedGameMenu( game, currentLevelNumber );
        topMenu = new UiTopMenuBar( levelSelected, levelManager, game );
        pauseMenu = new UiPauseMenu( game );
    }

    public void update() {
        isLevelDone = levelManager.getLevelState();
        if ( isLevelDone == LevelState.LOST ) {
            isPaused = true;
            deathScreenMenu.update();
        }
        else if ( isLevelDone == LevelState.WON ) {
            isPaused = true;
            levelFinishedMenu.update();
        }
        else if ( isPaused == false && isLevelDone == LevelState.PLAYING ) {
            levelManager.update();
            topMenu.update();
        }
        else if ( isPaused == true && isLevelDone == LevelState.PLAYING ) {
            pauseMenu.update();
        }
    }

    public void render( Graphics g ) {
        topMenu.renderTopMenuBar(g,isPaused,levelManager.getHealth(), levelManager.getEggCollectedCurrentLevel(), levelManager.getKeyCollectedCurrentLevel());
        levelManager.render(g);
        if ( isLevelDone == LevelState.WON ) {
            levelFinishedMenu.render(g,topMenu.getTime(),levelManager.getEggCollectedCurrentLevel());
        }
        else if ( isLevelDone == LevelState.LOST ) {
            deathScreenMenu.render(g);
        }
        else if ( isPaused == true && isLevelDone == LevelState.PLAYING ) {
            pauseMenu.render(g);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ( isPaused == false ) {
            if ( e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D ) {
                //Right arrow key code
                levelManager.setDirection(Direction.RIGHT);
            } else if ( e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A ) {
                //gamePanel arrow key code
                levelManager.setDirection(Direction.LEFT);
            } else if ( e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W ) {
                //Up arrow key code
                levelManager.setDirection(Direction.UP);
            } else if ( e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S ) {
                //Down arrow key code
                levelManager.setDirection(Direction.DOWN);
            }
            if ( e.getKeyCode() == KeyEvent.VK_ESCAPE ) {
                isPaused = true;
                return;
            }
        }

        if ( isPaused == true ) {
            if ( e.getKeyCode() == KeyEvent.VK_ESCAPE && isLevelDone==LevelState.PLAYING ){
                isPaused = false;
                levelManager.removeDirection(Direction.RIGHT);
                levelManager.removeDirection(Direction.LEFT);
                levelManager.removeDirection(Direction.UP);
                levelManager.removeDirection(Direction.DOWN);
            }
        }
    }

    @Override
    public void keyReleased( KeyEvent e ) {
        if (isPaused==false){
            if ( e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D ) {
                //Right arrow key code
                levelManager.removeDirection(Direction.RIGHT);
            } else if ( e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A ) {
                //gamePanel arrow key code
                levelManager.removeDirection(Direction.LEFT);
            } else if ( e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W ) {
                //Up arrow key code
                levelManager.removeDirection(Direction.UP);
            } else if ( e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S ) {
                //Down arrow key code
                levelManager.removeDirection(Direction.DOWN);
            }
        }
    }

    @Override
    public void mousePressed( MouseEvent e ) {
        if( isPaused == false && isLevelDone==LevelState.PLAYING  ){
            topMenu.mousePressed(e);
        }
        else if (isLevelDone == LevelState.LOST ) {
            deathScreenMenu.mousePressed(e);
        }
        else if (isLevelDone == LevelState.WON ){
            levelFinishedMenu.mousePressed(e);
        }
        else {
            pauseMenu.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased( MouseEvent e ) {
        if( isPaused == false && isLevelDone==LevelState.PLAYING  ){
            topMenu.mouseReleased(e);
        }
        else if (isLevelDone == LevelState.LOST ) {
            deathScreenMenu.mouseReleased(e);
        }
        else if (isLevelDone == LevelState.WON ){
            levelFinishedMenu.mouseReleased(e);
        }
        else {
            pauseMenu.mouseReleased(e);
        }
    }

    @Override
    public void mouseMoved( MouseEvent e ) {
        if( isPaused == false && isLevelDone==LevelState.PLAYING  ){
            topMenu.mouseMoved(e);
        }
        else if (isLevelDone == LevelState.LOST ) {
            deathScreenMenu.mouseMoved(e);
        }
        else if (isLevelDone == LevelState.WON ){
            levelFinishedMenu.mouseMoved(e);
        }
        else {
            pauseMenu.mouseMoved(e);
        }
    }

}
