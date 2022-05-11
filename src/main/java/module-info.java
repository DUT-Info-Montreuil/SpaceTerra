module les_gens.spaceterra {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens les_gens.spaceterra to javafx.fxml;
    exports les_gens.spaceterra;
}