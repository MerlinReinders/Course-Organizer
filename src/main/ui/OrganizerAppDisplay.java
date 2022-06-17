package ui;

import model.CourseList;
import persistence.JsonReader;
import persistence.JsonWriter;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

// Organizer GUI app
public class OrganizerAppDisplay extends JFrame {

    private static final String JSON_STORE = "./data/courselist.json";
    CourseList courseListModel;
    JsonWriter jsonWriter;
    JsonReader jsonReader;

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    JPanel buttonArea = new JPanel();
    JPanel infoArea = new JPanel();
    JList infoList = new JList();

    CourseField courseNameField;
    CourseField courseYearField;
    CourseField courseSemesterField;
    CourseField courseCreditsField;

    private AudioStream soundStream;

    public OrganizerAppDisplay() {
        super("Organizer App");
        init();
        initializeGraphics();
    }

    private void init() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        this.courseListModel = new CourseList();
        try {
            this.soundStream = new AudioStream(new FileInputStream(new File("./data/Click.wav")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this organizer app will operate
    private void initializeGraphics() {
        setLayout(new GridLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        createComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS:  declares and instantiates all components
    private void createComponents() {

        this.buttonArea = new JPanel(new GridBagLayout());
        this.infoArea = new JPanel(new GridBagLayout());
        this.infoList = new JList();
        GridBagConstraints c = null;
        Title t = new Title(this, buttonArea, 0, 0, "Course Organizer");
        ListTitle t0 = new ListTitle(this, infoArea, 0, 0, "Course list:");
        GenericLabel courseNameLabel = new GenericLabel(this, buttonArea, 0, 1, "Course Name (i.e. \"CPSC210\"):");
        courseNameField = new CourseField(this, buttonArea, 1, 1);
        GenericLabel courseYearLabel = new GenericLabel(this, buttonArea, 0, 2, "Course Year [1-4]:");
        courseYearField = new CourseField(this, buttonArea, 1, 2);
        GenericLabel courseSemesterLabel = new GenericLabel(this, buttonArea, 0, 3, "Course Semester [1-2]:");
        courseSemesterField = new CourseField(this, buttonArea, 1, 3);
        GenericLabel courseCreditsLabel = new GenericLabel(this, buttonArea, 0, 4, "Course Credits [1-4]:");
        courseCreditsField = new CourseField(this, buttonArea, 1, 4);
        AddButton place5 = new AddButton(this, buttonArea, 0, 5);
        buttonArea.add(new JLabel());
        RemoveButton place6 = new RemoveButton(this, buttonArea, 0, 6);
        buttonArea.add(new JLabel());
        SaveButton place7 = new SaveButton(this, buttonArea, 0, 7);
        buttonArea.add(new JLabel());
        LoadButton place8 = new LoadButton(this, buttonArea, 1, 7);
        gridAreas();
    }

    private void gridAreas() {
        GridBagConstraints c;
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 1;
        this.add(buttonArea, c);

        infoList.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(60, 0, 20, 20);
        infoArea.add(infoList, c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        this.add(infoArea, c);
    }

    public void playSound() {
        AudioPlayer.player.start(this.soundStream);
        try {
            this.soundStream = new AudioStream(new FileInputStream(new File("./data/Click.wav")));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
