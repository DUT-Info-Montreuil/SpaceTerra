package vue;

import javafx.scene.Node;
import javafx.scene.ParallelCamera;

public class GameCam extends ParallelCamera {

    public GameCam(){
        super();
    }

    public void lookAt(Node node){
        this.layoutXProperty().bind(node.layoutXProperty());
        this.layoutYProperty().bind(node.layoutYProperty());
    }
}
