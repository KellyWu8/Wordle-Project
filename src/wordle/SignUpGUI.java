package wordle;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class SignUpGUI {
    private JFrame signUpFrame;
    private JLabel titleLabel;
    private JLabel msgLabel;
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton loginButton;
    private JButton signUpButton;
    private String targetWord = null;
    
    SignUpGUI() {
        signUpFrame = new JFrame("Sign Up");
        signUpFrame.setSize(300, 200);
        signUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signUpFrame.setResizable(false);
        signUpFrame.setLocationRelativeTo(null);
        
        // Create background panel
        JPanel background = new JPanel();
        background.setLayout(new GridLayout(5, 1));
        
        // Create Sign Up Label
        titleLabel = new JLabel("Sign Up", JLabel.CENTER);
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
                String username = usernameField.getText();
                String password = passwordField.getText();
                // =================== Log User Into Table =================== //
                if (username.equals("") || password.equals("")) {
                    msgLabel.setText("Enter the required fields.");
                }
                else if (authenticate(username) == false) { // Unique username
                    logNewUser(username, password);
                    signUpFrame.dispose();
                    new LoginGUI();
                }
                else if (authenticate(username) == true) {
                    msgLabel.setText("Username already exists.");
                }
            }
        });
        background.add(signUpButton);
        
        // Create Back to Login button
        loginButton = new JButton("Back To Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setMargin(new Insets(10, 20, 10, 20));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signUpFrame.dispose();
                new LoginGUI();
            }
        });
        background.add(loginButton);
        
        signUpFrame.add(background);
        signUpFrame.setVisible(true);
    }
    
    private boolean authenticate(String username) {
        // Check if user is in the USERS table in database
        Connection conn = null;
        String url = "jdbc:mariadb://127.0.0.1/cs3913";
        String dbUser = "cs3913";
        String dbPassword = "abc123";
        try {
            conn = DriverManager.getConnection(url, dbUser, dbPassword);
            String query = "SELECT * FROM USERS WHERE username=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { // User exists
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.toString());
        }
        return false;
    }
    
    private void logNewUser(String username, String password) {
        Connection conn = null;
        String url = "jdbc:mariadb://127.0.0.1/cs3913";
        String dbUser = "cs3913";
        String dbPassword = "abc123";
        try {
            conn = DriverManager.getConnection(url, dbUser, dbPassword);
            String query = "INSERT INTO USERS (username, password) VALUES ('" + username + "', '" + password + "')";

            // Insert user's login info into the database
            Statement s = conn.createStatement();
            s.execute(query);
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.toString());
        }
    }
}