package vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modele.Entity;

import java.util.HashMap;

public class EntityView {

    private HashMap<String, Image> images;
    private Entity ent;
    private ImageView imgView;

    public EntityView(Entity entity){
        ent = entity;
        imgView = new ImageView();
        images = new HashMap<>();
        //idle = new Image(String.valueOf(getClass().getResource("/Sprite/Enemies/" + ent.getClass().getName())));
        for(String state : ent.getStates()){
            try{
                images.put(state, new Image(String.valueOf(getClass().getResource("/Sprites/Enemies/" + ent.getClass().getSimpleName() + "/" + ent.getClass().getSimpleName() + "_" + state + ".gif"))));
            }catch (IllegalArgumentException e){
                System.out.println("Couldn't find Image");
            }
        }
        System.out.println(images.size());
    }
}