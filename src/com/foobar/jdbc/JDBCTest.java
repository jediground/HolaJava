package com.foobar.jdbc;

import com.foobar.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

/**
 * Created by Moch on 2/1/15.
 */
public class JDBCTest {

    @Test
    public void testQuery() {
        String sql = "SELECT user_id id, user_name name, email, birth "
                + "FROM users WHERE user_id = ?";
        User user = JDBCUtils.query(User.class, sql, 1);
        System.out.println(user);

        sql = "SELECT FlowId flowId, type, IDCard idCard, "
                + "examCard, studentName, "
                + "location, grade " + "FROM examstudent WHERE flowId = ?";
        Student stu = JDBCUtils.query(Student.class, sql, 10);
        System.out.println(stu);
    }

    @Test
    public void testJdbcStatement() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // 获取链接
            connection = JDBCUtils.getConnection();
            // 获取 Statement
            statement = connection.createStatement();
            // 准备 sql
            String sql = "SELECT * FROM users";
            // 执行 sql
            resultSet = statement.executeQuery(sql);
            // 处理结果
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String userName = resultSet.getString(2);
                String email = resultSet.getString("email");
                Date birth = resultSet.getDate(4);

                System.out.println(id);
                System.out.println(userName);
                System.out.println(email);
                System.out.println(birth);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(resultSet, statement, connection);
        }
    }

    @Test
    public void testAddNeWStudent() {
        Student student = new Student(9, 1, "167265367826751637", "1236536276", "Lily", "广州", 90);
        String sql = "INSERT INTO examstudent(" +
                        "flowid," +
                        "type," +
                        "idcard, " +
                        "examcard, " +
                        "studentname, " +
                        "location, " +
                        "grade) " +
                        "VALUES(?,?,?,?,?,?,?)";
        JDBCUtils.update(
                sql,
                student.getFlowId(),
                student.getType(),
                student.getIdCard(),
                student.getExamCard(),
                student.getStudentName(),
                student.getLocation(),
                student.getGrade()
        );
    }

    @Test
    public void testFetchStudent() {

    }
}

