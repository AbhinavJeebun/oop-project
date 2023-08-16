package objects;

import main.Game;

public class Potion extends GameObject {

    private float hoverOffset;
    private int maxHoverOffset, hoverDir = 1;

    // Constructor for creating a Potion object
    public Potion(int x, int y, int objType) {
        super(x, y, objType);
        doAnimation = true;

        initHitbox(7, 14);

        xDrawOffset = (int) (3 * Game.SCALE);
        yDrawOffset = (int) (2 * Game.SCALE);

        maxHoverOffset = (int) (10 * Game.SCALE);
    }

    // Update method for updating the Potion object
    public void update() {
        updateAnimationTick();
        updateHover();
    }

    // Update the hover effect of the Potion
    private void updateHover() {
        hoverOffset += (0.075f * Game.SCALE * hoverDir);

        // Change hover direction when reaching maxHoverOffset or minimum
        if (hoverOffset >= maxHoverOffset)
            hoverDir = -1;
        else if (hoverOffset < 0)
            hoverDir = 1;

        hitbox.y = y + hoverOffset;
    }
}
