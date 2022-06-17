package ui;

import javax.swing.*;
import java.awt.*;

// Course list title
public class ListTitle extends Component {

    public ListTitle(OrganizerAppDisplay organizerAppDisplay, JComponent parent, int x, int y, String labelMessage) {
        super(organizerAppDisplay, parent, getGridBagConstraints(x, y));
        ((JLabel) thisComponent).setText(labelMessage);
    }

    // MODIFIES: this
    // EFFECTS:  creates new title JLabel
    @Override
    protected void createComponent() {
        JLabel component = new JLabel();
        component.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));

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
        c.insets = new Insets(30, 0, 0, 20);
        c.anchor = GridBagConstraints.NORTHWEST;
        return c;
    }
}
