package persistence;

import org.json.JSONObject;

// This class uses a template created by CPSC210
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo).
// The original code has been modified for our specific use case.
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}