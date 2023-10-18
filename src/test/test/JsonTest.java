package test;

import model.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;

// represent the tests for Json
// This class uses a template created by CPSC210
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo).
// The original code has been modified for our specific use case.
public class JsonTest {
    protected void checkItem(String name, int quantity, String location, Item item) {
        assertEquals(name, item.getName());
        assertEquals(quantity, item.getQuantity());
        assertEquals(location, item.getLocation());
    }
}