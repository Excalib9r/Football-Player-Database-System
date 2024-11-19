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
import server.SellablePlayer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MarketListController implements Initializable {

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
    private Button buy;

    @FXML
    private TextField playerName;

    @FXML
    private Button refresh;

    private ObservableList<Player> list;

    @FXML
    void callBack(ActionEvent event) throws Exception {
        client.setInMarket(false);
        client.showMenuPage();
    }

    @FXML
    public void callRefresh(ActionEvent actionEvent) {
        tableView.setItems(client.forThreadMarketList());
    }

    @FXML
    void callBuy(ActionEvent event) throws IOException {
        String name = playerName.getText();
        playerName.setText("");
        if(!name.equalsIgnoreCase("")){
            if(client.existPlayerInMarket(name)){
                Player player = client.getPlayer(name);
                SellablePlayer sellablePlayer = new SellablePlayer(player, player.getClub().equalsIgnoreCase(client.getClbName()));
                client.getNetworkUtil().write(sellablePlayer);
                if(!player.getClub().equalsIgnoreCase(client.getClbName())){
                    player.setClub(client.getClub().getClubName());
                    client.getClub().addPlayer(player);
                }
            }
            else {
                client.showAlert("Player Doesn't exit in the Market");
            }
        }
        else{
            client.showAlert("Invalid Input");
        }

    }

    public void setMain(Client client) {
        this.client = client;
    }

    public void setList(ObservableList<Player> list){
        this.list = list;
        tableView.setItems(list);
        /*MarketList thread = new MarketList();
        new Thread(thread :: threadMarketList).start();*/
    }

    @Override
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

    /*public void threadMarketList(){
        while(true){
            tableView.setItems(main.forThreadMarketList());
        }
    }*/
}

