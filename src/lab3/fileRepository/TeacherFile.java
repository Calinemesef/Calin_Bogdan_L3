package lab3.fileRepository;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lab3.classes.Teacher;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherFile {
    public FileWriter file;

    public List<Teacher> readFile() throws IOException {
        List<Teacher> teachers= new ArrayList<>();

        Reader reader = new BufferedReader(new FileReader("src/lab3/fileRepository/profesori.json"));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode parser = objectMapper.readTree(reader);

        for(JsonNode pm : parser.path("Teacher")) {

            Teacher t = new Teacher(pm.path("firstName").asText(), pm.path("lastName").asText());
            t.setTeacherId(pm.path("teacherId").asLong());

            List<Long> idCourses = new ArrayList<>();
            for(JsonNode node : pm.path("courses")){
                idCourses.add(node.asLong());
            }
            t.setCourses(idCourses);

            teachers.add(t);
        }
        reader.close();

        return teachers;
    }


    public void writeFile(Iterable<Teacher> teachersList){

        JSONObject object = new JSONObject();
        JsonFactory jsonFactory = new JsonFactory();
        jsonFactory.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET,false);
        ObjectMapper mapper = new ObjectMapper(jsonFactory);

        try{
            file = new FileWriter("src/lab3/fileRepository/profesoriOutput.json");
            JSONArray teachers = new JSONArray();
            teachersList.forEach(teachers::add);

            object.put("Teacher", teachers);
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
