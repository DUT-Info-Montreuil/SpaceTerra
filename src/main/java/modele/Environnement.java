package modele;

import java.util.ArrayList;

public class Environnement {

    private ArrayList<Entity> entities;

    private Terrain terrain;

    public Environnement() {
        this.entities = new ArrayList<>();
        this.terrain = new Terrain("src/main/resources/Map/bigTest.json");

    }

    public Terrain getTerrain() {
        return this.terrain;
    }

    public void addEntities(Entity entity) {
        this.entities.add(entity);
    }

    public ArrayList<Entity> getEntities() {
        return this.entities;
    }




}
