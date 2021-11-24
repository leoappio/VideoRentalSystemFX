module com.example.locadoradevideofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.locadoradevideofx to javafx.fxml;
    opens com.example.locadoradevideofx.controller to javafx.fxml;
    exports com.example.locadoradevideofx;
}