package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Represents a button allowing one to load a course list from Json file
public class LoadButton extends Component {

    public LoadButton(OrganizerAppDisplay organizerAppDisplay, JComponent parent, int x, int y) {
        super(organizerAppDisplay, parent, getGridBagConstraints(x, y));
    }

    // MODIFIES: this
    // EFFECTS:  creates new load button
    @Override
    protected void createComponent() {
        JButton component = new JButton("Load");
        component.setBorderPainted(true);
        component.setFocusPainted(true);
        component.setContentAreaFilled(true);
        component.addActionListener(new LoadButtonClickHandler());
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
        c.insets = new Insets(0, 5, 0, 20);
        return c;
    }

    private class LoadButtonClickHandler implements ActionListener {

        // EFFECTS: Loads course list from Json file and displays in info-area
        @Override
        public void actionPerformed(ActionEvent e) {
            JList component = (organizerAppDisplay.infoList);
            try {
                organizerAppDisplay.courseListModel = organizerAppDisplay.jsonReader.read();
                System.out.println("Successfully loaded course list");
            } catch (IOException ex) {
                System.out.println("Unable to read from file");
            }
            component.setListData(organizerAppDisplay.courseListModel.getList().toArray());
        }
    }
}