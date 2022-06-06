package vue;

import javafx.scene.image.Image;
import modele.Entity;

import java.util.HashMap;

public class EntityView {

    private String[] STATES = {"idle", "walk", "attack", "flying", "run"};
    private HashMap<String, Image> images;
    private Entity ent;

    public EntityView(Entity entity){
        ent = entity;
        images = new HashMap<>();
        //idle = new Image(String.valueOf(getClass().getResource("/Sprite/Enemies/" + ent.getClass().getName())));
        for(int i = 0; i < STATES.length; i++){
            try{
                System.out.println("/Sprite/Enemies/" + ent.getClass().getSimpleName() + "/" + ent.getClass().getSimpleName() + "_" + STATES[i] + ".gif");
                images.put(STATES[i], new Image(String.valueOf(getClass().getResource("/Sprite/Enemies/" + ent.getClass().getSimpleName() + "/" + ent.getClass().getSimpleName() + "_" + STATES[i] + ".gif"))));
            }catch (Exception e){
                System.out.println("Couldn't find Image");
            }
        }
        System.out.println(images.size());
    }
}