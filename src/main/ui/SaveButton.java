package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// Represents a button allowing one to save a course list to a Json file
public class SaveButton extends Component {

    public SaveButton(OrganizerAppDisplay organizerAppDisplay, JComponent parent, int x, int y) {
        super(organizerAppDisplay, parent, getGridBagConstraints(x, y));
    }

    // MODIFIES: this
    // EFFECTS:  creates new save button
    @Override
    protected void createComponent() {
        JButton component = new JButton("Save");
        component.setBorderPainted(true);
        component.setFocusPainted(true);
        component.setContentAreaFilled(true);
        component.addActionListener(new SaveButtonClickHandler());
        component.setBackground(Color.LIGHT_GRAY);
        this.thisComponent = component;
    }

    public static GridBagConstraints getGridBagConstraints(int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 0.25;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 20, 0, 5);
        return c;
    }

    private class SaveButtonClickHandler implements ActionListener {

        // EFFECTS: saves course list to Json file
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                organizerAppDisplay.jsonWriter.open();
                organizerAppDisplay.jsonWriter.write(organizerAppDisplay.courseListModel);
                organizerAppDisplay.jsonWriter.close();
                System.out.println("Successfully wrote to file");
            } catch (FileNotFoundException ex) {
                System.out.println("Unable to write to file");
            }
        }
    }
}