package cn.xyspace.xysvr.common.test.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtil {

    public static Connection getConnection() throws SQLException {
        Connection con = null;
        con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/mybatis_test", "postgres", "postgres");
        return con;
    }

    public static void close(Connection con) {
        try {
            if (con != null)
                con.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(PreparedStatement ps) {
        try {
            if (ps != null)
                ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
