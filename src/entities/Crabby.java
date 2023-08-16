package entities;

import static utilz.Constants.EnemyConstants.*;
import static utilz.HelpMethods.IsFloor;
import static utilz.Constants.Dialogue.*;

import gamestates.Playing;

public class Crabby extends Enemy {

	// Constructor to initialize the Crabby enemy
	public Crabby(float x, float y) {
		super(x, y, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY);
		initHitbox(22, 19);
		initAttackBox(82, 19, 30);
	}

	// Update method to handle behavior and interactions
	public void update(int[][] lvlData, Playing playing) {
		updateBehavior(lvlData, playing);
		updateAnimationTick();
		updateAttackBox();
	}

	// Method to update the enemy's behavior based on its state
	private void updateBehavior(int[][] lvlData, Playing playing) {
		if (firstUpdate)
			firstUpdateCheck(lvlData);

		if (inAir) {
			inAirChecks(lvlData, playing);
		} else {
			switch (state) {
			case IDLE:
				// Check if the enemy is on the floor
				if (IsFloor(hitbox, lvlData))
					newState(RUNNING); // Transition to running state
				else
					inAir = true; // Enemy is in the air
				break;
			case RUNNING:
				// Check if the enemy can see the player
				if (canSeePlayer(lvlData, playing.getPlayer())) {
					turnTowardsPlayer(playing.getPlayer());
					if (isPlayerCloseForAttack(playing.getPlayer()))
						newState(ATTACK); // Transition to attack state
				}
				move(lvlData); // Move the enemy

				if (inAir)
					playing.addDialogue((int) hitbox.x, (int) hitbox.y, EXCLAMATION); // Add a dialogue when enemy is in air

				break;
			case ATTACK:
				if (aniIndex == 0)
					attackChecked = false;
				if (aniIndex == 3 && !attackChecked)
					checkPlayerHit(attackBox, playing.getPlayer()); // Check if player is hit by the attack
				break;
			case HIT:
				if (aniIndex <= GetSpriteAmount(enemyType, state) - 2)
					pushBack(pushBackDir, lvlData, 2f); // Apply pushback effect
				updatePushBackDrawOffset();
				break;
			}
		}
	}

}
