module rupizza {
    requires javafx.controls;
    requires javafx.fxml;

    opens rupizza to javafx.fxml;
    exports rupizza;
}
