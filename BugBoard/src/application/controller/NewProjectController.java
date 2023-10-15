package application.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import application.project.Project;
import application.reader.ProjectReader;
import application.writer.ProjectWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * NewProjectController controls the behavior of new_project.fxml.
 */
public class NewProjectController implements Initializable {
   @FXML
   private DatePicker datePicker;
   
   @FXML
   private TextField projectName;

   @FXML
   private TextArea projectDesc;
   
   private ProjectReader projectReader;
   private ProjectWriter projectWriter;
   private List<Project> projects;
   
   /**
    * On action, switches the current view to the home view.
    * @param event Action event.
    */
   @FXML
   private void onSaveButtonClick(ActionEvent event) {
      try {
         if (projectName.getText() == null || projectName.getText().isEmpty() || datePicker.getValue() == null)
            return;
         
         // Save data.
         projects.add(new Project(projectName.getText(), datePicker.getValue(), projectDesc.getText()));
         projectWriter.writeProjects(projects, "./data/project_data.csv");
         
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/home.fxml"));
         Parent root = fxmlLoader.load();
         Scene scene = new Scene(root);
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         stage.setScene(scene);
         stage.show();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      projectReader = new ProjectReader();
      projectWriter = new ProjectWriter();
      
      projects = projectReader.readProjects("./data/project_data.csv");
      datePicker.setValue(LocalDate.now());
   }
}
