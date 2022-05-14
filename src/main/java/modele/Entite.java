package modele;

abstract class Entite {

    private int vie;

    private int vitesse;

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public Entite(int vie, int vitesse){
        this.vie = vie;
        this.vitesse = vitesse;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }
}
