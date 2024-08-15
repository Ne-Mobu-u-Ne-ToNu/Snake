package org.example;

import java.awt.Graphics;

public abstract class Scene {
    public KL keyListener;

    public Scene(KL keyListener) {
        this.keyListener = keyListener; 
    }
    public abstract void update(double dt);
    public abstract void draw(Graphics g);
}
