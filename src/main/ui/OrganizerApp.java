package ui;

import model.Course;
import model.CourseList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Organizer console app
public class OrganizerApp {

    private static final String JSON_STORE = "./data/courselist.json";
    private CourseList courseListModel;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the organizer application
    public OrganizerApp() {
        runOrganizer();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runOrganizer() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddCourse();
        } else if (command.equals("r")) {
            doRemoveCourse();
        } else if (command.equals("p")) {
            doPrintCredits();
        } else if (command.equals("v")) {
            doViewCourseList();
        } else if (command.equals("s")) {
            saveCourseList();
        } else if (command.equals("l")) {
            loadCourseList();
        } else {
            System.out.println("Selection not valid");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes course list
    private void init() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        this.courseListModel = new CourseList();
        this.input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add course");
        System.out.println("\tr -> remove course");
        System.out.println("\tp -> print credits");
        System.out.println("\tv -> view schedule");
        System.out.println("\ts -> save course list");
        System.out.println("\tl -> load course list");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: adds a course to course list

    private void doAddCourse() {
        System.out.println("Enter year course is taken:");
        int year = input.nextInt();
        System.out.println("Enter semester course is taken:");
        int semester = input.nextInt();
        System.out.println("Enter name of course (e.g. \"MATH100\", \"CPSC210\"):");
        String name = input.next();
        System.out.println("Enter course credits:");
        int credits = input.nextInt();

        this.courseListModel.addCourse(new Course(semester, name, credits, year));
        System.out.println("Course " + name + " successfully created!");
    }

    // MODIFIES: this
    // EFFECTS: removes a course from the list

    private void doRemoveCourse() {
        System.out.println("Enter name of course (e.g. \"MATH100\", \"CPSC210\"):");
        String name = input.next();

        if (this.courseListModel.removeCourseByName(name)) {
            System.out.println("Course " + name + " successfully removed!");
        } else {
            System.out.println("Course " + name + " not found!");
        }
    }

    // MODIFIES: this
    // EFFECTS: prints the credits per year and semester

    private void doPrintCredits() {
        System.out.println("Enter year:");
        int year = input.nextInt();

        int semester1Credits =
                (this.courseListModel.getCourseListFromYear(year).getCourseListFromSemester(1)).totalCreditsSemester(1);
        int semester2Credits =
                (this.courseListModel.getCourseListFromYear(year).getCourseListFromSemester(2)).totalCreditsSemester(2);
        int totalCredits = this.courseListModel.totalCreditsYear(year);
        System.out.println("Semester 1 has " + semester1Credits + " credits"
                + " and semester 2 has " + semester2Credits + " credits, for a total of " + totalCredits + " credits.");
    }

    // MODIFIES: this
    // EFFECTS: prints the entire course list for a year

    private void doViewCourseList() {
        System.out.println("Enter year:");
        int year = input.nextInt();
        CourseList courseListByYear = this.courseListModel.getCourseListFromYear(year);

        for (Course c : courseListByYear.getCourseListFromSemester(1).getList()) {
            System.out.println("Semester " + c.getSemester() + " - " + c.getCourseName() + "");
        }

        for (Course c : courseListByYear.getCourseListFromSemester(2).getList()) {
            System.out.println("Semester " + c.getSemester() + " - " + c.getCourseName() + "");
        }
    }

    // EFFECTS: saves the course list to file
    private void saveCourseList() {
        try {
            jsonWriter.open();
            jsonWriter.write(courseListModel);
            jsonWriter.close();
            System.out.println("Saved course list to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads course list from file
    private void loadCourseList() {
        try {
            courseListModel = jsonReader.read();
            System.out.println("Loaded course list from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}




