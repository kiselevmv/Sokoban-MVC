module com.javalessons.sokoban {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.javalessons.sokoban to javafx.fxml;
    exports com.javalessons.sokoban;
}
