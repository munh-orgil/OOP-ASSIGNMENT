package frontend.screens.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import frontend.screens.exams.Exams;
import frontend.widgets.CustomButton;

public class SideBar {
    public JPanel sideBar = new JPanel(new BorderLayout());
    
    public SideBar() {
        sideBar.setPreferredSize(new Dimension(250, Layout.frame.getHeight() / 2 - 100));
        sideBar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
     
        JPanel emptyPanel = new JPanel(new BorderLayout());
        emptyPanel.setBorder(BorderFactory.createEmptyBorder(30, 240, 0, 0));
        sideBar.add(emptyPanel);

        JPanel navPanel = new JPanel(new GridLayout(10, 1));
        CustomButton classButton = new CustomButton("Хичээлүүд", sideBar.getWidth(), 0);
        CustomButton examButton = new CustomButton("Шалгалтууд", sideBar.getWidth(), 0);
        navPanel.add(classButton);
        navPanel.add(examButton);
        
        sideBar.add(navPanel);

        classButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                Layout.cardLayout.show(Layout.contentPanel, "classes");
            }
        });
        examButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Exams exam = new Exams(0);
                
                Layout.contentPanel.add(exam, "exams");
                Layout.cardLayout.show(Layout.contentPanel, "exams");
            }
        });
    }
}
