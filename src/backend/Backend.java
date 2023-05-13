package backend;

import java.sql.*;
import java.util.Vector;

import backend.database.database;
import backend.models.*;

public class Backend {
    public static void init() {
        database.init();

        Connection db = database.getConnection();
        try {
            Statement st;
            st = db.createStatement();
            Vector<Users> list = Model.List(Users.class, st, "select * from users");

            for(int i = 0; i < list.size(); i++) {
                Users cur = list.get(i);
                System.out.println(cur.FirstName + ' ' + cur.LastName + ' ' + cur.RegNo + ' ' + cur.Gender);
            }
            Users user = new Users();
            user.Id = 2;
            // Model.Delete(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}
