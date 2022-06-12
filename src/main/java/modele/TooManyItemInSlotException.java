package modele;

public class TooManyItemInSlotException extends Exception {

    public TooManyItemInSlotException(int slotNum){
        super("You try to add too many items in the slot " + slotNum);
    }
}
