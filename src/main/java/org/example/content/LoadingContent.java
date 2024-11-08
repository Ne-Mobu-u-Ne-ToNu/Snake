package org.example.content;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * This class contains all content that needs to be
 * preloaded once and used frequently
 * @author Ne-MoBu-u-Ne-ToNu
 * @version 1.0
 * @since 1.0
 */
public class LoadingContent {
    private static BufferedImage titleMenu, play, playPressed, exit, exitPressed, foreground, background,
            titleGameOver, playAgain, yes, yesPressed, no, noPressed, snakeHead, snakeBody, snakeTail;
    private static BufferedImage[] food;

    /**
     * This method needs to be called only once at
     * the beginning of the game to load all
     * the necessary files
     */
    public static void load() {
        try {
            // Loading content for the main menu
            BufferedImage spriteSheetMenu = ImageIO.read(new File("assets/menuSprite.png"));
            titleMenu = spriteSheetMenu.getSubimage(0, 242, 960, 240);
            play = spriteSheetMenu.getSubimage(0, 121, 261, 121);
            playPressed = spriteSheetMenu.getSubimage(264, 121, 261, 121);
            exit = spriteSheetMenu.getSubimage(0, 0, 233, 93);
            exitPressed = spriteSheetMenu.getSubimage(264, 0, 233, 93);

            // Loading background for game scene
            foreground = ImageIO.read(new File("assets/Grass.jpg"));
            background = ImageIO.read(new File("assets/Wall.png"));

            // Loading content for game over screen
            BufferedImage spriteSheetGameOver = ImageIO.read(new File("assets/gameOverSprite.png"));
            titleGameOver = spriteSheetGameOver.getSubimage(0, 0, 480, 480);
            playAgain = spriteSheetGameOver.getSubimage(0, 480, 377, 90);
            yes = spriteSheetGameOver.getSubimage(0, 570, 81, 52);
            yesPressed = spriteSheetGameOver.getSubimage(81, 570, 81, 52);
            no = spriteSheetGameOver.getSubimage(162, 572, 57, 49);
            noPressed = spriteSheetGameOver.getSubimage(219, 572, 57, 49);

            // Loading different food images
            BufferedImage spriteSheetFood = ImageIO.read(new File("assets/Food.png"));
            food = new BufferedImage[] {
              spriteSheetFood.getSubimage(0, 0, 200, 200),
              spriteSheetFood.getSubimage(200, 0, 200, 200),
              spriteSheetFood.getSubimage(400, 0, 200, 200),
              spriteSheetFood.getSubimage(0, 200, 200, 200),
              spriteSheetFood.getSubimage(200, 200, 200, 200),
              spriteSheetFood.getSubimage(400, 200, 200, 200)
            };

            // Loading snake images
            BufferedImage spriteSheetSnake = ImageIO.read(new File("assets/Snake.png"));
            snakeHead = spriteSheetSnake.getSubimage(0, 0, 300, 300);
            snakeBody = spriteSheetSnake.getSubimage(300,  125, 300, 450);
            snakeTail = spriteSheetSnake.getSubimage(0, 300, 300, 300);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to get image of the title of the game
     * @return image of the game title
     */
    public static BufferedImage getTitleMenu() {
        return titleMenu;
    }

    /**
     * Method to get image of the play button
     * @return image of the play button
     */
    public static BufferedImage getPlay() {
        return play;
    }

    /**
     * Method to get image of the play button pressed
     * @return image of the play button pressed
     */
    public static BufferedImage getPlayPressed() {
        return playPressed;
    }

    /**
     * Method to get image of the exit button
     * @return image of the exit button
     */
    public static BufferedImage getExit() {
        return exit;
    }

    /**
     * Method to get image of the exit button pressed
     * @return image of the exit button pressed
     */
    public static BufferedImage  getExitPressed() {
        return exitPressed;
    }

    /**
     * Method to get foreground image of the game field
     * @return foreground image of the game field
     */
    public static BufferedImage getForeground() {
        return foreground;
    }

    /**
     * Method to get background image of the game field
     * @return background image of the game field
     */
    public static BufferedImage getBackground() {
        return background;
    }

    /**
     * Method to get image of the title of the game over screen
     * @return image of the game over screen title
     */
    public static BufferedImage getTitleGameOver() {
        return titleGameOver;
    }

    /**
     * Method to get image of play again question
     * @return image of play again question
     */
    public static BufferedImage getPlayAgain() {
        return playAgain;
    }

    /**
     * Method to get image of the yes button
     * @return image of the yes button
     */
    public static BufferedImage getYes() {
        return yes;
    }

    /**
     * Method to get image of the yes button pressed
     * @return image of the yes button pressed
     */
    public static BufferedImage getYesPressed() {
        return yesPressed;
    }

    /**
     * Method to get image of the no button
     * @return image of the no button
     */
    public static BufferedImage getNo() {
        return no;
    }

    /**
     * Method to get image of the no button pressed
     * @return image of the no button pressed
     */
    public static BufferedImage getNoPressed() {
        return noPressed;
    }

    /**
     * Method to get array of images of different types of food
     * @return array of images of different types of food
     */
    public static BufferedImage[] getFood() {
        return food;
    }

    /**
     * Method to get image of the snake head
     * @return image of the snake head
     */
    public static BufferedImage getSnakeHead() {
        return snakeHead;
    }

    /**
     * Method to get image of the snake body
     * @return image of the snake body
     */
    public static BufferedImage getSnakeBody() {
        return snakeBody;
    }

    /**
     * Method to get image of the snake tail
     * @return image of the snake tail
     */
    public static BufferedImage getSnakeTail() {
        return snakeTail;
    }
}
