package GUI;

import Domain.Agent;
import Service.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {
    public TextField textFieldUser;
    public PasswordField textFieldPass;
    public DatePicker birthDate;
    Service service;
    Stage stage;
    Stage parentStage;
    Agent agent;
    public void setService(Service service,Stage stage,Agent agent,Stage parentStage){
        this.service = service;
        this.stage=stage;
        this.agent=agent;
        this.parentStage=parentStage;
    }
    public void handleDelete(ActionEvent actionEvent) {
        service.deleteAgent(agent);
        this.stage.close();
        this.parentStage.close();
    }

    public void handleExit(ActionEvent actionEvent) {
        this.stage.close();
    }

    public void handleEdit(ActionEvent actionEvent) {
        Agent a=new Agent(textFieldUser.getText(),textFieldPass.getText(),birthDate.getValue().toString());
        a.setId(agent.getId());
        int ok=service.updateAgent(a,agent.getUsername());
        if(ok==1){
            MessageAlert.showErrorMessage(null,"This username already exists");
        }
        else{
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Edit", "Account edited");
        }
    }

    public void handleGetAll(ActionEvent actionEvent) {
        try{
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(getClass().getResource("agentiall.fxml"));
            HBox root=loader.load();
            Stage dialogStage=new Stage();
            dialogStage.setTitle("Agents");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene=new Scene(root);
            dialogStage.setScene(scene);
            AllAgentsController loginController=loader.getController();
            loginController.setService(service,dialogStage,this.agent);
            dialogStage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
