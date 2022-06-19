package modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environment {

    private ObservableList<Entity> entities;
    private JsonGameLoader loader;
    public static Terrain terrain;
    public static Player player;


    public Environment(String mapPath) {
        entities = FXCollections.observableArrayList();
        loader = new JsonGameLoader(mapPath);
        this.terrain = new Terrain(loader);
    }

    public void init() {
        player = new Player(3500, 2030, terrain);
        Moobius moobius = new Moobius(terrain,3190, 2030);
        Bingus bingus = new Bingus(15000, 2030, terrain);
        Florb florb = new Florb(10000, 1980, terrain);
        Bib bib = new Bib(5000, 2030, terrain);
        this.getEntities().add(player);
        this.getEntities().add(bingus);
        this.getEntities().add(florb);
        this.getEntities().add(bib);
        this.getEntities().add(moobius);
    }

    public JsonGameLoader getLoader() {
        return loader;
    }

    public ObservableList<Entity> getEntities() {
        return this.entities;
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    public void deleteEntity(Entity entity) {
        this.entities.remove(entity);
    }
}
