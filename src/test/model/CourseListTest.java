package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourseListTest {

    CourseList courseList1;
    CourseList courseListEmpty;
    CourseList courseListOneCourse;
    CourseList courseListSemester1;
    CourseList courseListSemester2;
    CourseList courseListMath;
    CourseList courseListCpsc;
    CourseList courseListMath200;
    Course cpsc121 = new Course(2, "CPSC", 4, 1);
    Course cpsc110 = new Course(1, "CPSC", 4, 1);
    Course cpsc210 = new Course(2, "CPSC", 4, 1);
    Course math100 = new Course(1, "MATH", 3, 1);
    Course math101 = new Course(2, "MATH", 3, 1);
    Course scie113 = new Course(1, "SCIE", 3, 1);
    Course germ100 = new Course(1, "GERM", 3, 1);
    Course astr102 = new Course(2, "ASTR", 3, 1);
    Course wrds150 = new Course(2, "WRDS", 3, 1);
    Course math200 = new Course(1, "MATH", 3, 2);

    @BeforeEach
    void runBefore() {
        courseList1 = new CourseList();
        courseListEmpty = new CourseList();
        courseListOneCourse = new CourseList();
        courseListSemester1 = new CourseList();
        courseListSemester2 = new CourseList();
        courseListCpsc = new CourseList();
        courseListMath = new CourseList();
        courseListMath200 = new CourseList();

        courseList1.addCourse(cpsc110);
        courseList1.addCourse(math100);
        courseList1.addCourse(scie113);
        courseList1.addCourse(germ100);
        courseList1.addCourse(cpsc121);
        courseList1.addCourse(cpsc210);
        courseList1.addCourse(math101);
        courseList1.addCourse(astr102);
        courseList1.addCourse(wrds150);

        courseListOneCourse.addCourse(cpsc210);

        courseListSemester1.addCourse(cpsc110);
        courseListSemester1.addCourse(math100);
        courseListSemester1.addCourse(scie113);
        courseListSemester1.addCourse(germ100);

        courseListSemester2.addCourse(cpsc121);
        courseListSemester2.addCourse(cpsc210);
        courseListSemester2.addCourse(math101);
        courseListSemester2.addCourse(astr102);
        courseListSemester2.addCourse(wrds150);

        courseListCpsc.addCourse(cpsc110);
        courseListCpsc.addCourse(cpsc121);
        courseListCpsc.addCourse(cpsc210);

        courseListMath.addCourse(math100);
        courseListMath.addCourse(math101);

        courseListMath200.addCourse(math200);
    }

    @Test
    void testAddCourse() {
        CourseList newList = courseListEmpty;
        newList.addCourse(cpsc210);
        assertTrue(newList.equals(courseListOneCourse));
    }

    @Test
    void testRemoveCourse() {
        CourseList newList = courseListOneCourse;
        newList.removeCourse(cpsc210);
        assertTrue(newList.equals(courseListEmpty));
    }

    @Test
    void testGetCourseListFromYear() {
        CourseList newList = courseList1;
        newList.getCourseListFromYear(1);
        assertTrue(newList.equals(courseList1));
        assertEquals(courseListMath200, courseListMath200.getCourseListFromYear(2));
    }

    @Test
    void testGetCourseListFromSemester() {
        CourseList newList1 = courseList1.getCourseListFromSemester(1);
        CourseList newList2 = courseList1.getCourseListFromSemester(2);
        assertTrue(newList1.equals(courseListSemester1));
        assertTrue(newList2.equals(courseListSemester2));
    }

    @Test
    void testGetCourseListFromCourseName() {
        CourseList newList1 = courseList1.getCourseListFromCourseName("CPSC");
        CourseList newList2 = courseList1.getCourseListFromCourseName("MATH");
        assertTrue(newList1.equals(courseListCpsc));
        assertTrue(newList2.equals(courseListMath));
    }

    @Test
    void testTotalCreditsYear() {
        assertEquals(30, courseList1.totalCreditsYear(1));
    }

    @Test
    void testTotalCreditsSemester() {
        assertEquals(13, courseListSemester1.totalCreditsSemester(1));
        assertEquals(17, courseListSemester2.totalCreditsSemester(2));
    }

    @Test
    void testEquals() {
        CourseList newList = courseListSemester2;
        newList.removeCourse(cpsc210);
        assertTrue(courseList1.equals(courseList1));
        assertFalse(courseList1.equals(courseListEmpty));
        assertFalse(newList.equals(courseListSemester1));
    }

    @Test
    void testRemoveCourseByName() {
        assertTrue(courseListOneCourse.removeCourseByName("CPSC"));
        assertTrue(courseList1.removeCourseByName("GERM"));
        assertFalse(courseListOneCourse.removeCourseByName("GERM"));
    }
}
