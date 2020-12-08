////////////////////////////////////////////////////////////////////
// [Damiano] [Mason] [1201160]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

public class MenuItem {
    public enum items { Gelato, Budino, Bevanda }
    private items itemType;
    private String name;
    private double price;

    public MenuItem(String n, items t, double p){
        this.itemType = t;
        this.name = n;
        this.price = p;
    }

    public items getType(){
        return itemType;
    }

    public double getPrice(){
        return price;
    }
}
