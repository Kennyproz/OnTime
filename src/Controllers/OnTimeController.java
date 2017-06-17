package Controllers;

import Classes.Message;
import Classes.OnTimeCommunicator;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class OnTimeController implements Initializable{

    @FXML
    ListView lvAppointments, lvContacts;
    @FXML
    Button btnAppInfo,btnAppRefresh,btnConSendMsg,btnConAdd,btnConInfo, btnConDelete, btnLogout, btnSendMsg,btnSendBroad;
    @FXML
    Label lblAccName,lblAccContact,lblAccSendMsg,lblAccRecieveMsg;
    @FXML
    AnchorPane content;
    @FXML
    TextField tfTitle,tfMessage,tfLocation;


    private OnTimeCommunicator onTimeCommunicator;

    private String property;
    Message message;

    public void publisherConnect(){
        property = "Message";
        try{
        onTimeCommunicator = new OnTimeCommunicator(this);
        onTimeCommunicator.connectToPublisher("localhost",1099);
        onTimeCommunicator.subscribe(property);
        onTimeCommunicator.register(property);
        }
        catch (RemoteException re){
            System.out.println("Something went wrong with publisherConnect");
        }
    }

    public void requestMessage(String property, Message message){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lvAppointments.getItems().add(message);
                System.out.println("Message:" + message.toString());
            }
        });
    }

    public void sendMessagePressed(){
        Message message = new Message(tfTitle.getText(), tfMessage.getText(),tfLocation.getText());
        onTimeCommunicator.broadcast(property, message);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            this.onTimeCommunicator = new OnTimeCommunicator(this);
        }
        catch (RemoteException re){
            re.printStackTrace();
        }
        publisherConnect();

    }

    public void newMessage(Message message){

    }

    public void logout(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = (Parent) fxmlLoader.load(getClass().getResource("/Forms/LoginForm.fxml"));
            Stage primaryStage = new Stage();
            Stage oldStage = (Stage)content.getScene().getWindow();
            primaryStage.setTitle("Login");
            primaryStage.setScene(new Scene(root, 365, 365));
            System.out.println("Logged out succesfully.");
            oldStage.close();
            primaryStage.showAndWait();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        publisherConnect();
    }
}
