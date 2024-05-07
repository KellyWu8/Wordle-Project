package wordle;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class LetterBox extends JPanel {
    private JLabel letterBoxLabel;
    private int colorVal = 0;

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
        colorVal = val;
        letterBoxLabel.setForeground(Color.WHITE);
        repaint();
    }
    
    void setLetter(String letter) {
        letterBoxLabel.setText(letter);
        letterBoxLabel.setHorizontalAlignment(SwingConstants.CENTER);
        letterBoxLabel.setVerticalAlignment(SwingConstants.CENTER);
    }
    
    String getLetter() {
        return letterBoxLabel.getText();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (colorVal != 0) {
            Color color = null;
            if (colorVal == 1) { // Gray: letter not in word
                color = new Color(105, 108, 110);
            } else if (colorVal == 2) { // Yellow: letter in wrong position
                color = new Color(200,182,83);
            } else if (colorVal == 3) { // Green: letter in right position
                color = new Color(108,169,101);
            }

            // Paint the background with the set color
            g.setColor(color);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}