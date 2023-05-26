package frontend.screens.layout;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import frontend.screens.Login;
import frontend.screens.SignUp;

public class Layout {
    public static JFrame frame = new JFrame();
    public JPanel layoutPanel = new JPanel(new GridBagLayout());
    public static JPanel contentPanel = new JPanel();
    public static CardLayout cardLayout = new CardLayout();
    
    public Layout() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setBackground(Color.white);

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.DARK_GRAY);
        headerPanel.setPreferredSize(new Dimension(frame.getWidth(), 100));

        // SideBar
        SideBar sideBar = new SideBar();
        JPanel sidebarPanel = sideBar.sideBar;

        // Content
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setLayout(cardLayout);

        Login login = new Login();
        SignUp signUp = new SignUp();
        
        contentPanel.add(login.loginPanel, "login");
        contentPanel.add(signUp.signUpPanel, "signUp");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(sidebarPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        frame.add(mainPanel);
    }
}
