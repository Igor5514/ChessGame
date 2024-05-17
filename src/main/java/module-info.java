module com.example.cs230pz {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cs230pz to javafx.fxml;
    exports com.example.cs230pz;
}