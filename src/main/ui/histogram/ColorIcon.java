package ui.histogram;

import javax.swing.*;
import java.awt.*;

// Represents a ColorIcon
// This class uses a template from:
// https://stackoverflow.com/questions/29708147/custom-graph-java-swing
public class ColorIcon implements Icon {
    private int shadow = 3;

    private Color color;
    private int width;
    private int height;

    // MODIFIES: this
    // EFFECTS: Constructs a new ColorIcon object with the given color, width, and height.
    public ColorIcon(Color color, int width, int height) {
        this.color = color;
        this.width = width;
        this.height = height;
    }

    // MODIFIES: this
    // EFFECTS: Returns the width of this ColorIcon object.
    public int getIconWidth() {
        return width;
    }

    // MODIFIES: this
    // EFFECTS: Returns the height of this ColorIcon object.
    public int getIconHeight() {
        return height;
    }

    // MODIFIES: this
    // EFFECTS: Paints this ColorIcon object with the given Graphics object g,
    // at position (x, y) in the coordinate space of c.
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(color);
        g.fillRect(x, y, width - shadow, height);
        g.setColor(Color.GRAY);
        g.fillRect(x + width - shadow, y + shadow, shadow, height - shadow);
    }
}