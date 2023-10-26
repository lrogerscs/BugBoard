package application.pane;

import application.comment.Comment;
import application.controller.NewCommentController;
import application.project.Project;
import application.ticket.Ticket;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TicketPane extends VBox {
   Project project;
   TextField ticketTitle;
   TextArea ticketDesc;
   Button newCommentButton;
   HBox ticketTitlePane, buttonPane;
   
   public TicketPane(Project project, Ticket ticket) {
      this.project = project;
      ticketTitle = new TextField(ticket.getTitle());
      ticketDesc = new TextArea(ticket.getDescription());
      newCommentButton = new Button("+ New Comment");
      ticketTitlePane = new HBox(new Label("Title:"), ticketTitle);
      buttonPane = new HBox(newCommentButton);
      
      ticketTitle.setEditable(false);
      ticketDesc.setEditable(false);
      ticketTitlePane.setSpacing(10);
      buttonPane.setAlignment(Pos.CENTER_RIGHT);
      
      // Set button behavior.
      newCommentButton.setOnAction(event -> {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/new_comment.fxml"));
            Parent root = loader.load();
            NewCommentController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            stage.setScene(scene);
            stage.show();
            controller.setProjectTicket(project, ticket);
         } catch (Exception e) {
            e.printStackTrace();
         }
      });
      
      getChildren().addAll(ticketTitlePane, new Label("Description:"), ticketDesc, new Label("Comments:"));
      
      // Add comments to pane.
      for (Comment comment : ticket.getComments()) {
         TextArea commentDesc = new TextArea(comment.getDesc());
         Label commentDateTime = new Label(comment.getDateTime().toString());
         commentDesc.setEditable(false);
         commentDesc.getStyleClass().add("desc-box");
         commentDateTime.getStyleClass().add("time-label");
         getChildren().addAll(commentDesc, commentDateTime);
      }
      
      getChildren().add(buttonPane);
      
      ticketDesc.getStyleClass().add("desc-box");
      newCommentButton.getStyleClass().add("new-comment-button");
      getStyleClass().add("info-pane");
   }
}
