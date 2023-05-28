package frontend.modules;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;

import backend.models.Logins;
import backend.models.Users;

public class Modules {
    public static Users user;
    public static Logins login;

    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int screenWidth = screenSize.width;
    public static int screenHeight = screenSize.height;

    public static Dimension getPreferredHeight(JPanel panel, int width) {
        int height = 0;

        for (int i = 0; i < panel.getComponentCount(); i++) {
            Dimension size = panel.getComponent(i).getPreferredSize();
            height += size.height;
        }

        return new Dimension(width, height + 20);
    }
}
