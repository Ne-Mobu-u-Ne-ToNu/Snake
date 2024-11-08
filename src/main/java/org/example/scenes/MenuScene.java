package org.example.scenes;

import org.example.*;
import org.example.content.Constants;
import org.example.content.LoadingContent;
import org.example.helpers.Rect;
import org.example.listeners.KL;
import org.example.listeners.ML;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * This class is used to implement the menu screen
 * where you can start the game or exit from it.
 * @author Ne-MoBu-u-Ne-ToNu
 * @version 1.0
 * @since 1.0
 */
public class MenuScene extends Scene {

    /**
     * Image for the title of the game
     */
    public BufferedImage title;

    /**
     * Image for play button
     */
    public BufferedImage play;

    /**
     * Image for play button pressed
     */
    public BufferedImage playPressed;

    /**
     * Image for exit button
     */
    public BufferedImage exit;

    /**
     * Image for exit button pressed
     */
    public BufferedImage exitPressed;

    /**
     * Rectangle to store the parameters
     * of the play button image
     */
    public Rect playRect;

    /**
     * Rectangle to store the parameters
     * of the exit button image
     */
    public Rect exitRect;

    /**
     * Rectangle to store the parameters
     * ot the title image
     */
    public Rect titleRect;

    /**
     *
     */
    public BufferedImage playCurrent, exitCurrent;

    /**
     * Constructor of the class to load
     * all the main parameters
     * @param keyListener class for register keyboard buttons
     * @param mouseListener class for register mouse and cursor
     */
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

    /**
     * Method to update current state. It is used at the start of
     * the game where the player has to choose whether he wants
     * to play and go to the {@link GameScene gaming screen} or
     * close the game
     * @param dt difference of time between updates
     */
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

    /**
     * Method to draw current screen with its current state
     * @param g graphics object to draw on the screen
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Constants.backgroundColor);
        g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        g.drawImage(title, (int) titleRect.x, (int) titleRect.y, (int) titleRect.width, (int) titleRect.height, null);
        g.drawImage(playCurrent, (int) playRect.x, (int) playRect.y, (int) playRect.width, (int) playRect.height, null);
        g.drawImage(exitCurrent, (int) exitRect.x, (int) exitRect.y, (int) exitRect.width, (int) exitRect.height, null);
    }
}
