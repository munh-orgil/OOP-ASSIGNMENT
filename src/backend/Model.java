package backend;
import java.sql.*;
import java.lang.reflect.*;
import java.util.Vector;

public class Model {
    public static <T> T GetValue(Class<T> klazz, ResultSet rs) {
        Field[] fields = klazz.getDeclaredFields();
        T res;
        try {
            res = klazz.getDeclaredConstructor().newInstance();
            int idx = 0;
            for(Field field: fields) {
                idx++;
                if(field.getType().getSimpleName().equals("String")) {
                        try {
                            field.set(res, rs.getString(idx));
                        } catch (IllegalArgumentException | IllegalAccessException | SQLException e) {
                            e.printStackTrace();
                        }
                    
                    continue;
                }
                if(field.getType().getSimpleName().equals("Int")) {
                        try {
                            field.set(res, rs.getInt(idx));
                        } catch (IllegalArgumentException | IllegalAccessException | SQLException e) {
                            e.printStackTrace();
                        }
                }
            }
            return res; 
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> Vector<T> List(Class<T> klazz, final Statement st, String queryString) {
        Vector<T> res = new Vector<T>();
        try (ResultSet rs = st.executeQuery(queryString)) {
            int vectorIdx = 0;
            while(rs.next()) {
                final T obj = GetValue(klazz, rs);

                res.add(vectorIdx, obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public static <T> Vector<T> Create(T Value, final PreparedStatement prst, String queryString) {
        Class<? extends Object> klazz = Value.getClass();
        Field[] fields = klazz.getDeclaredFields();
        Vector<T> res = new Vector<T>();

        int idx = 0;
        for(Field field: fields) {
            if(field.getType().getSimpleName().equals("String")) {
                prst.setString(idx, field.get(Value));
            }
        }

        return res;
    }
}
