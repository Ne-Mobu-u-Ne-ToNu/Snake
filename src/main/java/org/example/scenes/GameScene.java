package org.example.scenes;

import org.example.content.Constants;
import org.example.content.LoadingContent;
import org.example.gameObjects.Food;
import org.example.gameObjects.Snake;
import org.example.helpers.Direction;
import org.example.helpers.Rect;
import org.example.listeners.KL;
import org.example.listeners.ML;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * This class is used to implement the game screen
 * where the most interesting things are happening.
 * @author Ne-MoBu-u-Ne-ToNu
 * @version 1.0
 * @since 1.0
 */
public class GameScene extends Scene {
    private final Rect backgroundRect, foregroundRect;
    private final BufferedImage foreground, background;

    /**
     * Snake object
     */
    public Snake snake;

    /**
     * Food object
     */
    public Food food;

    /**
     * Constructor of the class to load all
     * the main parameters
     * @param keyListener class for register keyboard buttons
     * @param mouseListener class for register mouse and cursor
     */
    public GameScene(KL keyListener, ML mouseListener) {
        super(keyListener, mouseListener);
        backgroundRect = new Rect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        foregroundRect = new Rect(Constants.TILE_SIZE, Constants.TILE_SIZE * 2,
                Constants.TILE_SIZE * Constants.FOREGROUND_WIDTH, Constants.TILE_SIZE * Constants.FOREGROUND_HEIGHT);
        snake = new Snake(Constants.initialSnakeSize, Constants.TILE_SIZE * 2, Constants.TILE_SIZE * 2 + Constants.TILE_SIZE,
                Constants.TILE_SIZE, Constants.TILE_SIZE, foregroundRect);
        this.food = new Food(foregroundRect, snake, (int) Constants.TILE_SIZE, (int) Constants.TILE_SIZE);
        food.spawn();

        foreground = LoadingContent.getForeground();
        background = LoadingContent.getBackground();
    }

    /**
     * Method to update current state. It is used to spawn {@link Food food} and
     * update {@link Snake snake's} current state
     * @param dt difference of time between updates
     */
    @Override
    public void update(double dt) {
        snakeControl();

        if (!food.isSpawned) food.spawn();
        food.update();
        snake.update(dt);
    }

    /**
     * Method to draw current screen with its current state
     * @param g graphics object to draw on the screen
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(background, (int) backgroundRect.x, (int) backgroundRect.y, (int) backgroundRect.width, (int) backgroundRect.height, null);
        g2.drawImage(foreground, (int) foregroundRect.x, (int) foregroundRect.y, (int) foregroundRect.width, (int) foregroundRect.height, null);

        snake.draw(g2);
        food.draw(g2);
    }

    /**
     * Method to change snake's direction according
     * to current pressed key on the keyboard
     */
    public void snakeControl() {
        if (keyListener.isKeyPressed(KeyEvent.VK_LEFT)) {
            snake.changeDirection(Direction.LEFT);
        } else if (keyListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
            snake.changeDirection(Direction.RIGHT);
        } else if (keyListener.isKeyPressed(KeyEvent.VK_UP)) {
            snake.changeDirection(Direction.UP);
        } else if (keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
            snake.changeDirection(Direction.DOWN);
        }
    }
}
