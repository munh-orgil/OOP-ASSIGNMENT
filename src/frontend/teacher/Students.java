package frontend.teacher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import backend.Model;
import backend.models.Users;
import frontend.student.layout.Layout;
import frontend.teacher.Classes;
import frontend.widgets.CustomButton;

public class Students {
    public JPanel sideBar = new JPanel(new BorderLayout());

    public Students() {
        sideBar.setPreferredSize(new Dimension(250, Layout.frame.getHeight() / 2 - 100));
        sideBar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));

        JPanel emptyPanel = new JPanel(new BorderLayout());
        emptyPanel.setBorder(BorderFactory.createEmptyBorder(30, 240, 0, 0));
        sideBar.add(emptyPanel);

        JPanel navPanel = new JPanel(new GridLayout(10, 1));
        CustomButton classButton = new CustomButton("Хичээлүүд", sideBar.getWidth(), 0);
        CustomButton examButton = new CustomButton("Шалгалтууд", sideBar.getWidth(), 0);
        CustomButton studentButton = new CustomButton("Сурагчид", sideBar.getWidth(), 0);
        navPanel.add(classButton);
        navPanel.add(examButton);
        navPanel.add(studentButton);

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
                Layout.cardLayout.show(Layout.contentPanel, "exams");
            }
        });

        studentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // Retrieve user data from the database
                List<Users> userList = Model.List(Users.class, new Users());

                // Create the column names array
                String[] columnNames = {"Username", "Last Name"};

                // Create the data array for the table
                Object[][] data = new Object[userList.size()][2];

                // Populate the data array with user information
                for (int i = 0; i < userList.size(); i++) {
                    Users user = userList.get(i);
                    data[i][0] = user.RegNo;
                    data[i][1] = user.LastName;
                }

                // Create the DefaultTableModel with the data and column names
                DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

                // Create the JTable with the DefaultTableModel
                JTable userTable = new JTable(tableModel);

                // Display the JTable in a dialog
                JOptionPane.showMessageDialog(null, new JScrollPane(userTable), "User Information", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }
}