package wordle;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.*;

public class LetterBox extends JPanel {
    private JLabel letterBoxLabel;

    LetterBox() {
        setPreferredSize(new Dimension(50, 50));
        setMaximumSize(new Dimension(50, 50));
        setLayout(new BorderLayout());
        setOpaque(false);

        letterBoxLabel = new JLabel();
        letterBoxLabel.setPreferredSize(new Dimension(50, 50));
        letterBoxLabel.setMaximumSize(new Dimension(50, 50));
        letterBoxLabel.setFont(new Font("Sanserif", Font.BOLD, 20));
        Border border = BorderFactory.createLineBorder(new Color(120,124,127), 2);
        letterBoxLabel.setBorder(border);
        letterBoxLabel.setOpaque(false);

        add(letterBoxLabel, BorderLayout.CENTER);
    }
    
    void setColor(int val) {
        Color color = null;
        if (val == 0) { // Gray: letter not in word
            color = new Color(105, 108, 110);
        }
        else if (val == 1) { // Yellow: letter in wrong position
            color = new Color(200,182,83);
        }
        else if (val == 2) { // Green: letter in right position
            color = new Color(108,169,101);
        }
        
        setBackground(color);
        letterBoxLabel.setOpaque(true);
        letterBoxLabel.setBackground(color);
        letterBoxLabel.setBorder(null);
    }
}

