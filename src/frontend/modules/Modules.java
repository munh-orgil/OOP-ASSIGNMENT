package frontend.modules;

import java.awt.Dimension;
import java.awt.Toolkit;

import backend.models.Logins;
import backend.models.Users;

public class Modules {
    public static Users user;
    public static Logins login;

    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int screenWidth = screenSize.width;
    public static int screenHeight = screenSize.height;

}
