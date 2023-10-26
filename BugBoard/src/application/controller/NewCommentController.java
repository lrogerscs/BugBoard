package application.controller;

import java.time.LocalDateTime;

import application.pane.CommentPane;
import application.project.Project;
import application.ticket.Ticket;
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
   
   @FXML
   private void onSaveButtonClick(ActionEvent event) {
      try {
         if (commentDesc.getText() == null || commentDesc.getText().isEmpty())
            return;
         
         // TODO: Save comment information here.
         
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
   
   public void setProjectTicket(Project project, Ticket ticket) {
      this.project = project;
      ticketTitle.setText(ticket.getTitle());
      ticketDesc.setText(ticket.getDescription());
      dateTime.setText(LocalDateTime.now().toString());
      commentPanelPane.getChildren().add(new CommentPane(ticket.getComments()));
   }
}
