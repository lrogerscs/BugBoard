package application.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import application.comment.Comment;
import application.pane.TicketPane;
import application.project.Project;
import application.ticket.Ticket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditProjectController {
   @FXML
   private TextField projectName;
   
   @FXML
   private DatePicker projectStartDate;
   
   @FXML
   private TextArea projectDesc;
   
   @FXML
   private VBox ticketPanelPane;
   
   private Project project;
   
   @FXML
   private void onSaveButtonClick(ActionEvent event) {
      try {
         Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/home.fxml"));
         Scene scene = new Scene(root);
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         stage.setScene(scene);
         stage.show();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   public void setProject(Project project) {
      this.project = project;
      projectName.setText(this.project.getName());
      projectStartDate.setValue(this.project.getDate());
      projectDesc.setText(this.project.getDesc());
      
      // Placeholder code until ticket saving is in place.
      List<Comment> comments = new ArrayList<Comment>();
      comments.add(new Comment("A new test comment!", LocalDateTime.now()));
      comments.add(new Comment("A second new test comment!", LocalDateTime.now()));
      Ticket ticket = new Ticket("Anonymous Project", "Test Ticket", "A test ticket!", comments);
      
      ticketPanelPane.getChildren().clear();
      ticketPanelPane.getChildren().add(new TicketPane(project, ticket));
   }
}
