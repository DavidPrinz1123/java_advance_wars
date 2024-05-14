module org.apps.java_advance_wars {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.apps.java_advance_wars to javafx.fxml;
    exports org.apps.java_advance_wars;
}