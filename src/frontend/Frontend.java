package frontend;
import java.awt.*;

import javax.swing.*;

import frontend.screens.Login;

// import frontend.screens.CenteredLoginExample;

public class Frontend extends JFrame {
    public Font mainFont = new Font("Product Sans", Font.BOLD,18);
    public JTextField tfUsername, tfPassword;
    public JLabel lbWelcome;

    public void init(){
        // CenteredLoginExample login = new CenteredLoginExample();
        // login.init();
        Login.init();
        // LoginPage.init();
    }
}
