package org.example;

import java.awt.*;

public class Food {
    public Rect background;
    public Snake snake;
    public int width, height;
    public Color color;
    public Rect rect;
    public int padding;
    public boolean isSpawned;

    public Food(Rect background, Snake snake, int width, int height, Color color) {
        this.background = background;
        this.snake = snake;
        this.width = width;
        this.height = height;
        this.color = color;
        this.rect = new Rect(0, 0, width, height);
        this.padding = (int)((Constants.TILE_WIDTH - this.width) / 2.0);
    }

    public void update(double dt) {
        if (snake.isIntersectingWithRect(this.rect)) {
            snake.grow();
            this.rect.x = -100000;
            this.rect.y = -100000;
            isSpawned = false;
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fillRect((int) this.rect.x + padding, (int) this.rect.y + padding, width, height);
    }

    public void spawn() {
        do {
            double randX = (int)(Math.random() * (int) (background.width / Constants.TILE_WIDTH)) * Constants.TILE_WIDTH + background.x;
            double randY = (int)(Math.random() * (int) (background.height / Constants.TILE_WIDTH)) * Constants.TILE_WIDTH + background.y;
            this.rect.x = randX;
            this.rect.y = randY;
        } while (snake.isIntersectingWithRect(this.rect));
        this.isSpawned = true;
    }
}
