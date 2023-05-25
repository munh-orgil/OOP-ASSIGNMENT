package frontend.screens.layout;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SideBar {
    public JPanel sideBar = new JPanel(new GridBagLayout());

    public SideBar() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 10, 5, 10);

        JButton classButton = new JButton("Classes");
        constraints.gridx = 0;
        constraints.gridy = 0;
        sideBar.add(classButton, constraints);

        JButton examButton = new JButton("Exams");
        constraints.gridx = 0;
        constraints.gridy = 1;

        sideBar.add(examButton, constraints);
        
        classButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Layout.cardLayout.show(Layout.contentPanel, "classes");
            }
        });
        examButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Layout.cardLayout.show(Layout.contentPanel, "exams");
            }
        });
    }
}
