module les_gens.spaceterra {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens les_gens.spaceterra to javafx.fxml;
    exports les_gens.spaceterra;
}