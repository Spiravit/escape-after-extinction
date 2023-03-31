package group7.userInterface;

import group7.gameStates.gameStates;
import org.junit.Test;

import static org.junit.Assert.*;

public class UiDeathScreenTest {
    @Test(expected = IndexOutOfBoundsException.class)
    public void checkInvalidLevelInDeathMenu(){
        // should make IndexOutOfBoundError since we don't have a 4th level
        UiDeathScreen DeathMenu = new UiDeathScreen(null,4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void checkNegativeLevelInDeathMenu(){
        // should make IndexOutOfBoundError since we don't have a negative level number
        UiDeathScreen DeathMenu = new UiDeathScreen(null,-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void checkZeroLevelInDeathMenu(){
        // should make IndexOutOfBoundError since we don't have a 0 level number
        UiDeathScreen DeathMenu = new UiDeathScreen(null,0);
    }

    @Test
    public void checkInitializedButtonDeathMenu(){
        // This test checks that buttons are initialized correctly
        // in Death Game menu
        UiDeathScreen DeathMenu = new UiDeathScreen(null,3);
        UiButtons[] arrayButton = DeathMenu.getMenuButtons();
        // checking if there are buttons actually initialized in death Game menu
        // by seeing if the array is not null
        assertNotNull(arrayButton);
        // Now checking to see if button is initialized in a way we expect
        // death Game menu to be
        // first button in death Game menu should be restart button
        assertEquals(arrayButton[0].getButtonGameStates(), gameStates.NEW_GAME);
        // 2nd button in death menu should be main menu button
        assertEquals(arrayButton[1].getButtonGameStates(), gameStates.IN_MENU);
        // last button in death menu should be exit button
        assertEquals(arrayButton[2].getButtonGameStates(), gameStates.QUIT);
    }
}