package objects;

import main.Game;

public class Spike extends GameObject {

    public Spike(int x, int y, int objType) {
        super(x, y, objType); // Call the constructor of the base class (GameObject) with the given position and object type
        initHitbox(32, 16); // Initialize the hitbox of the spike with width 32 and height 16
        xDrawOffset = 0; // Set the x-draw offset for rendering
        yDrawOffset = (int) (Game.SCALE * 16); // Set the y-draw offset for rendering based on the scale of the game
        hitbox.y += yDrawOffset; // Adjust the y-coordinate of the hitbox based on the y-draw offset
    }
}
