package application.controller;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

/**
 * HomeController controls the behavior of home.fxml.
 */
public class HomeController implements Initializable {
   @FXML
   private VBox projectPanelPane;
   
   @FXML
   private TextField searchBar;
   
   private ProjectReader projectReader;
   private TicketReader ticketReader;
   private CommentReader commentReader;
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
   
   private List<Project> search(String substring) {
	   List<Project> matchingProjects = new ArrayList<>();
	   for (Project p : projects) {
	      if (p.getName().toLowerCase().contains(substring.toLowerCase()))
	         matchingProjects.add(p);
	      else {
	         for (Ticket ticket : p.getTickets()) {
	            if (ticket.getTitle().toLowerCase().contains(substring.toLowerCase())) {
	               matchingProjects.add(p);
	               break;
	            }
	         }
	      }
	   }
	   return matchingProjects;
   }

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      projectReader = new ProjectReader();
      ticketReader = new TicketReader();
      commentReader = new CommentReader();
      projects = projectReader.readProjects("./data/project_data.csv");
      tickets = ticketReader.readTickets("./data/ticket_data.csv");
      comments = commentReader.readComments("./data/comment_data.csv");
      
      // Add tickets to projects, add comments to tickets, add projects to the display.
      for (Project project : projects) {
         List<Ticket> projectTickets = new ArrayList<Ticket>();
         for (Ticket ticket : tickets) {
            List<Comment> ticketComments = new ArrayList<Comment>();
            for (Comment comment : comments) {
               if (comment.getTicketName().equals(ticket.getTitle()) 
                     && comment.getProjectName().equals(ticket.getProjectName())) {
                  ticketComments.add(comment);
               }
            }
            if (ticket.getProjectName().equals(project.getName()))
               projectTickets.add(ticket);
            ticket.setComments(ticketComments);
         }
         project.setTickets(projectTickets);
         projectPanelPane.getChildren().add(new ProjectPane(project));
      }
      
      // Set search behavior.
      searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
         List<Project> matchingProjects = search(searchBar.getText());
         projectPanelPane.getChildren().clear();
         for (Project project : matchingProjects)
            projectPanelPane.getChildren().add(new ProjectPane(project));
      });
   }
}