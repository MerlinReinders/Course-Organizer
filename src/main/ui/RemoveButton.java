package ui;

import model.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a button allowing one to remove a course from a course list
public class RemoveButton extends Component {

    public RemoveButton(OrganizerAppDisplay organizerAppDisplay, JComponent parent, int x, int y) {
        super(organizerAppDisplay, parent, getGridBagConstraints(x, y));
    }

    // MODIFIES: this
    // EFFECTS:  creates new remove button
    @Override
    protected void createComponent() {
        JButton component = new JButton("Remove Course");
        component.setBorderPainted(true);
        component.setFocusPainted(true);
        component.setContentAreaFilled(true);
        component.addActionListener(new RemoveButtonClickHandler());
        component.setBackground(Color.LIGHT_GRAY);
        this.thisComponent = component;
    }

    public static GridBagConstraints getGridBagConstraints(int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 0.25;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 20, 0, 20);
        return c;
    }

    private class RemoveButtonClickHandler implements ActionListener {

        // EFFECTS: removes course from course list and updates info-area
        @Override
        public void actionPerformed(ActionEvent e) {
            JList component = (organizerAppDisplay.infoList);
            Course c = ((Course) component.getSelectedValue());
            organizerAppDisplay.courseListModel.removeCourse(c);
            component.setListData(organizerAppDisplay.courseListModel.getList().toArray());
        }
    }

}
