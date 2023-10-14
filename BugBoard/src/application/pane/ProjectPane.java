package application.pane;

import javafx.geometry.Pos;
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
      
      // Placeholder formatting.
      this.name.setAlignment(Pos.CENTER_LEFT);
      this.date.setAlignment(Pos.CENTER_LEFT);
      this.name.setPrefWidth(200);
      this.date.setPrefWidth(200);
      this.name.setMaxWidth(200);
      this.date.setMaxWidth(200);
      
      getChildren().addAll(this.name, this.date);
      
      // Set style class.
      getStyleClass().add("project-pane");
   }
}