package org.example;

/**
 * This class is used to start the game
 * gaming screen classes
 * @author Ne-MoBu-u-Ne-ToNu
 * @version 1.0
 * @since 1.0
 */
public class Main {

    /**
     * Method to start the program
     * @param args command line parameters
     */
    public static void main(String[] args) {
        Window window = Window.getWindow();

        Thread thread = new Thread(window);
        thread.start();
    }
}