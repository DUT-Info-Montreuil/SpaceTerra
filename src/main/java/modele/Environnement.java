package modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {

    private ObservableList<Entity> entities;

    private Terrain terrain;

    public Environnement() {
        this.entities = FXCollections.observableArrayList();
        this.terrain = new Terrain("src/main/resources/Map/bigTest.json");

    }

    public Terrain getTerrain() {
        return this.terrain;
    }

    public void addEntities(Entity entity) {
        this.entities.add(entity);
    }

    public ObservableList<Entity> getEntities() {
        return this.entities;
    }

    public void deleteEntity(Entity entity) {
        this.getEntities().remove(entity);
    }
}
