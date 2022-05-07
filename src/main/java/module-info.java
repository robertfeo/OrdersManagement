module app.amagon {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.microsoft.sqlserver.jdbc;
    //requires java.sql;
    requires java.persistence;
    requires jakarta.persistence;
    exports app.amagon;
    opens app.amagon to javafx.fxml;
}