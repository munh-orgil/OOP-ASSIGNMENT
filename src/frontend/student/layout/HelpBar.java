package frontend.student.layout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import frontend.modules.Modules;
import frontend.widgets.SwingCalendar;

public class HelpBar {
    public JPanel helpBar = new JPanel(new FlowLayout());
    
    public HelpBar() {
        helpBar.setPreferredSize(new Dimension((int)(Modules.screenWidth * 0.18), Layout.frame.getHeight() - 100));
        helpBar.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.black));

        SwingCalendar sc = new SwingCalendar();
        // sc.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        helpBar.add(sc);
    }
}
