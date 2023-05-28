package frontend.student.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import frontend.Frontend;
import frontend.modules.Modules;

public class Header {
    public JPanel header = new JPanel(new FlowLayout(FlowLayout.LEADING));
    
    public Header() {
        header.setPreferredSize(new Dimension(Layout.frame.getWidth(), 75));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        JLabel imageLabel = new JLabel();

        int width = 300;
        int height = 60;
        imageLabel.setPreferredSize(new Dimension(width, height));

        ImageIcon logoImage = new ImageIcon("./assets/exammr.png");
        Image scaledImage = logoImage.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
        JPanel emptyPanel = new JPanel(new BorderLayout());
        emptyPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        emptyPanel.add(imageLabel);
        header.add(emptyPanel, BorderLayout.WEST);

        JLabel imageLabel1= new JLabel();
        int width1 = 50;
        int height1 = 50;
        imageLabel1.setPreferredSize(new Dimension(width1, height1));
        
        ImageIcon proImage = new ImageIcon("./assets/profile1.png");
        Image scaledImage1 = proImage.getImage().getScaledInstance(width1, height1, Image.SCALE_SMOOTH);
        imageLabel1.setIcon(new ImageIcon(scaledImage1));
        JPanel myPanel = new JPanel(new BorderLayout());
        myPanel.setBorder(BorderFactory.createEmptyBorder(0, 1000, 0, 0));
        myPanel.add(imageLabel1);
        header.add(myPanel, BorderLayout.EAST);

            
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        header.add(menuBar, BorderLayout.EAST);

        JMenu menu = new JMenu("Username");
        menuBar.add(menu);

        JMenuItem item1 = new JMenuItem("Profile");
        JMenuItem item2 = new JMenuItem("Log Out");

        item2.addActionListener(new ActionListener() {
             
            @Override
            public void actionPerformed(ActionEvent e) {
                frontend.student.layout.Layout layout = new frontend.student.layout.Layout();
                layout.frame.setVisible(false);
                Frontend.frame.setVisible(true);

                // Login login = new Login();
                // login.frame.setVisible(true);
            }
        });

        menu.add(item1);
        menu.add(item2);
        if (Modules.login != null) {
            menu.setText(Modules.login.Username);
        }
    }
}