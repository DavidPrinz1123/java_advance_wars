module org.apps.advancewars {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens org.apps.advancewars to javafx.fxml;
    exports org.apps.advancewars;
}