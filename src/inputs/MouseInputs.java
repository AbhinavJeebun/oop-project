package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import gamestates.Gamestate;
import main.GamePanel;

// Mouse input handler that implements the MouseListener and MouseMotionListener interfaces.
public class MouseInputs implements MouseListener, MouseMotionListener {

	private GamePanel gamePanel;

	// Constructor to initialize mouse inputs with a reference to the game panel.
	public MouseInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public void mouseDragged(MouseEvent e) {
		// Depending on the active game state, call the corresponding mouseDragged
		// method.
		switch (Gamestate.state) {
			case PLAYING -> gamePanel.getGame().getPlaying().mouseDragged(e);
			case OPTIONS -> gamePanel.getGame().getGameOptions().mouseDragged(e);
		}
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public void mouseMoved(MouseEvent e) {
		// Depending on the active game state, call the corresponding mouseMoved method.
		switch (Gamestate.state) {
			case MENU -> gamePanel.getGame().getMenu().mouseMoved(e);
			case PLAYING -> gamePanel.getGame().getPlaying().mouseMoved(e);
			case OPTIONS -> gamePanel.getGame().getGameOptions().mouseMoved(e);
		}
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public void mouseClicked(MouseEvent e) {
		// Depending on the active game state, call the corresponding mouseClicked
		// method.
		switch (Gamestate.state) {
			case PLAYING -> gamePanel.getGame().getPlaying().mouseClicked(e);
		}
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public void mousePressed(MouseEvent e) {
		// Depending on the active game state, call the corresponding mousePressed
		// method.
		switch (Gamestate.state) {
			case MENU -> gamePanel.getGame().getMenu().mousePressed(e);
			case PLAYING -> gamePanel.getGame().getPlaying().mousePressed(e);
			case OPTIONS -> gamePanel.getGame().getGameOptions().mousePressed(e);
		}
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public void mouseReleased(MouseEvent e) {
		// Depending on the active game state, call the corresponding mouseReleased
		// method.
		switch (Gamestate.state) {
			case MENU -> gamePanel.getGame().getMenu().mouseReleased(e);
			case PLAYING -> gamePanel.getGame().getPlaying().mouseReleased(e);
			case OPTIONS -> gamePanel.getGame().getGameOptions().mouseReleased(e);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Not In use
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Not In use
	}

}