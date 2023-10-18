module com.example.proyectogestionpedidos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;


    opens com.example.proyectogestionpedidos to javafx.fxml;
    exports com.example.proyectogestionpedidos;
}