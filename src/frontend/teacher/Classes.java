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

public class Classes {
    public JPanel classesPanel = new JPanel();
    public CardLayout classesCardLayout = new CardLayout(0, 0);

    public Classes() {
        // BoxLayout bl = new BoxLayout(classesPanel, BoxLayout.Y_AXIS);
        // classesPanel.setLayout(bl);
        
        backend.models.Classes classFilter = new backend.models.Classes();
        classFilter.TeacherId = Modules.user.Id;
        Vector<backend.models.Classes> classes = Model.List(backend.models.Classes.class, classFilter);
        GridLayout gl = new GridLayout(classes.size() + 4, 1);
        classesPanel.setLayout(gl);

        CustomButton addClass = new CustomButton("анги нэмэх", Modules.screenWidth - 600, 100);

            addClass.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    classesPanel.add(CreateClasses.panel, "CreateClasses");
                    classesCardLayout.show(Layout.contentPanel, "CreateClasses");
                    }
                });

            FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
            JPanel panel = new JPanel(fl);
            panel.add(addClass);
            classesPanel.add(panel, BorderLayout.CENTER);


        if(classes.size() == 0){
            JLabel labelaaa = new JLabel("tng al", null, 0);
            panel.add(labelaaa);
        } 
        else{
        // for(Students student: students) {
        //     backend.models.Classes classFilter = new backend.models.Classes();
        //     classFilter.Id = student.ClassId;

            
        //     JPanel panel = new JPanel(fl);
        //     Vector<backend.models.Classes> classes = Model.List(backend.models.Classes.class, classFilter);
        //     if(Objects.isNull(classes.get(0).Description)) {
        //         classes.get(0).Description = "";
        //     }
        //     String text = "<html><body style='padding:20'>" + classes.get(0).Name + "<br>" + classes.get(0).Description + "</body></html>";
        //     CustomButton classButton = new CustomButton(text, Modules.screenWidth - 600, 100);
        //     Font font = classButton.getFont();
        //     float fontSize = font.getSize() + 6.0f;
        //     Font newFont = font.deriveFont(fontSize);
        //     classButton.setHorizontalAlignment(SwingConstants.LEFT);
        //     classButton.setFont(newFont);
        //     classButton.setRadius(20);

        //     panel.add(classButton);
        //     classesPanel.add(panel, BorderLayout.CENTER);
            
            // classButton.addActionListener(new ActionListener() {
            //     @Override
            //     public void actionPerformed(ActionEvent ae) {
            //         Exams exam = new Exams(student.ClassId);
            //         Layout.contentPanel.add(exam, "exams");
            //         Layout.cardLayout.show(Layout.contentPanel, "exams");
            //         }
            //     });
        //     }
        }
    }
}
