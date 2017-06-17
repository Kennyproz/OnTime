package Controllers;

import Classes.Repository.UserDatabaseContext;
import Classes.Repository.UserRepository;
import Classes.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ken on 13-6-2017.
 */
public class RegisterController implements Initializable {

    @FXML
    Button btnRegister, btnCancel;
    @FXML
    TextField tfRegUsername;
    @FXML
    PasswordField pwfRegPass;
    @FXML
    AnchorPane apRegister;

    UserRepository userRepository;
    User user;

    public void cancel(){
        Stage stage = (Stage)apRegister.getScene().getWindow();
        stage.close();
    }
    public void register(){
        if (!tfRegUsername.getText().isEmpty()){
            if (!pwfRegPass.getText().isEmpty()){
                user = new User(tfRegUsername.getText(),pwfRegPass.getText());
                if (userRepository.addUser(user)){
                    Stage stage = (Stage)apRegister.getScene().getWindow();
                    System.out.println("Registered.");
                    stage.close();
                }
                else {
                    System.out.println("Something went wrong af");
                }
            }
            else {
                System.out.printf("No password inserted.");
            }
        }
        else {
            System.out.println("No username inserted.");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       userRepository = new UserRepository(new UserDatabaseContext());

    }
}
