module com.example.examenpracticamarzojavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.swing;
    requires jasperreports;
    opens clase;
    opens conexion;
    opens dao;


    opens com.example.examenpracticamarzojavafx to javafx.fxml;
    exports com.example.examenpracticamarzojavafx;
}