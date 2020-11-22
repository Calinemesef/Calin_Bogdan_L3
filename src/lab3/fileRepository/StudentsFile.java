package lab3.fileRepository;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lab3.classes.Student;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsFile {

    public List<Student> readFile() throws IOException {
         List<Student> students = new ArrayList<>();

         Reader reader = new BufferedReader(new FileReader("studenti.json"));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode parser = objectMapper.readTree(reader);

        for(JsonNode pm : parser.path("Student")) {

            Student s = new Student(pm.path("firstName").asText(), pm.path("lastName").asText());
            s.setStudentId(pm.path("studentId").asLong());
            s.setTotalCredits(pm.path("totalCredits").asInt());

            List<Long> idCourses = new ArrayList<>();
            for(JsonNode node : pm.path("enrolledCourses")){
                idCourses.add(node.asLong());
            }
            s.setEnrolledCourses(idCourses);

            students.add(s);
        }
        reader.close();

        return students;
    }

    FileWriter file;

    public void writeFile(Iterable<Student> studentsList){

        JSONObject object = new JSONObject();
        JsonFactory jsonFactory = new JsonFactory();
        jsonFactory.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET,false);
        ObjectMapper mapper = new ObjectMapper(jsonFactory);

        try{
            file = new FileWriter("studenti.json");
            JSONArray students = new JSONArray();
            studentsList.forEach(students::add);

            object.put("Student", students);
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
