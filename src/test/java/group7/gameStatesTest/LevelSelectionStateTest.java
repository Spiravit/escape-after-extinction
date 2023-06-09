package group7.gameStatesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import group7.gameStates.LevelSelectionState;
import group7.gameStates.gameStates;
import group7.helperClasses.buttonSpriteRow;


public class LevelSelectionStateTest {
    LevelSelectionState check;

    @BeforeEach
    void setup() {
        check = new LevelSelectionState(null);
    }

    // Test valid buttons are loaded
    @Test
    public void shouldLoadButtons() {
        Method m;

        try {
            m = LevelSelectionState.class.getDeclaredMethod("loadButtons");
            m.setAccessible(true); // allow access to private method loadButtons()
            m.invoke(check);
    
            // check button one is "prev"
            assertEquals(gameStates.PERV, check.stateButton[0].getButtonGameStates());
            assertEquals(buttonSpriteRow.PREV_BUTTON, check.stateButton[0].getButtonSpriteRowNumber());
            // check button two is "next" 
            assertEquals(gameStates.NEXT, check.stateButton[1].getButtonGameStates());
            assertEquals(buttonSpriteRow.NEXT_BUTTON, check.stateButton[1].getButtonSpriteRowNumber());
            // check button three is "in_level", AKA play/start game 
            assertEquals(gameStates.IN_LEVEL, check.stateButton[2].getButtonGameStates());
            assertEquals(buttonSpriteRow.LETS_PLAY_BUTTON, check.stateButton[2].getButtonSpriteRowNumber());
            // check button four is "in_menu", AKA return to main menu
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
        assertTrue(check.getIndexLevelNumbers() == 1);
    }

    // Test increment array index doesnt exceed bounds
    // If increment index array[4] should wrap around to index array[0], not a positive out of bound index: 5 
    @Test
    public void shouldIncrementSpriteArrayInBound() {
        assertTrue(check.getIndexLevelNumbers() == 0); // initial position

        for (int i = 0; i < check.getNumberOfLevels() - 1; i++) {
            check.incrementSpriteArrayIndex();
        }

        assertTrue(check.getIndexLevelNumbers() == check.getNumberOfLevels() - 1); // Boundary end array
        check.incrementSpriteArrayIndex();
        assertTrue(check.getIndexLevelNumbers() == 0); // wrap around position
    }

    // Test decrement sprite array index till its back where it started
    // Test decrement array index doesnt exceed bounds, 
    // If decrement index array[0] should wrap around to index array[2], not a negative out of bound index: -1 
    @Test
    public void shouldDecrementSpriteArrayFullLoopInBound() {
        assertTrue(check.getIndexLevelNumbers() == 0); // initial position
        check.decrementSpriteArrayIndex();
        assertTrue(check.getIndexLevelNumbers() == check.getNumberOfLevels() - 1); // wrap around position, AKA index for end of array

        for (int i = check.getNumberOfLevels() - 1; i > 1; i--) {
            check.decrementSpriteArrayIndex();
        }

        assertTrue(check.getIndexLevelNumbers() == 1);
        check.decrementSpriteArrayIndex();
        assertTrue(check.getIndexLevelNumbers() == 0); // should be back at initial index
    }
}