package app.amagon;

import app.amagon.entities.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import app.amagon.entities.Customer;
import java.math.BigDecimal;
import java.sql.*;
import java.util.HashMap;
import java.util.Objects;

public class DBUtil {

    private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    private static Connection db_connection;

    private static PreparedStatement p_stmt;

    private static ResultSet rs;

    private static ObservableList<Customer> customerList;

    private static ObservableList<Product> productList;

    public static void dbConnect() throws SQLException, ClassNotFoundException{
        String dbPass = "robert1324";
        String dbHost = "localhost";
        String dbPort = "1433";
        String dbName = "DB2_Projekt";
        String dbUser = "robert";
        String connectionURL = "jdbc:sqlserver://" +
                dbHost + ":" + dbPort + ";" + "databaseName=" + dbName + ";" +
                "user=" + dbUser + ";" +
                "password=" + dbPass + ";" + "encrypt=false;trustServerCertificate=true;";
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
        if (db_connection != null && !db_connection.isClosed()){
            db_connection.close();
            db_connection = null;
        }
    }

    private static void getCustomerData() throws SQLException {
        customerList = FXCollections.observableArrayList();
        try{
            if (!db_connection.isClosed()) {
                p_stmt = db_connection.prepareStatement("select * from [amagon].[customer]");
                rs = p_stmt.executeQuery();
                while (rs.next()) {
                    Customer customer = new Customer(
                            rs.getInt("customer_id"),
                            rs.getString("surname"),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("city"));
                    customerList.add(customer);
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

    private static void getProductData() throws SQLException {
        productList = FXCollections.observableArrayList();
        try{
            if (!db_connection.isClosed()) {
                p_stmt = db_connection.prepareStatement("select * from [amagon].[product]");
                rs = p_stmt.executeQuery();
                while (rs.next()) {
                    Product product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("product_name"),
                            rs.getString("category"),
                            rs.getBigDecimal("price"),
                            rs.getInt("quantity"));
                    productList.add(product);
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

    public static ObservableList<Product> getProductList() throws SQLException, ClassNotFoundException {
        dbConnect();
        getProductData();
        dbDisconnect();
        return productList;
    }

    public static ObservableList<Customer> getAllCustomersList() throws SQLException, ClassNotFoundException {
        dbConnect();
        getCustomerData();
        dbDisconnect();
        return customerList;
    }

    public static ObservableList<Customer> getCustomersList(){
        return customerList;
    }

    public static ObservableList<Integer> getCustomerIDs() throws SQLException {
        ObservableList<Integer> customerIDs = FXCollections.observableArrayList();
        try{
            if (!db_connection.isClosed()) {
                p_stmt = db_connection.prepareStatement("select customer_id from amagon.customer");
                rs = p_stmt.executeQuery();
                while (rs.next()) {
                    customerIDs.add(rs.getInt("customer_id"));
                }
            }
            else{
                System.out.println("Es besteht keine Verbindung mit der Datenbank");
            }
        }catch (SQLException e){
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
        return customerIDs;
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
            e.printStackTrace();
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch(Exception e) { e.printStackTrace(); }
            }
            if (p_stmt != null) {
                try {
                    p_stmt.close();
                } catch(Exception e) { e.printStackTrace(); }
            }
            if (db_connection != null) {
                try {
                    db_connection.close();
                } catch(Exception e) { e.printStackTrace(); }
            }
        }
        return total;
    }

    public static int getTotalProducts() throws SQLException {
        int total = 0;
        try {
            if (!db_connection.isClosed()) {
                p_stmt = db_connection.prepareStatement("select [amagon].[getTotalProducts]() as total");
                rs = p_stmt.executeQuery();
                while(rs.next()){
                    total = rs.getInt(rs.findColumn("total"));
                }
            } else {
                System.out.println("Es besteht keine Verbindung mit der Datenbank");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch(Exception e) { e.printStackTrace(); }
            }
            if (p_stmt != null) {
                try {
                    p_stmt.close();
                } catch(Exception e) { e.printStackTrace(); }
            }
            if (db_connection != null) {
                try {
                    db_connection.close();
                } catch(Exception e) { e.printStackTrace(); }
            }
        }
        return total;
    }

    /*public static int getTotalProductsCategory(String category) throws SQLException {
        int total = 0;
        try {
            if (!db_connection.isClosed()) {
                p_stmt = db_connection.prepareStatement("select [amagon].[getTotalProductsCategory](?) as total");
                p_stmt.setString(1, category);
                rs = p_stmt.executeQuery();
                while(rs.next()){
                    total = rs.getInt(rs.findColumn("total"));
                }
            } else {
                System.out.println("Es besteht keine Verbindung mit der Datenbank");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch(Exception e) { e.printStackTrace(); }
            }
            if (p_stmt != null) {
                try {
                    p_stmt.close();
                } catch(Exception e) { e.printStackTrace(); }
            }
            if (db_connection != null) {
                try {
                    db_connection.close();
                } catch(Exception e) { e.printStackTrace(); }
            }
        }
        return total;
    }*/

    public static HashMap<String,Integer> getListProductCategory() throws SQLException {
        HashMap<String, Integer> listNumberProductCategories = new HashMap<>();
        try {
            if (!db_connection.isClosed()) {
                p_stmt = db_connection.prepareStatement("exec [amagon].[getListProductCategories]");
                rs = p_stmt.executeQuery();
                while(rs.next()){
                    listNumberProductCategories.put(rs.getString("category"),rs.getInt("total"));
                }
            } else {
                System.out.println("Es besteht keine Verbindung mit der Datenbank");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch(Exception e) { e.printStackTrace(); }
            }
            if (p_stmt != null) {
                try {
                    p_stmt.close();
                } catch(Exception e) { e.printStackTrace(); }
            }
            if (db_connection != null) {
                try {
                    db_connection.close();
                } catch(Exception e) { e.printStackTrace(); }
            }
        }
        return listNumberProductCategories;
    }

    public static void deleteCustomerByID(String ID) throws SQLException{
        try{
            if (!db_connection.isClosed()) {
                p_stmt = db_connection.prepareStatement("exec [amagon].[deleteByCustomerID] ?");
                p_stmt.setString(1, ID);
                p_stmt.execute();
            }
            else{
                System.out.println("Es besteht keine Verbindung mit der Datenbank");
            }
        }catch (SQLException e){
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

    public static void deleteProductByID(String ID) throws SQLException{
        try{
            if (!db_connection.isClosed()) {
                p_stmt = db_connection.prepareStatement("exec [amagon].[deleteProductByID] ?");
                p_stmt.setString(1, ID);
                p_stmt.execute();
            }
            else{
                System.out.println("Es besteht keine Verbindung mit der Datenbank");
            }
        }catch (SQLException e){
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

    public static void addCustomer(String surname, String name, String address, String city) throws SQLException {
        try{
            if (!db_connection.isClosed()) {
                if (!Objects.equals(surname, "") || !Objects.equals(name, "") || !Objects.equals(address, "") || !Objects.equals(city, "")){
                    p_stmt = db_connection.prepareStatement("exec [amagon].[addCustomer] ?,?,?,?");
                    p_stmt.setString(1, surname);
                    p_stmt.setString(2, name);
                    p_stmt.setString(3, address);
                    p_stmt.setString(4, city);
                    p_stmt.execute();
                }
            }
            else{
                System.out.println("Es besteht keine Verbindung mit der Datenbank");
            }
        }catch (SQLException e){
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

    public static void addProduct(String ProductName, String ProductCategory, String Price, String Quantity) throws SQLException {
        try{
            if (!db_connection.isClosed()) {
                if (!Objects.equals(ProductName, "") || !Objects.equals(ProductCategory, "") || !Objects.equals(Price, "") || !Objects.equals(Quantity, "")){
                    p_stmt = db_connection.prepareStatement("exec [amagon].[addProduct] ?,?,?,?");
                    p_stmt.setString(1, ProductName);
                    p_stmt.setString(2, ProductCategory);
                    p_stmt.setString(3, Price);
                    p_stmt.setString(4, Quantity);
                    p_stmt.execute();
                }
            }
            else{
                System.out.println("Es besteht keine Verbindung mit der Datenbank");
            }
        }catch (SQLException e){
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

    public static void editProduct(int ID, String ProductName, String ProductCategory, String Price, int Quantity) throws SQLException{
        try{
            if (!db_connection.isClosed()) {
                p_stmt = db_connection.prepareStatement("exec [amagon].[editProduct] ?,?,?,?,?");
                p_stmt.setInt(1, ID);
                p_stmt.setString(2, ProductName);
                p_stmt.setString(3, ProductCategory);
                p_stmt.setString(4, Price);
                p_stmt.setInt(5, Quantity);
                p_stmt.execute();
            }
            else{
                System.out.println("Es besteht keine Verbindung mit der Datenbank");
            }
        }catch (SQLException e){
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

    public static void getCustomerByID(String ID) throws SQLException {
        customerList.clear();
        try{
            if (!db_connection.isClosed()) {
                p_stmt = db_connection.prepareStatement("exec [amagon].[getCustomerByID] ?");
                p_stmt.setString(1,ID);
                rs = p_stmt.executeQuery();
                while (rs.next()) {
                    Customer customer = new Customer(
                            rs.getInt("customer_id"),
                            rs.getString("surname"),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("city"));
                    customerList.add(customer);
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