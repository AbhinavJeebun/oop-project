package objects;

import static utilz.Constants.ObjectConstants.*;  // Import constants from ObjectConstants.
import main.Game;

public class GameContainer extends GameObject {

    public GameContainer(int x, int y, int objType) {
        super(x, y, objType);  // Call the parent class constructor with provided x, y, and objType.
        createHitbox();  // Create the hitbox for the game container.
    }

    private void createHitbox() {
        if (objType == BOX) {
            initHitbox(25, 18);  // Initialize hitbox dimensions for a box.

            xDrawOffset = (int) (7 * Game.SCALE);  // Set horizontal draw offset for a box.
            yDrawOffset = (int) (12 * Game.SCALE);  // Set vertical draw offset for a box.

        } else {
            initHitbox(23, 25);  // Initialize hitbox dimensions for a different type.

            xDrawOffset = (int) (8 * Game.SCALE);  // Set horizontal draw offset for the different type.
            yDrawOffset = (int) (5 * Game.SCALE);  // Set vertical draw offset for the different type.
        }

        hitbox.y += yDrawOffset + (int) (Game.SCALE * 2);  // Adjust hitbox position vertically.
        hitbox.x += xDrawOffset / 2;  // Adjust hitbox position horizontally.
    }

    public void update() {
        if (doAnimation)
            updateAnimationTick();  // Update animation tick if animation is enabled.
    }
}
