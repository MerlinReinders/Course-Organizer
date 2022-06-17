package ui;

import javax.swing.*;
import java.awt.*;

// Represents generic labels
public class GenericLabel extends Component {

    public GenericLabel(OrganizerAppDisplay organizerAppDisplay, JComponent parent, int x, int y, String labelMessage) {
        super(organizerAppDisplay, parent, getGridBagConstraints(x, y));
        ((JLabel) thisComponent).setText(labelMessage);
    }

    // MODIFIES: this
    // EFFECTS:  creates new generic JLabel
    @Override
    protected void createComponent() {
        JLabel component = new JLabel();
        component.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));

        this.thisComponent = component;
    }

    public static GridBagConstraints getGridBagConstraints(int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 60, 0, 0);
        c.anchor = GridBagConstraints.WEST;
        return c;
    }
}
