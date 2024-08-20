package org.example;

import java.awt.*;
import java.awt.geom.Rectangle2D;

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


    public Snake(int size, double startX, double startY, double bodyWidth, double bodyHeight, Rect background) {
        this.size = size;
        this.bodyWidth = bodyWidth;
        this.bodyHeight = bodyHeight;
        this.background = background;
        this.direction = Direction.RIGHT;

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
            double subWidth = (piece.width - 6.0) / 2.0;
            double subHeight = (piece.height - 6.0) / 2.0;

            g2.setColor(Color.BLACK);
            g2.fill(new Rectangle2D.Double(piece.x + 2.0, piece.y + 2.0, subWidth, subHeight));
            g2.fill(new Rectangle2D.Double(piece.x + 4.0 + subWidth, piece.y + 2.0, subWidth, subHeight));
            g2.fill(new Rectangle2D.Double(piece.x + 2.0, piece.y + 4.0 + subHeight, subWidth, subHeight));
            g2.fill(new Rectangle2D.Double(piece.x + 4.0 + subWidth, piece.y + 4.0 + subHeight, subWidth, subHeight));
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

        tail = (tail - 1) % body.length;
        body[tail] = newBodyPiece;
    }
}
