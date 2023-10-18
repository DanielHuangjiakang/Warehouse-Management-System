package persistence;

import model.WareHouse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Item;
import org.json.*;

// Represents a reader that reads warehouse from JSON data stored in file
// This class uses a template created by CPSC210
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo).
// The original code has been modified for our specific use case.
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public WareHouse read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWareHouse(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses warehouse from JSON object and returns it
    private WareHouse parseWareHouse(JSONObject jsonObject) {
        String name = jsonObject.getString("Name");
        WareHouse wareHouse = new WareHouse(name);
        addItems(wareHouse, jsonObject);
        addItemRecords(wareHouse, jsonObject);
        return wareHouse;
    }

    // MODIFIES: wareHouse
    // EFFECTS: parses Items from JSON object and adds them to warehouse
    private void addItems(WareHouse wareHouse, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Items");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addItem(wareHouse, nextThingy);
        }
    }

    // MODIFIES: wareHouse
    // EFFECTS: parses ItemRecord from JSON object and adds them to warehouse
    private void addItemRecords(WareHouse wareHouse, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("ItemRecord");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addItemRecord(wareHouse, nextThingy);
        }
        wareHouse.changeIsDoneReadFromJson();
    }

    // MODIFIES: wareHouse
    // EFFECTS: parses item from JSON object and adds it to warehouse
    private void addItem(WareHouse wareHouse, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int quantity = jsonObject.getInt("quantity");
        String location = jsonObject.getString("location");
        Item item = new Item(name, quantity, location);
        wareHouse.addItem(item);
    }

    // MODIFIES: wareHouse
    // EFFECTS: parses itemRecord from JSON object and adds it to warehouse
    private void addItemRecord(WareHouse wareHouse, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int quantity = jsonObject.getInt("quantity");
        String location = jsonObject.getString("location");
        Item item = new Item(name, quantity, location);
        wareHouse.addItemRecord(item);
    }
}