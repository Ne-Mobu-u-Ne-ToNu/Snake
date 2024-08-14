package org.example;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements Runnable {
    public boolean isRunning;

    public Window(int width, int height, String title) {
        setSize(width, height);
        setTitle(title);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        isRunning = true;
    }

    public void update(double deltaTime) {
        Image dbImage = createImage(getWidth(), getWidth());
        Graphics dbGraphics = dbImage.getGraphics();
        this.draw(dbGraphics);
        getGraphics().drawImage(dbImage, 0, 0, this);
    }

    public void draw(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());
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
    }
}
