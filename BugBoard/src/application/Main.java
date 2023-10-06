package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
   @Override
   public void start(Stage stage) {
      try {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home_view.fxml"));
         Scene scene = new Scene(fxmlLoader.load());
         
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
