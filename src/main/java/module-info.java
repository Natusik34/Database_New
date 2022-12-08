module com.example.database {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires activation;
    requires java.mail;


    opens com.example.database to javafx.fxml;
    exports com.example.database;
}