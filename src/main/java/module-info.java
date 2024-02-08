module com.zhuhaievych.coursework {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires com.fasterxml.jackson.databind;


    opens com.zhuhaievych.coursework to javafx.fxml;
    opens com.zhuhaievych.coursework.controllers to javafx.fxml;
    exports com.zhuhaievych.coursework;
}