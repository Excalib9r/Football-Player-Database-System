package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Locale;

import static java.lang.System.exit;

public class MenuController {
    private Client client;

    @FXML
    private Label menu;

    @FXML
    private Label clubName;

    @FXML
    private Button exit;

    @FXML
    private Button searchPlayer;

    @FXML
    private Button playerList;

    @FXML
    private Button buy;

    @FXML
    void callBuy(ActionEvent event) throws IOException {
        client.showMarketListPage();
    }

    @FXML
    void callExit(ActionEvent event) {
        exit(0);
    }

    @FXML
    void callPlayerList(ActionEvent event) throws Exception {
        client.showPlayerListPage();
    }

    @FXML
    void callSearchPlayer(ActionEvent event) throws Exception {
        client.showPlayerPage();
    }

    public void setMain(Client client){
        this.client = client;
    }
    public void setClubName(String clubName){
        this.clubName.setText("Welcome to " + clubName.toUpperCase(Locale.ROOT));
    }
}
