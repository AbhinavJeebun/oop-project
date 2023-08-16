package objects;

import java.awt.geom.Rectangle2D;

import main.Game;

import static utilz.Constants.Projectiles.*;

public class Projectile {
    private Rectangle2D.Float hitbox; // The hitbox of the projectile
    private int dir; // The direction of the projectile (1 for right, -1 for left)
    private boolean active = true; // Whether the projectile is currently active or not

    public Projectile(int x, int y, int dir) {
        int xOffset = (int) (-3 * Game.SCALE); // Offset for the projectile's x-coordinate
        int yOffset = (int) (5 * Game.SCALE); // Offset for the projectile's y-coordinate

        if (dir == 1)
            xOffset = (int) (29 * Game.SCALE); // If direction is right, adjust the x-offset

        // Create the projectile's hitbox based on the given position and offsets
        hitbox = new Rectangle2D.Float(x + xOffset, y + yOffset, CANNON_BALL_WIDTH, CANNON_BALL_HEIGHT);
        this.dir = dir; // Set the direction of the projectile
    }

    public void updatePos() {
        hitbox.x += dir * SPEED; // Update the projectile's x-coordinate based on its direction and speed
    }

    public void setPos(int x, int y) {
        hitbox.x = x; // Set the projectile's x-coordinate
        hitbox.y = y; // Set the projectile's y-coordinate
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox; // Get the hitbox of the projectile
    }

    public void setActive(boolean active) {
        this.active = active; // Set whether the projectile is active or not
    }

    public boolean isActive() {
        return active; // Check if the projectile is currently active
    }
}
