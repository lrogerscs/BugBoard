package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;

/**
 * Main loads the default FXML file, sets window name/icon.
 */
public class Main extends Application {
   @Override
   public void start(Stage stage) {
      try {
         Scene scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("fxml/home.fxml")));
         stage.getIcons().add(new Image(getClass().getClassLoader().getResource("image/BugBoardIcon.png").toExternalForm()));
         stage.setTitle("BugBoard");
         stage.setScene(scene);
         stage.show();
      } catch(Exception e) {
         e.printStackTrace();
      }
   }
   
   public static void main(String[] args) {
      launch(args);
   }
}
