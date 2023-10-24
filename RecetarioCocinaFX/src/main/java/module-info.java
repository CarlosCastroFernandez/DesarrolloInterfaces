module com.example.recetariococinafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires javafx.media;

    opens com.example.recetariococinafx.audio;
    opens com.example.recetariococinafx.imagenes;
    opens com.example.recetariococinafx to javafx.fxml;
    exports com.example.recetariococinafx;
}