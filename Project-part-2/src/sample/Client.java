package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import util.NetworkUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Client extends Application {
    private Stage stage;
    private Club club;
    private NetworkUtil networkUtil;
    private String clbName;
    private List<Player> marketList;
    private ObservableList<Player> oList;
    private boolean inMarket;
    private boolean inList;

    public void setInMarket(boolean inMarket){
        this.inMarket = inMarket;
    }
    public boolean getInMarket(){
        return this.inMarket;
    }
    public void setInList(boolean inList){
        this.inList = inList;
    }
    public boolean getInList(){
        return this.inList;
    }

    public void setClbName(String name){ clbName = name; }
    public String getClbName() { return clbName; }
    public Stage getStage() {
        return stage;
    }
    public void setClub(Club club){
        this.club = club;
        club.callAdd();
    }
    public Club getClub(){
        return this.club;
    }
    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }
    public void setMarketList(List<Player> marketList){
        this.marketList = marketList;
        oList = FXCollections.observableList(this.marketList);
    }

    public boolean existPlayerInMarket(String name){
        for(Player player : marketList){
            if(player.getName().equalsIgnoreCase(name))
                return true;
        }
        return false;
    }

    public Player getPlayer(String name){
        for(Player player : marketList){
            if(player.getName().equalsIgnoreCase(name))
                return player;
        }
        return null;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        club = new Club();
        marketList = new ArrayList<>();
        oList = FXCollections.observableArrayList();
        connectToServer();
        showLoginPage();
    }

    private void connectToServer() throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        networkUtil = new NetworkUtil(serverAddress, serverPort);
        new ReadThread(this);
    }

    public void showLoginPage() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        LoginController controller = loader.getController();
        controller.setMain(this);

        stage.setTitle("Login");
        stage.setScene(new Scene(root, 400, 250));
        stage.show();
    }

    public void showMenuPage() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("menu.fxml"));
        Parent root = loader.load();

        MenuController controller = loader.getController();
        controller.setMain(this);
        controller.setClubName(getClbName());
        stage.setTitle("Home");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void showPlayerPage() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("searchPlayer.fxml"));
        Parent root = loader.load();

        SearchPlayerController controller = loader.getController();
        controller.setMain(this);
        controller.setClbName(getClbName());
        stage.setTitle("Player");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void showPlayerListPage() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("seeList.fxml"));
        Parent root = loader.load();

        setInList(true);

        SeeListController controller = loader.getController();
        controller.setMain(this);
        controller.setPlayerInfo(club.getPlayers());
        stage.setTitle("Club");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void showMarketListPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("marketList.fxml"));
        Parent root = loader.load();

        setInMarket(true);

        MarketListController controller = loader.getController();
        controller.setMain(this);
        controller.setList(oList);
        stage.setTitle("Club");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void showCountryWisePage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("countryWise.fxml"));
        Parent root = loader.load();

        CountryWiseController controller = loader.getController();
        controller.setMain(this);
        controller.setList();
        stage.setTitle("Country Wise Count");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void showClubYearlySalaryPage(double totalSalary) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("clubTotalSalary.fxml"));
        Parent root = loader.load();

        ClubTotalSalaryController controller = loader.getController();
        controller.setStage(stage);
        controller.setClubName(getClub().getClubName());
        controller.setSalary(totalSalary);

        stage.setTitle("PlayerInfo");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void showAlert(String str) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText(str);
        alert.setContentText("Not Found");
        alert.showAndWait();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public ObservableList<Player> forThreadSeeList(){
        return FXCollections.observableList(getClub().getPlayers());
    }

    public ObservableList<Player> forThreadMarketList(){
        return oList;
    }
}
