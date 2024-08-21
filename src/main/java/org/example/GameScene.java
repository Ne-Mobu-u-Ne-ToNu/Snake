package org.example;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class GameScene extends Scene {
    private final Rect backgroundRect, foregroundRect;
    private final BufferedImage foreground, background;
    public Snake snake;
    public Food food;
    public GameScene(KL keyListener, ML mouseListener) {
        super(keyListener, mouseListener);
        backgroundRect = new Rect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        foregroundRect = new Rect(24, 48, Constants.TILE_WIDTH * 31, Constants.TILE_WIDTH * 22);
        snake = new Snake(5, 48, 48 + 24, 24, 24, foregroundRect);
        this.food = new Food(foregroundRect, snake, 24, 24);
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
