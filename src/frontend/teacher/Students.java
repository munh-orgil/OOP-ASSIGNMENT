package frontend.teacher;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.HashSet;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import backend.Model;
import backend.models.Logins;
import backend.models.Users;
import frontend.modules.Modules;

public class Students extends JPanel {
    public Students() {
        BoxLayout bl = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(bl);

        JPanel labelPanel = new JPanel(new FlowLayout());
        JLabel titleLabel = new JLabel("Оюутнууд");
        Font font = titleLabel.getFont();
        titleLabel.setFont(font.deriveFont(30f));
        labelPanel.add(titleLabel);

        labelPanel.setMaximumSize(Modules.getPreferredHeight(labelPanel, (int)(Modules.screenWidth * 0.55)));

        add(labelPanel);

        String[] columnNames = {"Овог", "Нэр"};
        Logins loginsFilter = new Logins();
        loginsFilter.isTeacher = 0;
        Vector<Logins> loginsList = Model.List(Logins.class, loginsFilter);
        Vector<Integer> duplicatedUsers = new Vector<>();
        for(Logins login: loginsList) {
            duplicatedUsers.add(login.UserId);
        }
        HashSet<Integer> userIds = new HashSet<>(duplicatedUsers);
        String[][] rowData = new String[userIds.size()][2];
        int idx = 0;
        for(Integer id: userIds) {
            Users usersFilter = new Users();
            usersFilter.Id = id;
            Vector<Users> usersList = Model.List(Users.class, usersFilter);
            Users user = usersList.get(0);
            rowData[idx][0] = user.LastName;
            rowData[idx][1] = user.FirstName;
            idx++;
        }
        
        DefaultTableModel model = new DefaultTableModel(rowData, columnNames);

        JTable studentsTable = new JTable(model);

        studentsTable.setRowHeight(30);
        
        JScrollPane scrollPane = new JScrollPane(studentsTable);
        scrollPane.setPreferredSize(new Dimension((int)(Modules.screenWidth * 0.55), 30 + studentsTable.getRowHeight() * studentsTable.getRowCount()));
        JPanel tablePanel = new JPanel();
        tablePanel.add(scrollPane);

        tablePanel.setMaximumSize(Modules.getPreferredHeight(tablePanel, (int)(Modules.screenWidth * 0.55)));

        add(tablePanel);
    }
}