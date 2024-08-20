package org.example;

import java.awt.Graphics;

public abstract class Scene {
    public KL keyListener;
    public ML mouseListener;

    public Scene(KL keyListener, ML mouseListener) {
        this.keyListener = keyListener;
        this.mouseListener = mouseListener;
    }
    public abstract void update(double dt);
    public abstract void draw(Graphics g);
}
