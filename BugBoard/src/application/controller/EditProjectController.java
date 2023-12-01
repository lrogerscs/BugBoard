package application.controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import application.comment.Comment;
import application.pane.TicketPane;
import application.project.Project;
import application.reader.CommentReader;
import application.reader.ProjectReader;
import application.reader.TicketReader;
import application.ticket.Ticket;
import application.writer.CommentWriter;
import application.writer.ProjectWriter;
import application.writer.TicketWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * EditProjectController controls the behavior of edit_project.fxml.
 */
public class EditProjectController implements Initializable {
   @FXML
   private TextField projectName;
   
   @FXML
   private DatePicker projectStartDate;
   
   @FXML
   private TextArea projectDesc;
   
   @FXML
   private ScrollPane ticketScrollPane;
   
   private ProjectReader projectReader;
   private TicketReader ticketReader;
   private CommentReader commentReader;
   private ProjectWriter projectWriter;
   private TicketWriter ticketWriter;
   private CommentWriter commentWriter;
   private VBox ticketPanelPane;
   private Project project;
   private List<Project> projects;
   private List<Ticket> tickets;
   private List<Comment> comments;
   
   /**
    * On action, (if possible) saves information then switches to the home view.
    * @param event Action event.
    */
   @FXML
   private void onSaveButtonClick(ActionEvent event) {
      try {
         // Check violations.
         if (projectName.getText() == null || projectName.getText().isEmpty() || projectStartDate.getValue() == null)
            return;
         
         for (int i = 0; i < project.getTickets().size(); i++) {
            if (((TicketPane) ticketPanelPane.getChildren().get(i)).getTitleField().isEmpty())
               return;
            for (int j = 0; j < project.getTickets().get(i).getComments().size(); j++) {
               if (((TicketPane) ticketPanelPane.getChildren().get(i)).getCommentField(j).isEmpty())
                  return;
            }
         }
         
         // Save project data.
         for (Project p : projects) {
            if (p.equals(project)) {
               p.setName(projectName.getText());
               p.setDate(projectStartDate.getValue());
               p.setDesc(projectDesc.getText());
            }
         }
         projectWriter.writeProjects(projects, "./data/project_data.csv");
         
         // Save ticket data.
         for (Ticket t : tickets) {
            if (t.nameEquals(project.getName())) {
               t.setProjectName(projectName.getText());
               for (int i = 0; i < project.getTickets().size(); i++) {
                  if (t.getTitle().equals(project.getTickets().get(i).getTitle())) {
                     t.setTitle(((TicketPane) ticketPanelPane.getChildren().get(i)).getTitleField());
                     t.setDesc(((TicketPane) ticketPanelPane.getChildren().get(i)).getDescField());
                  }
               }
            }
         }
         ticketWriter.writeTickets(tickets, "./data/ticket_data.csv");

         // Save comment data.
         for (Comment c : comments) {
            if (c.nameEquals(project.getName())) {
               c.setProjectName(projectName.getText());
               for (int i = 0; i < project.getTickets().size(); i++) {
                  if (c.getTicketName().equals(project.getTickets().get(i).getTitle())) {
                     c.setTicketName(((TicketPane) ticketPanelPane.getChildren().get(i)).getTitleField());
                     for (int j = 0; j < project.getTickets().get(i).getComments().size(); j++) {
                        if (c.getDateTime().toString().equals(project.getTickets().get(i).getComments().get(j).getDateTime().toString())) {
                           if (!c.getDesc().equals(((TicketPane) ticketPanelPane.getChildren().get(i)).getCommentField(j)))
                              c.setDateTime(LocalDateTime.now());
                           c.setDesc(((TicketPane) ticketPanelPane.getChildren().get(i)).getCommentField(j));
                        }
                     }
                  }
               }
            }
         }
         commentWriter.writeComments(comments, "./data/comment_data.csv");
         
         Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/home.fxml"));
         Scene scene = new Scene(root);
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         stage.setScene(scene);
         stage.show();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   /**
    * On action, switches the current view to the home view.
    * @param event Action event.
    */
   @FXML
   private void onCancelButtonClick(ActionEvent event) {
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
   
   /**
    * Sets the project to be displayed.
    * @param project Project to be displayed.
    */
   public void setProject(Project project) {
      this.project = project;
      projectName.setText(this.project.getName());
      projectStartDate.setValue(this.project.getDate());
      projectDesc.setText(this.project.getDesc());
      for (Ticket t : project.getTickets())
         ticketPanelPane.getChildren().add(new TicketPane(projects, project, t));
   }
   
   /**
    * Deletes a given ticket from the system.
    * @param ticket Ticket to be deleted.
    */
   public void deleteTicket(Ticket ticket) {
      // Remove ticket data.
      project.getTickets().removeIf(t -> t.getTitle().equals(ticket.getTitle()));
      tickets.removeIf(t -> t.getTitle().equals(ticket.getTitle()) && t.getProjectName().equals(ticket.getProjectName()));
      comments.removeIf(c -> c.getTicketName().equals(ticket.getTitle()) && c.getProjectName().equals(ticket.getProjectName()));
      
      // Rewrite.
      ticketWriter.writeTickets(tickets, "./data/ticket_data.csv");
      commentWriter.writeComments(comments, "./data/comment_data.csv");
      
      // Create new TicketPane objects.
      ticketPanelPane.getChildren().clear();
      for (Ticket t : project.getTickets())
         ticketPanelPane.getChildren().add(new TicketPane(projects, project, t));
   }
   
   public void moveTicket(String projectName, Ticket ticket) {
      // Alter data.
      project.getTickets().removeIf(t -> t.getTitle().equals(ticket.getTitle()));
      for (Ticket t : tickets) {
         if (t.getProjectName().equals(ticket.getProjectName()) && t.getTitle().equals(ticket.getTitle()))
            t.setProjectName(projectName);
      }
      for (Comment c : comments) {
         if (c.getProjectName().equals(ticket.getProjectName()) && c.getTicketName().equals(ticket.getTitle()))
            c.setProjectName(projectName);
      }
      
      // Rewrite.
      ticketWriter.writeTickets(tickets, "./data/ticket_data.csv");
      commentWriter.writeComments(comments, "./data/comment_data.csv");
      
      // Create new TicketPane objects.
      ticketPanelPane.getChildren().clear();
      for (Ticket t : project.getTickets())
         ticketPanelPane.getChildren().add(new TicketPane(projects, project, t));
   }

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      projectReader = new ProjectReader();
      ticketReader = new TicketReader();
      commentReader = new CommentReader();
      projectWriter = new ProjectWriter();
      ticketWriter = new TicketWriter();
      commentWriter = new CommentWriter();
      projects = projectReader.readProjects("./data/project_data.csv");
      tickets = ticketReader.readTickets("./data/ticket_data.csv");
      comments = commentReader.readComments("./data/comment_data.csv");
      ticketPanelPane = new VBox();
      
      ticketPanelPane.setStyle("-fx-padding: 10; -fx-spacing: 10;");
      ticketScrollPane.setFitToHeight(true);
      ticketScrollPane.setFitToWidth(true);
      ticketScrollPane.setContent(ticketPanelPane);
   }
}
