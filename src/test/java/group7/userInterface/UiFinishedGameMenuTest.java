package group7.userInterface;

import group7.gameStates.gameStates;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class UiFinishedGameMenuTest {
    UiFinishedGameMenu wonMenu;

    @Test //(expected = IndexOutOfBoundsException.class)
    public void checkInvalidLevelInGameFinishedMenu(){
        // should make IndexOutOfBoundError since we don't have a 4th level
        try {
            System.out.println("test setting invalid level 4");
            wonMenu = new UiFinishedGameMenu(null,4);           
            fail();
        } catch ( IndexOutOfBoundsException ex ) {
            assertTrue( ex instanceof IndexOutOfBoundsException );
        }
    }

    @Test //(expected = IndexOutOfBoundsException.class)
    public void checkNegativeLevelInGameFinishedMenu(){
        // should make IndexOutOfBoundError since we don't have a negative level number
        try {
            System.out.println("test setting invalid level 4");
            wonMenu = new UiFinishedGameMenu(null, -1);            
            fail();
        } catch ( IndexOutOfBoundsException ex ) {
            assertTrue( ex instanceof IndexOutOfBoundsException );
        }
    }

    @Test //(expected = IndexOutOfBoundsException.class)
    public void checkZeroLevelInGameFinishedMenu(){
        // should make IndexOutOfBoundError since we don't have a 0 level number
        try {
            System.out.println("test setting invalid level 4");
            wonMenu = new UiFinishedGameMenu(null, 0);            
            fail();
        } catch ( IndexOutOfBoundsException ex ) {
            assertTrue( ex instanceof IndexOutOfBoundsException );
        }
    }

    @Test
    public void checkInitializedButtonLevel1or2(){
        // This test checks that buttons are initialized correctly
        // in Finished Game menu in level 1 and 2
        wonMenu = new UiFinishedGameMenu(null, 1);
        UiButtons[] arrayButton = wonMenu.getMenuButtons();
        // checking if there are buttons actually initialized in Finished Game menu
        // by seeing if the array is not null
        assertNotNull(arrayButton);
        // Now checking to see if button is initialized in a way we expect
        // Finished Game menu to be
        // first button in Finished Game menu of level 1/2 should be next level
        assertEquals(arrayButton[0].getButtonGameStates(), gameStates.Next_Level);
        // 2nd button in  Finished Game  menu should be restart
        assertEquals(arrayButton[1].getButtonGameStates(), gameStates.NEW_GAME);
        // last button in  Finished Game  menu should be main menu button
        assertEquals(arrayButton[2].getButtonGameStates(), gameStates.IN_MENU);
    }

    @Test
    public void checkInitializedButtonLevel3(){
        // This test checks that buttons are initialized correctly
        // in Finished Game menu in level 3( last level of game)
        wonMenu = new UiFinishedGameMenu(null, 3);
        UiButtons[] arrayButton = wonMenu.getMenuButtons();
        // checking if there are buttons actually initialized in Finished Game menu
        // by seeing if the array is not null
        assertNotNull(arrayButton);
        // Now checking to see if button is initialized in a way we expect
        // Finished Game menu to be
        // first button in Finished Game menu of level 3 should be main menu button
        assertEquals(arrayButton[0].getButtonGameStates(), gameStates.IN_MENU);
        // 2nd button in  Finished Game  menu should be restart button
        assertEquals(arrayButton[1].getButtonGameStates(), gameStates.NEW_GAME);
        // last button in  Finished Game  menu should be exit button
        assertEquals(arrayButton[2].getButtonGameStates(), gameStates.QUIT);
    }
}