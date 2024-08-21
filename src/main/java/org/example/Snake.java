package org.example;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Snake {
    public Rect[] body = new Rect[1000];
    public Rect background;
    public double bodyWidth, bodyHeight;
    public Direction direction;

    public int size;
    public int tail = 0;
    public int head = 0;

    public double ogWaitBetweenUpdates = 0.1f;
    public double waitTimeLeft = ogWaitBetweenUpdates;

    public Map<Direction, Double> snakeHeadRotation, snakeBodyRotation,
            snakeTailRotation;


    public Snake(int size, double startX, double startY, double bodyWidth, double bodyHeight, Rect background) {
        this.size = size;
        this.bodyWidth = bodyWidth;
        this.bodyHeight = bodyHeight;
        this.background = background;
        this.direction = Direction.RIGHT;

        snakeHeadRotation = new HashMap<>();
        snakeBodyRotation = new HashMap<>();
        snakeTailRotation = snakeHeadRotation;

        snakeHeadRotation.put(Direction.RIGHT, 90.0);
        snakeHeadRotation.put(Direction.LEFT, -90.0);
        snakeHeadRotation.put(Direction.UP, 0.0);
        snakeHeadRotation.put(Direction.DOWN, 180.0);

        snakeBodyRotation.put(Direction.RIGHT, 0.0);
        snakeBodyRotation.put(Direction.LEFT, 180.0);
        snakeBodyRotation.put(Direction.UP, -90.0);
        snakeBodyRotation.put(Direction.DOWN, 90.0);

        for (int i = 0; i <= size; i++) {
            Rect bodyPiece = new Rect(startX + i * bodyWidth, startY, bodyWidth, bodyHeight, Direction.RIGHT);
            body[i] = bodyPiece;
            head++;
        }
        head--;
    }

    public void update(double dt) {
        if (waitTimeLeft > 0) {
            waitTimeLeft -= dt;
            return;
        }

        if (isIntersectingWithSelf()) {
            Window.getWindow().changeState(2);
        }
        changeDirection();
        move();
    }

    public void draw(Graphics2D g2) {
        for (int i = tail; i != head; i = (i + 1) % body.length) {
            Rect piece = body[i];

            if (i == head - 1) {
                g2.rotate(Math.toRadians(snakeHeadRotation.get(body[head].direction)), piece.x + bodyWidth / 2, piece.y + bodyHeight / 2);
                g2.drawImage(LoadingContent.getSnakeHead(), (int) piece.x - 4, (int) piece.y, (int) bodyWidth + 8, (int) bodyHeight, null);
                g2.rotate(Math.toRadians(-snakeHeadRotation.get(body[head].direction)), piece.x + bodyWidth / 2, piece.y + bodyHeight / 2);
            } else if (i == tail) {
                g2.rotate(Math.toRadians(snakeTailRotation.get(piece.direction) + 10), piece.x + bodyWidth / 2, piece.y + bodyHeight / 2);
                g2.drawImage(LoadingContent.getSnakeTail(), (int) piece.x - 6, (int) piece.y - 12, (int) bodyWidth + 8, (int) bodyHeight + 10, null);
                g2.rotate(Math.toRadians(-snakeTailRotation.get(piece.direction) - 10), piece.x + bodyWidth / 2, piece.y + bodyHeight / 2);
            } else {
                g2.rotate(Math.toRadians(snakeBodyRotation.get(piece.direction)), piece.x + bodyWidth / 2, piece.y + bodyHeight / 2);
                g2.drawImage(LoadingContent.getSnakeBody(), (int) piece.x, (int) piece.y, (int) bodyWidth, (int) bodyHeight, null);
                g2.rotate(Math.toRadians(-snakeBodyRotation.get(body[i].direction)), piece.x + bodyWidth / 2, piece.y + bodyHeight / 2);
            }
        }
    }

    public boolean isIntersectingWithSelf() {
        Rect headR = body[head];
        return isIntersectingWithRect(headR) || isIntersectingWithBoundaries(headR);
    }

    private boolean isIntersecting(Rect r1, Rect r2) {
        return (r1.x >= r2.x && r1.x + r1.width <= r2.x + r2.width &&
                r1.y >= r2.y && r1.y + r1.height <= r2.y + r2.height);
    }

    public boolean isIntersectingWithRect(Rect rect) {
        for (int i = tail; i != head; i = (i + 1) % body.length) {
            if (isIntersecting(rect, body[i])) return true;
        }
        return false;
    }

    public boolean isIntersectingWithBoundaries(Rect head) {
        return (head.x < background.x || head.x + head.width > background.x + background.width ||
                head.y < background.y || head.y + head.height > background.y + background.height);
    }

    public void move() {
        waitTimeLeft = ogWaitBetweenUpdates;
        double newX = 0;
        double newY = 0;

        switch (body[head].direction) {
            case RIGHT -> {
                newX = body[head].x + bodyWidth;
                newY = body[head].y;
            }
            case LEFT -> {
                newX = body[head].x - bodyWidth;
                newY = body[head].y;
            }
            case UP -> {
                newX = body[head].x;
                newY = body[head].y - bodyHeight;
            }
            case DOWN -> {
                newX = body[head].x;
                newY = body[head].y + bodyHeight;
            }
        }

        body[(head + 1) % body.length] = body[tail];
        body[(head + 1) % body.length].direction = body[head].direction;
        body[tail] = null;
        head = (head + 1) % body.length;
        tail = (tail + 1) % body.length;

        body[head].x = newX;
        body[head].y = newY;
    }

    public void changeDirection(Direction direction) {
        this.direction = direction;
    }

    private void changeDirection() {
        Direction headDirection = body[head].direction;
        if (headDirection == Direction.LEFT && direction != Direction.RIGHT ||
                headDirection == Direction.RIGHT && direction != Direction.LEFT ||
                headDirection == Direction.UP && direction != Direction.DOWN ||
                headDirection == Direction.DOWN && direction != Direction.UP) {
            body[head].direction = direction;
        }
    }

    public void grow() {
        double newX = 0;
        double newY = 0;
        Direction newDirection = Direction.RIGHT;

        switch (body[tail].direction) {
            case RIGHT -> {
                newX = body[tail].x - bodyWidth;
                newY = body[tail].y;
                newDirection = Direction.LEFT;
            }
            case LEFT -> {
                newX = body[tail].x + bodyWidth;
                newY = body[tail].y;
            }
            case UP -> {
                newX = body[tail].x;
                newY = body[tail].y + bodyHeight;
                newDirection = Direction.DOWN;
            }
            case DOWN -> {
                newX = body[tail].x;
                newY = body[tail].y - bodyHeight;
                newDirection = Direction.UP;
            }
        }

        Rect newBodyPiece = new Rect(newX, newY, bodyWidth, bodyHeight, newDirection);
        
        tail = tail - 1;
        if (tail < 0) {
            tail = body.length + tail;
        }
        body[tail] = newBodyPiece;
    }
}
