package lab3.fileRepository;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lab3.classes.Course;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CoursesFile {

    public List<Course> readFile() throws IOException {
        List<Course> courses = new ArrayList<>();

        Reader reader = new BufferedReader(new FileReader("src/lab3/fileRepository/profesori.json"));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode parser = objectMapper.readTree(reader);

        for(JsonNode pm : parser.path("Course")) {

            Course c = new Course(pm.path("name").asText());
            c.setTeacher(pm.path("teacherId").asLong());
            c.setMaxEnrollment(pm.path("maxEnrollment").asInt());
            c.setCredits(pm.path("credits").asInt());
            c.setCourseId(pm.path("courseId").asLong());

            List<Long> idStudents = new ArrayList<>();
            for(JsonNode node : pm.path("studentsEnrolled")){
                idStudents.add(node.asLong());
            }
            c.setStudentsEnrolled(idStudents);

            courses.add(c);
        }
        reader.close();

        return courses;
    }

    FileWriter file;

    public void writeFile(Iterable<Course> coursesList){

        JSONObject object = new JSONObject();
        JsonFactory jsonFactory = new JsonFactory();
        jsonFactory.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET,false);
        ObjectMapper mapper = new ObjectMapper(jsonFactory);

        try{
            file = new FileWriter("cursuri.json");
            JSONArray courses = new JSONArray();
            coursesList.forEach(courses::add);

            object.put("Course", courses);
            mapper.writeValue(file, object);
        } catch (IOException e){
            e.printStackTrace();

        }finally {
            try{
                file.flush();
                file.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }

    }

}
