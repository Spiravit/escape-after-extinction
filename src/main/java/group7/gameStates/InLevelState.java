package group7.gameStates;

import group7.Game;
import group7.userInterface.UiPauseMenu;
import group7.userInterface.UiTopMenuBar;
import group7.Graphics.GraphicsButtons;
import group7.Graphics.GraphicsGrid;
import group7.levels.LevelManager;
import group7.helperClasses.AssetLoader;
import group7.helperClasses.Direction;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static group7.Graphics.GraphicsPanel.panelWidth;


// This class is for when the game is in Playing state
// it initializes a level
// render and update them
// the class is extending the abstract State class, a super class for all states
public class InLevelState extends State {

    protected LevelManager levelManager;
    private UiTopMenuBar topMenu;
    private UiPauseMenu pauseMenu;
    public InLevelState(Game game, int playerDinoNumber, int levelSelected) {
        super(game);
        topMenu = new UiTopMenuBar(2,game,1,8,1,8);
        pauseMenu = new UiPauseMenu(game);
        this.levelManager = new LevelManager(playerDinoNumber,levelSelected);
    }
    public void update() {
        if (isPaused==false) {
            levelManager.update();
            topMenu.update();
        }
        else{
            pauseMenu.update();
        }
    }
    public void render(Graphics g) {
        topMenu.renderTopMenuBar(g,isPaused,100);
        levelManager.render(g);
        if (isPaused==true){
            pauseMenu.render(g);
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (isPaused==false){
            if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                //Right arrow key code
                levelManager.setDirection(Direction.RIGHT);
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                //gamePanel arrow key code
                levelManager.setDirection(Direction.LEFT);
            } else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
                //Up arrow key code
                levelManager.setDirection(Direction.UP);
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
                //Down arrow key code
                levelManager.setDirection(Direction.DOWN);
            }
            if (e.getKeyCode()==KeyEvent.VK_ESCAPE){
                isPaused = true;
                return;
            }
        }
        if (isPaused==true){
            if (e.getKeyCode()==KeyEvent.VK_ESCAPE){
                isPaused = false;
                levelManager.removeDirection(Direction.RIGHT);
                levelManager.removeDirection(Direction.LEFT);
                levelManager.removeDirection(Direction.UP);
                levelManager.removeDirection(Direction.DOWN);
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (isPaused==false){
            if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                //Right arrow key code
                levelManager.removeDirection(Direction.RIGHT);
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                //gamePanel arrow key code
                levelManager.removeDirection(Direction.LEFT);
            } else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
                //Up arrow key code
                levelManager.removeDirection(Direction.UP);
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
                //Down arrow key code
                levelManager.removeDirection(Direction.DOWN);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(isPaused==false){
            return;
        }
        else{
            pauseMenu.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(isPaused==false){
            return;
        }
        pauseMenu.mouseReleased(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(isPaused==false){
            return;
        }
        pauseMenu.mouseMoved(e);
    }

}
