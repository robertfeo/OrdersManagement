package app.amagon;

import jpa.entities.Customer;

import javax.persistence.EntityManager;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class DBUtil {
    private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String connectionURL;
    private static Connection db_connection;
    private static PreparedStatement p_stmt;
    private static ResultSet rs;
    private static Statement statement;

    private List<Customer> customerList;

    /*private static EntityManager entityManager;*/

    public DBUtil() throws SQLException, ClassNotFoundException {

        String dbHost = "localhost";
        String dbPort = "1433";
        String dbName = "DB2_Projekt";
        String dbUser = "robert";
        String dbPass = "robert1324";

        connectionURL="jdbc:sqlserver://"+
                dbHost +":"+ dbPort +";"+"databaseName="+ dbName +";"+
                "user="+ dbUser +";"+
                "password="+ dbPass +";"+"encrypt=false;trustServerCertificate=true;";
    }
    public static void dbConnect() throws SQLException, ClassNotFoundException{
        try{
            Class.forName(JDBC_DRIVER);
        }catch(ClassNotFoundException e){
            System.out.println("Wo ist dein MySQL JDBC Driver?");
            e.printStackTrace();
            throw e;
        }
        try{
            db_connection = DriverManager.getConnection(connectionURL);
        }catch (SQLException e){
            System.out.println("Verbindung fehlgeschlagen!");
            e.printStackTrace();
            throw e;
        }
    }
    public static void dbDisconnect() throws SQLException{
        try {
            if (db_connection != null && !db_connection.isClosed()){
                db_connection.close();
            }
        }catch (Exception e){
            throw e;
        }
    }
    public static void dbExecuteQuery(String sqlStatement) throws SQLException, ClassNotFoundException{
        try{
            if (!db_connection.isClosed()) {
                p_stmt = db_connection.prepareStatement(sqlStatement);
                rs = p_stmt.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getString("customer_id")
                            + " " + rs.getString("surname")
                            + " " + rs.getString("name")
                            + " " + rs.getString("address")
                            + " " + rs.getString("city"));
                }
            }
            else{
                System.out.println("Es besteht keine Verbindung mit der Datenbank");
            }
        }catch (SQLException e){
            System.out.println("Problem beim Ausführen von Query.");
            e.printStackTrace();
            throw e;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch(Exception e) {}
            }
            if (p_stmt != null) {
                try {
                    p_stmt.close();
                }
                catch(Exception e) {}
            }
            if (db_connection != null) {
                try {
                    db_connection.close();
                }
                catch(Exception e) {}
            }

        }
    }

    public void initData() throws SQLException {
        getCustomerData();
    }

    private void getCustomerData() throws SQLException {
        try{
            if (!db_connection.isClosed()) {
                p_stmt = db_connection.prepareStatement("select * from amagon.customer");
                rs = p_stmt.executeQuery();
                while (rs.next()) {
                    this.customerList.add(new Customer(
                            rs.getInt("customer_id"),
                            rs.getString("surname"),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("city")));
                }
            }
            else{
                System.out.println("Es besteht keine Verbindung mit der Datenbank");
            }
        }catch (SQLException e){
            System.out.println("Problem beim Ausführen von Query.");
            e.printStackTrace();
            throw e;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch(Exception e) { e.printStackTrace(); }
            }
            if (p_stmt != null) {
                try {
                    p_stmt.close();
                }
                catch(Exception e) { e.printStackTrace(); }
            }
            if (db_connection != null) {
                try {
                    db_connection.close();
                }
                catch(Exception e) { e.printStackTrace(); }
            }

        }
    }
}
