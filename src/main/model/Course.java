package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a course having a name, a semester and credits
public class Course implements Writable {
    private int semester;                                                    // the semester a course is in [1, 2]
    private String courseName;                                               // the name of a course
    private int credits;                                                     // the credits of a course [1, 4]
    private int year;                                                        // the year of a course [1, 4]

    public Course(int semester, String courseName, int credits, int year) {
        this.semester = semester;
        this.courseName = courseName;
        this.credits = credits;
        this.year = year;
    }

    // EFFECTS: checks if two courses are equal
    @Override
    public boolean equals(Object o) {
        Course c = (Course) o;
        return this.semester == c.getSemester() && this.courseName.equals(c.courseName)
                && this.credits == c.getCredits() && this.year == c.getYear();
    }

    public int getYear() {

        return year;
    }

    public int getSemester() {

        return semester;
    }

    public int getCredits() {

        return credits;
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("semester", this.semester);
        json.put("courseName", this.courseName);
        json.put("credits", this.credits);
        json.put("year", this.year);
        return json;
    }

    @Override
    public String toString() {
        String yearString = Integer.toString(year);
        String semesterString = Integer.toString(semester);
        String creditsString = Integer.toString(credits);

        String s0 = semesterString + "; Credits: " + creditsString;
        String s1 = this.courseName + " | Year: " + yearString + "; Semester: " + s0;
        return s1;
    }

}





