module org.apps.advancewars {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens org.apps.advancewars to javafx.fxml;
    exports org.apps.advancewars;
    exports org.apps.advancewars.controllers;
    opens org.apps.advancewars.controllers to javafx.fxml;
}