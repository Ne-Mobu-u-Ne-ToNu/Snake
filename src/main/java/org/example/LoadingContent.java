package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class LoadingContent {
    private static BufferedImage titleMenu, play, playPressed, exit, exitPressed, foreground, background,
            titleGameOver, playAgain, yes, yesPressed, no, noPressed, snakeHead, snakeBody, snakeTail;
    private static BufferedImage[] food;

    public static void load() {
        try {
            BufferedImage spriteSheetMenu = ImageIO.read(new File("assets/menuSprite.png"));
            titleMenu = spriteSheetMenu.getSubimage(0, 242, 960, 240);
            play = spriteSheetMenu.getSubimage(0, 121, 261, 121);
            playPressed = spriteSheetMenu.getSubimage(264, 121, 261, 121);
            exit = spriteSheetMenu.getSubimage(0, 0, 233, 93);
            exitPressed = spriteSheetMenu.getSubimage(264, 0, 233, 93);

            foreground = ImageIO.read(new File("assets/Grass.jpg"));
            background = ImageIO.read(new File("assets/Wall.png"));

            BufferedImage spriteSheetGameOver = ImageIO.read(new File("assets/gameOverSprite.png"));
            titleGameOver = spriteSheetGameOver.getSubimage(0, 0, 480, 480);
            playAgain = spriteSheetGameOver.getSubimage(0, 480, 377, 90);
            yes = spriteSheetGameOver.getSubimage(0, 570, 81, 52);
            yesPressed = spriteSheetGameOver.getSubimage(81, 570, 81, 52);
            no = spriteSheetGameOver.getSubimage(162, 572, 57, 49);
            noPressed = spriteSheetGameOver.getSubimage(219, 572, 57, 49);

            BufferedImage spriteSheetFood = ImageIO.read(new File("assets/Food.png"));
            food = new BufferedImage[] {
              spriteSheetFood.getSubimage(0, 0, 200, 200),
              spriteSheetFood.getSubimage(200, 0, 200, 200),
              spriteSheetFood.getSubimage(400, 0, 200, 200),
              spriteSheetFood.getSubimage(0, 200, 200, 200),
              spriteSheetFood.getSubimage(200, 200, 200, 200),
              spriteSheetFood.getSubimage(400, 200, 200, 200)
            };

            BufferedImage spriteSheetSnake = ImageIO.read(new File("assets/Snake.png"));
            snakeHead = spriteSheetSnake.getSubimage(0, 0, 300, 300);
            snakeBody = spriteSheetSnake.getSubimage(300,  125, 300, 450);
            snakeTail = spriteSheetSnake.getSubimage(0, 300, 300, 300);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static BufferedImage getTitleMenu() {
        return titleMenu;
    }

    public static BufferedImage getPlay() {
        return play;
    }

    public static BufferedImage getPlayPressed() {
        return playPressed;
    }

    public static BufferedImage getExit() {
        return exit;
    }

    public static BufferedImage  getExitPressed() {
        return exitPressed;
    }

    public static BufferedImage getForeground() {
        return foreground;
    }

    public static BufferedImage getBackground() {
        return background;
    }

    public static BufferedImage getTitleGameOver() {
        return titleGameOver;
    }

    public static BufferedImage getPlayAgain() {
        return playAgain;
    }

    public static BufferedImage getYes() {
        return yes;
    }

    public static BufferedImage getYesPressed() {
        return yesPressed;
    }

    public static BufferedImage getNo() {
        return no;
    }

    public static BufferedImage getNoPressed() {
        return noPressed;
    }

    public static BufferedImage[] getFood() {
        return food;
    }

    public static BufferedImage getSnakeHead() {
        return snakeHead;
    }

    public static BufferedImage getSnakeBody() {
        return snakeBody;
    }

    public static BufferedImage getSnakeTail() {
        return snakeTail;
    }
}
