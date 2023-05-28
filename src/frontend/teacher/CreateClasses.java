package frontend.teacher;
import java.awt.*;
import java.util.Vector;

import javax.swing.*;

import backend.Model;
import backend.models.Users;
import frontend.widgets.DualListBoxPanel;
 
public class CreateClasses extends JPanel {
  private JTextField ClassNameField, DescField;
  
  public CreateClasses(){
    setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.BOTH;
    constraints.insets = new Insets(5, 10, 5, 10);

    JLabel ClassName = new JLabel("Хичээлийн нэр:");
    constraints.gridx = 0;
    constraints.gridy = 0;
    add(ClassName, constraints);
    
    ClassNameField = new JTextField(20);
    constraints.gridx = 1;
    constraints.gridy = 0;
    ClassNameField.setPreferredSize(new Dimension(10, 40));
    add(ClassNameField, constraints);

    JLabel DescLabel = new JLabel("Тайлбар:");
    constraints.gridx = 0;
    constraints.gridy = 1;
    add(DescLabel, constraints);

    DescField = new JTextField(20);
    constraints.gridx = 1;
    constraints.gridy = 1;
    DescField.setPreferredSize(new Dimension(10, 40));
    add(DescField, constraints);
   
    JLabel studentsLabel = new JLabel("Оюутнууд:");
    constraints.gridx = 0;
    constraints.gridy = 2;
    add(studentsLabel, constraints);

    Vector<Users> users = Model.List(Users.class, new Users());

    DualListBoxPanel dualListPanel = new DualListBoxPanel();
    dualListPanel.setSourceChoicesTitle("Бүгд");
    dualListPanel.setDestinationChoicesTitle("Таны оюутнууд");

    DefaultListModel<String> listModel = new DefaultListModel<>();

    for(Users user: users) {
      listModel.addElement(user.FirstName);
    }

    dualListPanel.addSourceElements(listModel);

    constraints.gridx = 0;
    constraints.gridy = 3;
    constraints.gridwidth = 2;
    add(dualListPanel, constraints);

    
  }
}