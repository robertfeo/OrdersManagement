package app.amagon;

import javafx.collections.ObservableList;
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

    private static String dbHost = "localhost";
    private static String dbPort = "1433";
    private static String dbName = "DB2_Projekt";
    private static String dbUser = "robert";
    private static String dbPass = "robert1324";

    private ObservableList<Customer> customerList;

    /*private static EntityManager entityManager;*/

    public static void dbConnect() throws SQLException, ClassNotFoundException{
        connectionURL ="jdbc:sqlserver://"+
                dbHost +":"+ dbPort +";"+"databaseName="+ dbName +";"+
                "user="+ dbUser +";"+
                "password="+ dbPass +";"+"encrypt=false;trustServerCertificate=true;";
        try{
            Class.forName(JDBC_DRIVER);
        }catch(ClassNotFoundException e){
            System.out.println("SQL Server JDBC Driver fehlt");
            e.printStackTrace();
            throw e;
        }
        try{
            db_connection = DriverManager.getConnection(connectionURL);
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }
    }
    public static void dbDisconnect() throws SQLException{
        try {
            if (db_connection != null && !db_connection.isClosed()){
                db_connection.close();
                db_connection = null;
            }
        }catch (Exception e){
            throw e;
        }
    }
    public static Connection getConnection(){
        return db_connection;
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

    public static ObservableList<Customer> getCustomerList(){
        return this.customerList;
    }

    public static int getTotalCustomers() throws SQLException {
        int total = 0;
        try {
            if (!db_connection.isClosed()) {
                p_stmt = db_connection.prepareStatement("select [amagon].[getTotalCustomers]() as total");
                rs = p_stmt.executeQuery();
                while(rs.next()){
                    total = rs.getInt(rs.findColumn("total"));
                }
            } else {
                System.out.println("Es besteht keine Verbindung mit der Datenbank");
            }
        } catch (SQLException e) {
            System.out.println("Problem beim Ausfuehren von Query.");
            e.printStackTrace();
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
            }
            if (p_stmt != null) {
                try {
                    p_stmt.close();
                } catch (Exception e) {
                }
            }
            if (db_connection != null) {
                try {
                    db_connection.close();
                } catch (Exception e) {
                }
            }
        }
        return total;
    }
}
