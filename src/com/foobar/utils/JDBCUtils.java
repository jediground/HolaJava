package com.foobar.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * Created by Moch on 2/1/15.
 */
public class JDBCUtils {
    public static Connection getConnection() throws Exception {
        String driverClass = null;
        String jdbcUrl = null;
        String user = null;
        String password = null;

        InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties prop = new Properties();
        prop.load(in);
        driverClass = prop.getProperty("driver");
        jdbcUrl = prop.getProperty("jdbcUrl");
        user = prop.getProperty("user");
        password = prop.getProperty("password");

        // 注册驱动
        Class.forName(driverClass);
        Connection connection = (Connection) DriverManager.getConnection(jdbcUrl, user, password);

        return connection;
    }

    public static void release(ResultSet resultSet, Statement statement, Connection connection) {
        if (null != resultSet) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (null != statement) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void update(String sql) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            statement.executeLargeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(null, statement, connection);
        }
    }

    public static void update(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(null, preparedStatement, connection);
        }
    }

    public static <T> T query(Class<T> clazz, String sql, Object... args) {
        List<T> list = queryForList(clazz, sql, args);
        if (list.size() > 0) {
            return list.get(0);
        }

        return null;
    }

    public static <T> List<T> queryForList(Class<T> clazz, String sql, Object... args) {
        List<T> list = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();

            List<Map<String, Object>> pairs = handleResultSet(resultSet);

            list = convertMapListToBeanList(clazz, pairs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(resultSet, preparedStatement, connection);
        }

        return list;
    }

    private static List<Map<String, Object>> handleResultSet(ResultSet resultSet) throws SQLException {
        List<Map<String, Object>> pairs = new ArrayList<Map<String, Object>>();
        List<String> columnLabels = getColumnLabels(resultSet);
        Map<String, Object> map = null;
        while (resultSet.next()) {
            map = new HashMap<String, Object>();
            for (String columnLabel: columnLabels) {
                map.put(columnLabel, resultSet.getObject(columnLabel));
            }
            pairs.add(map);
        }

        return pairs;
    }

    private static List<String> getColumnLabels(ResultSet resultSet) throws SQLException {
        List<String> columnLabels = new ArrayList<String>();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
            columnLabels.add(resultSetMetaData.getColumnLabel(i + 1));
        }

        return columnLabels;
    }

    private static <T> T convertMapToBean(Class<T> clazz, Map<String, Object>map) throws IllegalAccessException, InstantiationException {
        T bean = clazz.newInstance();
        for (Map.Entry<String, Object> entry: map.entrySet()) {
            ReflectionUtils.setFieldValue(bean, entry.getKey(), entry.getValue());
        }
        return bean;
    }

    private static <T> List<T> convertMapListToBeanList(Class<T> clazz, List<Map<String, Object>>mapList) throws InstantiationException, IllegalAccessException {
        List<T> result = new ArrayList<T>();
        if (mapList.size() > 0) {
            for (Map<String, Object> map : mapList) {
                result.add(convertMapToBean(clazz, map));
            }
        }

        return result;
    }

    // 返回某条记录的某一个字段的值或一个统计的值
    public static <E> E getValue(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return (E) resultSet.getObject(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(resultSet, preparedStatement, connection);
        }

        return null;
    }

}
