package modele;

abstract class Ennemis extends Entite{

    int attaque;
    boolean volant;
    boolean explosive;

    public Ennemis(int vie, int vitesse, int attaque, boolean volant, boolean explosive){
        super(vie, vitesse);
        this.attaque = attaque;
        this.volant = volant;
        this.explosive = explosive;
    }

    public static void mouvement(){

    }

}
