package frontend.teacher;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import backend.Model;
import backend.models.Classes;
import frontend.modules.Modules;
import frontend.teacher.layout.Layout;
import frontend.widgets.CustomButton;

public class Exams extends JPanel {
    public Exams(int classId) {
        BoxLayout bl = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(bl);
        
        Vector<Integer> classIds = new Vector<>();
        if(classId != 0) {
            classIds.add(classId);
        } else {
            backend.models.Classes classFilter = new backend.models.Classes();
            classFilter.TeacherId = Modules.user.Id;
            Vector<backend.models.Classes> classList = Model.List(Classes.class, classFilter);
            for(Classes classes: classList) {
                classIds.add(classes.Id);
            }
        }

        JPanel addPanel = new JPanel(new FlowLayout());
        CustomButton addExam = new CustomButton("Шалгалт нэмэх", (int)(Modules.screenWidth * 0.20), 80);
        addExam.addActionListener(e -> {
            JOptionPane.showOptionDialog(
                this,
                new AddExam(),
                "Шалгалт нэмэх",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new Object[] {},
                null
            );
        });
        addExam.setRadius(30);
        addPanel.add(addExam);
        addPanel.setMaximumSize(Modules.getPreferredHeight(addPanel, (int)(Modules.screenWidth * 0.55)));
        add(addPanel);

        Vector<backend.models.Exams> allExamsList = new Vector<>();
        
        for(Integer classesId: classIds) {
            backend.models.Exams examsFilter = new backend.models.Exams();
            examsFilter.ClassId = Modules.user.Id;
            Vector<backend.models.Exams> examsList = Model.List(backend.models.Exams.class, examsFilter);
            for(backend.models.Exams exam: examsList) {
                allExamsList.add(exam);
            }
        }

        if(allExamsList.size() == 0){
            JPanel noClassPanel = new JPanel(new BorderLayout());
            JLabel noClassLabel = new JLabel("Шалгалтгүй байна");
            Font font = noClassLabel.getFont();
            noClassLabel.setFont(font.deriveFont(30f));
            noClassPanel.add(noClassLabel, BorderLayout.CENTER);
            noClassPanel.setMaximumSize(Modules.getPreferredHeight(noClassPanel, (int)(Modules.screenWidth * 0.55)));
            add(noClassPanel);
        } else {
            for(backend.models.Exams exam: allExamsList) {
                JPanel teacherExamPanel = new JPanel();
                BoxLayout teacherExamLayout = new BoxLayout(teacherExamPanel, BoxLayout.Y_AXIS);
                teacherExamPanel.setLayout(teacherExamLayout);
                
                FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
                JPanel panel = new JPanel(fl);
                if(Objects.isNull(exam.Description)) {
                    exam.Description = "";
                }
                String text = "<html><body style='padding:20'>" + exam.Title + "<br>" + exam.Description + "</body></html>";
                CustomButton examButton = new CustomButton(text, (int)(Modules.screenWidth * 0.55) - 100, 100);
                Font font = examButton.getFont();
                float fontSize = font.getSize() + 6.0f;
                Font newFont = font.deriveFont(fontSize);
                examButton.setHorizontalAlignment(SwingConstants.LEFT);
                examButton.setFont(newFont);
                examButton.setRadius(20);

                CustomButton delete = new CustomButton("", 40, 40);
                ImageIcon deleteIcon = new ImageIcon("./assets/delete.png");
                Image scaledDeleteImage = deleteIcon.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
                delete.setIcon(new ImageIcon(scaledDeleteImage));
                delete.setRadius(100);

                delete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        Model.Delete(exam);

                        Exams exams = new Exams(classId);
                        Layout.contentPanel.add(exams, "exams");
                        Layout.cardLayout.show(Layout.contentPanel, "exams");
                    }
                });

                JPanel editingPanel = new JPanel(new BorderLayout());
                editingPanel.add(delete, BorderLayout.CENTER);

                editingPanel.setMaximumSize(new Dimension(100, 100));
                JPanel buttonPanel = new JPanel(new FlowLayout());
                buttonPanel.add(examButton);
                buttonPanel.add(editingPanel);

                panel.add(buttonPanel);
                teacherExamPanel.add(panel, BorderLayout.CENTER);

                examButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        // Exams exam = new Exams(classes.Id);
                        // Layout.contentPanel.add(exam, "exams");
                        // Layout.cardLayout.show(Layout.contentPanel, "exams");
                    }
                });
    
                teacherExamPanel.setMaximumSize(Modules.getPreferredHeight(teacherExamPanel, (int)(Modules.screenWidth * 0.55)));
                add(teacherExamPanel);
            }
        }

        
        
    }
}
