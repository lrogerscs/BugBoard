package application.pane;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * ProjectPane displays project information inside a container.
 */
public class ProjectPane extends HBox {
   Label name;
   Label date;
   
   public ProjectPane(String name, String date) {
      this.name = new Label(name);
      this.date = new Label(date);
      
      getChildren().addAll(this.name, this.date);
      
      // Set style class.
      this.name.getStyleClass().add("project-pane-label");
      this.date.getStyleClass().add("project-pane-label");
      getStyleClass().add("project-pane");
   }
}