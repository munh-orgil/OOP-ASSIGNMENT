package frontend.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import backend.Model;
import backend.models.Classes;
import backend.models.Students;
import frontend.modules.Modules;
import frontend.screens.layout.Layout;
import frontend.widgets.CustomButton;

public class Exams {
    public JPanel examsPanel = new JPanel();

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

            GridLayout gl = new GridLayout(examList.size() + 4, 1);
            examsPanel.setLayout(gl);
            
            for(int j = 0; j < examList.size(); j++) {
                backend.models.Exams exam = examList.get(j);
                FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
                JPanel panel = new JPanel(fl);

                JPanel buttonPanel = new JPanel(new BorderLayout());
                JPanel westButtonPanel = new JPanel(new BorderLayout());
                JLabel examTitle = new JLabel(exam.Title);
                Font titleFont = examTitle.getFont();
                examTitle.setFont(titleFont.deriveFont(24f));
                westButtonPanel.add(examTitle, BorderLayout.NORTH);
                
                JLabel examDescription = new JLabel(exam.Description);
                Font descriptionFont = examDescription.getFont();
                examDescription.setFont(descriptionFont.deriveFont(18f));
                examDescription.setForeground(new Color(100, 100, 100));
                westButtonPanel.add(examDescription, BorderLayout.SOUTH);
                westButtonPanel.setOpaque(false);

                JPanel eastButtonPanel = new JPanel(new BorderLayout());
                JLabel className = new JLabel("Анги: " + classinfo.Name);
                Font classNameFont = className.getFont();
                className.setFont(classNameFont.deriveFont(24f));
                className.setForeground(new Color(100, 200, 100));
                eastButtonPanel.add(className, BorderLayout.NORTH);

                JLabel startDate = new JLabel("Эхлэх: " + exam.StartTime);
                Font startDateFont = startDate.getFont();
                startDate.setFont(startDateFont.deriveFont(18f));
                startDate.setForeground(new Color(100, 100, 200));
                eastButtonPanel.add(startDate, BorderLayout.SOUTH);
                eastButtonPanel.setOpaque(false);

                buttonPanel.add(westButtonPanel, BorderLayout.WEST);
                buttonPanel.add(eastButtonPanel, BorderLayout.EAST);
                buttonPanel.setOpaque(false);

                // String text = "<html><body style='padding:20'>"
                //  + exam.Title + "<br>" + exam.Description + 
                //  "</body></html>";
                CustomButton examButton = new CustomButton("", Modules.screenWidth - 600, 100);
                // Font font = examButton.getFont();
                // float fontSize = font.getSize() + 6.0f;
                // Font newFont = font.deriveFont(fontSize);
                // examButton.setHorizontalAlignment(SwingConstants.LEFT);
                // examButton.setFont(newFont);
                examButton.setRadius(20);
                examButton.add(buttonPanel);
                panel.add(examButton);
                
                examsPanel.add(panel, BorderLayout.CENTER);

                examButton.addActionListener(new ActionListener () {
                    public void actionPerformed(ActionEvent ae) {
                        Questions questions = new Questions(exam.Id);
                        Layout.contentPanel.add(questions, "questions");
                        Layout.cardLayout.show(Layout.contentPanel, "questions");
                    }
                });
            }
        }
    }
}
