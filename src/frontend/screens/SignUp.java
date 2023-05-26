package frontend.screens;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;

import backend.Model;
import backend.models.Logins;
import backend.models.Users;
import frontend.Frontend;
import frontend.widgets.CustomButton;

public class SignUp implements ActionListener {
    private JFrame frame;
    private JTextField usernameField, passwordField, passwordRepeatField, regNoField, lastNameField, firstNameField;
    private JComboBox<String> genderComboBox;
    public JPanel signUpPanel = new JPanel(new GridBagLayout());
    public JRadioButton buttonYes, buttonNo;

    public SignUp() {
        frame = new JFrame("Sign Up Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 10, 5, 10);
        constraints.weighty = 1;

        JLabel usernameLabel = new JLabel("Username:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        signUpPanel.add(usernameLabel, constraints);
        
        usernameField = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.gridwidth = 2;
        usernameField.setPreferredSize(new Dimension(10, 30));
        signUpPanel.add(usernameField, constraints);
        
        JLabel passwordLabel = new JLabel("Password:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        signUpPanel.add(passwordLabel, constraints);
        
        passwordField = new JPasswordField(15);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        passwordField.setPreferredSize(new Dimension(10, 30));
        signUpPanel.add(passwordField, constraints);
        
        JLabel passwordRepeatLabel = new JLabel("Repeat Password:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        signUpPanel.add(passwordRepeatLabel, constraints);
        
        passwordRepeatField = new JPasswordField(15);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        passwordRepeatField.setPreferredSize(new Dimension(10, 30));
        signUpPanel.add(passwordRepeatField, constraints);
        
        JLabel regNoLabel = new JLabel("Register Number:");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        signUpPanel.add(regNoLabel, constraints);
        
        regNoField = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        regNoField.setPreferredSize(new Dimension(10, 30));
        signUpPanel.add(regNoField, constraints);

        JLabel lastNameLabel = new JLabel("Lastname:");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        signUpPanel.add(lastNameLabel, constraints);
        
        lastNameField = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        lastNameField.setPreferredSize(new Dimension(10, 30));
        signUpPanel.add(lastNameField, constraints);
        

        JLabel firstNameLabel = new JLabel("Firstname:");
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        signUpPanel.add(firstNameLabel, constraints);
        
        firstNameField = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        firstNameField.setPreferredSize(new Dimension(10, 30));
        signUpPanel.add(firstNameField, constraints);

        JLabel genderLabel = new JLabel("Gender");
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        signUpPanel.add(genderLabel, constraints);

        Vector<String> genders = new Vector<>();
        genders.add("-");
        genders.add("Male");
        genders.add("Female");
        genderComboBox = new JComboBox<String>(genders);
        constraints.gridx = 1;
        constraints.gridy = 6;
        signUpPanel.add(genderComboBox, constraints);

        buttonYes = new JRadioButton("Yes");
        buttonNo = new JRadioButton("No");
        ButtonGroup bGroup = new ButtonGroup();
        JLabel isTeacherLabel = new JLabel("Are you teacher?");
        constraints.gridx = 0;
        constraints.gridy = 7;
        signUpPanel.add(isTeacherLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 7;
        signUpPanel.add(buttonYes, constraints);
        constraints.gridx = 2;
        constraints.gridy = 7;
        signUpPanel.add(buttonNo, constraints);

        bGroup.add(buttonYes);
        bGroup.add(buttonNo);
        
        CustomButton signUpButton = new CustomButton("SignUp", 0, 0);
        signUpButton.setRadius(20);
        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        signUpButton.setPreferredSize(new Dimension(10, 30));
        signUpPanel.add(signUpButton, constraints);
        
        CustomButton cancelButton = new CustomButton("Cancel", 0, 0);
        cancelButton.setRadius(20);
        constraints.gridx = 0;
        constraints.gridy = 9;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        cancelButton.setPreferredSize(new Dimension(10, 30));
        signUpPanel.add(cancelButton, constraints);
        
        frame.add(signUpPanel);
        signUpButton.addActionListener(this);
        cancelButton.addActionListener(this);
        
        // frame.setSize(800, 800);
        // frame.setVisible(true);
        // frame.setLocationRelativeTo(null);
    }
    public void actionPerformed(ActionEvent ae) {
        JButton sourceButton = (JButton) ae.getSource();
        String buttonLabel = sourceButton.getText();

        if(buttonLabel.equals("Cancel")) {
            Frontend.frame.setSize(600, 300);
            Frontend.frame.setLocation(Frontend.centerWindow(600, 300));
            Frontend.cardLayout.show(Frontend.mainPanel, "login");
            return;
        }

        // usernameField, passwordField, passwordRepeatField, regNoField, lastNameField, firstNameField
        String userValue = usernameField.getText();
        String passValue = passwordField.getText();
        String passRepValue = passwordRepeatField.getText();
        String regValue = regNoField.getText();
        String lnameValue = lastNameField.getText();
        String fnameValue = firstNameField.getText();
        int gender = genderComboBox.getSelectedIndex();
        boolean isTeacher = buttonYes.isSelected();
        
        Logins sameLogin = new Logins();
        sameLogin.Username = userValue;
        Vector<Logins> users = Model.List(Logins.class, sameLogin);
        if(users.size() > 0) {
            JOptionPane.showMessageDialog(null, "Username is already taken");
        } else {
            if(!passRepValue.equals(passValue)) {
                JOptionPane.showMessageDialog(null, "Repeated password does not match");
            } else {
                Logins newLogin = new Logins();
                Users newUser = new Users();
                newUser.RegNo = regValue;
                Vector<Users> sameUsers = Model.List(Users.class, newUser);
                if(sameUsers.size() > 0) {
                    newLogin.UserId = sameUsers.get(0).Id;
                } else {
                    newUser.FirstName = fnameValue;
                    newUser.LastName = lnameValue;
                    newUser.RegNo = regValue;
                    newUser.Gender = gender;
                    newLogin.UserId = Model.Create(newUser);
                }
                newLogin.isTeacher = isTeacher ? 1 : 0;
                newLogin.Username = userValue;
                newLogin.Password = passValue;
                Model.Create(newLogin);
                JOptionPane.showMessageDialog(null, "Profile created successfully.");
            }
            Frontend.frame.setSize(600, 300);
            Frontend.frame.setLocation(Frontend.centerWindow(600, 300));
            Frontend.cardLayout.show(Frontend.mainPanel, "login");
        }
    }

    public static void init() {
        new SignUp();
    }
}