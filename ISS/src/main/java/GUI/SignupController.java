package GUI;

import Domain.Agent;
import Service.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignupController {
    @FXML
    public TextField textFieldUser;
    @FXML
    public PasswordField textFieldPass;
    @FXML
    public DatePicker birthDate;
    Service service;
    Stage stage;

    public void setService(Service service,Stage stage) {
        this.service = service;
        this.stage=stage;
    }

    public void handleExit(ActionEvent actionEvent) {
        stage.close();
    }

    public void handleSignup(ActionEvent actionEvent) {
        Agent user=service.findAgentByUn(textFieldUser.getText());
        if(user!=null){
            MessageAlert.showErrorMessage(null,"This username already exists");
            return;
        }
        service.addAgent(textFieldUser.getText(),textFieldPass.getText(),birthDate.getValue().toString());
        MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Signup", "Account created");
        stage.close();
    }
}
