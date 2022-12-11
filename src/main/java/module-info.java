/**
 * opens, exports javafx.fml
 */
module com.myfarm.mco_2_gutierrezvillaceran {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.myfarm.mco_2_gutierrezvillaceran to javafx.fxml;
    exports com.myfarm.mco_2_gutierrezvillaceran.model;
    opens com.myfarm.mco_2_gutierrezvillaceran.model to javafx.fxml;
    exports com.myfarm.mco_2_gutierrezvillaceran.model.board;
    opens com.myfarm.mco_2_gutierrezvillaceran.model.board to javafx.fxml;
    exports com.myfarm.mco_2_gutierrezvillaceran;
}