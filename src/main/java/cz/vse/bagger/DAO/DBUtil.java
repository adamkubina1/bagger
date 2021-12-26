package cz.vse.bagger.DAO;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    private static Connection connection = null;

    //Connection String
    //String connStr = "jdbc:oracle:thin:Username/Password@IP:Port/SID";
    //Username=HR, Password=HR, IP=localhost, IP=1521, SID=xe
    private static final String connectionString = "jdbc:mysql://localhost:3306/Bagger_test";


    public static void dbConnect() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your Driver?");
            e.printStackTrace();
            throw e;
        }

        System.out.println("JDBC Driver Registered!");

        try {
            connection = DriverManager.getConnection(connectionString, "root", "");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static void dbDisconnect() throws SQLException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e){
            throw e;
        }
    }

    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;
        try {
            dbConnect();
            System.out.println("Select statement: " + queryStmt + "\n");

            stmt = connection.createStatement();

            resultSet = stmt.executeQuery(queryStmt);

            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
        return crs;
    }

    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        try {
            dbConnect();
            stmt = connection.createStatement();
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
    }
}
