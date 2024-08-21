package org.example.gameObjects;

import org.example.helpers.Rect;
import org.example.content.Constants;
import org.example.content.LoadingContent;

import java.awt.*;

@SuppressWarnings("unused")
public class Food {
    public Rect background;
    public Snake snake;
    public int width, height;
    public Rect rect;
    public int foodType;
    public boolean isSpawned;

    public Food(Rect background, Snake snake, int width, int height) {
        this.background = background;
        this.snake = snake;
        this.width = width;
        this.height = height;
        this.rect = new Rect(0, 0, width, height);
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
        g2.drawImage(LoadingContent.getFood()[foodType],(int) this.rect.x, (int) this.rect.y, width, height, null);
    }

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
