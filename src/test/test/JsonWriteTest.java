package test;

import model.Event;
import model.EventLog;
import model.Item;
import model.WareHouse;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// represent the tests for JsonWriter
// This class uses a template created by CPSC210
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo).
// The original code has been modified for our specific use case.
public class JsonWriteTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            WareHouse wareHouse = new WareHouse("Daniels Warehouse");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWareHouse() {
        try {
            WareHouse wareHouse = new WareHouse("Daniels Warehouse");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWarehouse.json");
            writer.open();
            writer.write(wareHouse);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWarehouse.json");
            wareHouse = reader.read();
            assertEquals("Daniels Warehouse", wareHouse.getName());
            assertEquals(0, wareHouse.numItems());
            assertEquals(0, wareHouse.numItemRecord());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWarehouse() {
        try {
            WareHouse wareHouse = new WareHouse("Daniels Warehouse");
            wareHouse.addItem(new Item("apple", 100, "A"));
            wareHouse.addItem(new Item("pear", 250, "B"));
            wareHouse.addItem(new Item("tube", 400, "C"));
            wareHouse.addItemRecord(new Item("apple", 200, "+"));
            wareHouse.addItemRecord(new Item("pear", 300, "+"));
            wareHouse.addItemRecord(new Item("tube", 400, "+"));
            wareHouse.addItemRecord(new Item("apple", 100, "-"));
            wareHouse.addItemRecord(new Item("pear", 50, "-"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWareHouse.json");
            writer.open();
            writer.write(wareHouse);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWareHouse.json");
            wareHouse = reader.read();
            assertEquals("Daniels Warehouse", wareHouse.getName());
            List<Item> items = wareHouse.getItems();
            assertEquals(3, items.size());
            List<Item> itemRecords = wareHouse.getItemRecords();
            assertEquals(5, itemRecords.size());
            checkItem("apple", 100, "A", items.get(0));
            checkItem("pear", 250, "B", items.get(1));
            checkItem("tube", 400, "C", items.get(2));
            checkItem("apple", 200, "+", itemRecords.get(0));
            checkItem("pear", 300, "+", itemRecords.get(1));
            checkItem("tube", 400, "+", itemRecords.get(2));
            checkItem("apple", 100, "-", itemRecords.get(3));
            checkItem("pear", 50, "-", itemRecords.get(4));

            wareHouse.removeItem(0);
            assertEquals(2, items.size());
            assertEquals("pear", items.get(0).getName());
            assertEquals("tube", items.get(1).getName());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
