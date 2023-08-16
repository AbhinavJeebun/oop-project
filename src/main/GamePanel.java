package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

// Represents the main panel where the game graphics are displayed and user input is captured.
public class GamePanel extends JPanel {

	private MouseInputs mouseInputs;
	private Game game;

	// Constructor to create and initialize the game panel.
	public GamePanel(Game game) {
		// Initialize the mouse inputs and associate them with this panel.
		mouseInputs = new MouseInputs(this);
		this.game = game;

		// Set the size of the game panel.
		setPanelSize();

		// Add input listeners to capture keyboard and mouse events.
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}

	// Set the preferred size of the game panel.
	private void setPanelSize() {
		Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
		setPreferredSize(size);
	}

	// Method to update the game state.
	public void updateGame() {

	}

	// Method to paint the game graphics on the panel.
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Call the game's render method to display graphics.
		game.render(g);
	}

	// Get the associated Game instance.
	public Game getGame() {
		return game;
	}

}