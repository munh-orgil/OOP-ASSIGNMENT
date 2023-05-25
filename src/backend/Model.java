package backend;
import java.sql.*;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Vector;

import backend.database.database;

public class Model {
    public static <T> T GetValue(Class<T> klazz, ResultSet rs) {
        Field[] fields = klazz.getDeclaredFields();
        T res;
        try {
            res = klazz.getDeclaredConstructor().newInstance();
            Integer idx = 0;
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
                if(field.getType().getSimpleName().equals("Integer")) {
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

    public static String camelToSnake(String str) {

        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        str = str.replaceAll(regex, replacement).toLowerCase();
        return str;
    }
    
    public static <T> Vector<T> List(Class<T> klazz, T filter) {
        Connection db = database.getConnection();
        try {
            String className = klazz.getSimpleName();
            Field[] allFields = klazz.getDeclaredFields();
            Field[] fields = {};
            for(Field field: allFields) {
                Object val = field.get(filter);
                if(val != null) {
                    fields = Arrays.copyOf(fields, fields.length + 1);
                    fields[fields.length - 1] = field;
                }
            }

            String queryString = "select * from " + camelToSnake(className);
            String filterString = " where ";
            boolean ok = false;
            for(Integer i = 0; i < fields.length - 1; i++) {
                Field field = fields[i];
                String name = camelToSnake(field.getName());
                if(field.getType().getSimpleName().equals("String")) {
                    String val = (String) field.get(filter);
                    if(val != null) {
                        val = "'%" + val.toLowerCase() + "%'";
                        filterString += "lower(" + name + ") LIKE " + val + " and ";
                        ok = true;
                    }
                }
                if(field.getType().getSimpleName().equals("Integer")) {
                    Integer val = (Integer) field.get(filter);
                    if(val != null) {
                        filterString += name + "=" + val.toString() + " and ";
                        ok = true;
                    }
                }
            }

            if(fields.length > 0) {
                String name = camelToSnake(fields[fields.length - 1].getName());
                if(fields[fields.length - 1].getType().getSimpleName().equals("String")) {
                    String val = (String) fields[fields.length - 1].get(filter);
                    if(val != null) {
                        val = "'%" + val.toLowerCase() + "%'";
                        filterString += "lower(" + name + ") LIKE " + val;
                        ok = true;
                    }
                }
                if(fields[fields.length - 1].getType().getSimpleName().equals("Integer")) {
                    Integer val = (Integer) fields[fields.length - 1].get(filter);
                    if(val != null) {
                        filterString += name + "=" + val.toString();
                        ok = true;
                    }
                }
            }

            if(ok) {
                queryString += filterString;
            }

            System.out.println(queryString);

            Statement prst = db.createStatement();
            ResultSet rs = prst.executeQuery(queryString);
            Vector<T> res = new Vector<T>();
            Integer vectorIdx = 0;
            while(rs.next()) {
                final T obj = GetValue(klazz, rs);

                res.add(vectorIdx, obj);
            }
            return res;
        } catch (SQLException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> int Create(T Value) {
        Class<? extends Object> klazz = Value.getClass();
        String className = klazz.getSimpleName();
        Field[] fields = klazz.getDeclaredFields();
        String sqlString = "insert into " + camelToSnake(className) + " values(NULL, ";
        for(Integer i = 0; i < fields.length - 2; i++) {
            sqlString += "?, ";
        }
        sqlString += "?)";

        Connection db = database.getConnection();
        try {
            PreparedStatement prst = db.prepareStatement(sqlString);
            for(Integer i = 1; i < fields.length; i++) {
                if(fields[i].getType().getSimpleName().equals("String")) {
                    String val = (String) fields[i].get(Value);
                    prst.setString(i, val);
                    continue;
                }
                if(fields[i].getType().getSimpleName().equals("Integer")) {
                    Integer val = (Integer) fields[i].get(Value);
                    prst.setInt(i, val);
                    continue;
                }
            }
            prst.execute();
            
            sqlString = "select id from " + camelToSnake(className);
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery(sqlString);
            int id = 0;
            while(rs.next()) {
                id = rs.getInt(1);
            }
            return id;
            
        } catch (SQLException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static <T> void Update(T Value) {
        Class<? extends Object> klazz = Value.getClass();
        String className = klazz.getSimpleName();
        Field[] fields = klazz.getDeclaredFields();
        Integer n = fields.length;
        String sqlString = "update " + camelToSnake(className) + " set ";
        for(Integer i = 1; i < n - 1; i++) {
            sqlString += camelToSnake(fields[i].getName()) + " = ?, ";
        }
        sqlString += camelToSnake(fields[n - 1].getName()) + " = ? ";
        sqlString += "where id = ?;";

        Connection db = database.getConnection();
        try {
            PreparedStatement prst = db.prepareStatement(sqlString);
            for(Integer i = 1; i < n; i++) {
                if(fields[i].getType().getSimpleName().equals("String")) {
                    String val = (String) fields[i].get(Value);
                    prst.setString(i, val);
                    continue;
                }
                if(fields[i].getType().getSimpleName().equals("Integer")) {
                    Integer val = (Integer) fields[i].get(Value);
                    prst.setInt(i, val);
                    continue;
                }
            }
            Integer val = (Integer) fields[0].get(Value);
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
            Integer val = (Integer) fields[0].get(Value);
            prst.setInt(1, val);
            prst.execute();
        } catch (SQLException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
