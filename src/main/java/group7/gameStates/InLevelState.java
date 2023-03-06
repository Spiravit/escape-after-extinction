package group7.gameStates;

import group7.Game;
import group7.Graphics.GraphicsButtons;
import group7.Graphics.GraphicsGrid;
import group7.entities.Player;
import group7.levels.LevelManager;
import group7.utils.AssetLoader;
import group7.utils.Direction;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static group7.Graphics.GraphicsPanel.panelHeight;
import static group7.Graphics.GraphicsPanel.panelWidth;


// This class is for when the game is in Playing state
// it initializes a player, enemies, level
// render and update them
// the class is extending the abstract State class, a super class for all states
public class InLevelState extends State {
        public Player player;
        protected LevelManager levelManager;
        int DinoNumber = 1;
        public boolean isPaused = false;
         protected GraphicsButtons[] PauseMenuButtons = new GraphicsButtons[4];
        private BufferedImage PauseBackground;
        private static final int PAUSE_BACKGROUND_HEIGHT=80+4*GraphicsGrid.getScaleY()+20;
        private static final int PAUSE_BACKGROUND_WIDTH=4*GraphicsGrid.getScaleX(); // The width of main Menu is 4 Grids
        public InLevelState(Game game, int PlayerDinoNumber) {
            super(game);
            this.levelManager = new LevelManager();
            this.levelManager.loadLevel(1);
            this.DinoNumber = PlayerDinoNumber;
            this.player = new Player(1, 1, this.levelManager.getLevelData(),PlayerDinoNumber);
            PauseBackground = AssetLoader.getSpriteAtlas(AssetLoader.MAIN_MENU_BACKGROUND);
            PauseMenuButtons[0] = new GraphicsButtons(game,panelWidth / 2, 170, 0, gameStates.RESTART);
            PauseMenuButtons[1] = new GraphicsButtons(game,panelWidth / 2, 170 + 10 + 1 * GraphicsGrid.getScaleY(), 11, gameStates.IN_LEVEL);
            PauseMenuButtons[2] = new GraphicsButtons(game,panelWidth / 2, 170 + 20 + 2 * GraphicsGrid.getScaleY(), 10, gameStates.IN_MENU);
            PauseMenuButtons[3] = new GraphicsButtons(game,panelWidth / 2, 170 + 30 + 3 * GraphicsGrid.getScaleY(), 3, gameStates.QUIT);
        }
        public void update() {
            if (isPaused==false){
                player.update();
            }
            else{
                for (GraphicsButtons button : PauseMenuButtons)
                    button.update();
            }
        }
        public void render(Graphics g) {
            levelManager.render(g);
            player.render(g);
            if (isPaused){
                renderPause(g);
            }
        }


    @Override
        public void keyPressed(KeyEvent e) {
            if (isPaused==false){
                if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
                    //Right arrow key code
                    player.setDirection(Direction.RIGHT);
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
                    //gamePanel arrow key code
                    player.setDirection(Direction.LEFT);
                } else if (e.getKeyCode() == KeyEvent.VK_UP ) {
                    //Up arrow key code
                    player.setDirection(Direction.UP);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
                    //Down arrow key code
                    player.setDirection(Direction.DOWN);
                }
                if (e.getKeyCode()==KeyEvent.VK_ESCAPE){
                    isPaused = true;
                    return;
                }
            }
            if (isPaused==true){
                if (e.getKeyCode()==KeyEvent.VK_ESCAPE){
                    isPaused = false;
                    player.removeDirection(Direction.RIGHT);
                    player.removeDirection(Direction.LEFT);
                    player.removeDirection(Direction.UP);
                    player.removeDirection(Direction.DOWN);
                }
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {
            if (isPaused==false){
                if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
                    //Right arrow key code
                    player.removeDirection(Direction.RIGHT);
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
                    //gamePanel arrow key code
                    player.removeDirection(Direction.LEFT);
                } else if (e.getKeyCode() == KeyEvent.VK_UP ) {
                    //Up arrow key code
                    player.removeDirection(Direction.UP);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
                    //Down arrow key code
                    player.removeDirection(Direction.DOWN);
                }
            }
        }

    @Override
    public void mousePressed(MouseEvent e) {
            if(isPaused==false){
                return;
            }
        for (GraphicsButtons button : PauseMenuButtons) {
            if (button.isMouseInButton(e)) {
                button.setMousePressed(true);
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(isPaused==false){
            return;
        }
        for (GraphicsButtons button : PauseMenuButtons) {
            if (button.isMouseInButton(e)) {
                if (button.isMousePressed())
                    button.applyGamestate();
                break;
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for (GraphicsButtons button : PauseMenuButtons) {
            button.resetBools();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(isPaused==false){
            return;
        }
        for (GraphicsButtons button : PauseMenuButtons)
            button.setMouseOver(false);

        for (GraphicsButtons button : PauseMenuButtons) {
            if (button.isMouseInButton(e)) {
                button.setMouseOver(true);
                break;
            }

        }
    }
    private void renderPause(Graphics g) {
        g.drawImage(PauseBackground,
                panelWidth / 2 - 2*GraphicsGrid.getScaleX(),
                140,
                PAUSE_BACKGROUND_WIDTH,
                PAUSE_BACKGROUND_HEIGHT,
                null);

        for (GraphicsButtons buttons : PauseMenuButtons) {
            buttons.render(g);
        }
    }
}
