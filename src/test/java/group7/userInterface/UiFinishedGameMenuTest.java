package group7.userInterface;

import group7.gameStates.gameStates;
import org.junit.Test;

import static org.junit.Assert.*;

public class UiFinishedGameMenuTest {

    @Test(expected = IndexOutOfBoundsException.class)
    public void checkInvalidLevelInGameFinishedMenu(){
        // should make IndexOutOfBoundError since we don't have a 4th level
        UiFinishedGameMenu wonMenu = new UiFinishedGameMenu(null,4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void checkNegativeLevelInGameFinishedMenu(){
        // should make IndexOutOfBoundError since we don't have a negative level number
        UiFinishedGameMenu wonMenu = new UiFinishedGameMenu(null,-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void checkZeroLevelInGameFinishedMenu(){
        // should make IndexOutOfBoundError since we don't have a 0 level number
        UiFinishedGameMenu wonMenu = new UiFinishedGameMenu(null,0);
    }

    @Test
    public void checkInitializedButtonLevel1or2(){
        // This test checks that buttons are initialized correctly
        // in Finished Game menu in level 1 and 2
        UiFinishedGameMenu wonMenu = new UiFinishedGameMenu(null,1);
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
        UiFinishedGameMenu wonMenu = new UiFinishedGameMenu(null,3);
        UiButtons[] arrayButton = wonMenu.getMenuButtons();
        // checking if there are buttons actually initialized in Finished Game menu
        // by seeing if the array is not null
        assertNotNull(arrayButton);
        // Now checking to see if button is initialized in a way we expect
        // Finished Game menu to be
        // first button in Finished Game menu of level 3 should be player selection button
        assertEquals(arrayButton[0].getButtonGameStates(), gameStates.IN_MENU);
        // 2nd button in main menu should be level selection button
        assertEquals(arrayButton[1].getButtonGameStates(), gameStates.NEW_GAME);
        // last button in main menu should be exit button
        assertEquals(arrayButton[2].getButtonGameStates(), gameStates.QUIT);
    }
}