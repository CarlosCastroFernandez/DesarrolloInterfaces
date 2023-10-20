module com.example.bienvenidofx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bienvenidofx to javafx.fxml;
    exports com.example.bienvenidofx;
}