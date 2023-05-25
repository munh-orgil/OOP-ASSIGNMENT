package frontend.screens;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;

import backend.models.Logins;
import backend.Model;

public class Login implements ActionListener {
    private JFrame frame;
    private JTextField usernameField, passwordField;

    public Login() {
        frame = new JFrame("Centered Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new GridBagLayout());
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 10, 5, 10);
        
        JLabel usernameLabel = new JLabel("Username:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        mainPanel.add(usernameLabel, constraints);
        
        usernameField = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 0;
        mainPanel.add(usernameField, constraints);
        
        JLabel passwordLabel = new JLabel("Password:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPanel.add(passwordLabel, constraints);
        
        passwordField = new JPasswordField(15);
        constraints.gridx = 1;
        constraints.gridy = 1;
        mainPanel.add(passwordField, constraints);
        
        JButton loginButton = new JButton("Login");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        mainPanel.add(loginButton, constraints);
        
        JButton signUpButton = new JButton("Sign Up");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        mainPanel.add(signUpButton, constraints);

        
        
        frame.add(mainPanel);
        loginButton.addActionListener(this);
        
        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    public void actionPerformed(ActionEvent ae) {
        JButton sourceButton = (JButton) ae.getSource();
        String buttonLabel = sourceButton.getText();

        if(buttonLabel.equals("Login")) {
            String userValue = usernameField.getText();
            String passValue = passwordField.getText();
    
            Logins login = new Logins();
            login.Username = userValue;
            Vector<Logins> users = Model.List(Logins.class, login);
            if(users.size() > 0) {
                Logins user = new Logins();
                for (int i = 0 ; i < users.size(); i++) {
                    Logins curUser = users.get(i);
                    if (curUser.Username.equals(userValue)) {
                        user = curUser;
                        break;
                    }
                }
                if(user.Password.equals(passValue)) {
                    JOptionPane.showMessageDialog(null, "Success!");
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong password");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Wrong user information");
            }
        } else {
            
        }
    }

    public static void init() {
        new Login();
    }
}