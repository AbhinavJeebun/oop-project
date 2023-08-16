package objects;

public class Grass {

    private int x, y, type;  // Position and type of the grass.

    // Constructor for the Grass object.
    public Grass(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    // Getter method for the x-coordinate.
    public int getX() {
        return x;
    }

    // Getter method for the y-coordinate.
    public int getY() {
        return y;
    }

    // Getter method for the type of grass.
    public int getType() {
        return type;
    }
}
