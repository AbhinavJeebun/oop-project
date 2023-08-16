package gamestates;

import java.awt.event.MouseEvent;

import audio.AudioPlayer;
import main.Game;
import ui.MenuButton;

// Base class representing a game state.
public class State {

	// Reference to the main game instance.
	protected Game game;

	// Constructor to initialize a game state with a reference to the main game
	// instance.
	public State(Game game) {
		this.game = game;
	}

	// Check if the mouse event is within the bounds of a menu button.
	public boolean isIn(MouseEvent e, MenuButton mb) {
		return mb.getBounds().contains(e.getX(), e.getY());
	}

	// Get the main game instance.
	public Game getGame() {
		return game;
	}

	// Set the active game state and manage audio based on the new state.
	@SuppressWarnings("incomplete-switch")
	public void setGamestate(Gamestate state) {
		switch (state) {
			case MENU -> game.getAudioPlayer().playSong(AudioPlayer.MENU_1);
			// Set the level song based on the current level.
			case PLAYING -> game.getAudioPlayer().setLevelSong(game.getPlaying().getLevelManager().getLevelIndex());
		}
		// Update the active game state.
		Gamestate.state = state;
	}

}