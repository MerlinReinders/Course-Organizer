package ui;

import model.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a button allowing one to add a course to a list
public class AddButton extends Component {

    public AddButton(OrganizerAppDisplay organizerAppDisplay, JComponent parent, int x, int y) {
        super(organizerAppDisplay, parent, getGridBagConstraints(x, y));
    }

    // MODIFIES: this
    // EFFECTS:  creates add button
    @Override
    protected void createComponent() {
        JButton component = new JButton("Add Course");
        component.setBorderPainted(true);
        component.setFocusPainted(true);
        component.setContentAreaFilled(true);
        component.addActionListener(new AddButtonClickHandler());
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

    private class AddButtonClickHandler implements ActionListener {

        // EFFECTS: Adds a new course to course list and displays in info-area
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField nameFieldComponent = (JTextField) organizerAppDisplay.courseNameField.thisComponent;
            JTextField yearFieldComponent = (JTextField) organizerAppDisplay.courseYearField.thisComponent;
            JTextField semesterFieldComponent = (JTextField) organizerAppDisplay.courseSemesterField.thisComponent;
            JTextField creditsFieldComponent = (JTextField) organizerAppDisplay.courseCreditsField.thisComponent;
            String name = nameFieldComponent.getText();
            String yearString = yearFieldComponent.getText();
            int year = Integer.parseInt(yearString);
            String semesterString = semesterFieldComponent.getText();
            int semester = Integer.parseInt(semesterString);
            String creditsString = creditsFieldComponent.getText();
            int credits = Integer.parseInt(creditsString);

            organizerAppDisplay.courseListModel.addCourse(new Course(semester, name, credits, year));
            organizerAppDisplay.infoList.setListData(organizerAppDisplay.courseListModel.getList().toArray());
            organizerAppDisplay.playSound();
        }
    }
}
