package entities;

import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.Dialogue.*;
import static utilz.HelpMethods.CanMoveHere;
import static utilz.HelpMethods.IsFloor;
import static utilz.Constants.Directions.*;

import gamestates.Playing;

public class Pinkstar extends Enemy {

	// Control variables for Pinkstar's behavior
	private boolean preRoll = true;
	private int tickSinceLastDmgToPlayer;
	private int tickAfterRollInIdle;
	private int rollDurationTick, rollDuration = 300;

	// Constructor to initialize Pinkstar's position and dimensions
	public Pinkstar(float x, float y) {
		super(x, y, PINKSTAR_WIDTH, PINKSTAR_HEIGHT, PINKSTAR);
		initHitbox(17, 21);
	}

	// Update Pinkstar's behavior and animation
	public void update(int[][] lvlData, Playing playing) {
		updateBehavior(lvlData, playing);
		updateAnimationTick();
	}

	// Update Pinkstar's specific behavior
	private void updateBehavior(int[][] lvlData, Playing playing) {
		if (firstUpdate)
			firstUpdateCheck(lvlData);

		if (inAir)
			inAirChecks(lvlData, playing);
		else {
			switch (state) {
			case IDLE:
				preRoll = true;
				if (tickAfterRollInIdle >= 120) {
					if (IsFloor(hitbox, lvlData))
						newState(RUNNING);
					else
						inAir = true;
					tickAfterRollInIdle = 0;
					tickSinceLastDmgToPlayer = 60;
				} else
					tickAfterRollInIdle++;
				break;
			case RUNNING:
				if (canSeePlayer(lvlData, playing.getPlayer())) {
					newState(ATTACK);
					setWalkDir(playing.getPlayer());
				}
				move(lvlData, playing);
				break;
			case ATTACK:
				if (preRoll) {
					if (aniIndex >= 3)
						preRoll = false;
				} else {
					move(lvlData, playing);
					checkDmgToPlayer(playing.getPlayer());
					checkRollOver(playing);
				}
				break;
			case HIT:
				if (aniIndex <= GetSpriteAmount(enemyType, state) - 2)
					pushBack(pushBackDir, lvlData, 2f);
				updatePushBackDrawOffset();
				tickAfterRollInIdle = 120;

				break;
			}
		}
	}

	// Check for damage to the player
	private void checkDmgToPlayer(Player player) {
		if (hitbox.intersects(player.getHitbox()))
			if (tickSinceLastDmgToPlayer >= 60) {
				tickSinceLastDmgToPlayer = 0;
				player.changeHealth(-GetEnemyDmg(enemyType), this);
			} else
				tickSinceLastDmgToPlayer++;
	}

	// Set Pinkstar's walking direction based on the player's position
	private void setWalkDir(Player player) {
		if (player.getHitbox().x > hitbox.x)
			walkDir = RIGHT;
		else
			walkDir = LEFT;

	}

	// Move Pinkstar and handle rolling behavior
	protected void move(int[][] lvlData, Playing playing) {
		float xSpeed = 0;

		if (walkDir == LEFT)
			xSpeed = -walkSpeed;
		else
			xSpeed = walkSpeed;

		if (state == ATTACK)
			xSpeed *= 2;

		if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData))
			if (IsFloor(hitbox, xSpeed, lvlData)) {
				hitbox.x += xSpeed;
				return;
			}

		if (state == ATTACK) {
			rollOver(playing);
			rollDurationTick = 0;
		}

		changeWalkDir();

	}

	// Check if Pinkstar should roll over and reset roll duration
	private void checkRollOver(Playing playing) {
		rollDurationTick++;
		if (rollDurationTick >= rollDuration) {
			rollOver(playing);
			rollDurationTick = 0;
		}
	}

	// Perform the roll-over behavior and trigger a dialogue
	private void rollOver(Playing playing) {
		newState(IDLE);
		playing.addDialogue((int) hitbox.x, (int) hitbox.y, QUESTION);
	}


}
