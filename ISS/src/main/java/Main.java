import GUI.StartController;
import Repository.AgentDBRepository;
import Repository.ComandaDBRepository;
import Repository.ComandaRepository;
import Repository.ProdusDBRepository;
import Service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException{
        Properties serverProps = new Properties();
        try {
            serverProps.load(new FileReader("bd.config"));
            //System.setProperties(serverProps);

            System.out.println("Properties set. ");
            //System.getProperties().list(System.out);
            serverProps.list(System.out);
        } catch (IOException e) {
            System.out.println("Cannot find bd.config " + e);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("start.fxml"));
        AgentDBRepository repoa = new AgentDBRepository();
        ProdusDBRepository repop = new ProdusDBRepository();
        ComandaDBRepository repoc = new ComandaDBRepository();
        Service service = new Service(repoa, repop,repoc);
        HBox userLayout = fxmlLoader.load();
        stage.setScene(new Scene(userLayout));
        StartController controller = fxmlLoader.getController();
        controller.setService(service,stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
