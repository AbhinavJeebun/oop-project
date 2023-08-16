package entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import gamestates.Playing;
import levels.Level;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;

public class EnemyManager {

	// Reference to the current game state
	private Playing playing;

	// Arrays to hold images for different enemy types
	private BufferedImage[][] crabbyArr, pinkstarArr, sharkArr;

	// Reference to the current level
	private Level currentLevel;

	// Constructor to initialize the enemy manager
	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
	}

	// Load enemies for the given level
	public void loadEnemies(Level level) {
		this.currentLevel = level;
	}

	// Update enemy behavior
	public void update(int[][] lvlData) {
		boolean isAnyActive = false;
		for (Crabby c : currentLevel.getCrabs())
			if (c.isActive()) {
				c.update(lvlData, playing);
				isAnyActive = true;
			}

		for (Pinkstar p : currentLevel.getPinkstars())
			if (p.isActive()) {
				p.update(lvlData, playing);
				isAnyActive = true;
			}

		for (Shark s : currentLevel.getSharks())
			if (s.isActive()) {
				s.update(lvlData, playing);
				isAnyActive = true;
			}

		if (!isAnyActive)
			playing.setLevelCompleted(true);
	}

	// Draw enemies on the screen
	public void draw(Graphics g, int xLvlOffset) {
		drawCrabs(g, xLvlOffset);
		drawPinkstars(g, xLvlOffset);
		drawSharks(g, xLvlOffset);
	}

	// Draw sharks on the screen
	private void drawSharks(Graphics g, int xLvlOffset) {
		for (Shark s : currentLevel.getSharks())
			if (s.isActive()) {
				g.drawImage(sharkArr[s.getState()][s.getAniIndex()],
					(int) s.getHitbox().x - xLvlOffset - SHARK_DRAWOFFSET_X + s.flipX(),
					(int) s.getHitbox().y - SHARK_DRAWOFFSET_Y + (int) s.getPushDrawOffset(),
					SHARK_WIDTH * s.flipW(), SHARK_HEIGHT, null);
				// Additional drawHitbox and drawAttackBox calls can be uncommented if needed
			}
	}

	// Draw pinkstars on the screen
	private void drawPinkstars(Graphics g, int xLvlOffset) {
		for (Pinkstar p : currentLevel.getPinkstars())
			if (p.isActive()) {
				g.drawImage(pinkstarArr[p.getState()][p.getAniIndex()],
					(int) p.getHitbox().x - xLvlOffset - PINKSTAR_DRAWOFFSET_X + p.flipX(),
					(int) p.getHitbox().y - PINKSTAR_DRAWOFFSET_Y + (int) p.getPushDrawOffset(),
					PINKSTAR_WIDTH * p.flipW(), PINKSTAR_HEIGHT, null);
				// Additional drawHitbox call can be uncommented if needed
			}
	}

	// Draw crabs on the screen
	private void drawCrabs(Graphics g, int xLvlOffset) {
		for (Crabby c : currentLevel.getCrabs())
			if (c.isActive()) {
				g.drawImage(crabbyArr[c.getState()][c.getAniIndex()],
					(int) c.getHitbox().x - xLvlOffset - CRABBY_DRAWOFFSET_X + c.flipX(),
					(int) c.getHitbox().y - CRABBY_DRAWOFFSET_Y + (int) c.getPushDrawOffset(),
					CRABBY_WIDTH * c.flipW(), CRABBY_HEIGHT, null);
				// Additional drawHitbox and drawAttackBox calls can be uncommented if needed
			}
	}

	// Check if enemy is hit by the player's attack
	public void checkEnemyHit(Rectangle2D.Float attackBox) {
		// Iterate through crab enemies
		for (Crabby c : currentLevel.getCrabs())
			if (c.isActive())
				if (c.getState() != DEAD && c.getState() != HIT)
					if (attackBox.intersects(c.getHitbox())) {
						c.hurt(20);
						return;
					}

		// Iterate through pinkstar enemies
		for (Pinkstar p : currentLevel.getPinkstars())
			if (p.isActive()) {
				// Check pinkstar's state for attack animation
				if (p.getState() == ATTACK && p.getAniIndex() >= 3)
					return;
				else {
					if (p.getState() != DEAD && p.getState() != HIT)
						if (attackBox.intersects(p.getHitbox())) {
							p.hurt(20);
							return;
						}
				}
			}

		// Iterate through shark enemies
		for (Shark s : currentLevel.getSharks())
			if (s.isActive()) {
				if (s.getState() != DEAD && s.getState() != HIT)
					if (attackBox.intersects(s.getHitbox())) {
						s.hurt(20);
						return;
					}
			}
	}

	// Load enemy images
	private void loadEnemyImgs() {
		crabbyArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.CRABBY_SPRITE), 9, 5, CRABBY_WIDTH_DEFAULT, CRABBY_HEIGHT_DEFAULT);
		pinkstarArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.PINKSTAR_ATLAS), 8, 5, PINKSTAR_WIDTH_DEFAULT, PINKSTAR_HEIGHT_DEFAULT);
		sharkArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.SHARK_ATLAS), 8, 5, SHARK_WIDTH_DEFAULT, SHARK_HEIGHT_DEFAULT);
	}

	// Helper method to create image arrays from an atlas
	private BufferedImage[][] getImgArr(BufferedImage atlas, int xSize, int ySize, int spriteW, int spriteH) {
		BufferedImage[][] tempArr = new BufferedImage[ySize][xSize];
		for (int j = 0; j < tempArr.length; j++)
			for (int i = 0; i < tempArr[j].length; i++)
				tempArr[j][i] = atlas.getSubimage(i * spriteW, j * spriteH, spriteW, spriteH);
		return tempArr;
	}

	// Reset all enemies to their initial state
	public void resetAllEnemies() {
		for (Crabby c : currentLevel.getCrabs())
			c.resetEnemy();
		for (Pinkstar p : currentLevel.getPinkstars())
			p.resetEnemy();
		for (Shark s : currentLevel.getSharks())
			s.resetEnemy();
	}

	
}
