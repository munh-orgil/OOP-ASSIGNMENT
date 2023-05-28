package frontend.teacher;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

import backend.Model;
import backend.models.Students;
import backend.models.Users;
import frontend.modules.Modules;
import frontend.teacher.layout.Layout;
import frontend.widgets.CustomButton;
import frontend.widgets.DualListBoxPanel;
 
public class ClassesDialog extends JPanel {
  private JTextField ClassNameField, DescField;
  
  public ClassesDialog(backend.models.Classes classes, boolean isUpdate){
    setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.BOTH;
    constraints.insets = new Insets(5, 10, 5, 10);

    JLabel ClassName = new JLabel("Хичээлийн нэр:");
    constraints.gridx = 0;
    constraints.gridy = 0;
    add(ClassName, constraints);
    
    ClassNameField = new JTextField(20);
    ClassNameField.setText(classes.Name != null ? classes.Name : "");
    constraints.gridx = 1;
    constraints.gridy = 0;
    ClassNameField.setPreferredSize(new Dimension(10, 40));
    add(ClassNameField, constraints);

    JLabel DescLabel = new JLabel("Тайлбар:");
    constraints.gridx = 0;
    constraints.gridy = 1;
    add(DescLabel, constraints);

    DescField = new JTextField(20);
    DescField.setText(classes.Description != null ? classes.Description : "");
    constraints.gridx = 1;
    constraints.gridy = 1;
    DescField.setPreferredSize(new Dimension(10, 40));
    add(DescField, constraints);
   
    JLabel studentsLabel = new JLabel("Оюутнууд:");
    constraints.gridx = 0;
    constraints.gridy = 2;
    add(studentsLabel, constraints);

    Vector<Users> users = Model.List(Users.class, new Users());
    Vector<Students> oldStudents = new Vector<Students>();

    if(isUpdate) {
      Students studentsFilter = new Students();
      studentsFilter.ClassId = classes.Id;
      oldStudents = Model.List(Students.class, studentsFilter);
    }

    DualListBoxPanel dualListPanel = new DualListBoxPanel();
    dualListPanel.setSourceChoicesTitle("Бүгд");
    dualListPanel.setDestinationChoicesTitle("Таны оюутнууд");

    DefaultListModel<Users> listModel = new DefaultListModel<>();
    
    for(Users user: users) {
      boolean ok = true;
      for(Students students: oldStudents) {
        if(user.Id == students.UserId) {
          ok = false;
          break;
        }
      }
      if(ok) {
        listModel.addElement(user);
      }
    }
    
    DefaultListModel<Users> oldListModel = new DefaultListModel<>();

    for(Students students: oldStudents) {
      Users userFilter = new Users();
      userFilter.Id = students.UserId;
      Vector<Users> user = Model.List(Users.class, userFilter);
      oldListModel.addElement(user.get(0));
    }

    dualListPanel.addDestinationElements(oldListModel);

    dualListPanel.addSourceElements(listModel);

    constraints.gridx = 0;
    constraints.gridy = 3;
    constraints.gridwidth = 2;
    add(dualListPanel, constraints);

    CustomButton createClass = new CustomButton(!isUpdate ? "Үүсгэх" : "Засах", 200, 50);
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
        int classId;
        if(isUpdate) {
          classId = classes.Id;
          newClass.Id = classId;
          Model.Update(newClass);
        } else {
          classId = Model.Create(newClass);
        }
        ListModel res = dualListPanel.getDestListModel();
        
        if(isUpdate) {
          backend.models.Students studentFilter = new Students();
          studentFilter.ClassId = classId;
          Vector<backend.models.Students> studentList = Model.List(Students.class, studentFilter);
          for(Students student: studentList) {
            Model.Delete(student);
          }
        }
        for(int i = 0; i < res.getSize(); i++) {
          Model.Create(res.getElementAt(i));
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