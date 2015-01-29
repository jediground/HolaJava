package com.foobar.jdbc;

import java.io.InputStream;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

import com.mysql.jdbc.Connection;

public class JDBCClass {

    @Test
    public void testJdbc2() throws Exception {
        System.out.println(getConnection());
    }
    
    @Test
    public void testJdbc() throws SQLException {
        Properties info = new Properties();
        info.put("user", "holajava");
        info.put("password", "800820882o");
        String url = "jdbc:mysql://127.0.0.1:3306/MXDB";
        
        com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
        Connection connect = (Connection) driver.connect(url, info);
        System.out.println(connect);
    }
    
    public Connection getConnection() throws Exception {
        String driverClass = null;
        String jdbcUrl = null;
        String user = null;
        String password = null;
        
        InputStream in = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
        Properties prop = new Properties();
        prop.load(in);
        driverClass = prop.getProperty("driver");
        jdbcUrl = prop.getProperty("jdbcUrl");
        user = prop.getProperty("user");
        password = prop.getProperty("password");
        
        Properties info = new Properties();
        info.put("user", user);
        info.put("password", password);
        Driver driver = (Driver)Class.forName(driverClass).newInstance();
        Connection connect = (Connection)driver.connect(jdbcUrl, info);
        
        return connect;
    }
}
