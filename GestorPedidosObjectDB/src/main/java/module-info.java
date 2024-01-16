module app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javax.persistence;
    requires java.naming;
    requires lombok;
    requires java.logging;
    requires java.sql; // necesario para Date en JPA


    opens app to javafx.fxml;
    opens domain.producto;
    opens domain.pedido;
    opens domain.item;

    exports app;
    exports controllers;
    opens controllers to javafx.fxml;
    exports domain;
    opens domain to javafx.fxml;
}