package frontend.teacher;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.text.LayoutQueue;

import backend.Model;
import backend.models.Students;
import frontend.modules.Modules;
import frontend.student.exams.Exams;
import frontend.student.layout.Layout;
import frontend.widgets.CustomButton;

public class Classes extends JPanel {
    public static CardLayout cardLayout = new CardLayout();
    public static JPanel innerPanel = new JPanel();

    public Classes() {
        innerPanel.setLayout(cardLayout);
        backend.models.Classes classFilter = new backend.models.Classes();
        classFilter.TeacherId = Modules.user.Id;
        Vector<backend.models.Classes> classesList = Model.List(backend.models.Classes.class, classFilter);
        
        BoxLayout bl = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(bl);
        
        CustomButton addClass = new CustomButton("анги нэмэх", (int)(Modules.screenWidth * 0.55), 100);
        add(addClass);
        addClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cardLayout.show(innerPanel, "CreateClasses");
            }
        });

        if(classesList.size() == 0){
            JPanel noClassPanel = new JPanel(new BorderLayout());
            JLabel noClassLabel = new JLabel("Та ангигүй байна");
            Font font = noClassLabel.getFont();
            noClassLabel.setFont(font.deriveFont(30f));
            noClassPanel.add(noClassLabel, BorderLayout.CENTER);
            // noClassPanel.setPreferredSize(Modules.getPreferredHeight(noClassPanel, (int)(Modules.screenWidth * 0.30)));
            innerPanel.add(noClassPanel, "noClass");
            innerPanel.add(CreateClasses.panel, "createClasses");
        }
        else {
            JPanel teacherClassPanel = new JPanel();
            BoxLayout teacherClassLayout = new BoxLayout(teacherClassPanel, BoxLayout.Y_AXIS);
            teacherClassPanel.setLayout(teacherClassLayout);
            
            for(backend.models.Classes classes : classesList) {
                FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
                JPanel panel = new JPanel(fl);
                if(Objects.isNull(classes.Description)) {
                    classes.Description = "";
                }
                String text = "<html><body style='padding:20'>" + classes.Name + "<br>" + classes.Description + "</body></html>";
                CustomButton classButton = new CustomButton(text, (int)(Modules.screenWidth * 0.55), 100);
                Font font = classButton.getFont();
                float fontSize = font.getSize() + 6.0f;
                Font newFont = font.deriveFont(fontSize);
                classButton.setHorizontalAlignment(SwingConstants.LEFT);
                classButton.setFont(newFont);
                classButton.setRadius(20);

                panel.add(classButton);
                teacherClassPanel.add(panel, BorderLayout.CENTER);

                classButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        // Exams exam = new Exams(classes.Id);
                        // Layout.contentPanel.add(exam, "exams");
                        // Layout.cardLayout.show(Layout.contentPanel, "exams");
                    }
                });
            }

            innerPanel.add(teacherClassPanel, "teacherClasses");
            innerPanel.add(CreateClasses.panel, "createClasses");
        }
        add(innerPanel);
    }
}
