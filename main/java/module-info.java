module com.example.rpnjava4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.kravets.rpnjava4 to javafx.fxml;
    exports com.kravets.rpnjava4;
}