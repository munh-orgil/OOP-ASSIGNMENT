package backend.database;

import java.sql.*;
// import java.util.Timer;
// import java.util.TimerTask;

public class database {
    static final String DB_URL = "jdbc:mysql://localhost:3306/exammr";
    static final String USER = "root";
    static final String PASS = "3B5GqPjpDr5t";

    public static Connection DBConn;
    
    public static void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            DBConn = DriverManager.getConnection(DB_URL, USER, PASS);
            // DBConn.setNetworkTimeout(null, 30000);
            // Timer timer = new Timer();
            // timer.schedule(new KeepAliveTask(), 0, 300000);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // private static class KeepAliveTask extends TimerTask {
    //     public void run() {
    //         try {
    //             Statement statement = DBConn.createStatement();
    //             ResultSet resultSet = statement.executeQuery("SELECT 1");
    //             resultSet.close();
    //             statement.close();
    //         } catch (SQLException e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }

    public static Connection getConnection() {
        return DBConn;
    }
}
