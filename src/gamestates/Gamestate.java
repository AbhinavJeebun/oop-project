package gamestates;

// Enumeration representing different game states.
public enum Gamestate {

	// The player is actively playing the game.
	// The main menu state where players make selections.
	// The options state where players can adjust game settings.
	// The state indicating the player wants to quit the game.
	PLAYING, MENU, OPTIONS, QUIT;

	// The current active game state.
	public static Gamestate state = MENU;

}