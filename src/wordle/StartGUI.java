package wordle;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class StartGUI extends JFrame {
    private JFrame startFrame = null;
    private final LetterBox[][] letterBoxesArray = new LetterBox[6][5];
    private int currPos = 0; // First position
    private int currRow = 0; // First row
    private int numCorrect = 0;
    private String targetWord = null;
    private ArrayList<String> words = null;
    private String username = null;

    StartGUI(String word, String newUsername, ArrayList<String> newWords) {
        targetWord = word;
        username = newUsername;
        words = newWords;
        
        // Set up our frame for the window
        startFrame = new JFrame("Wordle by Kelly Wu");
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setSize(600, 900);
        startFrame.setResizable(false);
        startFrame.setLocationRelativeTo(null);
        
        // Have a panel that contains all the elements
        JPanel background = new JPanel();
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
    // ====================================================================== // 
        // Create title (Top)
        JLabel titleLabel = new JLabel("Wordle", JLabel.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        titleLabel.setPreferredSize(new Dimension(600, 80));
        titleLabel.setMaximumSize(new Dimension(600, 80));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    // ====================================================================== // 
        // Create the letter boxes (Middle)
        JPanel letterBoxesPanel = new JPanel();
        letterBoxesPanel.setLayout(new GridLayout(0, 1));
        letterBoxesPanel.setOpaque(false);

        // Create row panels
        JPanel[] rowsArray = new JPanel[6];

        // Fill row panels
        for (int i = 0; i < 6; ++i) {
            rowsArray[i] = createRowPanel();
            LetterBox[] lbArray = new LetterBox[5];
            for (int j = 0; j < 5; ++j) {
                LetterBox lb = new LetterBox();
                lbArray[j] = lb;
                rowsArray[i].add(lb);
            }
            letterBoxesArray[i] = lbArray;
            letterBoxesPanel.add(rowsArray[i]);
        }
    // ====================================================================== // 
        // Create keyboard (Bottom)
        JPanel keyboardPanel = new JPanel();
        keyboardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        keyboardPanel.setOpaque(false);
        
        // Top row keys
        JPanel topRowKeysPanel = new JPanel();
        topRowKeysPanel.setOpaque(false);
        topRowKeysPanel.add(createKey("Q", true).getKeyButton());
        topRowKeysPanel.add(createKey("W", true).getKeyButton());
        topRowKeysPanel.add(createKey("E", true).getKeyButton());
        topRowKeysPanel.add(createKey("R", true).getKeyButton());
        topRowKeysPanel.add(createKey("T", true).getKeyButton());
        topRowKeysPanel.add(createKey("Y", true).getKeyButton());
        topRowKeysPanel.add(createKey("U", true).getKeyButton());
        topRowKeysPanel.add(createKey("I", true).getKeyButton());
        topRowKeysPanel.add(createKey("O", true).getKeyButton());
        topRowKeysPanel.add(createKey("P", true).getKeyButton());
        
        // Middle row keys
        JPanel middleRowKeysPanel = new JPanel();
        middleRowKeysPanel.setOpaque(false);
        middleRowKeysPanel.add(createKey("A", true).getKeyButton());
        middleRowKeysPanel.add(createKey("S", true).getKeyButton());
        middleRowKeysPanel.add(createKey("D", true).getKeyButton());
        middleRowKeysPanel.add(createKey("F", true).getKeyButton());
        middleRowKeysPanel.add(createKey("G", true).getKeyButton());
        middleRowKeysPanel.add(createKey("H", true).getKeyButton());
        middleRowKeysPanel.add(createKey("J", true).getKeyButton());
        middleRowKeysPanel.add(createKey("K", true).getKeyButton());
        middleRowKeysPanel.add(createKey("L", true).getKeyButton());
        
        // Bottom row keys
        JPanel bottomRowKeysPanel = new JPanel();
        bottomRowKeysPanel.setOpaque(false);
        bottomRowKeysPanel.add(createKey("ENTER", false).getKeyButton());
        bottomRowKeysPanel.add(createKey("Z", true).getKeyButton());
        bottomRowKeysPanel.add(createKey("X", true).getKeyButton());
        bottomRowKeysPanel.add(createKey("C", true).getKeyButton());
        bottomRowKeysPanel.add(createKey("V", true).getKeyButton());
        bottomRowKeysPanel.add(createKey("B", true).getKeyButton());
        bottomRowKeysPanel.add(createKey("N", true).getKeyButton());
        bottomRowKeysPanel.add(createKey("M", true).getKeyButton());
        bottomRowKeysPanel.add(createKey("BACK", false).getKeyButton());

        keyboardPanel.add(topRowKeysPanel);
        keyboardPanel.add(middleRowKeysPanel);
        keyboardPanel.add(bottomRowKeysPanel);
    // ====================================================================== // 
        background.add(titleLabel);
        background.add(letterBoxesPanel);
        background.add(keyboardPanel);

        startFrame.add(background);
        startFrame.setVisible(true);
    }
    
    static JPanel createRowPanel() {
        JPanel rp = new JPanel();
        rp.setOpaque(false);
        return rp;
    }
    
    Key createKey(String text, Boolean isLetter) {
        Key key = new Key(text, isLetter);
        key.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isLetter) {
                    if (numCorrect != 5 && currRow < 6 && currPos != 5) {                                                
                        // Update the current letter box with the letter
                        String letter = text;
                        letterBoxesArray[currRow][currPos].setLetter(letter);
                        // Move to the next position
                        currPos++;
                    }
                }
                else if (text.equals("BACK")) {
                    if (currPos > 0) {
                         // Clear letterBox and move back one position
                        currPos--;
                        letterBoxesArray[currRow][currPos].setLetter("");
                    }
                }
                else if (text.equals("ENTER")) {
                    if (currRow < 6 && currPos == 5) {
                        // Check if this word is in the words list
                        String rowWord = "";
                        for (int i = 0; i < 5; i++) {
                            String currLetter = letterBoxesArray[currRow][i].getLetter().toLowerCase();
                            rowWord += currLetter;
                        }
                        
                        boolean found = words.contains(rowWord);        
                        if (found) { // word in word list
                            // Update the color of boxes
                            for (int i = 0; i < 5; i++) {
                                String currLetter = letterBoxesArray[currRow][i].getLetter().toLowerCase();

                                if (currLetter.equals(String.valueOf(targetWord.charAt(i)))) {
                                   letterBoxesArray[currRow][i].setColor(3); // Letter in right position
                                   numCorrect++;
                                }
                                else if (targetWord.contains(currLetter)) {
                                   letterBoxesArray[currRow][i].setColor(2); // Letter in wrong position
                                }
                                else {
                                    letterBoxesArray[currRow][i].setColor(1); // Letter is not in word
                                }
                            }
                            // Determine results
                            if (numCorrect == 5) {
                                new EndGUI(currRow, targetWord, username, startFrame);
                            }
                            else {
                                // Reset to new row
                                currRow++;
                                currPos = 0;
                                numCorrect = 0;

                                if (currRow == 6) {
                                    new EndGUI(currRow, targetWord, username, startFrame);
                                }
                            }
                        }
                        else { // word not in word list
                            new ReminderGUI();
                        }
                    }
                }
            }
        });
        return key;
    }
}