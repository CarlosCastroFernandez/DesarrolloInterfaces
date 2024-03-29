module com.example.examenfinalfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jasperreports;
    requires javafx.swing;


    opens com.example.examenfinalfx to javafx.fxml;
    exports com.example.examenfinalfx;
}