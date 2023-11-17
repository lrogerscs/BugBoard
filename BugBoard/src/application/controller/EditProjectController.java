package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.pane.TicketPane;
import application.project.Project;
import application.ticket.Ticket;
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
   
   private VBox ticketPanelPane;
   private Project project;
   
   /**
    * On action, switches the current view to the home view.
    * @param event Action event.
    */
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
      for (Ticket ticket : project.getTickets())
         ticketPanelPane.getChildren().add(new TicketPane(project, ticket));
   }

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      ticketPanelPane = new VBox();
      ticketPanelPane.setStyle("-fx-padding: 10; -fx-spacing: 10;");
      ticketScrollPane.setFitToHeight(true);
      ticketScrollPane.setFitToWidth(true);
      ticketScrollPane.setContent(ticketPanelPane);
   }
}
