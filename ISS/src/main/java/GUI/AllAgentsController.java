package GUI;

import Domain.Agent;
import Domain.Produs;
import Service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllAgentsController {
    @FXML
    public TableColumn<Agent, String> tableColumnBirthdate;
    @FXML
    public TableColumn<Agent,String> tableColumnUsername;
    @FXML
    public TableView<Agent> tableView;
    Service service;
    Stage stage;
    ObservableList<Agent> model= FXCollections.observableArrayList();

    public void setService(Service service,Stage stage,Agent agent) {
        this.service = service;
        this.stage=stage;
        initModel();
    }
    public void initialize(){
        tableColumnUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        tableColumnBirthdate.setCellValueFactory(new PropertyValueFactory<>("dataNasterii"));
        tableView.setItems(model);
    }
    public void initModel() {
        Iterable<Agent> agents = Arrays.stream(service.getAllAgents()).toList();
        List<Agent> l=new ArrayList<>();
        agents.forEach(l::add);
        model.setAll(l);
    }
    public void handleExit(ActionEvent actionEvent) {
        stage.close();
    }
}
