package vue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import modele.Entity;

import java.util.HashMap;

public class EntityView {

    private HashMap<String, Image> images;
    private Entity ent;
    private ImageView imgView;
    private Timeline timeline;
    private String currAction = "";
    private Pane pane;

    public EntityView(Entity entity, Pane pane){
        ent = entity;
        imgView = new ImageView();
        images = new HashMap<>();
        for(String state : ent.getActions()){
            try{
                images.put(state, new Image(String.valueOf(getClass().getResource("/Sprites/Entities/" + ent.getClass().getSimpleName() + "/" + ent.getClass().getSimpleName() + "_" + state + ".gif"))));
            }catch (IllegalArgumentException e){
                System.out.println("Couldn't find Image");
            }
        }
        this.imgView.xProperty().bind(ent.getHitbox().getX().subtract(images.get("idle").getWidth()/2 - ent.getHitbox().getWidth() / 2));
        this.imgView.yProperty().bind(ent.getHitbox().getY().subtract(images.get("idle").getHeight() - ent.getHitbox().getHeight()));
        System.out.println(images.size());
        pane.getChildren().add(imgView);
        startTimeline();
    }

    public void startTimeline(){
        timeline = new Timeline(new KeyFrame(Duration.millis(16.33), actionEvent -> {
            if(!currAction.equals(ent.getAction())){
                currAction = ent.getAction();
                imgView.setImage(images.get(currAction));
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}