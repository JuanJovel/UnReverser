package com.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that collects words from a text file as an ArrayList.
 * 
 * @author Juan Jovel (www.github.com/JuanJovel)
 * @version 1.1
 * 
 */
public class WordCollector {

    private ArrayList<String> wordsCollected;
    private File toBeCollectedFrom;
    private Scanner wordScanner;

    /**
     * Instantiates a new word collector.
     *
     * @param file
     *            the text file to collect the words.
     */
    public WordCollector(File file) {
        this.toBeCollectedFrom = file;
        this.collect();
    }


    /**
     * Collects the words from the provided file and saves them in an ArrayList.
     */
    private void collect() {
        try {
            this.wordScanner = new Scanner(this.toBeCollectedFrom);
        }
        catch (FileNotFoundException e) {
            System.out.println("WordCollector could not find the file: "
                + this.toBeCollectedFrom.getName());
        }
        wordsCollected = new ArrayList<>();
        while (wordScanner.hasNextLine()) {
            wordsCollected.add(wordScanner.nextLine());
        }

        wordScanner.close();
    }


    /**
     * Gets the collected words.
     *
     * @return the collected words as an ArrayList.
     */
    public ArrayList<String> getCollectedWords() {
        return wordsCollected;
    }

}
