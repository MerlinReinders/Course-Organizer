package persistence;

import model.Course;
import model.CourseList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            CourseList cl = new CourseList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCourseList() {
        try {
            CourseList cl = new CourseList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCourseList.json");
            writer.open();
            writer.write(cl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCourseList.json");
            cl = reader.read();
            assertEquals(0, cl.getLength());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralCourseList() {
        try {
            CourseList cl = new CourseList();
            cl.addCourse(new Course(1, "MATH100", 3, 1));
            cl.addCourse(new Course(2, "MATH101", 3, 1));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCourseList.json");
            writer.open();
            writer.write(cl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCourseList.json");
            cl = reader.read();
            List<Course> courses = cl.getList();
            assertEquals(2, courses.size());
            checkCourse(1, "MATH100", 3, 1, courses.get(0));
            checkCourse(2, "MATH101", 3, 1, courses.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
