package org.example.scenes;

import org.example.Window;
import org.example.content.Constants;
import org.example.content.LoadingContent;
import org.example.helpers.Rect;
import org.example.listeners.KL;
import org.example.listeners.ML;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameOverScene extends Scene {
    public BufferedImage title, playAgain, yes, yesPressed, no,noPressed;
    public Rect titleRect, playAgainRect, yesRect, noRect;
    public BufferedImage yesCurrent, noCurrent;

    public GameOverScene(KL keyListener, ML mouseListener) {
        super(keyListener, mouseListener);

        title = LoadingContent.getTitleGameOver();
        playAgain = LoadingContent.getPlayAgain();
        yes = LoadingContent.getYes();
        yesPressed = LoadingContent.getYesPressed();
        no = LoadingContent.getNo();
        noPressed = LoadingContent.getNoPressed();

        yesCurrent = yes;
        noCurrent = no;

        titleRect = new Rect(160, -50, 480, 480);
        playAgainRect = new Rect(250, 300, 300, 50);
        yesRect = new Rect(315, 370, 81, 40);
        noRect = new Rect(415, 370, 57, 40);
    }

    @Override
    public void update(double dt) {
        if (mouseListener.getX() >= yesRect.x && mouseListener.getX() <= yesRect.x + yesRect.width &&
                mouseListener.getY() >= yesRect.y && mouseListener.getY() <= yesRect.y + yesRect.height) {
            yesCurrent = yesPressed;
            if (mouseListener.isPressed()) {
                org.example.Window.getWindow().changeState(1);
                mouseListener.setNotPressed();
            }
        } else {
            yesCurrent = yes;
        }

        if (mouseListener.getX() >= noRect.x && mouseListener.getX() <= noRect.x + noRect.width &&
                mouseListener.getY() >= noRect.y && mouseListener.getY() <= noRect.y + noRect.height) {
            noCurrent = noPressed;
            if (mouseListener.isPressed()) {
                Window.getWindow().changeState(0);
                mouseListener.setNotPressed();
            }
        } else {
            noCurrent = no;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Constants.foregroundColor);
        g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        g.drawImage(title, (int) titleRect.x, (int) titleRect.y, (int) titleRect.width, (int) titleRect.height, null);
        g.drawImage(playAgain, (int) playAgainRect.x, (int) playAgainRect.y, (int) playAgainRect.width, (int) playAgainRect.height, null);
        g.drawImage(yesCurrent, (int) yesRect.x, (int) yesRect.y, (int) yesRect.width, (int) yesRect.height, null);
        g.drawImage(noCurrent, (int) noRect.x, (int) noRect.y, (int) noRect.width, (int) noRect.height, null);
    }
}
