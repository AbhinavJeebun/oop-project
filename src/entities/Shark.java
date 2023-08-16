package entities;

import static utilz.Constants.Dialogue.*;
import static utilz.Constants.Directions.LEFT;
import static utilz.Constants.EnemyConstants.*;
import static utilz.HelpMethods.CanMoveHere;
import static utilz.HelpMethods.IsFloor;

import gamestates.Playing;

public class Shark extends Enemy {

	// Constructor to initialize Shark properties
	public Shark(float x, float y) {
		super(x, y, SHARK_WIDTH, SHARK_HEIGHT, SHARK);
		initHitbox(18, 22);
		initAttackBox(20, 20, 20);
	}

	// Update method for Shark behavior
	public void update(int[][] lvlData, Playing playing) {
		updateBehavior(lvlData, playing);
		updateAnimationTick();
		updateAttackBoxFlip();
	}

	// Update Shark's behavior based on the game state
	private void updateBehavior(int[][] lvlData, Playing playing) {
		// Check if it's the first update for Shark
		if (firstUpdate)
			firstUpdateCheck(lvlData);

		// Check if Shark is in the air
		if (inAir)
			inAirChecks(lvlData, playing);
		else {
			switch (state) {
			case IDLE:
				// Transition from IDLE to RUNNING if on floor, else set to inAir
				if (IsFloor(hitbox, lvlData))
					newState(RUNNING);
				else
					inAir = true;
				break;
			case RUNNING:
				// Chase and attack player if in sight
				if (canSeePlayer(lvlData, playing.getPlayer())) {
					turnTowardsPlayer(playing.getPlayer());
					if (isPlayerCloseForAttack(playing.getPlayer()))
						newState(ATTACK);
				}

				move(lvlData);
				break;
			case ATTACK:
				// Attack behavior
				if (aniIndex == 0)
					attackChecked = false;
				else if (aniIndex == 3) {
					if (!attackChecked)
						checkPlayerHit(attackBox, playing.getPlayer());
					attackMove(lvlData, playing);
				}

				break;
	 		case HIT:
				// Handling when Shark is hit
				if (aniIndex <= GetSpriteAmount(enemyType, state) - 2)
					pushBack(pushBackDir, lvlData, 2f);
				updatePushBackDrawOffset();
				break;
			}
		}
	}

	// Perform the attack movement of the Shark
	protected void attackMove(int[][] lvlData, Playing playing) {
		float xSpeed = 0;

		if (walkDir == LEFT)
			xSpeed = -walkSpeed;
		else
			xSpeed = walkSpeed;

		// Move in the direction of attack
		if (CanMoveHere(hitbox.x + xSpeed * 4, hitbox.y, hitbox.width, hitbox.height, lvlData))
			if (IsFloor(hitbox, xSpeed * 4, lvlData)) {
				hitbox.x += xSpeed * 4;
				return;
			}
		// Transition back to IDLE state and add dialogue bubble
		newState(IDLE);
		playing.addDialogue((int) hitbox.x, (int) hitbox.y, EXCLAMATION);
	}
}
