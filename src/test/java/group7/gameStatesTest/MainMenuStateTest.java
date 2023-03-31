package group7.gameStatesTest;

import group7.Game;
import group7.gameStates.State;
import group7.gameStates.gameStates;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.Assert.*;

public class MainMenuStateTest {

    @Before
    public void setup(){
    }

    @After
    @Test
    public void keyPressed() throws AWTException {
        Game game = new Game();
        State mainMenuState = game.getCurrentState();
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ESCAPE);
        Assert.assertEquals(game.getCurrentState(), gameStates.QUIT);
    }

    @Test
    public void mousePressed() {
    }

    @Test
    public void mouseReleased() {
    }

    @Test
    public void mouseMoved() {
    }
}