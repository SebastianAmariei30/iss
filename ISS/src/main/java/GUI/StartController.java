package GUI;

import Service.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {
    Service service;
    Stage stage;

    public void setService(Service service,Stage stage) {
        this.service = service;
        this.stage=stage;
    }

    public void handleLogin(ActionEvent actionEvent) {
        try{
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(getClass().getResource("login.fxml"));
            HBox root=loader.load();
            Stage dialogStage=new Stage();
            dialogStage.setTitle("Login");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene=new Scene(root);
            dialogStage.setScene(scene);
            LoginController loginController=loader.getController();
            loginController.setService(service,dialogStage);
            dialogStage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void handleSignup(ActionEvent actionEvent) {
        try{
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(getClass().getResource("signup.fxml"));
            HBox root=loader.load();
            Stage dialogStage=new Stage();
            dialogStage.setTitle("Signup");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene=new Scene(root);
            dialogStage.setScene(scene);
            SignupController loginController=loader.getController();
            loginController.setService(service,dialogStage);
            dialogStage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
