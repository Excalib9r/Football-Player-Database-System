package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CountryWiseController implements Initializable {

    private Client client;

    public void setMain(Client client){
        this.client = client;
    }
    @FXML
    private TableView<Country> tableView;

    @FXML
    private TableColumn<Country, String> country;

    @FXML
    private TableColumn<Country, Integer> playerCount;

    @FXML
    private Button back;

    ObservableList<Country> list = FXCollections.observableArrayList();

    @FXML
    void callBack(ActionEvent event) throws Exception {
        client.showPlayerPage();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        country.setCellValueFactory(new PropertyValueFactory<Country, String>("name"));
        playerCount.setCellValueFactory(new PropertyValueFactory<Country, Integer>("playerCount"));
    }

    public void setList() {
        list = FXCollections.observableList(client.getClub().countryWiseCount());
        tableView.setItems(list);
    }
}

