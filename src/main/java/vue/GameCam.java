package vue;

import javafx.scene.Node;
import javafx.scene.ParallelCamera;

public class GameCam {

    private ParallelCamera gameCam;

    public GameCam(){
        gameCam = new ParallelCamera();
    }

    public ParallelCamera getGameCam() {
        return gameCam;
    }

    public void lookAt(Node node){
        gameCam.layoutXProperty().bind(node.layoutXProperty());
        gameCam.layoutYProperty().bind(node.layoutYProperty());
    }
}
