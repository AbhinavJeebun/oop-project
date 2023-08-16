package ui;

import java.awt.Rectangle;

public class PauseButton {

    // Coordinates and dimensions of the button
    protected int x, y, width, height;
    
    // The bounding rectangle that defines the button's area
    protected Rectangle bounds;

    // Constructor to initialize the button's position and size
    public PauseButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        createBounds();
    }

    // Create the bounding rectangle based on the button's properties
    private void createBounds() {
        bounds = new Rectangle(x, y, width, height);
    }

    // Getter and setter for the button's X coordinate
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    // Getter and setter for the button's Y coordinate
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Getter and setter for the button's width
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    // Getter and setter for the button's height
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    // Getter for the button's bounding rectangle
    public Rectangle getBounds() {
        return bounds;
    }

    // Setter for the button's bounding rectangle
    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
}
