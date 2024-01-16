module com.example.hibernatewithfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;

    opens com.example.hibernatewithfx to javafx.fxml;
    opens com.example.hibernatewithfx.controllers to javafx.fxml;
    opens com.example.hibernatewithfx.domain.usuario;
    opens com.example.hibernatewithfx.domain.juego;
    exports com.example.hibernatewithfx;
    exports com.example.hibernatewithfx.controllers;

}