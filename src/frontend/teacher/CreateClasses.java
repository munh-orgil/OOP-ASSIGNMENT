package frontend.teacher;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

import backend.Model;
import backend.models.Users;
import frontend.modules.Modules;
import frontend.teacher.layout.Layout;
import frontend.widgets.CustomButton;
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

    Integer idx = 0;
    for(Users user: users) {
      idx++;
      listModel.addElement(idx.toString() + ". " + user.FirstName);
    }

    dualListPanel.addSourceElements(listModel);

    constraints.gridx = 0;
    constraints.gridy = 3;
    constraints.gridwidth = 2;
    add(dualListPanel, constraints);

    CustomButton createClass = new CustomButton("Үүсгэх", 200, 50);
    createClass.setRadius(20);
    constraints.gridx = 0;
    constraints.gridy = 4;
    add(createClass, constraints);

    createClass.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {
        backend.models.Classes newClass = new backend.models.Classes();
        newClass.Name = ClassNameField.getText();
        newClass.Description = DescField.getText();
        newClass.TeacherId = Modules.user.Id;
        int classId = Model.Create(newClass);
        ListModel res = dualListPanel.getDestListModel();
        
        for(int i = 0; i < res.getSize(); i++) {
          String val = (String) res.getElementAt(i);
          String[] arr = val.split(". ");
          Users user = users.get(Integer.parseInt(arr[0]) - 1);
          backend.models.Students newStudent = new backend.models.Students();
          newStudent.ClassId = classId;
          newStudent.UserId = user.Id;
          Model.Create(newStudent);
        } 

        Container container = createClass.getParent();
        while (!(container instanceof JDialog) && container != null) {
            container = container.getParent();
        }

        if (container instanceof JDialog) {
          JDialog dialog = (JDialog) container;
          dialog.dispose();
        }
        
        Classes classes = new Classes();
        Layout.contentPanel.add(classes, "classes");
        Layout.cardLayout.show(Layout.contentPanel, "classes");
      }
    });
  }
}