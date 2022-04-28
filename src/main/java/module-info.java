module com.example.restaurantorders {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.microsoft.sqlserver.jdbc;
    exports com.example.restaurantorders;
    opens com.example.restaurantorders to javafx.fxml;
}