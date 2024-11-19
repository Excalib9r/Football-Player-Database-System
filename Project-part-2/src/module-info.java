module myjfx {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    opens sample to javafx.graphics, javafx.fxml, javafx.base;
    opens server to javafx.graphics, javafx.fxml, javafx.base;
    opens util to javafx.graphics, javafx.fxml, javafx.base;
    opens images to javafx,javafx.graphics, javafx.fxml, javafx.base;
}