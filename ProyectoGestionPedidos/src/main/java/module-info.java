module com.example.proyectogestionpedidos {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proyectogestionpedidos to javafx.fxml;
    exports com.example.proyectogestionpedidos;
}