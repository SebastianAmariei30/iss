package GUI;

import Domain.Agent;
import Domain.Produs;
import Service.Service;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AgentController {
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
    Service service;
    Stage stage;
    Agent agent;
    ObservableList<Produs> model= FXCollections.observableArrayList();

    public void setService(Service service,Stage stage,Agent agent) {
        this.service = service;
        this.stage=stage;
        this.agent=agent;
        initModel();
    }
    public void initialize(){
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
        stage.close();
    }
}
