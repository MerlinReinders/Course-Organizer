package ui;

import javax.swing.*;
import java.awt.*;

// Represents various course fields
public class CourseField extends Component {

    public CourseField(OrganizerAppDisplay organizerAppDisplay, JComponent parent, int x, int y) {
        super(organizerAppDisplay, parent, getGridBagConstraints(x, y));
    }

    public static GridBagConstraints getGridBagConstraints(int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(30, 20, 30, 20);
        return c;
    }

    // MODIFIES: this
    // EFFECTS:  creates new component
    @Override
    protected void createComponent() {
        this.thisComponent = new JTextField();
    }
}
