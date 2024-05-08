package wordle;

import java.awt.*;
import javax.swing.*;

public class Key extends JButton {    
    Key(String text, Boolean isLetter) {
        super(text);
        setMargin(new Insets(5, 5, 5, 5));
        setBackground(new Color(217,218,219));
        setBorderPainted(false);
        
        if (isLetter) { // Key is letter character
            setPreferredSize(new Dimension(40, 50));
            setFont(new Font("Sanserif", Font.BOLD, 18));
        }
        else { // Key is ENTER or BACK
            setPreferredSize(new Dimension(60, 50));
            setFont(new Font("Sanserif", Font.BOLD, 12));
        }
    }
    
    public JButton getKeyButton() {
        return this;
    }
}