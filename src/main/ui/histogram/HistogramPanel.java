package ui.histogram;

import ui.histogram.Bar;
import ui.histogram.ColorIcon;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Represents a HistogramPanel
// This class uses a template from:
// https://stackoverflow.com/questions/29708147/custom-graph-java-swing
public class HistogramPanel extends JPanel {
    private int histogramHeight = 200;
    private int barWidth = 50;
    private int barGap = 10;

    private JPanel barPanel;
    private JPanel labelPanel;

    private List<Bar> bars = new ArrayList<Bar>();

    // MODIFIES: this
    // EFFECTS: Constructor for the HistogramPanel class.
    //Initializes the histogram panel with a default border, layout, and panels for displaying the bars and labels.
    public HistogramPanel() {
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout());

        barPanel = new JPanel(new GridLayout(1, 0, barGap, 0));
        Border outer = new MatteBorder(1, 1, 1, 1, Color.BLACK);
        Border inner = new EmptyBorder(10, 10, 0, 10);
        Border compound = new CompoundBorder(outer, inner);
        barPanel.setBorder(compound);
        barPanel.setBackground(Color.white);

        labelPanel = new JPanel(new GridLayout(1, 0, barGap, 0));
        labelPanel.setBorder(new EmptyBorder(5, 10, 0, 10));
        labelPanel.setBackground(Color.WHITE);

        add(barPanel, BorderLayout.CENTER);
        add(labelPanel, BorderLayout.PAGE_END);
    }

    // MODIFIES: this
    // EFFECTS: Adds a new histogram column with the given label, value, and color to the list of bars.
    // Does not display the column until layoutHistogram() is called.
    public void addHistogramColumn(String label, int value, Color color) {
        Bar bar = new Bar(label, value, color);
        bars.add(bar);
    }

    // MODIFIES: this
    // EFFECTS: Updates the display of the histogram panel with the latest bars and their values.
    // Calculates the maximum value of all bars and scales each bar's height according to its value and the maximum
    // value. Displays each bar with a colored icon and its value as a label below it.
    // Displays each bar's label as a label in the label panel below the bars.
    public void layoutHistogram() {
        barPanel.removeAll();
        labelPanel.removeAll();

        int maxValue = 0;

        for (Bar bar: bars) {
            maxValue = Math.max(maxValue, bar.getValue());
        }

        for (Bar bar: bars) {
            JLabel label = new JLabel(bar.getValue() + "");
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalTextPosition(JLabel.TOP);
            label.setVerticalAlignment(JLabel.BOTTOM);
            int barHeight = (bar.getValue() * histogramHeight) / maxValue;
            Icon icon = new ColorIcon(bar.getColor(), barWidth, barHeight);
            label.setIcon(icon);
            barPanel.add(label);

            JLabel barLabel = new JLabel(bar.getLabel());
            barLabel.setHorizontalAlignment(JLabel.CENTER);
            labelPanel.add(barLabel);
        }
    }
}
