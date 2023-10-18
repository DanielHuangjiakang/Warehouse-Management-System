package ui.histogram;

import java.awt.*;

// Represents the Bar of the histogram Panel
// This class uses a template from:
// https://stackoverflow.com/questions/29708147/custom-graph-java-swing
public class Bar {
    private String label;
    private int value;
    private Color color;

    // MODIFIES: this
    // EFFECTS: Constructs a new Bar object with the given label, value, and color.
    public Bar(String label, int value, Color color) {
        this.label = label;
        this.value = value;
        this.color = color;
    }

    // MODIFIES: this
    // EFFECTS: Returns the label of this Bar object.
    public String getLabel() {
        return label;
    }

    // MODIFIES: this
    // EFFECTS: Returns the value of this Bar object.
    public int getValue() {
        return value;
    }

    // MODIFIES: this
    // EFFECTS: Returns the color of this Bar object.
    public Color getColor() {
        return color;
    }
}
