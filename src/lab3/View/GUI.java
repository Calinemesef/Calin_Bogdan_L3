package lab3.View;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lab3.classes.Course;
import lab3.classes.Student;
import lab3.system.RegistrationSystem;

public class GUI {

    private Stage window;
    private RegistrationSystem registrationSystem;
    private TableView<Student> table;
    private Long currentTeacher;

    /**
     * Constructor
     */
    public GUI(Stage window, RegistrationSystem registrationSystem) {
        try {
            this.registrationSystem = registrationSystem;
            this.window = window;
        } catch (Exception ex) {
            System.out.println("Ein Fehler ist aufgetreten!");
        }
    }


    /**
     * Teacher view
     * @param id von lehrer
     * @param vorname
     * @param nachname
     */
    private void teacherWindow(long id, String vorname, String nachname) {
        window.setTitle("Teacher " + id + " : " + vorname + " " + nachname);

        TableColumn<Student, String> studentName = new TableColumn<>("Vorname");
        studentName.setMinWidth(200);
        studentName.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Student, String> studentSurname = new TableColumn<>("Nachname");
        studentSurname.setMinWidth(200);
        studentSurname.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        table = new TableView<>();
        table.getColumns().addAll(studentName, studentSurname);
        table.setItems(this.registrationSystem.observableStudents(id));

        Scene scene = new Scene(table);
        window.setScene(scene);
        window.show();
    }

    /**
     * Student view
     * @param id von studenten
     * @param vorname
     * @param nachname
     */
    private void studentWindow(long id, String vorname, String nachname) {
        Stage studentWindow = new Stage();
        studentWindow.setTitle("Student " + id + " : " + vorname + " " + nachname);

        TableColumn<Course, String> courseId = new TableColumn<>("ID");
        courseId.setMinWidth(100);
        courseId.setCellValueFactory(new PropertyValueFactory<>("courseID"));

        TableColumn<Course, String> courseName = new TableColumn<>("Kurs");
        courseName.setMinWidth(200);
        courseName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Course, Integer> courseCredits = new TableColumn<>("Kreditanzahl");
        courseCredits.setMinWidth(200);
        courseCredits.setCellValueFactory(new PropertyValueFactory<>("credits"));

        TableColumn<Course, Integer> courseEnrollment = new TableColumn<>("Enrollment");
        courseEnrollment.setMinWidth(200);
        courseEnrollment.setCellValueFactory(new PropertyValueFactory<>("maxEnrollment"));

        TableView<Course> courseTable = new TableView<>();
        courseTable.getColumns().addAll(courseId, courseName, courseCredits, courseEnrollment);
        courseTable.setItems(this.registrationSystem.observableCourses());

        TextField enrollInput = new TextField();
        enrollInput.setPromptText("Kurs ID");
        Button enrollButton = new Button("Anmelden");
        Label studentCredits = new Label("                                                     "
                + "Krediten: " + this.registrationSystem.getStudentRepo().findOne(id).getTotalCredits());
        studentCredits.setPadding(new Insets(5, 0, 0, 0));

        Label messageBox = new Label("");
        messageBox.setPadding(new Insets(0, 10, 20, 10));

        HBox courseInteraction = new HBox(10);
        courseInteraction.setPadding(new Insets(10, 10, 10, 10));

        VBox layout = new VBox(8);
        layout.getChildren().addAll(courseTable, courseInteraction, messageBox);

        enrollButton.setOnAction(e -> {
            try {
                this.registrationSystem.registerGUI(id, Long.parseLong(enrollInput.getText()));
                messageBox.setText("Erfolgreich angemeldet!");
                studentCredits.setText("                                                     "
                        + "Krediten: " + this.registrationSystem.getStudentRepo().findOne(id).getTotalCredits());
                if (this.currentTeacher != null)
                    table.setItems(this.registrationSystem.observableStudents(this.currentTeacher));
            } catch (Exception ex) {
                messageBox.setText("Anmeldung nicht möglich!");
            }
            enrollInput.clear();
        });


        Scene scene = new Scene(layout);
        studentWindow.setScene(scene);
        studentWindow.show();
    }

}
