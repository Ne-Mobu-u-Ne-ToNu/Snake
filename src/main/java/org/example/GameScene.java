package org.example;

import java.awt.Graphics;
import java.awt.Color;

public class GameScene extends Scene {
    public GameScene(KL keyListener) {
        super(keyListener);
    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }
}
