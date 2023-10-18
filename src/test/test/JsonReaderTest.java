package test;

import model.Item;
import model.WareHouse;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// represent the tests for JsonReader
// This class uses a template created by CPSC210
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo).
// The original code has been modified for our specific use case.
public class JsonReaderTest extends JsonTest{
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            WareHouse wareHouse = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWareHouse() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWareHouse.json");
        try {
            WareHouse wareHouse = reader.read();
            assertEquals("Daniels Warehouse", wareHouse.getName());
            assertEquals(0, wareHouse.numItems());
            assertEquals(0, wareHouse.numItemRecord());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWareHouse() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWareHouse.json");
        try {
            WareHouse wareHouse = reader.read();
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
            fail("Couldn't read from file");
        }
    }
}
