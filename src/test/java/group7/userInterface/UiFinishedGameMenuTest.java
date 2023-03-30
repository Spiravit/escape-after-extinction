package group7.userInterface;

import group7.gameStates.gameStates;
import org.junit.Test;

import static org.junit.Assert.*;

public class UiFinishedGameMenuTest {

    @Test(expected = IndexOutOfBoundsException.class)
    public void checkInvalidLevelinGameFinishedMenu(){
        // should make IndexOutOfBoundError since we don't have a 4th level
        UiDeathScreen wonMenu = new UiDeathScreen(null,4);
    }

    @Test
    public void checkInitializedButtonLevel1or2(){
        // This test checks that buttons are initialized correctly
        // in Finished Game menu in level 1 and 2
        UiDeathScreen wonMenu = new UiDeathScreen(null,1);
        UiButtons[] arrayButton = wonMenu.getMenuButtons();
        // checking if there are buttons actually initialized in Finished Game menu
        // by seeing if the array is not null
        assertNotNull(arrayButton);
        // Now checking to see if button is initialized in a way we expect
        // Finished Game menu to be
        // first button in Finished Game menu of level 1/2 should be player selection button
        assertEquals(arrayButton[0].getButtonGameStates(), gameStates.Next_Level);
        // 2nd button in main menu should be level selection button
        assertEquals(arrayButton[1].getButtonGameStates(), gameStates.NEW_GAME);
        // last button in main menu should be exit button
        assertEquals(arrayButton[2].getButtonGameStates(), gameStates.IN_MENU);
    }

    @Test
    public void checkInitializedButtonLevel3(){
        // This test checks that buttons are initialized correctly
        // in Finished Game menu in level 3( last level of game)
        UiDeathScreen wonMenu = new UiDeathScreen(null,3);
        UiButtons[] arrayButton = wonMenu.getMenuButtons();
        // checking if there are buttons actually initialized in Finished Game menu
        // by seeing if the array is not null
        assertNotNull(arrayButton);
        // Now checking to see if button is initialized in a way we expect
        // Finished Game menu to be
        // first button in Finished Game menu of level 3 should be player selection button
        assertEquals(arrayButton[0].getButtonGameStates(), gameStates.RESTART);
        // 2nd button in main menu should be level selection button
        assertEquals(arrayButton[1].getButtonGameStates(), gameStates.IN_MENU);
        // last button in main menu should be exit button
        assertEquals(arrayButton[2].getButtonGameStates(), gameStates.QUIT);
    }
}