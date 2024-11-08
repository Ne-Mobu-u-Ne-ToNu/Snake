package org.example.helpers;


/**
 * This class is used manipulating
 * rectangle game pieces like snake body, food
 * or even menu buttons. It stores object's coordinates
 * on the screen and its size
 * @author Ne-MoBu-u-Ne-ToNu
 * @version 1.0
 * @since 1.0
 */
public class Rect {

    /**
     * X coordinate on the screen
     */
    public double x;

    /**
     * Y coordinate on the screen
     */
    public double y;

    /**
     * Width of the object
     */
    public double width;

    /**
     * Height of the object
     */
    public double height;

    /**
     * Direction of the object if it can move
     */
    public Direction direction;

    /**
     * Constructor of the class if we need to use
     * it for static not moving objects
     * @param x x coordinate on the screen
     * @param y y coordinate on the screen
     * @param width width of the object
     * @param height height of the object
     */
    public Rect(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Constructor of the class if we need to use
     * it for moving objects
     * @param x x coordinate on the screen
     * @param y y coordinate on the screen
     * @param width width of the object
     * @param height height of the object
     * @param direction direction of the object if it can move
     */
    public Rect(double x, double y, double width, double height, Direction direction) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.direction = direction;
    }
}
