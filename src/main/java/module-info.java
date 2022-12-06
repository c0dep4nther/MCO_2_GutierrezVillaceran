module com.myfarm.mco_2_gutierrezvillaceran {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.myfarm.mco_2_gutierrezvillaceran to javafx.fxml;
    exports com.myfarm.mco_2_gutierrezvillaceran.board;
    exports com.myfarm.mco_2_gutierrezvillaceran;
}