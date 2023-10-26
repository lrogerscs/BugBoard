package application.pane;

import java.util.List;

import application.comment.Comment;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class CommentPane extends VBox {
   public CommentPane(List<Comment> comments) {
      for (Comment comment : comments) {
         TextArea commentDesc = new TextArea(comment.getDesc());
         Label commentDateTime = new Label(comment.getDateTime().toString());
         commentDesc.setEditable(false);
         commentDesc.getStyleClass().add("desc-box");
         commentDateTime.getStyleClass().add("time-label");
         getChildren().addAll(commentDesc, commentDateTime);
      }
      
      getStyleClass().add("info-pane");
   }
}
