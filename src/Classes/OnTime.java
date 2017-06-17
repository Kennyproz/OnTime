package Classes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Ken on 11-6-2017.
 */
public class OnTime extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Forms/LoginForm.fxml"));
        primaryStage.setTitle("Classes.OnTime");
        primaryStage.setScene(new Scene(root, 365, 365));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
