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

public class SearchPlayerController implements Initializable {
    private Client client;
    private String clbName;

    @FXML
    private TextField playerName;

    @FXML
    private Button pName;

    @FXML
    private TextField CountryName;

    @FXML
    private Button Country;

    @FXML
    private TextField position;

    @FXML
    private Button pPosition;

    @FXML
    private TextField Max;

    @FXML
    private TextField Min;

    @FXML
    private Button salary;

    @FXML
    private Button countryCount;

    @FXML
    private Button back;

    @FXML
    private Button maxSalary;

    @FXML
    private Button maxAge;

    @FXML
    private Button maxHeight;

    @FXML
    private Button totalSalary;

    @FXML
    private TableView<Player> tableView;

    @FXML
    private TableColumn<Player, String> name;

    @FXML
    private TableColumn<Player, String> country;

    @FXML
    private TableColumn<Player, String> club;

    @FXML
    private TableColumn<Player, String> Position;

    @FXML
    private TableColumn<Player, Integer> number;

    @FXML
    private TableColumn<Player, Double> height;

    @FXML
    private TableColumn<Player, Integer> age;

    @FXML
    private TableColumn<Player, Double> wSalary;


    private ObservableList<Player> oList;

    @FXML
    void callBack(ActionEvent event) throws Exception {
        client.showMenuPage();
    }

    @FXML
    void callCountry(ActionEvent event) throws IOException {
        oList.clear();
        tableView.setItems(oList);
        String country = CountryName.getText();
        if(!country.equalsIgnoreCase("")) {
            List<Player> list = client.getClub().getCountryPlayer(country);
            if(list.size() == 0){
                client.showAlert("No Such Country and Club With These Names");
            }
            else {
                oList = FXCollections.observableList(list);
                tableView.setItems(oList);
            }
            CountryName.setText("");
        }
        else
        {
            client.showAlert("Empty Country or Club Name!");
        }
    }

    @FXML
    void callPlayerName(ActionEvent event) throws IOException {
        oList.clear();
        tableView.setItems(oList);
        String name = playerName.getText();
        if(!name.equalsIgnoreCase("")) {
            List<Player> list = client.getClub().getPlayerInfo(name);
            if(list.size() == 0){
                client.showAlert("No such Player With This Name");
            }
            else {
                oList = FXCollections.observableList(list);
                tableView.setItems(oList);
            }
            playerName.setText("");
        }
        else
        {
            client.showAlert("Empty Name!");
        }
    }

    @FXML
    void callPosition(ActionEvent event) throws IOException {
        oList.clear();
        tableView.setItems(oList);
        String post = position.getText();
        if(!post.equalsIgnoreCase("")) {
            List<Player> list = client.getClub().getPlayerInfoWP(post);
            if(list.size() == 0){
                client.showAlert("No Such Player With This Position");
            }
            else {
                oList = FXCollections.observableList(list);
                tableView.setItems(oList);
            }
            position.setText("");
        }
        else
        {
            client.showAlert("Empty Position!");
        }
    }

    @FXML
    void callSalary(ActionEvent event) throws IOException {
        oList.clear();
        tableView.setItems(oList);
        String min = Min.getText();
        String max = Max.getText();
        if(!min.equalsIgnoreCase("") && !max.equalsIgnoreCase("")){
            try{
                double MAX = Double.parseDouble(max);
                double MIN = Double.parseDouble(min);
                List<Player> list = client.getClub().getPlayersInfo(MIN, MAX);
                if(list.size() == 0){
                    client.showAlert("No Such Player Within This Range");
                }
                else {
                    oList = FXCollections.observableList(list);
                    tableView.setItems(oList);
                }
            }catch(Exception e){
                client.showAlert("Invalid Input");
            }
            Min.setText("");
            Max.setText("");
        }
        else
        {
            client.showAlert("Empty Input!");
        }
    }

    @FXML
    void callCountryCount(ActionEvent event) throws IOException {
        oList.clear();
        client.showCountryWisePage();
    }

    @FXML
    void callMaxAge(ActionEvent event) {
        List<Player> list = client.getClub().getClubMaxAge();
        oList = FXCollections.observableList(list);
        tableView.setItems(oList);
    }

    @FXML
    void callMaxHeight(ActionEvent event) {
        List<Player> list = client.getClub().getClubMaxHeight();
        oList = FXCollections.observableList(list);
        tableView.setItems(oList);
    }

    @FXML
    void callMaxSalary(ActionEvent event) {
        List<Player> list = client.getClub().getClubMaxSalary();
        oList = FXCollections.observableList(list);
        tableView.setItems(oList);
    }

    @FXML
    void callTotalSalary(ActionEvent event) throws IOException {
        oList.clear();
        tableView.setItems(oList);
        double totalSalary = client.getClub().totalYearlySalary();
        client.showClubYearlySalaryPage(totalSalary);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        oList = FXCollections.observableArrayList();
        name.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        country.setCellValueFactory(new PropertyValueFactory<Player, String>("country"));
        club.setCellValueFactory(new PropertyValueFactory<Player, String>("club"));
        Position.setCellValueFactory(new PropertyValueFactory<Player, String>("position"));
        age.setCellValueFactory(new PropertyValueFactory<Player, Integer>("age"));
        number.setCellValueFactory(new PropertyValueFactory<Player, Integer>("number"));
        height.setCellValueFactory(new PropertyValueFactory<Player, Double>("height"));
        wSalary.setCellValueFactory(new PropertyValueFactory<Player, Double>("weeklySalary"));
        oList.clear();
    }

    public void setMain(Client client) {
        this.client = client;
    }

    public void setClbName(String clbName) {
        this.clbName = clbName;
    }
}

