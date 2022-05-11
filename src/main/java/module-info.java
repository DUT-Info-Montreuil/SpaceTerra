module les_gens.spaceterra {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires java.desktop;


    opens les_gens.spaceterra to javafx.fxml;
    exports les_gens.spaceterra;
}