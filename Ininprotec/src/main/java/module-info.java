module com.example.ininprotec {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires javafx.web;
    requires javafx.swing;
    requires jasperreports;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires java.net.http;
    requires java.mail;
    requires activation;


    opens imagenes;
    opens clase;
    opens com.example.ininprotec to javafx.fxml;
    exports com.example.ininprotec;
}