package wordle;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDateTime;

public class Wordle {
    // Login Window Stuff
    private JFrame loginFrame;
    private JLabel loginLabel;
    private JLabel msgLabel;
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton loginButton;
    // Wordle Game Stuff
    private String targetWord = null;
    private boolean loginSuccess = false;

    public Wordle() {
        // ========================== Login Window ========================== //
        loginFrame = new JFrame("Login");
        loginFrame.setSize(300, 200);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setResizable(false);
        loginFrame.setLocationRelativeTo(null);
        
        // Create background panel
        JPanel background = new JPanel();
        background.setLayout(new GridLayout(4, 1));
        
        // Create Login Label
        loginLabel = new JLabel("Login", JLabel.CENTER);
        loginLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        background.add(loginLabel);
        
        // Create Message Label
        msgLabel = new JLabel();
        msgLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
        msgLabel.setForeground(Color.RED);
        msgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        background.add(msgLabel);
        
        // Create username and password panel
        JPanel userPassPanel = new JPanel();
        userPassPanel.setLayout(new GridLayout(2, 2));
        
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JTextField();
        loginButton = new JButton("Login");
        
        userPassPanel.add(usernameLabel);
        userPassPanel.add(usernameField);
        userPassPanel.add(passwordLabel);
        userPassPanel.add(passwordField);
        background.add(userPassPanel);
        
        // Create Login button
        loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setMargin(new Insets(10, 20, 10, 20));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                // ================== Starting Wordle Game ================== //
                if (authenticate(username, password)) {
                    LocalDateTime currTime = LocalDateTime.now(); // time when user login

                    loginFrame.dispose();
                    targetWord = new WordList("words.txt").getTargetWord();
                    new StartGUI(targetWord, username, currTime);
                } else {
                    msgLabel.setText("Invalid username or password.");
                }
            }
        });
        background.add(loginButton);
        
        loginFrame.add(background);
        loginFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new Wordle();
    }
    
    private boolean authenticate(String username, String password) {
        // Check if user is in the USERS table in database
        Connection conn = null;
        String url = "jdbc:mariadb://127.0.0.1/cs3913";
        String dbUser = "cs3913";
        String dbPassword = "abc123";
        try {
            conn = DriverManager.getConnection(url, dbUser, dbPassword);
            String query = "SELECT * FROM USERS WHERE username=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { // User exists and correct password
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.toString());
        }
        return false;
    }
}