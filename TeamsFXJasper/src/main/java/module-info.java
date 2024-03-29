module com.example.teamsfxjasper {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jasperreports;
    requires javafx.swing;
    opens errores;
    opens utils;
    opens clase;

    opens com.example.teamsfxjasper to javafx.fxml;
    exports com.example.teamsfxjasper;
}