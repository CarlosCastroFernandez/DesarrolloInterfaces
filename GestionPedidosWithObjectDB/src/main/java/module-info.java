module com.example.gestionpedidoswithobjectdb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javax.persistence;
    requires java.logging;
    requires java.naming;
    requires lombok;
    opens dao;
    opens clases;

    opens com.example.gestionpedidoswithobjectdb to javafx.fxml;
    exports com.example.gestionpedidoswithobjectdb;
}