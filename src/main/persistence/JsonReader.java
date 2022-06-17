package persistence;

import model.Course;
import model.CourseList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads course list from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads course list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CourseList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCourseList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses course list from JSON object and returns it
    private CourseList parseCourseList(JSONObject jsonObject) {
        CourseList cl = new CourseList();
        addCourses(cl, jsonObject);
        return cl;
    }

    // MODIFIES: cl
    // EFFECTS: parses courses from JSON object and adds them to course list
    private void addCourses(CourseList cl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("courses");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addCourse(cl, nextThingy);
        }
    }

    // MODIFIES: cl
    // EFFECTS: parses course from JSON object and adds it to course list
    private void addCourse(CourseList cl, JSONObject jsonObject) {
        int semester = jsonObject.getInt("semester");
        String courseName = jsonObject.getString("courseName");
        int credits = jsonObject.getInt("credits");
        int year = jsonObject.getInt("year");
        Course course = new Course(semester, courseName, credits, year);
        cl.addCourse(course);
    }
}
