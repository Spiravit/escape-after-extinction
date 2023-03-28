package group7.helperClasses;

/**
 * Holds constants for row numbers (starting from 0) that each button sprite is at mainMenuButtons.png .
 * For instance, PAUSED_BUTTON is 5 , that the sprites of pause button can be found
 * at (5+1)th row of mainMenuButtons.png
 *
 * @author Karmen Yung
 * @author Mohammad Parsaei
 * @author Salman Ayaz
 * @version 1.0
 * @since 2023-03-13
 */
public class buttonSpriteRow {

    // New button sprite is at first row of mainMenuButtons.png
    public static final int NEW_GAME_BUTTON = 0;

    // Load button sprite is at 2nd row of mainMenuButtons.png
    public static final int LOAD_GAME_BUTTON = 1;

    // credit button sprite is at 3rd row of mainMenuButtons.png
    public static final int CREDIT_BUTTON = 2;

    // Exit button sprite is at 4th row of mainMenuButtons.png
    public static final int EXIT_BUTTON = 3;

    // Unsaved Load game sprite is at first row of mainMenuButtons.png
    // Unsaved load game is for when the load game button is still not available
    // because there was no prior save file
    public static final int LOAD_GAME_UNSAVED_BUTTON = 4;

    // Pause button sprite is at 6th row of mainMenuButtons.png
    public static final int PAUSED_BUTTON = 5;

    // Next button sprite is at 7th row of mainMenuButtons.png
    public static final int NEXT_BUTTON = 6;

    // Previous button sprite is at 8th row of mainMenuButtons.png
    public static final int PREV_BUTTON = 7;

    // Let's play button sprite is at 9th row of mainMenuButtons.png
    public static final int LETS_PLAY_BUTTON = 8;

    // Return button sprite is at 10th row of mainMenuButtons.png
    public static final int RETURN_BUTTON = 9;

    // Main Menu button sprite is at 11th row of mainMenuButtons.png
    public static final int MAIN_MENU_BUTTON = 10;

    // Continue button sprite is at 12th row of mainMenuButtons.png
    public static final int CONTINUE_BUTTON = 11;

    // next level button sprite is at 13th row of mainMenuButtons.png
    public static final int NEXT_LEVEL_BUTTON = 12;

    // restart level button sprite is at 14th row of mainMenuButtons.png
    public static final int RESTART_BUTTON = 13;

}
