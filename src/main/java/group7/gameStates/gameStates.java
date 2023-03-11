package group7.gameStates;

/**
 * is used for define different stages of games.
 *
 */
public enum gameStates {
    IN_MENU, // IN_MENU is for the state of the game when it is main Menu
    IN_LEVEL, // IN_LEVEL is the describer of state of the game when it's in one the 3 levels
    QUIT,   // Quit is for when the player press the quit button on menu
    PLAYER_SELECTION_SUB_MENU,  //The stage when player is in player selection page
    LEVEL_SELECTION_SUB_MENU, //The stage when player is in level selection page
    CREDIT_SUB_MENU,  //The stage when player is in credit page
    NEXT,    //The substage when player has pressed next button
    PERV,   // The substage when player has pressed previous button
    RESTART,     // The substage when player has pressed restart button
    PAUSE; // The substage when player has pressed  pause button
}
