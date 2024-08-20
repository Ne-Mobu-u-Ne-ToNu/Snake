package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class LoadingContent {
    private static BufferedImage titleMenu, play, playPressed, exit, exitPressed, foreground,
            titleGameOver, playAgain, yes, yesPressed, no, noPressed;

    public static void load() {
        try {
            BufferedImage spriteSheetMenu = ImageIO.read(new File("assets/menuSprite.png"));
            titleMenu = spriteSheetMenu.getSubimage(0, 242, 960, 240);
            play = spriteSheetMenu.getSubimage(0, 121, 261, 121);
            playPressed = spriteSheetMenu.getSubimage(264, 121, 261, 121);
            exit = spriteSheetMenu.getSubimage(0, 0, 233, 93);
            exitPressed = spriteSheetMenu.getSubimage(264, 0, 233, 93);

            foreground = ImageIO.read(new File("assets/Grass.jpg"));

            BufferedImage spriteSheetGameOver = ImageIO.read(new File("assets/gameOverSprite.png"));
            titleGameOver = spriteSheetGameOver.getSubimage(0, 0, 480, 480);
            playAgain = spriteSheetGameOver.getSubimage(0, 480, 377, 90);
            yes = spriteSheetGameOver.getSubimage(0, 570, 81, 52);
            yesPressed = spriteSheetGameOver.getSubimage(81, 570, 81, 52);
            no = spriteSheetGameOver.getSubimage(162, 572, 57, 49);
            noPressed = spriteSheetGameOver.getSubimage(219, 572, 57, 49);

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
}
