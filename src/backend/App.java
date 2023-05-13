package backend;

import java.sql.*;
import java.util.Vector;

import backend.database.database;
import backend.models.*;

public class App {
    public static void main(String[] args) throws SQLException {
        database.init();

        Connection db = database.getConnection();
        Statement st = db.createStatement();
        Vector<User> list = Model.List(User.class, st, "select * from users");

        for(int i = 0; i < list.size(); i++) {
            User cur = list.get(i);
            System.out.println(cur.FirstName + ' ' + cur.LastName + ' ' + cur.RegNo + ' ' + cur.Gender);
        }
    }
}
