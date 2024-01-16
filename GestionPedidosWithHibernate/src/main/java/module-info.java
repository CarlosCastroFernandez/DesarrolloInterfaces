module com.example.gestionpedidoswithhibernate {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires javafx.swing;
    requires jasperreports;

    opens clases;
    opens com.example.gestionpedidoswithhibernate to javafx.fxml;
    exports com.example.gestionpedidoswithhibernate;
}