package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an item with its name, quantity, and location.
public class Item implements Writable {
    private String name;     // name of the item
    private int quantity;    // quantity of the item
    private String location; // location of the item

    // REQUIRES: name has a non-zero length, quantity > 0, location has a non-zero length, quantity.
    // EFFECTS: name of the item is set to name; quantity of the item is a
    // positive integer set to quantity; location of the item is set to location.
    public Item(String name, int quantity, String location) {
        this.name = name;
        this.quantity = quantity;
        this.location = location;
    }

    // REQUIRES: name has a non-zero length.
    // EFFECTS: produce the name of the item.
    public String getName() {
        return name;
    }

    // REQUIRES: quantity > 0
    // EFFECTS: produce the quantity of the item
    public int getQuantity() {
        return quantity;
    }

    // REQUIRES: location has a non-zero length, quantity.
    // EFFECTS: produce the location of the item
    public String getLocation() {
        return location;
    }

    // REQUIRES: newQuantity > 0
    // EFFECTS: add quantity of the item
    public void addQuantity(int newQuantity) {
        quantity += newQuantity;
    }

    // REQUIRES:  0 < newQuantity < quantity
    // EFFECTS: reduce quantity of the item
    public void reduceQuantity(int newQuantity) {
        quantity -= newQuantity;
    }


    // MODIFIES: this
    // EFFECTS: true if the quantity of the item is equal to the quantity we want to move,
    // false otherwise
    public boolean isNoMore(int quantityToRemove) {
        return quantityToRemove == quantity;
    }

    // MODIFIES: this
    // EFFECTS: true if the quantity of the item is greater than the quantity we want to move,
    // false otherwise
    public boolean isEnough(int quantityToRemove) {
        return quantityToRemove < quantity;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("quantity", quantity);
        json.put("location", location);
        return json;
    }
}
