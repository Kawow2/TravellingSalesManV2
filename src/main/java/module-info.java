module com.example.travellingsalesmanv3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.travellingsalesmanv3 to javafx.fxml;
    exports com.example.travellingsalesmanv3;
}