package frontend.screens.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frontend.modules.Modules;
import frontend.widgets.CustomButton;

public class SideBar {
    public JPanel sideBar = new JPanel(new FlowLayout());
    
    public SideBar() {
        sideBar.setPreferredSize(new Dimension(250, Layout.frame.getHeight() - 100));
        sideBar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTH;

        JPanel emptyPanel = new JPanel(new BorderLayout());
        emptyPanel.setBorder(BorderFactory.createEmptyBorder(30, 240, 0, 0));
        sideBar.add(emptyPanel);

        CustomButton classButton = new CustomButton("Classes", 210, 50);
        classButton.setRadius(20);
        constraints.gridx = 0;
        constraints.gridy = 0;
        sideBar.add(classButton, constraints);

        CustomButton examButton = new CustomButton("Exams", 210, 50);
        examButton.setRadius(20);
        constraints.gridx = 0;
        constraints.gridy = 1;
        sideBar.add(examButton, constraints);
        
        classButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Layout.cardLayout.show(Layout.contentPanel, "login");
            }
        });
        examButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Layout.cardLayout.show(Layout.contentPanel, "signUp");
            }
        });
    }
}
