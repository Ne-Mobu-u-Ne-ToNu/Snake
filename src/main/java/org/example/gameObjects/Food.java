package org.example.gameObjects;

import org.example.helpers.Rect;
import org.example.content.Constants;
import org.example.content.LoadingContent;

import java.awt.*;

/**
 * This class is used to spawn random kinds
 * of food on the game field
 * @author Ne-MoBu-u-Ne-ToNu
 * @version 1.0
 * @since 1.0
 */
public class Food {

    /**
     * Rectangle of the game field to know its size
     */
    public Rect background;

    /**
     * Reference to the snake
     */
    public Snake snake;

    /**
     * Rectangle of the food to know its size and location
     */
    public Rect rect;

    /**
     * Index of the food type in the array
     */
    public int foodType;

    /**
     * Boolean variable to know if the food needs to be spawned
     */
    public boolean isSpawned;

    /**
     * Constructor of the class
     * @param background game field
     * @param snake snake reference
     * @param width width of the food
     * @param height height of the Food
     */
    public Food(Rect background, Snake snake, int width, int height) {
        this.background = background;
        this.snake = snake;
        this.rect = new Rect(0, 0, width, height);
    }

    /**
     * Method to update current state. If snake is intersecting with
     * spawned food then food disappears from player's field of view
     * and snake's size increases by 1
     */
    public void update() {
        if (snake.isIntersectingWithRect(this.rect)) {
            snake.grow();
            this.rect.x = -100000;
            this.rect.y = -100000;
            isSpawned = false;
        }
    }

    /**
     * Method to draw randomly generated food on the gaming screen using drawImage
     * method.
     * @param g2 instance of the class Graphics2D to draw food
     */
    public void draw(Graphics2D g2) {
        g2.drawImage(LoadingContent.getFood()[foodType],(int) this.rect.x,
                (int) this.rect.y, (int) this.rect.width, (int) this.rect.height, null);
    }

    /**
     * Method to randomly generate location of the food within gaming field
     * boundaries. If just generated food coordinates are intersecting with
     * snake then these coordinates are being generated again until they aren't.
     * After that food type index will also be generated.
     */
    public void spawn() {
        do {
            double randX = (int)(Math.random() * (int) (background.width / Constants.TILE_SIZE)) * Constants.TILE_SIZE + background.x;
            double randY = (int)(Math.random() * (int) (background.height / Constants.TILE_SIZE)) * Constants.TILE_SIZE + background.y;
            this.rect.x = randX;
            this.rect.y = randY;
        } while (snake.isIntersectingWithRect(this.rect));
        foodType = (int) (Math.random() * LoadingContent.getFood().length);
        this.isSpawned = true;
    }
}
