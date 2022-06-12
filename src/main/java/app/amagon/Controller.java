package app.amagon;

import app.amagon.entities.InvoiceItem;
import app.amagon.entities.Order;
import app.amagon.entities.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import app.amagon.entities.Customer;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Objects;

public class Controller{
    @FXML
    public TableColumn<Customer,Integer> tbCustomerID;
    @FXML
    public TableColumn<Customer,String>  tbCustomerSurname;
    @FXML
    public TableColumn<Customer,String> tbCustomerName;
    @FXML
    public TableColumn<Customer,String> tbCustomerAddress;
    @FXML
    public TableColumn<Customer,String> tbCustomerCity;
    @FXML
    public TextField txfDeleteByID;
    @FXML
    public TextField txfSurname;
    @FXML
    public TextField txfAddress;
    @FXML
    public TextField txfName;
    @FXML
    public TextField txfCity;
    @FXML
    public ChoiceBox<Integer> cbCustomerID;
    @FXML
    public TextField txfSearchCustomerBySurname;
    @FXML
    public TableView<Product> productTable;
    @FXML
    public TableColumn<Product,Integer> tbProductID;
    @FXML
    public TableColumn<Product,String> tbProductName;
    @FXML
    public TableColumn<Product,String> tbProductCategory;
    @FXML
    public TableColumn<Product, BigDecimal> tbProductPrice;
    @FXML
    public TextField txfProductName;
    @FXML
    public TextField txfProductCategory;
    @FXML
    public TextField txfProductPrice;
    @FXML
    public BarChart<CategoryAxis, NumberAxis> barChart;
    @FXML
    public CategoryAxis yAxis;
    @FXML
    public NumberAxis xAxis;
    @FXML
    public TextField txfProductID;
    @FXML
    public Button btnDeleteProduct;
    @FXML
    public TableColumn<Product, Integer> tbProductQuantity;
    @FXML
    public TextField txfProductQuantity;
    @FXML
    public Label lbNumberProducts;
    @FXML
    public TextField txfCustomerNr;
    @FXML
    public TextField txfOrderNr;
    @FXML
    public Text txtCustomerNr;
    @FXML
    public Text txtOrderNr;
    @FXML
    public Text txtOrderDate;
    @FXML
    public Text txtTax;
    @FXML
    public TableColumn<InvoiceItem,Integer> tbPosition;
    @FXML
    public TableColumn<InvoiceItem,Integer> tbItemID;
    @FXML
    public TableColumn<InvoiceItem,Integer> tbItemDescription;
    @FXML
    public TableColumn<InvoiceItem,Integer> tbNumberItems;
    @FXML
    public TableColumn<InvoiceItem,Integer> tbPriceItem;
    @FXML
    public TableColumn<InvoiceItem,Integer> tbTotalPriceItem;
    @FXML
    public TableView<InvoiceItem> invoiceItemsTable;
    @FXML
    public Text txtTotalPriceOrder;
    @FXML
    public CheckBox cbHasOrder;
    @FXML
    public Text txtOrderNrCustomerScene;
    @FXML
    public Text txtOrderDateCustomerScene;
    @FXML
    public Text txtOrderTotalCustomerScene;
    @FXML
    double x,y;
    @FXML
    public Label lbNumberOrders;
    @FXML
    public Button btnCustomerAdd;
    @FXML
    public Button btnCustomerRefresh;
    @FXML
    public TableView<Customer> customerTable;
    @FXML
    private Label lbRegisteredCustomer;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;

    //  CHANGE TO MAIN SCENE
    public void mainScene(@NotNull ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/app/amagon/main.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        scene.getRoot().setOnMousePressed(evt ->{
            x = evt.getSceneX();
            y = evt.getSceneY();
        });
        scene.getRoot().setOnMouseDragged(evt ->{
            stage.setX(evt.getScreenX() - x);
            stage.setY(evt.getScreenY() - y);
        });
        stage.show();
    }

    //  CHANGE TO ORDER SCENE
    public void orderScene(@NotNull ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/app/amagon/bestellungen.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        scene.getRoot().setOnMousePressed(evt ->{
            x = evt.getSceneX();
            y = evt.getSceneY();
        });
        scene.getRoot().setOnMouseDragged(evt ->{
            stage.setX(evt.getScreenX() - x);
            stage.setY(evt.getScreenY() - y);
        });
        stage.show();
    }

    //  CHANGE TO CUSTOMERS SCENE
    public void customerScene(@NotNull ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/app/amagon/kunden.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        scene.getRoot().setOnMousePressed(evt ->{
            x = evt.getSceneX();
            y = evt.getSceneY();
        });
        scene.getRoot().setOnMouseDragged(evt ->{
            stage.setX(evt.getScreenX() - x);
            stage.setY(evt.getScreenY() - y);
        });
        stage.show();
    }

