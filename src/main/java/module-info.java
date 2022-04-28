module app.restaurantorders {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.microsoft.sqlserver.jdbc;
    requires java.sql;
    exports app.restaurantorders;
    opens app.restaurantorders to javafx.fxml;
}