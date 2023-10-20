module com.example.recetariococinafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens com.example.recetariococinafx to javafx.fxml;
    exports com.example.recetariococinafx;
}