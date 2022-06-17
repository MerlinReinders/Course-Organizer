package ui;

import javax.swing.*;
import java.awt.*;

// Abstract component class
public abstract class Component {

    protected JComponent thisComponent;
    protected OrganizerAppDisplay organizerAppDisplay;

    public Component(OrganizerAppDisplay organizerAppDisplay, JComponent parent, GridBagConstraints c) {
        this.organizerAppDisplay = organizerAppDisplay;
        createComponent();
        parent.add(this.thisComponent, c);
    }

    // EFFECTS: creates component
    protected abstract void createComponent();

}
