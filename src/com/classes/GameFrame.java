package com.classes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * The Class GameFrame.
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame {

    private JTextField solutionField;
    private JLabel wordLabel;
    private Reverser gameReverser;
    private JLabel timer;
    private long startTime;
    private long endTime;
    private ArrayList<String> gameWords;
    private int[] randomIndexes;
    private int wordsSolved = 0;
    private static final int NUM_WORDS_PER_GAME = 10;
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 350;
    private JButton beginButton;
    private JButton tryAgain;

    /**
     * Instantiates a new GameFrame.
     *
     * @throws FileNotFoundException
     *             if the file doesn't exist.
     */
    public GameFrame() throws FileNotFoundException {

        // Sets the title, icon, dimensions, and layout of the frame.
        setTitle("THE UNREVERSER");
        ImageIcon windowIcon = new ImageIcon("windowIcon.png");
        setIconImage(windowIcon.getImage());
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        int xLocation = (screenDimension.width/2) - (FRAME_WIDTH/2);
        int yLocation = (screenDimension.height/2) - (FRAME_HEIGHT/2);
        setLocation(xLocation, yLocation);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 20));

        // Initializes a new Reverser.
        gameReverser = new Reverser();

        // Collects the words from the file using the WordCollector class.
        File wordsFile = new File("gameWords.txt");
        WordCollector gameCollector = new WordCollector(wordsFile);
        gameWords = gameCollector.getCollectedWords();

        // Initializes an array of 10 random integers.
        randomIndexes = new int[10];
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            int randomIndex = random.nextInt(gameWords.size());
            randomIndexes[i] = randomIndex;
        }

        // Declares and initializes a north panel.
        JPanel northPanel = new JPanel();
        getContentPane().add(northPanel, BorderLayout.NORTH);
        // Adds a title label to the north panel.
        JLabel title = new JLabel("UNREVERSER");
        title.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 50));
        northPanel.add(title);

        // Declares and initializes a center panel.
        JPanel centerPanel = new JPanel();
        getContentPane().add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(new BorderLayout(0, 5));
        
        // Initializes and sets the properties of the solution field text box.
        solutionField = new JTextField();
        solutionField.setHorizontalAlignment(SwingConstants.CENTER);
        solutionField.setFont(new Font("Tahoma", Font.PLAIN, 40));
        solutionField.setEditable(false);
        solutionField.addActionListener(new ReverseListener());
        centerPanel.add(solutionField);
        solutionField.setColumns(20);
        
        // Initializes and sets the properties of the reversed word label.
        wordLabel = new JLabel("Click BEGIN to start!");
        wordLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        wordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(wordLabel, BorderLayout.SOUTH);
        // Initializes an sets the properties of the timer label.
        timer = new JLabel("Unreverse all 10 words as fast as possible!");
        timer.setFont(new Font("Tahoma", Font.PLAIN, 30));
        timer.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(timer, BorderLayout.NORTH);

        // Declares and initializes a south panel.
        JPanel southPanel = new JPanel();
        
        // Initializes and sets the properties of the begin button.
        beginButton = new JButton("BEGIN");
        beginButton.addActionListener(new BeginListener());
        southPanel.add(beginButton);
        
        // Initializes and sets the properties of the try again button.
        tryAgain = new JButton("AGAIN");
        tryAgain.addActionListener(new TryAgainListener());
        southPanel.add(tryAgain);
        getContentPane().add(southPanel, BorderLayout.SOUTH);

        // Sets the frame to be visible.
        setVisible(true);

    }

    /**
     * The listener interface for receiving reverse events.
     * The class that is interested in processing a reverse
     * event implements this interface, and the object created
     * with that class is registered with a component using the
     * component's <code>addReverseListener<code> method. When
     * the reverse event occurs, that object's appropriate
     * method is invoked.
     *
     * @see reverseEvent
     */
    private class ReverseListener implements ActionListener {

        /**
         * Action performed.
         *
         * @param event
         *            the event
         */
        @Override
        public void actionPerformed(ActionEvent reverseEvent) {
            // If the user has unreversed the word and there is more words to
            // go, change to the next word.
            if (gameReverser.isReverseOf(solutionField.getText(), wordLabel
                .getText()) && wordsSolved + 1 < NUM_WORDS_PER_GAME) {
                solutionField.setText("");
                wordLabel.setText(gameReverser.reverse(gameWords.get(
                    randomIndexes[wordsSolved + 1])));
                wordsSolved++;
            }

            // If the user has finished solving all the words calculate the time
            // it took them and display according messages.
            else if (wordsSolved + 1 >= NUM_WORDS_PER_GAME) {
                solutionField.setText("Time!");
                solutionField.setEditable(false);
                endTime = System.nanoTime();
                double timeTaken = (endTime - startTime) / 1e9;
                NumberFormat format = new DecimalFormat("0.00");
                timer.setText("You took: " + format.format(timeTaken)
                    + " seconds");
                wordLabel.setText("Nice job!");

            }

            repaint();
        }

    }


    /**
     * The listener interface for receiving begin events.
     * The class that is interested in processing a begin
     * event implements this interface, and the object created
     * with that class is registered with a component using the
     * component's <code>addBeginListener<code> method. When
     * the begin event occurs, that object's appropriate
     * method is invoked.
     *
     * @see beginEvent
     */
    private class BeginListener implements ActionListener {

        /**
         * Action performed.
         *
         * @param beginEvent
         *            the begin event
         */
        @Override
        public void actionPerformed(ActionEvent beginEvent) {
            // When the user clicks begin, the timer must be started, the begin
            // button must be disabled and the solution field should be changed
            // to editable.
            startTime = System.nanoTime();
            beginButton.setEnabled(false);
            solutionField.setEditable(true);
            solutionField.requestFocus();
            wordLabel.setText(gameReverser.reverse(gameWords.get(
                randomIndexes[wordsSolved])));
            repaint();
        }

    }


    /**
     * The listener interface for receiving tryAgain events.
     * The class that is interested in processing a tryAgain
     * event implements this interface, and the object created
     * with that class is registered with a component using the
     * component's <code>addTryAgainListener<code> method. When
     * the tryAgain event occurs, that object's appropriate
     * method is invoked.
     *
     * @see tryAgainEvent
     */
    private class TryAgainListener implements ActionListener {

        /**
         * Action performed.
         *
         * @param tryAgain
         *            the try again
         */
        @Override
        public void actionPerformed(ActionEvent tryAgainEvent) {
            // Disposes this GameFrame and creates a new one.
            dispose();
            try {
                new GameFrame();
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}
