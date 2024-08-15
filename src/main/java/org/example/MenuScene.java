package org.example;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class MenuScene extends Scene {
    public MenuScene(KL keyListener) {
        super(keyListener);
    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }
}
