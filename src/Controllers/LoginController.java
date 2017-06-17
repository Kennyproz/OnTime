package Controllers;

import Classes.Repository.UserDatabaseContext;
import Classes.Repository.UserRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ken on 9-6-2017.
 */
public class LoginController implements Initializable{

    @FXML TextField tfUsername;
    @FXML PasswordField pwfPass;
    @FXML AnchorPane content;

    UserRepository userRepository = new UserRepository(new UserDatabaseContext());

    public void enterAppliction(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = (Parent) fxmlLoader.load(getClass().getResource("/Forms/OnTimeForm.fxml"));
            Stage primaryStage = new Stage();
            Stage oldStage = (Stage)content.getScene().getWindow();
            primaryStage.setTitle("OnTime");
            primaryStage.setScene(new Scene(root, 365, 365));
            System.out.println("Welcome "+ tfUsername.getText());
            oldStage.close();
            primaryStage.showAndWait();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void login(){
        if (!tfUsername.getText().isEmpty() && !pwfPass.getText().isEmpty()){
            if(userRepository.login(tfUsername.getText(),pwfPass.getText())) {
                enterAppliction();
            }
        }
        else{
            System.out.println("Username or password is empty.");
        }

    }

    public void register(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = (Parent) fxmlLoader.load(getClass().getResource("/Forms/RegisterForm.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Register");
            primaryStage.setScene(new Scene(root, 365, 365));
            primaryStage.showAndWait();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
