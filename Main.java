package garden_sim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Start1");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainPane.fxml")));
        primaryStage.setTitle("Bees are friends");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        MainController.getTheController().setStage(primaryStage);
        MainController.getTheController().scene = scene;
        MainController.getTheController().setEventHandlers();

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
