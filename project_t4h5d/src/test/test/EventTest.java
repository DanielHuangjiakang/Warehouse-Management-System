package test;

import model.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

// represent the Unit tests for the Event class
// This class uses a template created by CPSC210
// (https://github.students.cs.ubc.ca/CPSC210/AlarmSystem).
// The original code has been modified for our specific use case.
public class EventTest {
    private Event e;
    private Event e2;
    private Date d;

    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e = new Event("Sensor open at door");   // (1)
        d = Calendar.getInstance().getTime();   // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("Sensor open at door", e.getDescription());
        assertEquals(d, e.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "Sensor open at door", e.toString());
    }

    @Test
    public void testEquals() {
        assertTrue(e.equals(e));
        assertFalse(e.equals(e2));
        assertFalse(e.equals("1"));
    }

    @Test
    public void testHashCode() {
        assertNotEquals(-1415121508, e.hashCode());
    }
}
