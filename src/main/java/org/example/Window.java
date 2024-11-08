package org.example;

import org.example.content.Constants;
import org.example.content.LoadingContent;
import org.example.listeners.KL;
import org.example.listeners.ML;
import org.example.scenes.GameOverScene;
import org.example.scenes.GameScene;
import org.example.scenes.MenuScene;
import org.example.scenes.Scene;

import javax.swing.*;
import java.awt.*;

/**
 * This class is used to load all the parameter related
 * to game screen and its listeners. Also, it is used
 * to switch between gaming screens and close the game
 * @author Ne-MoBu-u-Ne-ToNu
 * @version 1.0
 * @since 1.0
 */
public class Window extends JFrame implements Runnable {
    private static Window window = null;

    /**
     * Boolean variable to understand if the
     * program should be running
     */
    public boolean isRunning;

    /**
     * Current state is an integer value representing
     * which screen should see the user right now
     */
    public int currentState;

    /**
     * Current gaming scene to get its methods and run them
     */
    public Scene currentScene;

    /**
     * {@link KL Class} for register keyboard buttons
     */
    public KL keyListener = new KL();

    /**
     * {@link ML Class} for register mouse and cursor
     */
    public ML mouseListener = new ML();

    private Window(int width, int height, String title) {
        LoadingContent.load();
        setSize(width, height);
        setTitle(title);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(keyListener);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        isRunning = true;
        changeState(0);
    }

    /**
     * Method to get the instance of this class. If there are no
     * any instances we create the new one with default screen
     * parameters described in the {@link Constants constants} class
     * @return instance of this class
     */
    public static Window getWindow() {
        if (Window.window == null) {
            Window.window = new Window(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, Constants.TITLE);
        }
        return Window.window;
    }

    /**
     * Method to change current screen
     * @param newState number of the screen needed
     */
    public void changeState(int newState) {
        currentState = newState;
        switch (currentState) {
            case 0 -> currentScene = new MenuScene(keyListener, mouseListener);
            case 1 -> currentScene = new GameScene(keyListener, mouseListener);
            case 2 -> currentScene = new GameOverScene(keyListener, mouseListener);
            default -> {
                System.out.println("Unknown scene");
                currentScene = null;
            }
        }
    }

    /**
     * Method to close the program. It stops the thread
     * by setting {@link #isRunning isRunning} boolean
     * variable to false
     */
    public void close() {
        isRunning = false;
    }

    /**
     * Method to update current state.
     * @param dt difference of time between updates
     */
    public void update(double dt) {
        Image dbImage = createImage(getWidth(), getWidth());
        Graphics dbGraphics = dbImage.getGraphics();
        this.draw(dbGraphics);
        getGraphics().drawImage(dbImage, 0, 0, this);

        currentScene.update(dt);
    }

    /**
     * Method to draw current screen with its current state
     * @param g graphics object to draw on the screen
     */
    public void draw(Graphics g) {
        currentScene.draw(g);
    }

    /**
     * Method to start the program and run on a distinct
     * thread
     */
    @Override
    public void run() {
        double lastFrameTime = 0.0;
        try {
            while (isRunning) {
                double time = System.nanoTime() * 1e-9;
                double deltaTime = time - lastFrameTime;
                lastFrameTime = time;

                update(deltaTime);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        this.dispose();
    }
}
