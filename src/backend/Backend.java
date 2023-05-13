package backend;

import java.util.Vector;

import backend.database.database;
import backend.models.*;

public class Backend {
    public static void init() {
        database.init();

        Vector<Users> list = Model.List(Users.class);

        for(int i = 0; i < list.size(); i++) {
            Users cur = list.get(i);
            System.out.println(cur.FirstName + ' ' + cur.LastName + ' ' + cur.RegNo + ' ' + cur.Gender);
        }
        Users user = new Users();
        user.Id = 2;
        // Model.Delete(user);
    }
}
