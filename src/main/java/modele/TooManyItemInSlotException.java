package modele;

public class TooManyItemInSlotException extends Exception {

    public TooManyItemInSlotException(int slotNum){
        super("You tried to add too many items in the slot " + slotNum);
    }
}
