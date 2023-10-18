package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a warehouse having a collection of items and item records
// This class uses a template created by CPSC210
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo).
// The original code has been modified for our specific use case.
public class WareHouse implements Writable {
    private boolean isDoneReadFromJson;
    private String name;
    private ArrayList<Item> items;
    private ArrayList<Item> itemRecords;

    // EFFECTS: constructs warehouse with a name and empty list of items and item records
    public WareHouse(String name) {
        this.name = name;
        items = new ArrayList<>();
        itemRecords = new ArrayList<>();
        isDoneReadFromJson = false;
    }

    public String getName() {
        return name;
    }

    // MODIFIES: isDoneReadFromJson
    // EFFECTS: change the isDoneReadFromJson to true
    public void changeIsDoneReadFromJson() {
        isDoneReadFromJson = true;
    }

    // MODIFIES: this
    // EFFECTS: adds item to this warehouse
    public void addItem(Item item) {
        items.add(item);
    }

    // MODIFIES: this
    // EFFECTS: adds itemRecord to this warehouse
    public void addItemRecord(Item item) {
        if (isDoneReadFromJson) {
            if (item.getLocation().equals("+")) {
                EventLog.getInstance().logEvent(new Event(item.getQuantity() + " " + item.getName()
                        + " added to warehouse"));
            } else {
                EventLog.getInstance().logEvent(new Event(item.getQuantity() + " " + item.getName()
                        + " removed from warehouse"));
            }
        }
        itemRecords.add(item);
    }

    // EFFECTS: returns an unmodifiable list of items in this warehouse
    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    // EFFECTS: returns an unmodifiable list of item records in this warehouse
    public List<Item> getItemRecords() {
        return Collections.unmodifiableList(itemRecords);
    }

    // MODIFIES: this
    // EFFECTS: remove the item at the specified location
    public void removeItem(int i) {
        items.remove(i);
    }

    // EFFECTS: returns number of items in this warehouse
    public int numItems() {
        return items.size();
    }

    // EFFECTS: returns number of item records in this warehouse
    public int numItemRecord() {
        return itemRecords.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name);
        json.put("Items", itemsToJson());
        json.put("ItemRecord", itemRecordToJson());
        return json;
    }

    // EFFECTS: returns item records in this warehouse as a JSON array
    private JSONArray itemRecordToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item t : itemRecords) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns items in this warehouse as a JSON array
    private JSONArray itemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item t : items) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }
}


