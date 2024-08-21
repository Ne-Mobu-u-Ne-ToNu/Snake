package org.example.scenes;

import org.example.*;
import org.example.content.Constants;
import org.example.content.LoadingContent;
import org.example.helpers.Rect;
import org.example.listeners.KL;
import org.example.listeners.ML;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MenuScene extends Scene {
    public BufferedImage title, play, playPressed, exit, exitPressed;
    public Rect playRect, exitRect, titleRect;
    public BufferedImage playCurrent, exitCurrent;
    public MenuScene(KL keyListener, ML mouseListener) {
        super(keyListener, mouseListener);

            title = LoadingContent.getTitleMenu();
            play = LoadingContent.getPlay();
            playPressed = LoadingContent.getPlayPressed();
            exit = LoadingContent.getExit();
            exitPressed = LoadingContent.getExitPressed();


        playCurrent = play;
        exitCurrent = exit;

        titleRect = new Rect(240, 100, 300, 100);
        playRect = new Rect(310, 280, 150, 70);
        exitRect = new Rect(318, 355, 130, 55);
    }

    @Override
    public void update(double dt) {
        if (mouseListener.getX() >= playRect.x && mouseListener.getX() <= playRect.x + playRect.width &&
                mouseListener.getY() >= playRect.y && mouseListener.getY() <= playRect.y + playRect.height) {
            playCurrent = playPressed;
            if (mouseListener.isPressed()) {
                Window.getWindow().changeState(1);
            }
        } else {
            playCurrent = play;
        }

        if (mouseListener.getX() >= exitRect.x && mouseListener.getX() <= exitRect.x + exitRect.width &&
                mouseListener.getY() >= exitRect.y && mouseListener.getY() <= exitRect.y + exitRect.height) {
            exitCurrent = exitPressed;
            if (mouseListener.isPressed()) {
                Window.getWindow().close();
            }
        } else {
            exitCurrent = exit;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Constants.foregroundColor);
        g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        g.drawImage(title, (int) titleRect.x, (int) titleRect.y, (int) titleRect.width, (int) titleRect.height, null);
        g.drawImage(playCurrent, (int) playRect.x, (int) playRect.y, (int) playRect.width, (int) playRect.height, null);
        g.drawImage(exitCurrent, (int) exitRect.x, (int) exitRect.y, (int) exitRect.width, (int) exitRect.height, null);
    }
}
