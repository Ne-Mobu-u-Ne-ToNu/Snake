package org.example.gameObjects;

import org.example.helpers.Direction;
import org.example.helpers.Rect;
import org.example.Window;
import org.example.content.LoadingContent;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to describe the snake. It can make the snake
 * move, change direction, grow and collide with itself, boundaries and
 * food.
 * @author Ne-MoBu-u-Ne-ToNu
 * @version 1.0
 * @since 1.0
 */
public class Snake {

    /**
     * Body of the snake as an array of 10000 body pieces
     */
    public Rect[] body = new Rect[10000];

    /**
     * Rectangle of the game field
     */
    public Rect background;

    /**
     * Size of each body piece
     */
    public double bodyWidth, bodyHeight;

    /**
     * Direction of the snake current or to be changed
     */
    public Direction direction;

    /**
     * Current pointer on the tail of the snake
     */
    public int tail = 0;

    /**
     * Current pointer on the head of the snake
     */
    public int head = 0;

    /**
     * Waiting time between updates. Basically it is snake's speed.
     * More waiting time, less snake's speed
     */
    public double ogWaitBetweenUpdates = 0.1f;

    /**
     * Remaining time to wait between updates
     */
    public double waitTimeLeft = ogWaitBetweenUpdates;

    private final Map<Direction, Double> snakeHeadRotation, snakeBodyRotation,
            snakeTailRotation;


    /**
     * Constructor of the class where each field and snake's body are being initialized
     * @param size initial size of the snake
     * @param startX starting x coordinate of the snake
     * @param startY starting y coordinate of the snake
     * @param bodyWidth width of the snake's body piece
     * @param bodyHeight height of the snake's body piece
     * @param background rectangle describing game field
     */
    public Snake(int size, double startX, double startY, double bodyWidth, double bodyHeight, Rect background) {
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

    /**
     * Method to update current state. When remaining time between updates
     * is more than zero only remaining time is being updated. Else this
     * method checks whether snake is intersecting with itself. If yes
     * then it is game over else snake's state is being updated.
     * @param dt time between frames
     */
    public void update(double dt) {
        if (waitTimeLeft > 0) {
            waitTimeLeft -= dt;
            return;
        }

        if (isIntersectingForGameOver()) {
            Window.getWindow().changeState(2);
        }
        changeDirection();
        move();
    }

    /**
     * Method to draw current state of the snake. It has three main parts
     * to draw: head, body and tail
     * @param g2 object with methods to draw something on the screen
     */
    public void draw(Graphics2D g2) {
        for (int i = tail; i != head; i = (i + 1) % body.length) {
            Rect piece = body[i];

            /*
              We need to check which body part we are dealing with, because
              it depends on what picture is to be loaded and how to rotate
              it. Also, you can scale particular body part.
             */
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

    /**
     * Method to check if the snake is intersecting with itself
     * and boundaries ot the game scene
     * @return boolean value
     */
    public boolean isIntersectingForGameOver() {
        Rect headR = body[head];
        return isIntersectingWithRect(headR) || isIntersectingWithBoundaries(headR);
    }

    private boolean isIntersecting(Rect r1, Rect r2) {
        return (r1.x >= r2.x && r1.x + r1.width <= r2.x + r2.width &&
                r1.y >= r2.y && r1.y + r1.height <= r2.y + r2.height);
    }

    /**
     * This method is used when we need to know if the snake is
     * intersecting with particular rectangle. It can be useful
     * for checking for game over like in {@link #isIntersectingForGameOver() isIntersectingForGameOver}
     * method or for generating and eating food like in {@link Food#spawn() spawn} and
     * {@link Food#update() update} methods
     * @param rect rectangle to check
     * @return boolean value
     */
    public boolean isIntersectingWithRect(Rect rect) {
        for (int i = tail; i != head; i = (i + 1) % body.length) {
            if (isIntersecting(rect, body[i])) return true;
        }
        return false;
    }

    private boolean isIntersectingWithBoundaries(Rect head) {
        return (head.x < background.x || head.x + head.width > background.x + background.width ||
                head.y < background.y || head.y + head.height > background.y + background.height);
    }

    /**
     * Method to move the snake in the asked direction. It
     * finds new x and y coordinates according to current
     * head direction. It is called from the {@link #update(double) update} method
     */
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

        /*
          To make the snake move after all, we need
          to take one piece from the tail and move
          it to our new head position and then
          update tail position in the snake array
         */
        body[(head + 1) % body.length] = body[tail];
        body[(head + 1) % body.length].direction = body[head].direction;
        body[tail] = null;
        head = (head + 1) % body.length;
        tail = (tail + 1) % body.length;

        body[head].x = newX;
        body[head].y = newY;
    }

    /**
     * This method changes the direction of the snake's head, but
     * it cannot change it opposite to the current direction
     * @param direction direction to be changed on
     */
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

    /**
     * This method creates a new body piece of the snake
     * opposite to the previous tail direction, and it is
     * called on the {@link #update(double) update} method
     */
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
