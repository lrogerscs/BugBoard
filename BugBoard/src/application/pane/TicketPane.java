package application.pane;

import java.util.List;

import application.comment.Comment;
import application.controller.EditProjectController;
import application.controller.NewCommentController;
import application.project.Project;
import application.ticket.Ticket;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * TicketPane displays ticket information/options inside of a container.
 */
public class TicketPane extends VBox {
   List<Project> projects;
   Project project;
   Ticket ticket;
   TextField ticketTitle;
   TextArea ticketDesc;
   Button newCommentButton, deleteButton;
   MenuButton moveMenuButton;
   HBox ticketTitlePane, buttonPane;
   
   public TicketPane(List<Project> projects, Project project, Ticket ticket) {
      this.projects = projects;
      this.project = project;
      this.ticket = ticket;
      ticketTitle = new TextField(this.ticket.getTitle());
      ticketDesc = new TextArea(this.ticket.getDesc());
      newCommentButton = new Button("+ New Comment");
      deleteButton = new Button("Delete");
      moveMenuButton = new MenuButton("Move");
      ticketTitlePane = new HBox(new Label("Title:"), ticketTitle);
      buttonPane = new HBox(newCommentButton, moveMenuButton, deleteButton);
      
      ticketTitle.setFocusTraversable(false);
      ticketDesc.setFocusTraversable(false);
      ticketTitlePane.setSpacing(10);
      buttonPane.setSpacing(10);
      buttonPane.setAlignment(Pos.CENTER_RIGHT);
      
      // Set button behavior.
      newCommentButton.setOnAction(event -> loadNewComment(event));
      deleteButton.setOnAction(event -> deleteTicket(event));
      moveMenuButton.setOnAction(event -> moveTicket(event));
      
      getChildren().addAll(ticketTitlePane, new Label("Description:"), ticketDesc, new Label("Comments:"));
      
      // Add comments, menu items.
      addComments();
      addMoveMenuItems();
      
      getChildren().add(buttonPane);
      
      ticketDesc.getStyleClass().add("desc-box");
      newCommentButton.getStyleClass().add("new-comment-button");
      deleteButton.getStyleClass().add("delete-button");
      getStyleClass().add("info-pane");
   }
   
   public String getTitleField() {
      return ticketTitle.getText();
   }
   
   public String getDescField() {
      return ticketDesc.getText();
   }
   
   public String getCommentField(int index) {
      return ((TextArea) getChildren().get(4 + index * 2)).getText();
   }
   
   private void addComments() {
      for (Comment c : ticket.getComments()) {
         TextArea commentDesc = new TextArea(c.getDesc());
         Label commentDateTime = new Label(c.getDateTime().toString());
         commentDesc.setFocusTraversable(false);
         commentDesc.getStyleClass().add("desc-box");
         commentDateTime.getStyleClass().add("time-label");
         getChildren().addAll(commentDesc, commentDateTime);
      }
   }
   
   private void addMoveMenuItems() {
      for (Project p : projects) {
         MenuItem item = new MenuItem(p.getName());
         item.setOnAction(event -> moveTicket(event));
         moveMenuButton.getItems().add(item);
      }
   }
   
   private void loadNewComment(ActionEvent event) {
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
   }
   
   private void moveTicket(ActionEvent event) {
      try {
         FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/edit_project.fxml"));
         Parent root = loader.load();
         EditProjectController controller = loader.getController();
         Scene scene = new Scene(root);
         Stage stage = (Stage) ((Node) ((MenuItem) event.getSource()).getParentPopup().getOwnerNode()).getScene().getWindow();
         
         stage.setScene(scene);
         stage.show();
         controller.setProject(project);
         controller.moveTicket(((MenuItem) event.getSource()).getText(),ticket);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   private void deleteTicket(ActionEvent event) {
      try {
         FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/edit_project.fxml"));
         Parent root = loader.load();
         EditProjectController controller = loader.getController();
         Scene scene = new Scene(root);
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         
         stage.setScene(scene);
         stage.show();
         controller.setProject(project);
         controller.deleteTicket(ticket);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
