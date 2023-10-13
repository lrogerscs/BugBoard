package application.pane;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ProjectPane extends HBox {
   Label name;
   Label start;
   
   public ProjectPane(String name, String start) {
      this.name = new Label(name);
      this.start = new Label(start);
      
      getChildren().addAll(this.name, this.start);
      
      // Set style class.
      getStyleClass().add("project-pane");
   }
}
