package app.amagon;

import app.amagon.entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class DBUtil {

    private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    private static Connection db_connection;

    private static PreparedStatement p_stmt;

    private static ResultSet rs;

    private static ObservableList<Customer> customerList;

    private static ObservableList<Product> productList;

    private static ObservableList<Order> orderList;

    private static List<OrderItem> orderItemsList;

    private static ObservableList<InvoiceItem> invoiceItemsList;

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

    public static int getTotalOrders() throws SQLException {
        int total = 0;
        try {
            if (!db_connection.isClosed()) {
                p_stmt = db_connection.prepareStatement("select [amagon].[getTotalOrders]() as total");
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

    public static void getCustomerBySurname(String sName) throws SQLException {
        customerList.clear();
        try{
            if (!db_connection.isClosed()) {
                p_stmt = db_connection.prepareStatement("exec [amagon].[getCustomerBySurname] ?");
                p_stmt.setString(1,sName);
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

    private static List<OrderItem> getOrderItemsList(String orderID) throws SQLException, ClassNotFoundException {
        dbConnect();
        getOrderItemsByOrderID(orderID);
        dbDisconnect();
        return orderItemsList;
    }

    public static void getOrderItemsByOrderID(String orderID) throws SQLException {
        orderItemsList = FXCollections.observableArrayList();
        orderItemsList.clear();
        try{
            if (!db_connection.isClosed()) {
                p_stmt = db_connection.prepareStatement("exec [amagon].[getOrderItemsByOrderID] ?");
                p_stmt.setInt(1,Integer.parseInt(orderID));
                rs = p_stmt.executeQuery();
                while (rs.next()) {
                    OrderItem orderItem = new OrderItem(
                            rs.getInt("item_id"),
                            rs.getInt("order_id"),
                            rs.getInt("product_id"),
                            rs.getInt("quantity"),
                            rs.getDouble("price"));
                    orderItemsList.add(orderItem);
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

    public static ObservableList<Order> getOrderListByCustomerID(String customerID) throws SQLException, ClassNotFoundException {
        dbConnect();
        getOrderByCustomerID(customerID);
        dbDisconnect();
        return orderList;
    }

    public static void getOrderByCustomerID(String customerID) throws SQLException {
        orderList = FXCollections.observableArrayList();
        orderList.clear();
        try{
            if (!db_connection.isClosed()) {
                p_stmt = db_connection.prepareStatement("exec [amagon].[getOrderByCustomerID] ?");
                p_stmt.setString(1,customerID);
                rs = p_stmt.executeQuery();
                while (rs.next()) {
                    Order order = new Order(
                            rs.getInt("order_id"),
                            rs.getDate("order_date"),
                            rs.getInt("customer_id")
                    );
                    orderList.add(order);
                }
                for (Order order : orderList){
                    order.setOrderItemsByOrderId(getOrderItemsList(String.valueOf(order.getOrderId())));
                }
            }
            else{
                System.out.println("Es besteht keine Verbindung mit der Datenbank");
            }
        }catch (SQLException e){
            System.out.println("Problem beim Ausführen von Query.");
            e.printStackTrace();
            throw e;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
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

    public static ObservableList<InvoiceItem> getInvoiceItemsListByCustomerID(String customerID) throws SQLException, ClassNotFoundException {
        dbConnect();
        getInvoiceItemsByCustomerID(customerID);
        dbDisconnect();
        return invoiceItemsList;
    }

    public static void getInvoiceItemsByCustomerID(String customerID) throws SQLException {
        invoiceItemsList = FXCollections.observableArrayList();
        //invoiceItemsList.clear();
        try{
            if (!db_connection.isClosed()) {
                p_stmt = db_connection.prepareStatement("exec [amagon].[getInvoiceItemsByCustomer] ?");
                p_stmt.setString(1,customerID);
                rs = p_stmt.executeQuery();
                while (rs.next()) {
                    InvoiceItem invoiceItem = new InvoiceItem(
                            rs.getInt("Position"),
                            rs.getInt("Artikelnummer"),
                            rs.getString("Artikelbezeichnung"),
                            rs.getInt("Anzahl"),
                            rs.getDouble("Listenpreis"),
                            rs.getDouble("Gesamtbetrag")

                    );
                    invoiceItemsList.add(invoiceItem);
                }
                for (Order order : orderList){
                    order.setOrderItemsByOrderId(getOrderItemsList(String.valueOf(order.getOrderId())));
                }
            }
            else{
                System.out.println("Es besteht keine Verbindung mit der Datenbank");
            }
        }catch (SQLException e){
            System.out.println("Problem beim Ausführen von Query.");
            e.printStackTrace();
            throw e;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
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