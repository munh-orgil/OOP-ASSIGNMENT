package frontend;
import java.awt.*;

import javax.swing.*;

import frontend.screens.Login;
import frontend.screens.SignUp;

// import frontend.screens.CenteredLoginExample;

public class Frontend extends JFrame {
    public Font mainFont = new Font("Product Sans", Font.BOLD,18);
    public JTextField tfUsername, tfPassword;
    public JLabel lbWelcome;
    public static JPanel mainPanel = new JPanel();
    public static CardLayout cardLayout = new CardLayout();
    public static JFrame frame = new JFrame("Exammr");

    public void init(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        mainPanel.setLayout(cardLayout);
        
        Login login = new Login();
        mainPanel.add(login.loginPanel, "login");
        SignUp signUp = new SignUp();
        mainPanel.add(signUp.signUpPanel, "signUp");
        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
