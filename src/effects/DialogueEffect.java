package effects;

import static utilz.Constants.ANI_SPEED;
import static utilz.Constants.Dialogue.*;

public class DialogueEffect {

	// Position and type of the dialogue effect
	private int x, y, type;

	// Animation index and animation tick counter
	private int aniIndex, aniTick;

	// Flag to indicate if the effect is active
	private boolean active = true;

	// Constructor to initialize the dialogue effect
	public DialogueEffect(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	// Update method to progress animation and deactivate if animation is complete
	public void update() {
		aniTick++;

		// Check if it's time to advance the animation frame
		if (aniTick >= ANI_SPEED) {
			aniTick = 0;
			aniIndex++;

			// Check if the animation is complete
			if (aniIndex >= GetSpriteAmount(type)) {
				active = false;
				aniIndex = 0;
			}
		}
	}

	// Deactivate the dialogue effect
	public void deactive() {
		active = false;
	}

	// Reset the dialogue effect with new position and activation
	public void reset(int x, int y) {
		this.x = x;
		this.y = y;
		active = true;
	}

	// Get the current animation index
	public int getAniIndex() {
		return aniIndex;
	}

	// Get the X position of the dialogue effect
	public int getX() {
		return x;
	}

	// Get the Y position of the dialogue effect
	public int getY() {
		return y;
	}

	// Get the type of the dialogue effect
	public int getType() {
		return type;
	}

	// Check if the dialogue effect is currently active
	public boolean isActive() {
		return active;
	}
}
