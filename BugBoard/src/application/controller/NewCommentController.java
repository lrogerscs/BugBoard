package application.controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import application.comment.Comment;
import application.pane.CommentPane;
import application.project.Project;
import application.reader.CommentReader;
import application.ticket.Ticket;
import application.writer.CommentWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * NewCommentController controls the behavior of new_comment.fxml.
 */
public class NewCommentController {
   @FXML
   private TextField ticketTitle;
   
   @FXML
   private TextArea ticketDesc;
   
   @FXML
   private TextField dateTime;
   
   @FXML
   private TextArea commentDesc;
   
   @FXML
   private VBox commentPanelPane;
   
   private Project project;
   //TODO
   private CommentWriter commentWriter;
   private CommentReader commentReader;
   private List<Comment> comments;
   //End of TODO
   
   /**
    * On action, switches the current view to the edit project view.
    * @param event Action event.
    */
   @FXML
   private void onSaveButtonClick(ActionEvent event) {
      try {
         if (commentDesc.getText() == null || commentDesc.getText().isEmpty())
            return;
         
         commentReader = new CommentReader();
         commentWriter = new CommentWriter();
         comments = commentReader.readComments("./data/comment_data.csv");
         System.out.println(comments);
         for (Comment comment: comments)
         {
        	 System.out.println(comment.getDesc());
         }
         
         // TODO: Save comment information here.
         comments.add(new Comment(LocalDateTime.parse(dateTime.getText()), commentDesc.getText()));
         commentWriter.writeComments(comments, "./data/comment_data.csv");
         //End of TODO
         
         FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/edit_project.fxml"));
         Parent root = loader.load();
         EditProjectController controller = loader.getController();
         Scene scene = new Scene(root);
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         
         stage.setScene(scene);
         stage.show();
         controller.setProject(project);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   /**
    * Sets the project, ticket to be displayed.
    * @param project Project to be displayed.
    * @param ticket Ticket to be displayed.
    */
   public void setProjectTicket(Project project, Ticket ticket) {
      this.project = project;
      ticketTitle.setText(ticket.getTitle());
      ticketDesc.setText(ticket.getDescription());
      dateTime.setText(LocalDateTime.now().toString());
      commentPanelPane.getChildren().add(new CommentPane(ticket.getComments()));
   }
   
   //TODO add initialize method
   public void initialize(URL location, ResourceBundle resources)
   {
	   commentReader = new CommentReader();
	   commentWriter = new CommentWriter();
	   
	   //comments array should be used to display previous comments
	   comments = commentReader.readComments("./data/comment_data.csv");
	   System.out.println(comments);
   }
   //End of TODO
}
