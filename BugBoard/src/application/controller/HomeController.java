package application.controller;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import application.pane.ProjectPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

/**
 * HomeController controls the behavior of home.fxml.
 */
public class HomeController implements Initializable {
   @FXML
   private VBox projectPanelPane;
   
   /**
    * On action, switches the current view to the project view.
    * @param event Action event.
    */
   @FXML
   private void onNewProjectButtonClick(ActionEvent event) {
      try {
         Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/new_project.fxml"));
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
      // Add projects to the display.
      projectPanelPane.getChildren().add(new ProjectPane("test project", "10/10/2023"));
   }
}