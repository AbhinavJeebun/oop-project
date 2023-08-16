package entities;

import static utilz.Constants.Directions.DOWN;
import static utilz.Constants.Directions.LEFT;
import static utilz.Constants.Directions.UP;
import static utilz.HelpMethods.CanMoveHere;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import main.Game;

public abstract class Entity {

	// Position and dimensions
	protected float x, y;
	protected int width, height;
	protected Rectangle2D.Float hitbox;

	// Animation
	protected int aniTick, aniIndex;
	protected int state;

	// Air movement
	protected float airSpeed;
	protected boolean inAir = false;

	// Health
	protected int maxHealth;
	protected int currentHealth;

	// Attack
	protected Rectangle2D.Float attackBox;

	// Movement speed
	protected float walkSpeed;

	// Pushback variables
	protected int pushBackDir;
	protected float pushDrawOffset;
	protected int pushBackOffsetDir = UP;

	// Constructor to initialize the entity's position and dimensions
	public Entity(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	// Update pushback draw offset based on direction
	protected void updatePushBackDrawOffset() {
		float speed = 0.95f;
		float limit = -30f;

		if (pushBackOffsetDir == UP) {
			pushDrawOffset -= speed;
			if (pushDrawOffset <= limit)
				pushBackOffsetDir = DOWN;
		} else {
			pushDrawOffset += speed;
			if (pushDrawOffset >= 0)
				pushDrawOffset = 0;
		}
	}

	// Push the entity back after taking damage
	protected void pushBack(int pushBackDir, int[][] lvlData, float speedMulti) {
		float xSpeed = 0;
		if (pushBackDir == LEFT)
			xSpeed = -walkSpeed;
		else
			xSpeed = walkSpeed;

		if (CanMoveHere(hitbox.x + xSpeed * speedMulti, hitbox.y, hitbox.width, hitbox.height, lvlData))
			hitbox.x += xSpeed * speedMulti;
	}

	// Draw the attack box of the entity
	protected void drawAttackBox(Graphics g, int xLvlOffset) {
		g.setColor(Color.red);
		g.drawRect((int) (attackBox.x - xLvlOffset), (int) attackBox.y, (int) attackBox.width, (int) attackBox.height);
	}

	// Draw the hitbox of the entity
	protected void drawHitbox(Graphics g, int xLvlOffset) {
		g.setColor(Color.PINK);
		g.drawRect((int) hitbox.x - xLvlOffset, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
	}

	// Initialize the hitbox of the entity
	protected void initHitbox(int width, int height) {
		hitbox = new Rectangle2D.Float(x, y, (int) (width * Game.SCALE), (int) (height * Game.SCALE));
	}

	// Get the hitbox of the entity
	public Rectangle2D.Float getHitbox() {
		return hitbox;
	}

	// Get the state of the entity
	public int getState() {
		return state;
	}

	// Get the animation index of the entity
	public int getAniIndex() {
		return aniIndex;
	}

	// Set a new state for the entity
	protected void newState(int state) {
		this.state = state;
		aniTick = 0;
		aniIndex = 0;
	}

}
