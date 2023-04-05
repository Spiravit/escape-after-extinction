package group7.userInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import group7.gameStates.gameStates;


public class UiPauseMenuTest {
    UiPauseMenu pauseMenu;
    UiButtons[] arrayButton; 

    @BeforeEach
    void setup() {
        pauseMenu = new UiPauseMenu(null);
        arrayButton = pauseMenu.getMenuButtons();
    }

    @Test
    // This test checks that buttons are initialized correctly in pause menu
    public void shouldInitializedCorrectButtons() {
        // checking that array is not null
        assertNotNull(arrayButton);
        // Now checking to see if button is initialized in a way we expect pause menu to be
        // first button in pause menu should be player selection button
        assertEquals(arrayButton[0].getButtonGameStates(), gameStates.RESUME);
        // 2nd button in pause menu should be level selection button
        assertEquals(arrayButton[1].getButtonGameStates(), gameStates.NEW_GAME);
        // 3rd button in pause menu should be exit button
        assertEquals(arrayButton[2].getButtonGameStates(), gameStates.IN_MENU);
        // last button in pause menu should be exit button
        assertEquals(arrayButton[3].getButtonGameStates(), gameStates.QUIT);
    }   
    
    @Test 
    // In this test, we make sure our pause menu only contains 4 buttons
    public void buttonsCountCheckMainMenu () {
        try {
            System.out.println("test setting invalid 5th button for pause menu");
            UiButtons button = arrayButton[4];            
            fail();
        } catch ( IndexOutOfBoundsException ex ) {
            assertTrue( ex instanceof IndexOutOfBoundsException );
        }
    }
}
