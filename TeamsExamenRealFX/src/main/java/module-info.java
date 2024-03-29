module com.example.teamsexamenrealfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.swing;
    requires jasperreports;


    opens com.example.teamsexamenrealfx to javafx.fxml;
    exports com.example.teamsexamenrealfx;
}