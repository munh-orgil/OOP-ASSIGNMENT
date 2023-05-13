package backend;
import java.sql.*;
import java.lang.reflect.*;
import java.util.Vector;

import backend.database.database;

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

    public static String camelToSnake(String str) {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        str = str.replaceAll(regex, replacement).toLowerCase();
        return str;
    }

    public static <T> void Create(T Value) {
        Class<? extends Object> klazz = Value.getClass();
        String className = klazz.getSimpleName();
        Field[] fields = klazz.getDeclaredFields();
        String sqlString = "insert into " + camelToSnake(className) + " values(NULL, ";
        for(int i = 0; i < fields.length - 2; i++) {
            sqlString += "?, ";
        }
        sqlString += "?)";

        Connection db = database.getConnection();
        try {
            PreparedStatement prst = db.prepareStatement(sqlString);
            for(int i = 1; i < fields.length; i++) {
                if(fields[i].getType().getSimpleName().equals("String")) {
                    String val = (String) fields[i].get(Value);
                    prst.setString(i, val);
                    continue;
                }
                if(fields[i].getType().getSimpleName().equals("int")) {
                    int val = (int) fields[i].get(Value);
                    prst.setInt(i, val);
                    continue;
                }
            }
            prst.execute();
        } catch (SQLException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static <T> void Update(T Value) {
        Class<? extends Object> klazz = Value.getClass();
        String className = klazz.getSimpleName();
        Field[] fields = klazz.getDeclaredFields();
        int n = fields.length;
        String sqlString = "update " + camelToSnake(className) + " set ";
        for(int i = 1; i < n - 1; i++) {
            sqlString += camelToSnake(fields[i].getName()) + " = ?, ";
        }
        sqlString += camelToSnake(fields[n - 1].getName()) + " = ? ";
        sqlString += "where id = ?;";

        Connection db = database.getConnection();
        try {
            PreparedStatement prst = db.prepareStatement(sqlString);
            for(int i = 1; i < n; i++) {
                if(fields[i].getType().getSimpleName().equals("String")) {
                    String val = (String) fields[i].get(Value);
                    prst.setString(i, val);
                    continue;
                }
                if(fields[i].getType().getSimpleName().equals("int")) {
                    int val = (int) fields[i].get(Value);
                    prst.setInt(i, val);
                    continue;
                }
            }
            int val = (int) fields[0].get(Value);
            prst.setInt(n, val);
            prst.execute();
        } catch (SQLException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static <T> void Delete(T Value) {
        Class<? extends Object> klazz = Value.getClass();
        String className = klazz.getSimpleName();
        Field[] fields = klazz.getDeclaredFields();
        String sqlString = "delete from " + camelToSnake(className) + " where id = ?;";

        Connection db = database.getConnection();
        try {
            PreparedStatement prst = db.prepareStatement(sqlString);
            int val = (int) fields[0].get(Value);
            prst.setInt(1, val);
            prst.execute();
        } catch (SQLException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
