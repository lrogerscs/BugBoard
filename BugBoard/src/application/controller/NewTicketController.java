package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.project.Project;
import application.reader.ProjectReader;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewTicketController implements Initializable {
   @FXML
   private ComboBox<String> projectComboBox;
   
   @FXML
   private TextField ticketTitle;
   
   @FXML
   private TextArea ticketDesc;
   
   private ProjectReader projectReader;
   private List<String> projectNames;
   
   @FXML
   private void onSaveButtonClick(ActionEvent event) {
      try {
         if (projectComboBox.getValue() == null || ticketTitle.getText() == null || ticketTitle.getText().isEmpty())
            return;
         
         // TODO: Save ticket information here.
         
         Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/home.fxml"));
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
      projectNames = new ArrayList<String>();
      
      // Initialize projects for ComboBox.
      for (Project project : projectReader.readProjects("./data/project_data.csv"))
         projectNames.add(project.getName());
      projectComboBox.setItems(FXCollections.observableArrayList(projectNames));
   }
}
