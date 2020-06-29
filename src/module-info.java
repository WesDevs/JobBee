module JobBee {
    requires javafx.controls;
    requires java.sql;
    requires javafx.fxml;
    requires sqlite.jdbc;

    exports com.wesdev.JobBee.Controllers;

    opens com.wesdev.JobBee;
    opens com.wesdev.JobBee.models;
    opens com.wesdev.JobBee.Controllers;
}