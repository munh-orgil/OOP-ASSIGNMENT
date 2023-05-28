package frontend.student.exams;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import backend.Model;
import backend.models.Answers;
import backend.models.Classes;
import backend.models.Students;
import frontend.modules.Modules;
import frontend.widgets.CustomButton;

public class Exams extends JPanel {
    public Exams(int classId) {
        BoxLayout bl = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(bl);
        
        JPanel buttons = new JPanel(new FlowLayout());

        CustomButton overExams = new CustomButton("Дууссан шалгалтууд", (int)(Modules.screenWidth * 0.55) / 2, 80);
        CustomButton comingExams = new CustomButton("Авах шалгалтууд", (int)(Modules.screenWidth * 0.55) / 2, 80);

        // classButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent ae) {
        //         Exams exam = new Exams(student.ClassId);
        //         Layout.contentPanel.add(exam, "exams");
        //         Layout.cardLayout.show(Layout.contentPanel, "exams");
        //     }
        // });

        buttons.add(overExams);
        buttons.add(comingExams);

        add(buttons);
        
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

        Vector<backend.models.Exams> overExamList = new Vector<>();
        Vector<backend.models.Exams> comingExamList = new Vector<>();

        for(int i = 0 ; i < classIds.size(); i++) {
            int id = classIds.get(i).intValue();
            backend.models.Classes classFilter = new Classes();
            classFilter.Id = id;
            
            backend.models.Exams examFilter = new backend.models.Exams();
            examFilter.ClassId = id;
            Vector<backend.models.Exams> examList = Model.List(backend.models.Exams.class, examFilter);
            Collections.sort(examList);
            LocalDate today = LocalDate.now();
            LocalTime now = LocalTime.now();
            for(int j = 0; j < examList.size(); j++) {
                backend.models.Exams exam = examList.get(j);
                Answers answersFilter = new Answers();
                answersFilter.ExamId = exam.Id;
                Vector<Answers> answersList = Model.List(Answers.class, answersFilter);
                if(answersList.size() > 0) {
                    overExamList.add(exam);
                    continue;
                }

                int res = LocalDate.parse(exam.EndDate).compareTo(today);
                if(res == 0) {
                    res = LocalTime.parse(exam.EndTime).compareTo(now);
                }
                if(res < 0) {
                    overExamList.add(exam);
                } else {
                    comingExamList.add(exam);
                }
            }
        }

        CardLayout cardLayout = new CardLayout();
        JPanel examPanel = new JPanel(cardLayout);

        examPanel.add(new ExamsBuilder(comingExamList, "Авах шалгалтууд"), "comingExams");
        examPanel.add(new ExamsBuilder(overExamList, "Дууссан шалгалтууд"), "overExams");

        add(examPanel);

        comingExams.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cardLayout.show(examPanel, "comingExams");
            }
        });
        overExams.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cardLayout.show(examPanel, "overExams");
            }
        });
    }
}
