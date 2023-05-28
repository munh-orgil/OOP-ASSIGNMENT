package frontend;
import java.awt.*;

import javax.swing.*;

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
        frame.setSize(600, 300);
        mainPanel.setLayout(cardLayout);
        
        Login login = new Login();
        mainPanel.add(login.loginPanel, "login");
        SignUp signUp = new SignUp();
        mainPanel.add(signUp.signUpPanel, "signUp");
        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static Point centerWindow(int width, int height) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int x = (screenWidth - width) / 2;
        int y = (screenHeight - height) / 2;

        System.out.println(x);
        System.out.println(y);
        System.out.println(screenWidth);
        System.out.println(screenHeight);

        Point res = new Point(x, y);

        return res;
    }
}
