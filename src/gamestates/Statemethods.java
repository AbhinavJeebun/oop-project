package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

// Interface defining methods that should be implemented by classes representing different game states.
public interface Statemethods {

	// Update the logic of the game state.
	public void update();

	// Render the game state on the screen using the provided Graphics object.
	public void draw(Graphics g);

	// Called when the mouse is clicked.
	public void mouseClicked(MouseEvent e);

	// Called when a mouse button is pressed.
	public void mousePressed(MouseEvent e);

	// Called when a mouse button is released.
	public void mouseReleased(MouseEvent e);

	// Called when the mouse is moved.
	public void mouseMoved(MouseEvent e);

	// Called when a key is pressed.
	public void keyPressed(KeyEvent e);

	// Called when a key is released.
	public void keyReleased(KeyEvent e);

}