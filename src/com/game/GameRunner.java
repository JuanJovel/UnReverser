package com.game;

import java.io.FileNotFoundException;
import com.classes.GameFrame;

/**
 * Runs the game.
 */
public class GameRunner {

    /**
     * The main method.
     *
     * @param args
     *            not used.
     * @throws FileNotFoundException
     *             if the word file is not found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        @SuppressWarnings("unused")
        GameFrame game = new GameFrame();
    }

}
