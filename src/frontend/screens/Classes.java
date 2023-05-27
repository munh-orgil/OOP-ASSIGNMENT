package frontend.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.text.LayoutQueue;

import backend.Model;
import backend.models.Students;
import frontend.modules.Modules;
import frontend.screens.layout.Layout;
import frontend.widgets.CustomButton;

public class Classes {
    public JPanel classesPanel = new JPanel();

    public Classes() {
        // BoxLayout bl = new BoxLayout(classesPanel, BoxLayout.Y_AXIS);
        // classesPanel.setLayout(bl);

        Students studentFilter = new Students();
        studentFilter.UserId = Modules.user.Id;
        Vector<Students> students = Model.List(Students.class, studentFilter);
        GridLayout gl = new GridLayout(students.size() + 4, 1);
        classesPanel.setLayout(gl);
        for(Students student: students) {
            backend.models.Classes classFilter = new backend.models.Classes();
            classFilter.Id = student.ClassId;

            FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
            JPanel panel = new JPanel(fl);
            Vector<backend.models.Classes> classes = Model.List(backend.models.Classes.class, classFilter);
            if(Objects.isNull(classes.get(0).Description)) {
                classes.get(0).Description = "";
            }
            String text = "<html><body style='padding:20'>" + classes.get(0).Name + "<br>" + classes.get(0).Description + "</body></html>";
            CustomButton classButton = new CustomButton(text, Modules.screenWidth - 600, 100);
            Font font = classButton.getFont();
            float fontSize = font.getSize() + 6.0f;
            Font newFont = font.deriveFont(fontSize);
            classButton.setHorizontalAlignment(SwingConstants.LEFT);
            classButton.setFont(newFont);
            classButton.setRadius(20);

            panel.add(classButton);
            classesPanel.add(panel, BorderLayout.CENTER);

            classButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    Exams exam = new Exams(student.ClassId);
                    Layout.contentPanel.add(exam.examsPanel, "exams");
                    Layout.cardLayout.show(Layout.contentPanel, "exams");
                }
            });
        }
    }
}
