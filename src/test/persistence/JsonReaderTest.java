package persistence;

import model.Course;
import model.CourseList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            CourseList cl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCourseList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCourseList.json");
        try {
            CourseList cl = reader.read();
            assertEquals(0, cl.getLength());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralCourseList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCourseList.json");
        try {
            CourseList cl = reader.read();
            List<Course> courses = cl.getList();
            assertEquals(2, courses.size());
            checkCourse(1, "MATH100", 3, 1, courses.get(0));
            checkCourse(2, "MATH101", 3, 1, courses.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
