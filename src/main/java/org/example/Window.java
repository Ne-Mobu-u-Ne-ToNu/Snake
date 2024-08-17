package org.example;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements Runnable {
    private static Window window = null;
    public boolean isRunning;

    public int currentState;
    public Scene currentScene;
    public KL keyListener = new KL();
    public ML mouseListener = new ML();

    public Window(int width, int height, String title) {
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

    public static Window getWindow() {
        if (Window.window == null) {
            Window.window = new Window(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, Constants.TITLE);
        }
        return Window.window;
    }

    public void changeState(int newState) {
        currentState = newState;
        switch (currentState) {
            case 0 -> currentScene = new MenuScene(keyListener, mouseListener);
            case 1 -> currentScene = new GameScene(keyListener);
            default -> {
                System.out.println("Unknown scene");
                currentScene = null;
            }
        }
    }

    public void close() {
        isRunning = false;
    }

    public void update(double dt) {
        Image dbImage = createImage(getWidth(), getWidth());
        Graphics dbGraphics = dbImage.getGraphics();
        this.draw(dbGraphics);
        getGraphics().drawImage(dbImage, 0, 0, this);

        currentScene.update(dt);
    }

    public void draw(Graphics g) {
        currentScene.draw(g);
    }

    @Override
    public void run() {
        double lastFrameTime = 0.0;
        try {
            while (isRunning) {
                double time = Time.getTime();
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
