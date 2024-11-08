package org.example.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * This class is used register if left
 * mouse button is pressed and to know
 * coordinates of the cursor
 * @author Ne-MoBu-u-Ne-ToNu
 * @version 1.0
 * @since 1.0
 */
public class ML extends MouseAdapter implements MouseListener {

    private boolean isPressed;
    private double x, y;

    /**
     * Method to register if left mouse
     * button is pressed
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            isPressed = true;
        }
    }

    /**
     * Method to register if right mouse
     * button is released
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        isPressed = false;
    }

    /**
     * Method to track coordinates of the cursor
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    /**
     * Method to get current mouse
     * cursor x coordinate on the screen
     * @return x coordinate of the cursor
     */
    public double getX() {
        return this.x;
    }

    /**
     * Method to get current mouse
     * cursor y coordinate of the cursor
     * @return y coordinate of the cursor
     */
    public double getY() {
        return this.y;
    }

    /**
     * Method to get if left mouse button is pressed
     * @return boolean value
     */
    public boolean isPressed() {
        return this.isPressed;
    }

    /**
     * Method to manually set left mouse button on release
     * state. Useful when there are two buttons on two screens
     * in the same place, like in game over scene in
     * {@link org.example.scenes.GameOverScene#update(double) update} method
     */
    public void setNotPressed() {
        this.isPressed = false;
    }
}
