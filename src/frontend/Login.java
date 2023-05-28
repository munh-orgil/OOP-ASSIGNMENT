package frontend;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import java.util.Vector;

import javax.swing.*;

import backend.models.Logins;
import backend.models.Users;
import frontend.modules.Modules;
import frontend.student.layout.Layout;
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

        JLabel usernameLabel = new JLabel("Нэвтрэх нэр:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        loginPanel.add(usernameLabel, constraints);
        
        usernameField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        usernameField.setPreferredSize(new Dimension(10, 40));
        loginPanel.add(usernameField, constraints);

        
        JLabel passwordLabel = new JLabel("Нууц үг:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        loginPanel.add(passwordLabel, constraints);

        passwordField = new JPasswordField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        passwordField.setPreferredSize(new Dimension(10, 40));
        loginPanel.add(passwordField, constraints);
        
        CustomButton loginButton = new CustomButton("Нэвтрэх", 0, 50);
        loginButton.setRadius(20);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        loginButton.setPreferredSize(new Dimension(10, 40));
        loginPanel.add(loginButton, constraints);
        
        CustomButton signUpButton = new CustomButton("Бүртгүүлэх", 0, 50);
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
        
        usernameField.setText("muug");
        passwordField.setText("1234");
        // frame.setSize(600, 480);
    }
    public void actionPerformed(ActionEvent ae) {
        JButton sourceButton = (JButton) ae.getSource();
        String buttonLabel = sourceButton.getText();

        if(buttonLabel.equals("Нэвтрэх")) {
            String userValue = usernameField.getText();
            String passValue = passwordField.getText();
    
            Logins login = new Logins();
            login.Username = userValue;
            Vector<Logins> users = Model.List(Logins.class, login);
            if(users.size() > 0) {
                Logins userLogin = new Logins();
                for (int i = 0 ; i < users.size(); i++) {
                    Logins curUser = users.get(i);
                    if (curUser.Username.equals(userValue)) {
                        userLogin = curUser;
                        break;
                    }
                }
                if(userLogin.Password.equals(passValue)) {
                    Vector<Logins> getUser = Model.List(Logins.class, userLogin);
                    int userId = getUser.get(0).UserId;
                    Users findUser = new Users();
                    findUser.Id = userId;
                    Vector<Users> theUser = Model.List(Users.class, findUser);
                    Modules.user = theUser.get(0);
                    if(Objects.isNull(userLogin.ProfileImagePath)) {
                        userLogin.ProfileImagePath = "./assets/profile.png";
                    }
                    Modules.login = userLogin;

                    Frontend.frame.setVisible(false);
                    if(userLogin.isTeacher == 1) {
                        frontend.teacher.layout.Layout layout = new frontend.teacher.layout.Layout();
                        layout.frame.setVisible(true);
                    } else {
                        frontend.student.layout.Layout layout = new frontend.student.layout.Layout();
                        layout.frame.setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Нууц үг буруу");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Хэрэглэгчийн мэдээлэл буруу байна.");
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