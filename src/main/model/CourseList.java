package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents a list of courses
public class CourseList implements Writable {
    private ArrayList<Course> courseList;

    public CourseList() {
        this.courseList = new ArrayList<Course>();
    }

    public CourseList(ArrayList<Course> arrList) {
        this.courseList = arrList;
    }

    // MODIFIES: this
    // EFFECTS: adds course to course list

    public void addCourse(Course c) {
        this.courseList.add(c);
    }

    // MODIFIES: this
    // EFFECTS: removes a course from the list

    public void removeCourse(Course c) {
        this.courseList.remove(c);
    }

    // MODIFIES: this
    // EFFECTS: removes a course from the list by its name

    public boolean removeCourseByName(String name) {
        ArrayList<Course> newList = new ArrayList<Course>();
        boolean hasRemoved = false;
        for (Course c : this.courseList) {
            if (!c.getCourseName().equals(name)) {
                newList.add(c);

            } else {
                hasRemoved = true;
            }
        }
        this.courseList = newList;
        return hasRemoved;
    }

    // REQUIRES: year must be between 1-4
    // MODIFIES: this
    // EFFECTS: gets new course list depending on year given

    public CourseList getCourseListFromYear(int year) {
        ArrayList<Course> newList = new ArrayList<Course>();
        for (Course c : this.courseList) {
            if (c.getYear() == year) {
                newList.add(c);
            }
        }
        return new CourseList(newList);
    }

    // REQUIRES: semester must be between 1-2
    // MODIFIES: this
    // EFFECTS: gets new course list depending on semester given

    public CourseList getCourseListFromSemester(int semester) {
        ArrayList<Course> newList = new ArrayList<Course>();
        for (Course c : this.courseList) {
            if (c.getSemester() == semester) {
                newList.add(c);
            }
        }
        return new CourseList(newList);
    }

    // MODIFIES: this
    // EFFECTS: gets new course list depending on course name given

    public CourseList getCourseListFromCourseName(String name) {
        ArrayList<Course> newList = new ArrayList<Course>();
        for (Course c : this.courseList) {
            if (c.getCourseName().equals(name)) {
                newList.add(c);
            }
        }
        return new CourseList(newList);
    }

    // REQUIRES: year must be between 1-4
    // MODIFIES: this
    // EFFECTS: gets total credits per year given

    public int totalCreditsYear(int year) {
        ArrayList<Course> newList = this.getCourseListFromYear(year).getList();
        int counter = 0;
        for (Course c : newList) {
            counter = counter + c.getCredits();
        }
        return counter;
    }

    // REQUIRES: semester must be between 1-2
    // MODIFIES: this
    // EFFECTS: gets total credits per semester given

    public int totalCreditsSemester(int semester) {
        ArrayList<Course> newList = this.getCourseListFromSemester(semester).getList();
        int counter = 0;
        for (Course c : this.courseList) {
            counter = counter + c.getCredits();
        }
        return counter;
    }

    public int getLength() {
        return this.courseList.size();
    }

    public ArrayList<Course> getList() {
        return this.courseList;
    }

    // MODIFIES: this
    // EFFECTS: checks if two course lists are equal and are in the same order

    @Override
    public boolean equals(Object o) {
        CourseList clist = (CourseList) o;
        if (this.getLength() != clist.getLength()) {
            return false;
        }
        ArrayList<Course> arrayList2 = clist.getList();
        for (int i = 0; i < this.getLength(); i++) {
            if (!this.courseList.get(i).equals(arrayList2.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("courses", coursesToJson());
        return json;
    }

    // EFFECTS: returns things in this course list as a JSON array
    private JSONArray coursesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Course c : this.courseList) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}
