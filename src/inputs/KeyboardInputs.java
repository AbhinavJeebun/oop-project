package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gamestates.Gamestate;
import main.GamePanel;

// Keyboard input handler that implements the KeyListener interface.
public class KeyboardInputs implements KeyListener {

	private GamePanel gamePanel;

	// Constructor to initialize keyboard inputs with a reference to the game panel.
	public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public void keyReleased(KeyEvent e) {
		// Depending on the active game state, call the corresponding keyReleased
		// method.
		switch (Gamestate.state) {
			case MENU -> gamePanel.getGame().getMenu().keyReleased(e);
			case PLAYING -> gamePanel.getGame().getPlaying().keyReleased(e);
		}
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public void keyPressed(KeyEvent e) {
		// Depending on the active game state, call the corresponding keyPressed method.
		switch (Gamestate.state) {
			case MENU -> gamePanel.getGame().getMenu().keyPressed(e);
			case PLAYING -> gamePanel.getGame().getPlaying().keyPressed(e);
			case OPTIONS -> gamePanel.getGame().getGameOptions().keyPressed(e);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Not in use in this implementation.
	}
}