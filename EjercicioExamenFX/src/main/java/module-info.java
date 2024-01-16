module com.example.ejercicioexamenfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens com.example.ejercicioexamenfx to javafx.fxml;
    exports com.example.ejercicioexamenfx;
    exports controllers;
    opens controllers to javafx.fxml;
}