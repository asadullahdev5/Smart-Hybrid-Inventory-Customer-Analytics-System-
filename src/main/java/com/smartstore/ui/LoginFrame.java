package com.smartstore.ui;

import com.smartstore.dao.UserDAO;
import com.smartstore.user; // Make sure package name and capitalization is correct
import javax.swing.*;

public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginFrame() {

        setTitle("Smart Store - Login");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setBounds(30, 30, 80, 25);
        add(lblUser);

        txtUsername = new JTextField();
        txtUsername.setBounds(120, 30, 150, 25);
        add(txtUsername);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(30, 70, 80, 25);
        add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(120, 70, 150, 25);
        add(txtPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(120, 110, 150, 30);
        add(btnLogin);

        // Action Listener using modern Lambda
        btnLogin.addActionListener(e -> {

            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());

            user user = new user(username, password);
            UserDAO userDAO = new UserDAO();

            boolean success = userDAO.loginUser(user);

            if (success) {
                JOptionPane.showMessageDialog(
                        null,
                        "Welcome " + user.getUsername() +
                                (user.getRole() != null ? " (" + user.getRole() + ")" : "")
                );

                // 👇 FIX: Yeh line Dashboard screen open karegi
                new DashboardFrame().setVisible(true);

                dispose(); // Yeh purani login window close karegi
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Invalid Username or Password!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }

    public static void main(String[] args) {
        new LoginFrame().setVisible(true);
    }
}