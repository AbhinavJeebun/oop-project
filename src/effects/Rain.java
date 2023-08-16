package effects;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.Game;
import utilz.LoadSave;

public class Rain {

	// Array to store raindrop positions
	private Point2D.Float[] drops;

	// Random number generator
	private Random rand;

	// Speed at which raindrops fall
	private float rainSpeed = 1.25f;

	// Raindrop particle image
	private BufferedImage rainParticle;

	// Constructor to initialize rain effect
	public Rain() {
		rand = new Random();
		drops = new Point2D.Float[1000];
		rainParticle = LoadSave.GetSpriteAtlas(LoadSave.RAIN_PARTICLE);
		initDrops();
	}

	// Initialize raindrop positions
	private void initDrops() {
		for (int i = 0; i < drops.length; i++)
			drops[i] = getRndPos();
	}

	// Generate a random position for a raindrop
	private Point2D.Float getRndPos() {
		return new Point2D.Float((int) getNewX(0), rand.nextInt(Game.GAME_HEIGHT));
	}

	// Update raindrop positions
	public void update(int xLvlOffset) {
		for (Point2D.Float p : drops) {
			p.y += rainSpeed;
			if (p.y >= Game.GAME_HEIGHT) {
				p.y = -20;
				p.x = getNewX(xLvlOffset);
			}
		}
	}

	// Generate a new X position for a raindrop
	private float getNewX(int xLvlOffset) {
		float value = (-Game.GAME_WIDTH) + rand.nextInt((int) (Game.GAME_WIDTH * 3f)) + xLvlOffset;
		return value;
	}

	// Draw raindrop particles
	public void draw(Graphics g, int xLvlOffset) {
		for (Point2D.Float p : drops)
			g.drawImage(rainParticle, (int) p.getX() - xLvlOffset, (int) p.getY(), 3, 12, null);
	}
}
