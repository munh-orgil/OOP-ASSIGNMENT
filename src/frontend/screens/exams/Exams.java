package frontend.screens.exams;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;

import backend.Model;
import backend.models.Classes;
import backend.models.Students;
import frontend.modules.Modules;
import frontend.screens.Questions;
import frontend.screens.layout.Layout;
import frontend.widgets.CustomButton;

public class Exams extends JPanel {
    public Exams(int classId) {
        Vector<Integer> classIds = new Vector<Integer>();
        if(classId == 0) {
            Students studentFilter = new Students();
            studentFilter.UserId = Modules.user.Id;
            Vector<Students> studentList = Model.List(Students.class, studentFilter);
            for(Students student: studentList) {
                classIds.add(student.ClassId);
            }
        } else {
            classIds.add(classId);
        }

        for(int i = 0 ; i < classIds.size(); i++) {
            int id = classIds.get(i).intValue();
            backend.models.Classes classFilter = new Classes();
            classFilter.Id = id;
            Vector<Classes> classList = Model.List(Classes.class, classFilter);
            Classes classinfo = classList.get(0);
            
            backend.models.Exams examFilter = new backend.models.Exams();
            examFilter.ClassId = id;
            Vector<backend.models.Exams> examList = Model.List(backend.models.Exams.class, examFilter);

            Collections.sort(examList);
            
        }
    }
}
