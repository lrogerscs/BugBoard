package application.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.Project;
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
   
   /**
    * On action, switches the current view to the home view.
    * @param event Action event.
    */
   @FXML
   private void onSaveButtonClick(ActionEvent event) {
      try {
         Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/home.fxml"));;
         Scene scene = new Scene(root);
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         stage.setScene(scene);
         stage.show();
         
         // TODO: Save inputs/call appropriate method here.
         //Instantiate a Project object and populate the appropriate fields with the user input (use a constructor)
         Project project = new Project(projectName.getText(), datePicker.getValue(), projectDesc.getText());
         
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      datePicker.setValue(LocalDate.now());
   }
}
