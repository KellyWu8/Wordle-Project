package wordle;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class Wordle extends JFrame {
    
    static LetterBox[][] letterBoxesArray = new LetterBox[6][5];
    static int currPos = 0; // First position
    static int currRow = 0; // First row

    public static void main(String[] args) {
        // Set up our frame for the window
        JFrame frame = new JFrame("Wordle by Kelly Wu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 900);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        
        // Have a panel that contains all the elements
        JPanel background = new JPanel();
        background.setBackground(Color.white);
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
// ========================================================================== // 
        // Create title (Top)
        JLabel titleLabel = new JLabel("Wordle", JLabel.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        titleLabel.setPreferredSize(new Dimension(600, 80));
        titleLabel.setMaximumSize(new Dimension(600, 80));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
// ========================================================================== // 
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
// ========================================================================== // 
        // Create keyboard (Bottom)
        JPanel keyboardPanel = new JPanel();
        keyboardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        keyboardPanel.setOpaque(false);
        
        // Top row keys
        JPanel topRowKeysPanel = new JPanel();
        topRowKeysPanel.setOpaque(false);
        topRowKeysPanel.add(createLetterKey("Q"));
        topRowKeysPanel.add(createLetterKey("W"));
        topRowKeysPanel.add(createLetterKey("E"));
        topRowKeysPanel.add(createLetterKey("R"));
        topRowKeysPanel.add(createLetterKey("T"));
        topRowKeysPanel.add(createLetterKey("Y"));
        topRowKeysPanel.add(createLetterKey("U"));
        topRowKeysPanel.add(createLetterKey("I"));
        topRowKeysPanel.add(createLetterKey("O"));
        topRowKeysPanel.add(createLetterKey("P"));
        
        // Middle row keys
        JPanel middleRowKeysPanel = new JPanel();
        middleRowKeysPanel.setOpaque(false);
        middleRowKeysPanel.add(createLetterKey("A"));
        middleRowKeysPanel.add(createLetterKey("S"));
        middleRowKeysPanel.add(createLetterKey("D"));
        middleRowKeysPanel.add(createLetterKey("F"));
        middleRowKeysPanel.add(createLetterKey("G"));
        middleRowKeysPanel.add(createLetterKey("H"));
        middleRowKeysPanel.add(createLetterKey("J"));
        middleRowKeysPanel.add(createLetterKey("K"));
        middleRowKeysPanel.add(createLetterKey("L"));
        
        // Bottom row keys
        JPanel bottomRowKeysPanel = new JPanel();
        bottomRowKeysPanel.setOpaque(false);
        bottomRowKeysPanel.add(createSpecialKey("ENTER"));
        bottomRowKeysPanel.add(createLetterKey("Z"));
        bottomRowKeysPanel.add(createLetterKey("X"));
        bottomRowKeysPanel.add(createLetterKey("C"));
        bottomRowKeysPanel.add(createLetterKey("V"));
        bottomRowKeysPanel.add(createLetterKey("B"));
        bottomRowKeysPanel.add(createLetterKey("N"));
        bottomRowKeysPanel.add(createLetterKey("M"));
        bottomRowKeysPanel.add(createSpecialKey("BACK"));

        keyboardPanel.add(topRowKeysPanel);
        keyboardPanel.add(middleRowKeysPanel);
        keyboardPanel.add(bottomRowKeysPanel);
// ========================================================================== // 
        background.add(titleLabel);
        background.add(letterBoxesPanel);
        background.add(keyboardPanel);

        frame.add(background);
        frame.setVisible(true);
    }
    
    static JButton createLetterKey(String letter) {
        JButton b = new JButton(letter);
        b.setPreferredSize(new Dimension(40, 50));
        b.setMargin(new Insets(5, 5, 5, 5));
        b.setFont(new Font("Sanserif", Font.BOLD, 18));
        b.setBackground(new Color(217,218,219));
        b.setBorderPainted(false);
        return b;
    }
    
    static JButton createSpecialKey(String letter) {
        JButton b = new JButton(letter);
        b.setPreferredSize(new Dimension(60, 50));
        b.setMargin(new Insets(5, 5, 5, 5));
        b.setFont(new Font("Sanserif", Font.BOLD, 12));
        b.setBackground(new Color(217,218,219));
        b.setBorderPainted(false);
        return b;
    }
    
    static JPanel createRowPanel() {
        JPanel rp = new JPanel();
        rp.setOpaque(false);
        return rp;
    }
}