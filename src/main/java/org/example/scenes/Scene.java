package org.example.scenes;

import org.example.listeners.KL;
import org.example.listeners.ML;

import java.awt.Graphics;

/**
 * This class is used to help to implement all
 * gaming screen classes
 * @author Ne-MoBu-u-Ne-ToNu
 * @version 1.0
 * @since 1.0
 */
public abstract class Scene {

    /**
     * {@link KL Class} for register keyboard buttons
     */
    public KL keyListener;

    /**
     * {@link ML Class} for register mouse and cursor
     */
    public ML mouseListener;

    /**
     * Constructor of the class to load needed listeners
     * @param keyListener class for register keyboard buttons
     * @param mouseListener class for register mouse and cursor
     */
    public Scene(KL keyListener, ML mouseListener) {
        this.keyListener = keyListener;
        this.mouseListener = mouseListener;
    }

    /**
     *  Method to update current state
     * @param dt difference of time between updates
     */
    public abstract void update(double dt);

    /**
     * Method to draw current screen with its current state
     * @param g graphics object to draw on the screen
     */
    public abstract void draw(Graphics g);
}
