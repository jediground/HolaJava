package com.foobar.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
        T entity = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 1. get resultSet object
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();

            // 2. get ResultSetMetaData object
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            // 3. Create Map<Stirng, Object>, key: column alias, value: column value
            Map<String, Object> pairs = new HashMap<String, Object>();

            // 4. handle result set
            if (resultSet.next()) {
                for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
                    String columnLabel = resultSetMetaData.getColumnLabel(i + 1);
                    Object columnValue = resultSet.getObject(i + 1);
                    pairs.put(columnLabel, columnValue);
                }
            }

            // 5. Create new instance if needed
            if (pairs.size() > 0) {
                entity = clazz.newInstance();
                for (Map.Entry<String, Object> entry: pairs.entrySet()) {
                    String fieldName = entry.getKey();
                    Object value = entry.getValue();
                    ReflectionUtils.setFieldValue(entity, fieldName, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(resultSet, preparedStatement, connection);
        }

        return entity;
    }
}
