package group7;

import group7.gameStates.gameStates;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void changeGameStatesTest() {
        Game game = new Game();
        game.changeGameStates(gameStates.IN_LEVEL);
        assertEquals(game.getCurrentState(),gameStates.IN_LEVEL);
    }
}