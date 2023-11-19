package application.pane;

import java.util.List;

import application.comment.Comment;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

/**
 * CommentPane displays comment information inside of a container.
 */
public class CommentPane extends VBox {
   public CommentPane(List<Comment> comments) {
      for (Comment c : comments) {
         TextArea commentDesc = new TextArea(c.getDesc());
         Label commentDateTime = new Label(c.getDateTime().toString());
         commentDesc.setEditable(false);
         commentDesc.getStyleClass().add("desc-box");
         commentDateTime.getStyleClass().add("time-label");
         getChildren().addAll(commentDesc, commentDateTime);
      }
      
      getStyleClass().add("info-pane");
   }
}
