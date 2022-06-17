package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CourseTest {

    Course CPSC121 = new Course(2, "CPSC121", 4, 1);
    Course CPSC110 = new Course(1, "CPSC110", 4, 1);

    @Test
    void testEquals() {
        assertTrue(CPSC110.equals(CPSC110));
        assertFalse(CPSC110.equals(CPSC121));
    }

    @Test
    void testGetCourseName(){
        assertEquals("CPSC110", CPSC110.getCourseName());
    }

    @Test
    void testToString(){
        assertEquals("CPSC110 | Year: 1; Semester: 1; Credits: 4", CPSC110.toString());
    }
}