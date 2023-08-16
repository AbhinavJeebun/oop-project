package objects;

import main.Game;

public class Cannon extends GameObject {

    private int tileY;  // Stores the tile index along the Y-axis where the cannon is located.

    public Cannon(int x, int y, int objType) {
        super(x, y, objType);  // Call the parent class constructor with provided x, y, and objType.
        tileY = y / Game.TILES_SIZE;  // Calculate the tile index based on the y-coordinate.
        initHitbox(40, 26);  // Initialize the hitbox of the cannon with width 40 and height 26.
        hitbox.y += (int) (6 * Game.SCALE);  // Adjust hitbox position vertically.
    }

    public void update() {
        if (doAnimation)
            updateAnimationTick();  // Update animation tick if animation is enabled.
    }

    public int getTileY() {
        return tileY;  // Return the tile index along the Y-axis.
    }
}
