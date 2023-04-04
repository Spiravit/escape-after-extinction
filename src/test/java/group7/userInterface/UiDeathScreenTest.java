package group7.userInterface;

import group7.gameStates.gameStates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class UiDeathScreenTest {
    UiDeathScreen DeathMenu;

    @Test 
    public void checkInvalidLevelInDeathMenu() {
        // should make IndexOutOfBoundError since we don't have a 4th level
        try {
            System.out.println("test setting invalid level 4");
            DeathMenu = new UiDeathScreen(null, 4);            
            fail();
        } catch ( IndexOutOfBoundsException ex ) {
            assertTrue( ex instanceof IndexOutOfBoundsException );
        }
    }

    @Test 
    public void checkNegativeLevelInDeathMenu() {
        // should make IndexOutOfBoundError since we don't have a negative level number
        try {
            System.out.println("test setting invalid level -1");
            DeathMenu = new UiDeathScreen(null, -1);            
            fail();
        } catch ( IndexOutOfBoundsException ex ) {
            assertTrue( ex instanceof IndexOutOfBoundsException );
        }
    }

    @Test 
    public void checkZeroLevelInDeathMenu() {
        // should make IndexOutOfBoundError since we don't have a 0 level number
        try {
            System.out.println("test setting invalid level 0");
            DeathMenu = new UiDeathScreen(null, 0);            
            fail();
        } catch ( IndexOutOfBoundsException ex ) {
            assertTrue( ex instanceof IndexOutOfBoundsException );
        }
    }

    @Test
    public void checkInitializedButtonDeathMenu() {
        // This test checks that buttons are initialized correctly
        // in Death Game menu
        DeathMenu = new UiDeathScreen(null, 3);
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