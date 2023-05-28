package frontend.teacher.layout;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import frontend.teacher.Classes;

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
        Header header = new Header();
        JPanel headerPanel = header.header;

        // SideBar
        SideBar sideBar = new SideBar();
        JPanel sidebarPanel = sideBar.sideBar;

        // HelpBar
        HelpBar helpBar = new HelpBar();
        JPanel helpbarPanel = helpBar.helpBar;

        // Content
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setLayout(cardLayout);

        Classes classes = new Classes();
        contentPanel.add(classes.classesPanel, "classes");
        cardLayout.show(Layout.contentPanel, "classes");
        

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JScrollPane scrollableContent = new JScrollPane(contentPanel);
        JScrollBar verticalScrollBar = scrollableContent.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(20);

        

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(sidebarPanel, BorderLayout.WEST);
        mainPanel.add(helpbarPanel, BorderLayout.EAST);
        mainPanel.add(scrollableContent, BorderLayout.CENTER);

        frame.add(mainPanel);
    }
}
