package wordle;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EndGUI extends JFrame {
    private JFrame endFrame = null;
    private String title = null;
    private String detail = null;
    private LocalDateTime currTime = null;

    EndGUI(int rowNum, String word, String username, JFrame startFrame) {
        int score = rowNum + 1;
        
        if (rowNum < 6) { // User guessed correctly
            currTime = LocalDateTime.now(); // time when user won the game
            title = "Congratulations!";
            if ((rowNum + 1) == 1) {
                detail = "You guessed within " + score + " try.";
                
            }
            else {
                detail = "You guessed within " + score + " tries!";
            }
        } else {
            title = "Out of Tries!";
            detail = "The word was " + word;
        }

        // Set up the frame
        endFrame = new JFrame("Wordle by Kelly Wu");
        endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        endFrame.setSize(300, 200);
        endFrame.setResizable(false);
        endFrame.setLocationRelativeTo(null);

        // Create panel
        JPanel background = new JPanel();
        background.setLayout(new GridLayout(3, 1));

        // Create title label
        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create detail label
        JLabel detailLabel = new JLabel(detail, JLabel.CENTER);
        detailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        detailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create Play Again button
        JButton paButton = new JButton("Play Again");
        paButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        paButton.setBackground(Color.BLACK);
        paButton.setForeground(Color.WHITE);
        paButton.setFont(new Font("Arial", Font.BOLD, 16));
        paButton.setPreferredSize(new Dimension(120, 40));
        paButton.setMargin(new Insets(10, 20, 10, 20));
        
        paButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                endFrame.dispose();
                startFrame.dispose();
                new Wordle(username);
            }
        });

        background.add(titleLabel);
        background.add(detailLabel);
        background.add(paButton);

        endFrame.add(background);
        endFrame.setVisible(true);
        // ====================== Log User's Stats ====================== //
        if (rowNum < 6) {
            Connection conn = null;
            String url = "jdbc:mariadb://127.0.0.1/cs3913";
            String dbUser = "cs3913";
            String dbPassword = "abc123";
            try {
                conn = DriverManager.getConnection(url, dbUser, dbPassword);
                String time = currTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); // won time
                String query = "INSERT INTO STATS (time, username, score) VALUES ('" + time + "', '" + username + "', '" + score + "')";

                // Insert user's stats into the database
                Statement s = conn.createStatement();
                s.execute(query);
            } catch (SQLException ex) {
                System.out.println("Exception: " + ex.toString());
            }
        }
    }
}