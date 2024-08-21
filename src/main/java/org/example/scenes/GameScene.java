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

public class GameScene extends Scene {
    private final Rect backgroundRect, foregroundRect;
    private final BufferedImage foreground, background;
    public Snake snake;
    public Food food;
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

    @Override
    public void update(double dt) {
        snakeControl();

        if (!food.isSpawned) food.spawn();
        food.update(dt);
        snake.update(dt);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(background, (int) backgroundRect.x, (int) backgroundRect.y, (int) backgroundRect.width, (int) backgroundRect.height, null);
        g2.drawImage(foreground, (int) foregroundRect.x, (int) foregroundRect.y, (int) foregroundRect.width, (int) foregroundRect.height, null);

        snake.draw(g2);
        food.draw(g2);
    }

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
