module com.senai.academiasul {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.senai.academiasul to javafx.fxml;
    exports com.senai.academiasul;
}