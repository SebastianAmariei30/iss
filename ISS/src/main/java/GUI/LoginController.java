package GUI;

import Domain.Agent;
import Service.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    public TextField textFieldUser;
    @FXML
    public PasswordField textFieldPass;
    Service service;
    Stage stage;

    public void setService(Service service,Stage stage) {
        this.service = service;
        this.stage=stage;
    }

    public void handleLogin(ActionEvent actionEvent) {
        try{
            Agent user=service.findAgentByUnPs(textFieldUser.getText(),textFieldPass.getText());
            if(user==null){
                MessageAlert.showErrorMessage(null,"Incorect username/password");
                return;
            }
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(getClass().getResource("agent.fxml"));
            HBox root=loader.load();
            Stage dialogStage=new Stage();
            dialogStage.setTitle("Login");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene=new Scene(root);
            dialogStage.setScene(scene);
            AgentController loginController=loader.getController();
            loginController.setService(service,dialogStage,user);
            dialogStage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void handleExit(ActionEvent actionEvent) {
        stage.close();
    }
}
