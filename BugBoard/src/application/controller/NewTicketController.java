package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.comment.Comment;
import application.project.Project;
import application.reader.ProjectReader;
import application.reader.TicketReader;
import application.ticket.Ticket;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * NewTicketController controls the behavior of new_ticket.fxml.
 */
public class NewTicketController implements Initializable {
   @FXML
   private ComboBox<String> projectComboBox;
   
   @FXML
   private TextField ticketTitle;
   
   @FXML
   private TextArea ticketDesc;
   
   private ProjectReader projectReader;
   private TicketReader ticketReader;
   private TicketWriter ticketWriter;
   private List<Ticket> tickets;
   private List<String> projectNames;
   
   /**
    * On action, (if possible) saves information then switches to the home view.
    * @param event Action event.
    */
   @FXML
   private void onSaveButtonClick(ActionEvent event) {
      try {
         // Check violations.
         if (projectComboBox.getValue() == null || ticketTitle.getText() == null || ticketTitle.getText().isEmpty())
            return;
         
         for (Ticket t : tickets) {
            if (t.getProjectName().equals(projectComboBox.getValue()) 
                  && t.getTitle().toLowerCase().equals(ticketTitle.getText().toLowerCase()))
               return;
         }
         
         // Save ticket information.
         tickets.add(new Ticket(projectComboBox.getValue(), ticketTitle.getText(), ticketDesc.getText(), new ArrayList<Comment>()));
         ticketWriter.writeTickets(tickets, "./data/ticket_data.csv");
         
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

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      projectReader = new ProjectReader();
      ticketReader = new TicketReader();
      ticketWriter = new TicketWriter();
      tickets = ticketReader.readTickets("./data/ticket_data.csv");
      projectNames = new ArrayList<String>();
      
      // Initialize projects for ComboBox.
      for (Project p : projectReader.readProjects("./data/project_data.csv"))
         projectNames.add(p.getName());
      projectComboBox.setItems(FXCollections.observableArrayList(projectNames));
   }
}
