import javafx.application.Application;
import javafx.stage.Stage;
import modele.Map;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Map map = new Map("src/main/resources/Map/Test.json");
    }
}
