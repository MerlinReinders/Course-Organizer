package persistence;

import model.Course;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkCourse(int semester, String courseName, int credits, int year, Course course) {
        assertEquals(semester, course.getSemester());
        assertEquals(courseName, course.getCourseName());
        assertEquals(credits, course.getCredits());
        assertEquals(year, course.getYear());
    }
}
