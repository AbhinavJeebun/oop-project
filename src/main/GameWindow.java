package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

// Represents the main game window that contains the game panel.
public class GameWindow {
	private JFrame jframe;

	// Constructor to create and initialize the game window.
	public GameWindow(GamePanel gamePanel) {

		// Create a new JFrame instance
		jframe = new JFrame();

		// Set the default close operation for the window.
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add the game panel to the JFrame.
		jframe.add(gamePanel);

		// Set the window to not be resizable.
		jframe.setResizable(false);

		// Pack the components in the window to their preferred sizes.
		jframe.pack();

		// Center the window on the screen.
		jframe.setLocationRelativeTo(null);

		// Make the window visible.
		jframe.setVisible(true);

		// Add a window focus listener to handle focus events.
		jframe.addWindowFocusListener(new WindowFocusListener() {

			// Invoked when the window loses focus.
			@Override
			public void windowLostFocus(WindowEvent e) {
				// Notify the game panel that the window has lost focus.
				gamePanel.getGame().windowFocusLost();
			}

			// Invoked when the window gains focus.
			@Override
			public void windowGainedFocus(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

}