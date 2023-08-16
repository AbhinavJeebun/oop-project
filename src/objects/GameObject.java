package objects;

import static utilz.Constants.ANI_SPEED;
import static utilz.Constants.ObjectConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import main.Game;

public class GameObject {

    // Properties of the game object.
    protected int x, y, objType;  // Position and object type.
    protected Rectangle2D.Float hitbox;  // The hitbox of the object.
    protected boolean doAnimation, active = true;  // Animation and activity flags.
    protected int aniTick, aniIndex;  // Animation tick and index.
    protected int xDrawOffset, yDrawOffset;  // Drawing offsets.

    // Constructor for the game object.
    public GameObject(int x, int y, int objType) {
        this.x = x;
        this.y = y;
        this.objType = objType;
    }

    // Update the animation tick based on ANI_SPEED.
    protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= ANI_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(objType)) {
                aniIndex = 0;

                // Deactivate certain objects after animation completion.
                if (objType == BARREL || objType == BOX) {
                    doAnimation = false;
                    active = false;
                } else if (objType == CANNON_LEFT || objType == CANNON_RIGHT) {
                    doAnimation = false;
                }
            }
        }
    }

    // Reset the object's animation and activity state.
    public void reset() {
        aniIndex = 0;
        aniTick = 0;
        active = true;

        // Manage animation state for specific object types.
        if (objType == BARREL || objType == BOX || objType == CANNON_LEFT || objType == CANNON_RIGHT) {
            doAnimation = false;
        } else {
            doAnimation = true;
        }
    }

    // Initialize the hitbox with the provided width and height.
    protected void initHitbox(int width, int height) {
        hitbox = new Rectangle2D.Float(x, y, (int) (width * Game.SCALE), (int) (height * Game.SCALE));
    }

    // Draw the hitbox for debugging purposes.
    public void drawHitbox(Graphics g, int xLvlOffset) {
        g.setColor(Color.PINK);
        g.drawRect((int) hitbox.x - xLvlOffset, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }

    // Getter and setter methods for various properties.
    public int getObjType() {
        return objType;
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setAnimation(boolean doAnimation) {
        this.doAnimation = doAnimation;
    }

    public int getxDrawOffset() {
        return xDrawOffset;
    }

    public int getyDrawOffset() {
        return yDrawOffset;
    }

    public int getAniIndex() {
        return aniIndex;
    }

    public int getAniTick() {
        return aniTick;
    }
}
