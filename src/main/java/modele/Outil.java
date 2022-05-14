package modele;

public abstract class Outil {
    private int puissance;
    private int id;
    private String nom;


    public Outil(int puissance, int id, String nom) {
        this.puissance = puissance;
        this.id = id;
        this.nom = nom;
    }

    public abstract void utiliser();
}
