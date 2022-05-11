import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.Map;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Map map = new Map("src/main/resources/Map/Test.json");

        Group root = new Group();
        Scene scene = new Scene(root, 600,600, Color.LIGHTSKYBLUE);

        stage.setScene(scene);
        stage.show();
    }
}
