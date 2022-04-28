package app.restaurantorders;

import java.sql.*;

public class DBUtil {
    private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    //Hostname or IP
    private static String dbHost="134.108.190.89";
    //Port
    private static String dbPort="1433";
    //Database name
    private static String dbName="SWB_DB2_Projekt";
    //User
    private static String dbUser="swb4";
    //passwort
    private static String dbPass="swb4";

    //Create a variable fpr the connection string
    private static final String connectionURL="jdbc:sqlserver://"+
            dbHost+":"+dbPort+";"+"databaseName="+dbName+";"+
            "user="+dbUser+";"+
            "password="+dbPass+";"+"encrypt=true;trustServerCertificate=true;";
    //declare the JDBC objects
    private static java.sql.Connection connection = null;
    PreparedStatement p_stmt = null;
    ResultSet rs = null;

    public static void dbConnect() throws SQLException, ClassNotFoundException{
        try{
            Class.forName(JDBC_DRIVER);
        }catch(ClassNotFoundException e){
            System.out.println("Wo ist dein MySQL JDBC Driver?");
            e.printStackTrace();
            throw e;
        }
        System.out.println("JDBC Driver wurde gefunden, alles gut!");

        try{
            connection = DriverManager.getConnection(connectionURL);
        }catch (SQLException e){
            System.out.println("Verbindung fehlgeschlagen!");
            throw e;
        }
    }

    public static void dbDisconnect() throws SQLException{
        try {
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }catch (Exception e){
            throw e;
        }
    }


    public static void dbExecuteQuery(String sqlStatement) throws SQLException, ClassNotFoundException{
        Statement statement = null;
        /*try{

        }catch (SQLException e){
            System.out.println("Problem beim Ausführen von Query.");
            e.printStackTrace();
            throw e;
        }*/
    }











    /*    try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        connection = DriverManager.getConnection(connectionURL);
        //	Create, prepare and execute an SQL statement that returns some data.
        String SQL = "select * from [dbo].[bas_dishes]";
        p_stmt = connection.prepareStatement(SQL);
        rs = p_stmt.executeQuery();

        //	Iterate through the data in the result set and display it.
        while (rs.next()) {
            System.out.println(rs.getString("id") + " " + rs.getString("name"));
        }
    }
        catch(Exception e) {
        e.printStackTrace();
    }
        finally {
        if (rs!=null) {
            try {
                rs.close();
            }
            catch(Exception e) {}
        }
        if (p_stmt!=null) {
            try {
                p_stmt.close();
            }
            catch(Exception e) {}
        }
        if (connection !=null) {
            try {
                connection.close();
            }
            catch(Exception e) {}
        }

    }*/
}
