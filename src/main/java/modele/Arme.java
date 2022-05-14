package modele;

public class Arme extends Outil {
    private int degats;
    public Arme(int puissance, int id, String nom) {
        super(puissance, id, nom);
    }

    @Override
    public void utiliser() {

    }
}
