package org.example.scenes;

import org.example.Window;
import org.example.content.Constants;
import org.example.content.LoadingContent;
import org.example.helpers.Rect;
import org.example.listeners.KL;
import org.example.listeners.ML;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This class is used to implement the screen after
 * the game over. It consists with one question and to
 * answers. You can start the game again if you click
 * the yes button or go to main menu if you click on no button
 * @author Ne-MoBu-u-Ne-ToNu
 * @version 1.0
 * @since 1.0
 */
public class GameOverScene extends Scene {

    /**
     * Image for game over title
     */
    public BufferedImage title;

    /**
     * Image for play again question
     */
    public BufferedImage playAgain;

    /**
     * Image for yes button
     */
    public BufferedImage yes;

    /**
     * Image for yes button pressed
     */
    public BufferedImage yesPressed;

    /**
     * Image for no button
     */
    public BufferedImage no;

    /**
     * Image for no button pressed
     */
    public BufferedImage noPressed;

    /**
     * Rectangle to store the parameters of
     * game over title image
     */
    public Rect titleRect;

    /**
     * Rectangle to store the parameters of
     * play again button image
     */
    public Rect playAgainRect;

    /**
     * Rectangle to store the parameters of
     * yes button image
     */
    public Rect yesRect;

    /**
     * Rectangle to store the parameters of
     * no button image
     */
    public Rect noRect;

    /**
     * Current image of yes button to draw
     */
    public BufferedImage yesCurrent;

    /**
     * Current image of no button to draw
     */
    public BufferedImage noCurrent;

    /**
     * Constructor of the class to load all the main
     * parameters
     * @param keyListener class for register keyboard buttons
     * @param mouseListener class for register mouse and cursor
     */
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

    /**
     * Method to update current state. It is used to navigate between
     * game screens. After 'death' in the game you will have to
     * choose if you want to start again or no. If yes you redirect to
     * the {@link GameScene gaming screen}. If no you redirect to the
     * {@link  MenuScene menu screen}
     * @param dt difference of time between updates
     */
    @Override
    public void update(double dt) {
        if (mouseListener.getX() >= yesRect.x && mouseListener.getX() <= yesRect.x + yesRect.width &&
                mouseListener.getY() >= yesRect.y && mouseListener.getY() <= yesRect.y + yesRect.height) {
            yesCurrent = yesPressed;
            if (mouseListener.isPressed()) {
                org.example.Window.getWindow().changeState(1);
                // We need to set mouse button to released to not press button on the next screen
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
                // We need to set mouse button to released to not press button on the next screen
                mouseListener.setNotPressed();
            }
        } else {
            noCurrent = no;
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
        g.drawImage(playAgain, (int) playAgainRect.x, (int) playAgainRect.y, (int) playAgainRect.width, (int) playAgainRect.height, null);
        g.drawImage(yesCurrent, (int) yesRect.x, (int) yesRect.y, (int) yesRect.width, (int) yesRect.height, null);
        g.drawImage(noCurrent, (int) noRect.x, (int) noRect.y, (int) noRect.width, (int) noRect.height, null);
    }
}
