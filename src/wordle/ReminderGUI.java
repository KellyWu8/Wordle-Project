package wordle;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ReminderGUI extends JFrame {
    private JFrame reminderFrame;
    private final JLabel titleLabel;
    private final JButton closeButton;
    
    ReminderGUI() {
        reminderFrame = new JFrame("Wordle by Kelly Wu");
        reminderFrame.setSize(300, 150);
        reminderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        reminderFrame.setResizable(false);
        reminderFrame.setLocationRelativeTo(null);
        
        // Create background panel
        JPanel background = new JPanel();
        background.setLayout(new GridLayout(2, 1));
        
        // Create Login Label
        titleLabel = new JLabel("Not In Word List!", JLabel.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        background.add(titleLabel);
        
        // Create Login button
        closeButton = new JButton("Close");
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        closeButton.setBackground(Color.BLACK);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(new Font("Arial", Font.BOLD, 16));
        closeButton.setMargin(new Insets(10, 20, 10, 20));
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reminderFrame.dispose();
            }
        });
        background.add(closeButton);
        
        reminderFrame.add(background);
        reminderFrame.setVisible(true);
    }
}