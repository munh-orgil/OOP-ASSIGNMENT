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
import frontend.modules.Modules;
import frontend.widgets.CustomButton;

public class Classes extends JPanel {
    public Classes() {
        BoxLayout bl = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(bl);
        
        backend.models.Classes classFilter = new backend.models.Classes();
        classFilter.TeacherId = Modules.user.Id;
        Vector<backend.models.Classes> classesList = Model.List(backend.models.Classes.class, classFilter);
        
        JPanel addPanel = new JPanel(new FlowLayout());
        CustomButton addClass = new CustomButton("Хичээл нэмэх", (int)(Modules.screenWidth * 0.20), 80);
        addClass.addActionListener(e -> {
            JOptionPane.showOptionDialog(
                this,
                new CreateClasses(),
                "Хичээл нэмэх",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new Object[] {},
                null
            );
        });
        addClass.setRadius(30);
        addPanel.add(addClass);
        addPanel.setMaximumSize(Modules.getPreferredHeight(addPanel, (int)(Modules.screenWidth * 0.55)));
        add(addPanel);
        
        if(classesList.size() == 0){
            JPanel noClassPanel = new JPanel(new BorderLayout());
            JLabel noClassLabel = new JLabel("Та хичээлгүй байна");
            Font font = noClassLabel.getFont();
            noClassLabel.setFont(font.deriveFont(30f));
            noClassPanel.add(noClassLabel, BorderLayout.CENTER);
            noClassPanel.setMaximumSize(Modules.getPreferredHeight(noClassPanel, (int)(Modules.screenWidth * 0.55)));
            add(noClassPanel);
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
                CustomButton classButton = new CustomButton(text, (int)(Modules.screenWidth * 0.55) - 100, 100);
                Font font = classButton.getFont();
                float fontSize = font.getSize() + 6.0f;
                Font newFont = font.deriveFont(fontSize);
                classButton.setHorizontalAlignment(SwingConstants.LEFT);
                classButton.setFont(newFont);
                classButton.setRadius(20);

                CustomButton edit = new CustomButton("", 40, 40);
                ImageIcon editIcon = new ImageIcon("./assets/edit.png");
                Image scaledEditImage = editIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                edit.setIcon(new ImageIcon(scaledEditImage));
                edit.setRadius(100);

                CustomButton delete = new CustomButton("", 40, 40);
                ImageIcon deleteIcon = new ImageIcon("./assets/delete.png");
                Image scaledDeleteImage = deleteIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                delete.setIcon(new ImageIcon(scaledDeleteImage));
                delete.setRadius(100);

                JPanel editingPanel = new JPanel(new BorderLayout());
                editingPanel.add(edit, BorderLayout.NORTH);
                editingPanel.add(delete, BorderLayout.SOUTH);

                editingPanel.setMaximumSize(new Dimension(100, 100));
                JPanel buttonPanel = new JPanel(new FlowLayout());
                buttonPanel.add(classButton);
                buttonPanel.add(editingPanel);

                panel.add(buttonPanel);
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

            teacherClassPanel.setMaximumSize(Modules.getPreferredHeight(teacherClassPanel, (int)(Modules.screenWidth * 0.55)));
            add(teacherClassPanel);
        }
        // setMaximumSize(Modules.getPreferredHeight(this, (int)(Modules.screenWidth * 0.55)));
    }
}
