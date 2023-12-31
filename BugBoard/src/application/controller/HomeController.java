package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.comment.Comment;
import application.pane.ProjectPane;
import application.project.Project;
import application.reader.CommentReader;
import application.reader.ProjectReader;
import application.reader.TicketReader;
import application.ticket.Ticket;
import application.writer.CommentWriter;
import application.writer.ProjectWriter;
import application.writer.TicketWriter;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * HomeController controls the behavior of home.fxml.
 */
public class HomeController implements Initializable {
   @FXML
   private VBox projectPanelPane;
   
   @FXML
   private TextField searchBar;
   
   @FXML
   private ComboBox<String> searchPreferenceBar;
   
   private ProjectReader projectReader;
   private TicketReader ticketReader;
   private CommentReader commentReader;
   private ProjectWriter projectWriter;
   private TicketWriter ticketWriter;
   private CommentWriter commentWriter;
   private List<Project> projects;
   private List<Ticket> tickets;
   private List<Comment> comments;
   
   /**
    * On action, switches the current view to the new project view.
    * @param event Action event.
    */
   @FXML
   private void onNewProjectButtonClick(ActionEvent event) {
      try {
         Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/new_project.fxml"));
         Scene scene = new Scene(root);
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         stage.setScene(scene);
         stage.show();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   /**
    * On action, switches the current view to the new ticket view.
    * @param event Action event.
    */
   @FXML
   private void onNewTicketButtonClick(ActionEvent event) {
      try {
         Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/new_ticket.fxml"));
         Scene scene = new Scene(root);
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         stage.setScene(scene);
         stage.show();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   /**
    * Searches projects (and tickets if specified) and displays search results.
    * @param substring Substring to be matched.
    */
   private void search(String substring) {
      List<Project> matchingProjects = new ArrayList<>();

      for (Project p : projects) {
         if (p.getName().toLowerCase().contains(substring.toLowerCase()))
            matchingProjects.add(p);
         else if (searchPreferenceBar.getValue().equals("Projects, Tickets")) {
            for (Ticket ticket : p.getTickets()) {
               if (ticket.getTitle().toLowerCase().contains(substring.toLowerCase())) {
                  matchingProjects.add(p);
                  break;
               }
            }
         }
      }

      projectPanelPane.getChildren().clear();
      for (Project p : matchingProjects)
         projectPanelPane.getChildren().add(new ProjectPane(p));
   }
   
   /**
    * Deletes project in projects, tickets and comments by matching project names
    * @param project
    */
   public void deleteProject(Project project) {
      // Remove project data.
      projects.removeIf(p -> p.equals(project));
      tickets.removeIf(t -> t.getProjectName().equals(project.getName()));
      comments.removeIf(c -> c.getProjectName().equals(project.getName()));

      // Rewrite.
      projectWriter.writeProjects(projects, "./data/project_data.csv");
      ticketWriter.writeTickets(tickets, "./data/ticket_data.csv");
      commentWriter.writeComments(comments, "./data/comment_data.csv");
      
      // Create new ProjectPane objects.
      projectPanelPane.getChildren().clear();
      for (Project p : projects)
         projectPanelPane.getChildren().add(new ProjectPane(p));
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
      
      // Add tickets to projects, add comments to tickets, add projects to the display.
      for (Project p : projects) {
         List<Ticket> projectTickets = new ArrayList<Ticket>();
         for (Ticket t : tickets) {
            List<Comment> ticketComments = new ArrayList<Comment>();
            for (Comment c : comments) {
               if (c.getTicketName().equals(t.getTitle()) && c.getProjectName().equals(t.getProjectName()))
                  ticketComments.add(c);
            }
            if (t.getProjectName().equals(p.getName()))
               projectTickets.add(t);
            t.setComments(ticketComments);
         }
         p.setTickets(projectTickets);
         projectPanelPane.getChildren().add(new ProjectPane(p));
      }
      
      // Set search behavior.
      searchBar.textProperty().addListener((observable, oldValue, newValue) -> search(searchBar.getText()));
      searchPreferenceBar.valueProperty().addListener((observable, oldValue, newValue) -> search(searchBar.getText()));
      
      // Set search preferences.
      searchPreferenceBar.setItems(FXCollections.observableArrayList("Projects", "Projects, Tickets"));
      searchPreferenceBar.setValue("Projects");
   }
}