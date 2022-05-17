package modele;

public abstract class Item {
    private int id;
    private int maxQuantity;
    public Item(int id, int maxQuantity) {
        this.id = id;
        this.maxQuantity = maxQuantity;
    }

    public abstract void use();
}
