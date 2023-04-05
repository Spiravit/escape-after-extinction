package group7.userInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import group7.gameStates.gameStates;


public class UiMenuTest {

    UiMenu mainMenu;
    UiButtons[] arrayButton;

    @BeforeEach
    void setup() {
        mainMenu = new UiMenu(null);
        arrayButton = mainMenu.getMenuButtons();
    }

    @Test
    // This test checks that buttons are initialized correctly in main lobby menu
    public void checkCorrectInitializedButton() {
        // checking if there are buttons actually initialized in main Menu by seeing if the array is not null
        assertNotNull(arrayButton);
        // Now checking to see if button is initialized in a way we expect main menu to be
        // first button in main menu should be player selection button
        assertEquals(arrayButton[0].getButtonGameStates(), gameStates.PLAYER_SELECTION_SUB_MENU);
        // 2nd button in main menu should be level selection button
        assertEquals(arrayButton[1].getButtonGameStates(), gameStates.LEVEL_SELECTION_SUB_MENU);
        // last button in main menu should be exit button
        assertEquals(arrayButton[2].getButtonGameStates(), gameStates.QUIT);
    }

    @Test 
    public void buttonsCountCheckMainMenu () {
        // In this test, we make sure our main menu only contains 3 buttons
        UiButtons button = arrayButton[0];
        button = arrayButton[1];
        button = arrayButton[2];
        // Indexing with 3 should throw a index out of bound error
        try {
            System.out.println("test setting invalid 3rd button for main menu");
            button = arrayButton[3];            
            fail();
        } catch ( IndexOutOfBoundsException ex ) {
            assertTrue( ex instanceof IndexOutOfBoundsException );
        }
    }

    @Test
    public void resetButtonsMenuCheck(){
        // checking if resetButtons method in UiMenu
        // reset all booleans fields of all buttons back to false
        for (UiButtons button : arrayButton){
            // setting isMousePressed and isMouseOver boolean of all buttons to true
            button.setIsMousePressedButton(true);
            button.setIsMouseOverButton(true);
        }

        mainMenu.resetButtons();
        for (UiButtons button : arrayButton){
            // isMousePressed and isMouseOver boolean of all buttons should be false after resetting
            assertEquals(button.getIsMousePressed(),false);
            assertEquals(button.getIsMouseOver(),false);
        }
    }

}