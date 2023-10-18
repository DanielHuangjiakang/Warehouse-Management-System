package test;

import model.Event;
import model.EventLog;
import model.Item;
import model.WareHouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// represent the Unit tests for the EventLog class
// This class uses a template created by CPSC210
// (https://github.students.cs.ubc.ca/CPSC210/AlarmSystem).
// The original code has been modified for our specific use case.
public class EventLogTest {
    private Event e1;
    private Event e2;
    private Event e3;

    @BeforeEach
    public void loadEvents() {
        e1 = new Event("A1");
        e2 = new Event("A2");
        e3 = new Event("A3");
        EventLog el = EventLog.getInstance();
        el.logEvent(e1);
        el.logEvent(e2);
        el.logEvent(e3);
    }

    @Test
    public void testLogEvent() {
        List<Event> l = new ArrayList<Event>();

        EventLog el = EventLog.getInstance();
        for (Event next : el) {
            l.add(next);
        }
        assertTrue(l.contains(e1));
        assertTrue(l.contains(e2));
        assertTrue(l.contains(e3));
    }

    @Test
    void testWriteEvent() {
        EventLog el = EventLog.getInstance();
        el.clear();
        WareHouse wareHouse = new WareHouse("Daniels Warehouse");

        wareHouse.changeIsDoneReadFromJson();
        Item appleRecord = new Item("apple", 500,"+");
        Item pearRecord = new Item("pear", 100,"-");
        wareHouse.addItemRecord(appleRecord);
        wareHouse.addItemRecord(pearRecord);

        String eventString = "";
        for (Event event: EventLog.getInstance()) {
            eventString += event.getDescription();
        }
        assertEquals("Event log cleared.500 apple added to warehouse100 pear removed from warehouse",
                eventString);
    }

    @Test
    public void testClear() {
        EventLog el = EventLog.getInstance();
        el.clear();
        Iterator<Event> itr = el.iterator();
        assertTrue(itr.hasNext());   // After log is cleared, the clear log event is added
        assertEquals("Event log cleared.", itr.next().getDescription());
        assertFalse(itr.hasNext());
    }
}
