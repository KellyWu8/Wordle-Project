package wordle;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class LoginGUI extends JFrame {
    private JFrame loginFrame;
    private final JLabel titleLabel;
    private JLabel msgLabel;
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton loginButton;
    private final JButton signUpButton;
    WordList wl = null;
    private String targetWord = null;
    ArrayList<String> words = null;
    
    LoginGUI() {        
        loginFrame = new JFrame("Wordle by Kelly Wu");
        loginFrame.setSize(300, 200);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setResizable(false);
        loginFrame.setLocationRelativeTo(null);
        
        // Create background panel
        JPanel background = new JPanel();
        background.setLayout(new GridLayout(5, 1));
        
        // Create Login Label
        titleLabel = new JLabel("Login", JLabel.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        background.add(titleLabel);
        
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
                    loginFrame.dispose();
                    wl = new WordList("words.txt"); 
                    targetWord = wl.getTargetWord();
                    words = wl.getWords();
                    new StartGUI(targetWord, username, words);
                } else {
                    msgLabel.setText("Invalid username or password.");
                }
            }
        });
        background.add(loginButton);
        
        // Create Sign Up Button
        signUpButton = new JButton("Sign Up");
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUpButton.setBackground(Color.BLACK);
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFont(new Font("Arial", Font.BOLD, 16));
        signUpButton.setMargin(new Insets(10, 20, 10, 20));
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.dispose();
                new SignUpGUI();
            }
        });
        background.add(signUpButton);
        
        loginFrame.add(background);
        loginFrame.setVisible(true);
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