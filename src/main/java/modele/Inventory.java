package modele;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items;
    private int currSlot;
    private int nbSlot;

    public Inventory() {
        this.items = new ArrayList<>(10);
        this.currSlot = 0;
        this.nbSlot = 10;
    }


    public int getCurrSlot() {
        return currSlot;
    }

    public int getNbSlot() {
        return nbSlot;
    }

    public Item getCurrItem(){
        try{
            return items.get(currSlot);
        } catch (Exception e){
            return null;
        }
    }

    public Item getItemFromSlot(int numSlot){
        try{
            return items.get(numSlot);
        } catch (Exception e){
            return null;
        }
    }

    public void setNbSlot(int nbSlot){
        this.nbSlot = nbSlot;
    }

    public void setCurrSlot(int currSlot){
        this.currSlot = currSlot;
    }

    public void incrementSlot(){
        if(this.currSlot < this.nbSlot){
            currSlot++;
        }
    }
    public void decrementSlot(){
        if(this.currSlot > 0){
            currSlot--;
        }
    }
    public boolean isInventoryFull(){
        if(items.size() >= nbSlot){
            return true;
        }
        return false;
    }
    public Item removeFromSlot(){
        try {
            items.get(currSlot);
            Item item = items.remove(currSlot);
            items.add(currSlot, null);
            return item;
        } catch (IndexOutOfBoundsException e){
            System.out.println("slot vide !");
            return null;
        }
    }

    public void addIntoSlot(Item item){
        if(!isInventoryFull()){
            items.add(item);
        }
        else{
            System.out.println("inventaire plein !");
        }
    }

}
