package modele;

public class Tool extends Item{

    private int pickPower;
    private int axePower;

    public Tool(int id) {
        super(id, 1);
    }

    @Override
    public void use() {
        //break block
    }
}
