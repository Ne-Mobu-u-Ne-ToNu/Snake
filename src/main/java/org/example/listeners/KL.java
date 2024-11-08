package org.example.listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class is used register if one
 * of 128 keys on the keyboard is pressed
 * @author Ne-MoBu-u-Ne-ToNu
 * @version 1.0
 * @since 1.0
 */
public class KL extends KeyAdapter implements KeyListener {
    private final boolean[] keyPressed = new boolean[128];

    /**
     * Method to register if one of 128 keyboard keys is pressed
     * @param keyEvent the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        keyPressed[keyEvent.getKeyCode()] = true;
    }

    /**
     * Method to register if one of 128 keyboard keys is pressed
     * @param keyEvent the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        keyPressed[keyEvent.getKeyCode()] = false;
    }

    /**
     * Method to get current status of particular key
     * @param keyCode key to check
     * @return boolean value of the status
     */
    public boolean isKeyPressed(int keyCode) {
        return keyPressed[keyCode];
    }
}
