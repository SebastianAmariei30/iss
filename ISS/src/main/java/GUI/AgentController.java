package GUI;

import Domain.Agent;
import Domain.Produs;
import Service.Service;
import Uitls.Observer;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AgentController implements Observer {
    @FXML
    public TableView<Produs> tableView;
    @FXML
    public TableColumn<Produs,String> tableColumnNume;
    @FXML
    public TableColumn<Produs,String> tableColumnDescriere;
    @FXML
    public TableColumn<Produs,Double> tableColumnPret;
    @FXML
    public TableColumn<Produs,Integer> tableColumnCantitate;
    @FXML
    public TableColumn<Produs,Long> tableColumnId;
    public TextField textFieldCantitate;
    Service service;
    Stage stage;
    Agent agent;
    ObservableList<Produs> model= FXCollections.observableArrayList();

    public void setService(Service service,Stage stage,Agent agent) {
        this.service = service;
        this.stage=stage;
        this.agent=agent;
        service.addObserver(this);
        initModel();
    }
    public void initialize(){
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnNume.setCellValueFactory(new PropertyValueFactory<>("nume"));
        tableColumnDescriere.setCellValueFactory(new PropertyValueFactory<>("descriere"));
        tableColumnPret.setCellValueFactory(new PropertyValueFactory<>("pret"));
        tableColumnCantitate.setCellValueFactory(new PropertyValueFactory<>("cantitate"));
        tableView.setItems(model);
    }
    public void initModel() {
        Iterable<Produs> produses = Arrays.stream(service.getAllProducts()).toList();
        List<Produs> l=new ArrayList<>();
        produses.forEach(l::add);
        model.setAll(l);
    }
    public void handleExit(ActionEvent actionEvent) {
        service.removeObserver(this);
        stage.close();
    }

    public void handleComanda(ActionEvent actionEvent) {
        Produs produs=tableView.getSelectionModel().getSelectedItem();
        if(produs==null){
            MessageAlert.showErrorMessage(null,"Nu ati selectat nici un produs");
            return;
        }
        int cantitate=Integer.parseInt(textFieldCantitate.getText());
        if(service.order(produs.getId(),cantitate, agent.getId())==1){
            initModel();
        }
        else
            MessageAlert.showErrorMessage(null,"Cantitatea ceruta este prea mare");
    }

    @Override
    public void update() {
        initModel();
    }

    public void handleSettings(ActionEvent actionEvent) {
        try{
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(getClass().getResource("setaricont.fxml"));
            HBox root=loader.load();
            Stage dialogStage=new Stage();
            dialogStage.setTitle("Settings");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene=new Scene(root);
            dialogStage.setScene(scene);
            SettingsController loginController=loader.getController();
            loginController.setService(service,dialogStage,this.agent,this.stage);
            dialogStage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
