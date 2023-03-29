package group7.gameStatesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
//import group7.gameStates.State;
import group7.gameStates.gameStates;
import group7.gameStates.playerSelectionState;
import group7.helperClasses.buttonSpriteRow;

public class playerSelectionStateTest {
    playerSelectionState check; 

    @BeforeEach
    void setup() {
        check = new playerSelectionState(null);
    }

    // Test valid buttons are loaded
    @Test
    public void shouldLoadButtons() {
        Method m;

        try {
            m = playerSelectionState.class.getDeclaredMethod("loadButtons");
            m.setAccessible(true); // allow access to private method loadButtons()
            m.invoke(check);

            // check button one is "prev" 
            assertEquals(gameStates.PERV, check.stateButton[0].getButtonGameStates());
            assertEquals(buttonSpriteRow.PREV_BUTTON, check.stateButton[0].getButtonSpriteRowNumber());
            // check button two is "next" 
            assertEquals(gameStates.NEXT, check.stateButton[1].getButtonGameStates());
            assertEquals(buttonSpriteRow.NEXT_BUTTON, check.stateButton[1].getButtonSpriteRowNumber());
            // check button three is "in_level" 
            assertEquals(gameStates.IN_LEVEL, check.stateButton[2].getButtonGameStates());
            assertEquals(buttonSpriteRow.LETS_PLAY_BUTTON, check.stateButton[2].getButtonSpriteRowNumber());
            // check button four is "in_menu"
            assertEquals(gameStates.IN_MENU, check.stateButton[3].getButtonGameStates());
            assertEquals(buttonSpriteRow.RETURN_BUTTON, check.stateButton[3].getButtonSpriteRowNumber());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    // Test increment sprite array index by one
    @Test
    public void shouldIncrementSpriteArray() {
            check.incrementSpriteArrayIndex();
            assertTrue(check.getIndexCharacterDemo() == 1);
    }

    // Test decrement sprite array index by one
    @Test
    public void shouldDecrementSpriteArray() {
        check.decrementSpriteArrayIndex();
        assertTrue(check.getIndexCharacterDemo() == 4);
    }

    // Test increment array index doesnt exceed bounds
    // If increment index array[4] should wrap around to index array[0], not a positive out of bound index: 5 
    @Test
    public void shouldIncrementSpriteArrayInBound() {
        assertTrue(check.getIndexCharacterDemo() == 0); // initial position

        for (int i = 0; i < check.getNumberOfCharacters() - 1; i++) {
            check.incrementSpriteArrayIndex();
        }

        assertTrue(check.getIndexCharacterDemo() == 4); // Boundary end array
        check.incrementSpriteArrayIndex();
        assertTrue(check.getIndexCharacterDemo() == 0); // wrap around position
    }

    // Test decrement array index doesnt exceed bounds, 
    // If decrement index array[0] should wrap around to index array[4], not a negative out of bound index: -1 
    @Test
    public void shouldDecrementSpriteArrayInBound() {
        assertTrue(check.getIndexCharacterDemo() == 0); // initial position
        check.decrementSpriteArrayIndex();
        assertTrue(check.getIndexCharacterDemo() == 4); // wrap around position
    }
}
