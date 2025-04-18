module com.mil.tran {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires transitive java.sql;

    opens com.mil.tran to javafx.fxml;
    opens com.mil.tran.Controllers to javafx.fxml;

    exports com.mil.tran;
}
