package modele;

public class NotEnoughItemInSlotException extends Exception {

    public NotEnoughItemInSlotException(int slotNumber){
        super("You tried to remove too many items from the slot " + slotNumber);
    }

}