    public void productsScene(@NotNull ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/app/amagon/produkte.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        scene.getRoot().setOnMousePressed(evt ->{
            x = evt.getSceneX();
            y = evt.getSceneY();
        });
        scene.getRoot().setOnMouseDragged(evt ->{
            stage.setX(evt.getScreenX() - x);
            stage.setY(evt.getScreenY() - y);
        });
        stage.show();
    }

    public void exitProgram(@NotNull ActionEvent event) throws SQLException {
        final Node source = (Node)event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        DBUtil.dbDisconnect();
        stage.close();
    }

    public void addCustomerToDatabase() throws SQLException, ClassNotFoundException {
        DBUtil.dbConnect();
        try{
            DBUtil.addCustomer(txfSurname.getText(),txfName.getText(),txfAddress.getText(),txfCity.getText());
            this.refreshCustomerTable();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        DBUtil.dbDisconnect();
    }

    public void addProductToDatabase() throws SQLException, ClassNotFoundException {
        DBUtil.dbConnect();
        try{
            DBUtil.addProduct(txfProductName.getText(),txfProductCategory.getText(),txfProductPrice.getText(),txfProductQuantity.getText());
            this.refreshProductTable();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        DBUtil.dbDisconnect();
    }

    public void deleteCustomerFromDatabase() throws SQLException, ClassNotFoundException {
        DBUtil.dbConnect();
        try{
            DBUtil.deleteCustomerByID(txfDeleteByID.getText());
            this.refreshCustomerTable();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        DBUtil.dbDisconnect();
    }

    public void deleteProductFromDatabase() throws SQLException, ClassNotFoundException {
        DBUtil.dbConnect();
        try{
            DBUtil.deleteProductByID(txfProductID.getText());
            this.refreshProductTable();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        DBUtil.dbDisconnect();
    }

    public void refreshCustomerTable() throws SQLException, ClassNotFoundException {
        DBUtil.dbConnect();
        tbCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tbCustomerSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        tbCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tbCustomerCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        customerTable.setItems(DBUtil.getAllCustomersList());
        DBUtil.dbDisconnect();
        refreshCustomerChoiceList();
    }

    public void refreshProductTable() throws SQLException, ClassNotFoundException {
        DBUtil.dbConnect();
        tbProductID.setCellValueFactory(new PropertyValueFactory<>("productId"));
        tbProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        tbProductCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        tbProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tbProductQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        productTable.setItems(DBUtil.getProductList());
        DBUtil.dbDisconnect();
    }

    public void refreshCustomerChoiceList() throws SQLException, ClassNotFoundException {
        DBUtil.dbConnect();
        cbCustomerID.setItems(DBUtil.getCustomerIDs());
        DBUtil.dbDisconnect();
    }

    public void refreshBarChartProduct() throws SQLException, ClassNotFoundException {
        barChart.getData().clear();
        barChart.setLegendVisible(false);
        xAxis.setLabel("Anzahl Produkte");
        yAxis.setLabel("Kategorie");
        yAxis.tickLabelFontProperty().set(Font.font(9));
        XYChart.Series dataSeries = new XYChart.Series();
        DBUtil.dbConnect();
        HashMap<String,Integer> list = DBUtil.getListProductCategory();
        DBUtil.dbDisconnect();
        for (String key : list.keySet()){
            dataSeries.getData().add(new XYChart.Data(key, list.get(key)));
        }
        barChart.getData().add(dataSeries);
    }

    public void refreshDataMain() throws SQLException, ClassNotFoundException {
        DBUtil.dbConnect();
        lbRegisteredCustomer.setText(Integer.toString(DBUtil.getTotalCustomers()));
        DBUtil.dbDisconnect();
        DBUtil.dbConnect();
        lbNumberProducts.setText(Integer.toString(DBUtil.getTotalProducts()));
        DBUtil.dbDisconnect();
        DBUtil.dbConnect();
        lbNumberOrders.setText(Integer.toString(DBUtil.getTotalOrders()));
        DBUtil.dbDisconnect();
        refreshBarChartProduct();
    }

    public void searchCustomer() throws SQLException, ClassNotFoundException {
        DBUtil.dbConnect();
        try {
            if (!cbCustomerID.getSelectionModel().isEmpty()){
                DBUtil.getCustomerByID(String.valueOf(cbCustomerID.getSelectionModel().getSelectedItem()));
                customerTable.setItems(DBUtil.getCustomersList());
                for (Customer c : DBUtil.getAllCustomersList()){
                    if (c.getCustomerId() == cbCustomerID.getSelectionModel().getSelectedItem()){
                        txfSearchCustomerBySurname.setText(c.getSurname());
                    }
                }
            } else if (cbCustomerID.getSelectionModel().isEmpty() && !txfSearchCustomerBySurname.getText().isEmpty()) {
                DBUtil.getCustomerBySurname(txfSearchCustomerBySurname.getText());
            }else {
                refreshCustomerTable();
            }
            if (cbHasOrder.isSelected() && !cbCustomerID.getSelectionModel().isEmpty()){
                for(Order order : DBUtil.getOrderListByCustomerID(String.valueOf(cbCustomerID.getSelectionModel().getSelectedItem()))){
                    if (order.getCustomerId() == cbCustomerID.getSelectionModel().getSelectedItem()){
                        txtOrderNrCustomerScene.setText(String.valueOf(order.getOrderId()));
                        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                        txtOrderDateCustomerScene.setText(dateFormat.format(order.getOrderDate()));
                        DBUtil.dbConnect();
                        txtOrderTotalCustomerScene.setText(String.valueOf(DBUtil.getTotalInvoicePrice(order.getCustomerId())) + " €");
                        DBUtil.dbDisconnect();
                    }
                    else{
                        break;
                    }
                }
            }
        }catch(Exception ignored){}
        DBUtil.dbDisconnect();
    }

    public void searchOrder() throws SQLException, ClassNotFoundException {
        Order customerOrder = new Order();
        if (!txfCustomerNr.getText().isEmpty()){
            DBUtil.dbConnect();
            DBUtil.getOrderByCustomerID(txfCustomerNr.getText());
            for (Order order : DBUtil.getOrderListByCustomerID(txfCustomerNr.getText())){
                customerOrder = order;
            }
            DBUtil.dbDisconnect();
        }
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        txtOrderDate.setText(dateFormat.format(customerOrder.getOrderDate()));
        txtOrderNr.setText(String.valueOf(customerOrder.getOrderId()));
        txtCustomerNr.setText(String.valueOf(customerOrder.getCustomerId()));
        txfOrderNr.setText(String.valueOf(customerOrder.getOrderId()));
        DBUtil.dbConnect();
        txtTotalPriceOrder.setText(String.valueOf(DBUtil.getTotalInvoicePrice(customerOrder.getCustomerId())) + " €");
        DBUtil.dbDisconnect();
        showInvoiceItems();
    }

    public void showInvoiceItems() throws SQLException, ClassNotFoundException {
        tbPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        tbItemID.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        tbItemDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        tbNumberItems.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tbPriceItem.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        tbTotalPriceItem.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        invoiceItemsTable.setItems(DBUtil.getInvoiceItemsListByCustomerID(txfCustomerNr.getText()));
    }

    public void saveEditProductToDatabase() throws SQLException, ClassNotFoundException, NumberFormatException{
        DBUtil.dbConnect();
        try{
            try{
                DBUtil.dbConnect();
                DBUtil.editProduct(Integer.parseInt(txfProductID.getText()),
                        txfProductName.getText(),
                        txfProductCategory.getText(),
                        txfProductPrice.getText(),
                        Integer.parseInt(txfProductID.getText()));
                DBUtil.dbDisconnect();
            }catch(NullPointerException ignored){}
            this.refreshProductTable();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        DBUtil.dbDisconnect();
    }

    public void clickRowCustomer() {
        try{
            txfSurname.setText(customerTable.getSelectionModel().getSelectedItem().getSurname());
            txfName.setText(customerTable.getSelectionModel().getSelectedItem().getName());
            txfAddress.setText(customerTable.getSelectionModel().getSelectedItem().getAddress());
            txfCity.setText(customerTable.getSelectionModel().getSelectedItem().getCity());
            txfSearchCustomerBySurname.setText(customerTable.getSelectionModel().getSelectedItem().getSurname());
            cbCustomerID.getSelectionModel().select(customerTable.getSelectionModel().getSelectedItem().getCustomerId()-1);
            showInvoiceItems();
        }catch(NullPointerException | SQLException | ClassNotFoundException ignored){}
    }

    public void clickRowProduct(){
        try{
            txfProductName.setText(productTable.getSelectionModel().getSelectedItem().getProductName());
            txfProductCategory.setText(productTable.getSelectionModel().getSelectedItem().getCategory());
            txfProductPrice.setText(String.valueOf(productTable.getSelectionModel().getSelectedItem().getPrice()));
            txfProductID.setText(String.valueOf(productTable.getSelectionModel().getSelectedItem().getProductId()));
            txfProductQuantity.setText(String.valueOf(productTable.getSelectionModel().getSelectedItem().getQuantity()));
        }catch(NullPointerException ignored){}
    }
}