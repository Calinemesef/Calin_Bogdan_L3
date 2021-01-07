package lab3.View;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
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
     * Methode fur Erstellen der 2 Login-Seiten am Anfang des Programms
     */
    public void show() {
        this.loginWindow(true);  //Lehrer-View
        this.loginWindow(false); //Student-View
    }


    /**
     * Teacher view
     * @param id von lehrer
     * @param vorname vorname
     * @param nachname nachname
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
     * @param vorname vorname
     * @param nachname nachname
     */
    private void studentWindow(long id, String vorname, String nachname) {
        Stage studentWindow = new Stage();
        studentWindow.setTitle("Student " + id + " : " + vorname + " " + nachname);

        TableColumn<Course, String> courseId = new TableColumn<>("ID");
        courseId.setMinWidth(100);
        courseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));

        TableColumn<Course, String> courseName = new TableColumn<>("Kurs");
        courseName.setMinWidth(200);
        courseName.setCellValueFactory(new PropertyValueFactory<>("nume"));

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
        Label studentCredits = new Label("                 Krediten: " + this.registrationSystem.getStudentRepo().findOne(id).getTotalCredits());
        studentCredits.setPadding(new Insets(5, 0, 0, 0));

        Label messageBox = new Label("");
        messageBox.setPadding(new Insets(0, 10, 20, 10));

        HBox courseInteraction = new HBox(10);
        courseInteraction.setPadding(new Insets(10, 10, 10, 10));
        courseInteraction.getChildren().addAll(enrollInput, enrollButton, studentCredits);

        VBox layout = new VBox(8);
        layout.getChildren().addAll(courseTable, courseInteraction, messageBox);

        enrollButton.setOnAction(e -> {
            try {
                this.registrationSystem.registerGUI(id, Long.parseLong(enrollInput.getText()));
                messageBox.setText("Erfolgreich angemeldet!");
                studentCredits.setText("                   Krediten: " + this.registrationSystem.getStudentRepo().findOne(id).getTotalCredits());
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


    /**
     * Login view
     * perspective == true -> Lehrer-View
     * perspective == false -> Student-View
     */
    private void loginWindow(boolean perspective) {
        Stage loginStage = new Stage();

        if (perspective)
            loginStage.setTitle("Lehrer");
        else
            loginStage.setTitle("Student");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(8);
        grid.setHgap(10);

        Label nameLabel = new Label("Vorname:");
        Label surnameLabel = new Label("Nachname:");
        Label idLabel;

        if (perspective)
            idLabel = new Label("Lehrer ID:");
        else
            idLabel = new Label("Student ID:");

        TextField nameField = new TextField();
        TextField surnameField = new TextField();
        TextField idField = new TextField();

        nameField.setPromptText("Vorname");
        surnameField.setPromptText("Nachname");
        idField.setPromptText("ID");

        Button loginConfirmButton = new Button("Einloggen");
        Button loginDenyButton = new Button("Exit");
        HBox loginButtons = new HBox(10);
        loginButtons.getChildren().addAll(loginConfirmButton, loginDenyButton);

        GridPane.setConstraints(nameLabel, 0, 0);
        GridPane.setConstraints(nameField, 1, 0);
        GridPane.setConstraints(surnameLabel, 0, 1);
        GridPane.setConstraints(surnameField, 1, 1);
        GridPane.setConstraints(idLabel, 0, 2);
        GridPane.setConstraints(idField, 1, 2);
        GridPane.setConstraints(loginButtons, 1, 3);

        grid.getChildren().addAll(nameLabel, nameField, surnameLabel, surnameField, idLabel, idField, loginButtons);

        Scene loginScene = new Scene(grid, 600, 200);

        loginConfirmButton.setOnAction(e -> {
            long id = Long.parseLong(idField.getText());
            String vorname = nameField.getText();
            String nachname = surnameField.getText();

            if (perspective) {
                if (this.registrationSystem.validateTeacher(id, vorname, nachname)) {
                    this.currentTeacher = id;
                    this.teacherWindow(id, vorname, nachname);
                    loginStage.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Der Lehrer existiert nicht!");
                    alert.showAndWait();
                    loginStage.close();
                }
            } else {
                if (this.registrationSystem.validateStudent(id, vorname, nachname)) {
                    this.studentWindow(id, vorname, nachname);
                    loginStage.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Der Student existiert nicht!");
                    alert.showAndWait();
                    loginStage.close();
                }
            }
        });
        loginDenyButton.setOnAction(e -> loginStage.close());
        loginStage.setScene(loginScene);
        loginStage.show();
    }
}