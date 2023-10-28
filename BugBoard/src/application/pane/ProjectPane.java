package application.pane;

import application.controller.EditProjectController;
import application.project.Project;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
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
   Button edit;
   HBox buttonPane;
   
   public ProjectPane(Project project) {
      this.project = project;
      name = new Label(this.project.getName());
      date = new Label(this.project.getDate().toString());
      edit = new Button("Edit");
      buttonPane = new HBox();
      
      buttonPane.setAlignment(Pos.CENTER_RIGHT);
      setHgrow(buttonPane, Priority.ALWAYS);
      
      // Set button behavior.
      edit.setOnAction(event -> {
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
      });
      
      buttonPane.getChildren().add(edit);
      getChildren().addAll(this.name, this.date, buttonPane);
      
      this.name.getStyleClass().add("project-pane-label");
      this.date.getStyleClass().add("project-pane-label");
      edit.getStyleClass().add("edit-button");
      getStyleClass().add("info-pane");
   }
}