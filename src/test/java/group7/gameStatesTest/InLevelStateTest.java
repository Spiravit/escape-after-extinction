package group7.gameStatesTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import group7.gameStates.InLevelState;


public class InLevelStateTest {
    @Test
    public void CheckInLevelStateNegativeLevel(){
        // should make IndexOutOfBoundError since we don't have a negative level number
        try {
            InLevelState In = new InLevelState(null, 3, -1);
            fail();
        } catch ( IndexOutOfBoundsException ex ) {
            assertTrue( ex instanceof IndexOutOfBoundsException );
        }
    }

    @Test
    public void CheckInLevelStateZeroLevel(){
        // should make IndexOutOfBoundError since we don't have a zero level number
        try {
            InLevelState In = new InLevelState(null, 3, 0);
            fail();
        } catch ( IndexOutOfBoundsException ex ) {
            assertTrue( ex instanceof IndexOutOfBoundsException );
        }
    }

    @Test
    public void CheckInLevelStateInvalidLevel(){
        // should make IndexOutOfBoundError since we don't have a level number bigger than 3
        try {
            InLevelState In = new InLevelState(null, 3, 4);
            fail();
        } catch ( IndexOutOfBoundsException ex ) {
            assertTrue( ex instanceof IndexOutOfBoundsException );
        }
    }
}