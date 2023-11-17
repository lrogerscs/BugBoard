package application.pane;

import java.io.IOException;

import application.controller.EditProjectController;
import application.controller.HomeController;
import application.project.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * ProjectPane displays project information/options inside a container.
 */
public class ProjectPane extends HBox {
   Project project;
   Label name, date;
   Button edit, delete;
   HBox buttonPane;
   
   public ProjectPane(Project project) {
      this.project = project;
      name = new Label(this.project.getName());
      date = new Label(this.project.getDate().toString());
      edit = new Button("Edit");
      delete = new Button("Delete");
      buttonPane = new HBox();
      
      buttonPane.setStyle("-fx-alignment: center-right; -fx-spacing: 10");
      setHgrow(buttonPane, Priority.ALWAYS);
      
      // Set button behavior.
      edit.setOnAction(event -> loadEditProject(event));
      delete.setOnAction(event -> deleteProject(event));
      
      buttonPane.getChildren().addAll(edit, delete);
      getChildren().addAll(this.name, this.date, buttonPane);
      
      this.name.getStyleClass().add("project-pane-label");
      this.date.getStyleClass().add("project-pane-label");
      edit.getStyleClass().add("edit-button");
      delete.getStyleClass().add("delete-button");
      getStyleClass().add("info-pane");
   }
   
   //This method is calling the public deleteProject() method in HomeController and passing the reference of the project being deleted
   private void deleteProject(ActionEvent event)
   {
	   try
	   {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/home.fxml"));
		Parent root = fxmlLoader.load();
		HomeController homeController = fxmlLoader.getController();
		homeController.deleteProject(project);
	   }
	   catch (Exception e)
	   {
		   e.printStackTrace();
	   }
	   
	   //String projectName = project.getName();
	   //System.out.println("Delete " + projectName + " button pushed");
   }
   
   private void loadEditProject(ActionEvent event) {
      try {
         FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/edit_project.fxml"));
         Parent root = loader.load();
         EditProjectController controller = loader.getController();
         Scene scene = new Scene(root);
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         
         stage.setScene(scene);
         stage.show();
         controller.setProject(this.project);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}