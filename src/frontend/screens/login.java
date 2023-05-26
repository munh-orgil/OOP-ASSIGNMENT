package frontend.screens;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;

import backend.models.Logins;
import backend.models.Users;
import frontend.Frontend;
import frontend.modules.Modules;
import frontend.screens.layout.Layout;
import frontend.widgets.CustomButton;
import backend.Model;

public class Login implements ActionListener {
    private JFrame frame;
    private JTextField usernameField, passwordField;
    public JPanel loginPanel = new JPanel(new GridBagLayout());

    public Login() {
        frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(5, 10, 5, 10);

        JLabel usernameLabel = new JLabel("Username:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        loginPanel.add(usernameLabel, constraints);
        
        usernameField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        usernameField.setPreferredSize(new Dimension(10, 40));
        loginPanel.add(usernameField, constraints);
        
        JLabel passwordLabel = new JLabel("Password:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        loginPanel.add(passwordLabel, constraints);
        
        passwordField = new JPasswordField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        passwordField.setPreferredSize(new Dimension(10, 40));
        loginPanel.add(passwordField, constraints);
        
        CustomButton loginButton = new CustomButton("Login", 0, 50);
        loginButton.setRadius(20);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        loginButton.setPreferredSize(new Dimension(10, 40));
        loginPanel.add(loginButton, constraints);
        
        CustomButton signUpButton = new CustomButton("Sign Up", 0, 50);
        signUpButton.setRadius(20);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        signUpButton.setPreferredSize(new Dimension(10, 40));
        loginPanel.add(signUpButton, constraints);
        
        frame.add(loginPanel);
        loginButton.addActionListener(this);
        signUpButton.addActionListener(this);
        
        // frame.setSize(600, 480);
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
                    Vector<Logins> getUser = Model.List(Logins.class, user);
                    int userId = getUser.get(0).UserId;
                    Users findUser = new Users();
                    findUser.Id = userId;
                    Vector<Users> theUser = Model.List(Users.class, findUser);
                    Modules.user = theUser.get(0);
                    
                    Frontend.frame.setVisible(false);
                    Layout layout = new Layout();
                    layout.frame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong password");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Wrong user information");
            }
        } else {
            Frontend.frame.setSize(600, 500);
            Frontend.frame.setLocation(Frontend.centerWindow(600, 500));
            Frontend.cardLayout.show(Frontend.mainPanel, "signUp");
        }
    }

    // public static void init() {
    //     new Login();
    // }
}