package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SeeListController implements Initializable {

    private Client client;

    @FXML
    private TableView<Player> tableView;

    @FXML
    private TableColumn<Player, String> name;

    @FXML
    private TableColumn<Player, String> country;

    @FXML
    private TableColumn<Player, String> club;

    @FXML
    private TableColumn<Player, String> position;

    @FXML
    private TableColumn<Player, Integer> number;

    @FXML
    private TableColumn<Player, Double> height;

    @FXML
    private TableColumn<Player, Integer> age;

    @FXML
    private TableColumn<Player, Double> salary;

    @FXML
    private Button back;

    @FXML
    private TextField playerName;

    @FXML
    private Button sell;

    @FXML
    private Button refresh;

    private ObservableList<Player> list;

    @FXML
    void callBack(ActionEvent event) throws Exception {
        client.setInList(false);
        client.showMenuPage();
    }

    @FXML
    public void callRefresh(ActionEvent actionEvent) {
        tableView.setItems(client.forThreadSeeList());
    }


        @FXML
    void callSell(ActionEvent event) throws IOException {
        String name = playerName.getText();
        playerName.setText("");
        if(!name.equalsIgnoreCase("")){
            Player player = client.getClub().searchPlayer(name);
            if(player == null){
                client.showAlert("Player Not Found");
            }
            client.getNetworkUtil().write(player);
        }
        else
        {
            client.showAlert("Invalid Input");
        }
    }

    public void setPlayerInfo(List<Player> players){
        list = FXCollections.observableList(players);
        tableView.setItems(list);
        /*SeeList thread = new SeeList();
        new Thread(thread :: threadSeeList).start();*/
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        list = FXCollections.observableArrayList();
        name.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        country.setCellValueFactory(new PropertyValueFactory<Player, String>("country"));
        club.setCellValueFactory(new PropertyValueFactory<Player, String>("club"));
        position.setCellValueFactory(new PropertyValueFactory<Player, String>("position"));
        age.setCellValueFactory(new PropertyValueFactory<Player, Integer>("age"));
        number.setCellValueFactory(new PropertyValueFactory<Player, Integer>("number"));
        height.setCellValueFactory(new PropertyValueFactory<Player, Double>("height"));
        salary.setCellValueFactory(new PropertyValueFactory<Player, Double>("weeklySalary"));
    }

    public void setMain(Client client){
        this.client = client;
    }

    /*public void threadSeeList(){
        while(true) {
            tableView.setItems(main.forThreadSeeList());
        }
    }*/

}

