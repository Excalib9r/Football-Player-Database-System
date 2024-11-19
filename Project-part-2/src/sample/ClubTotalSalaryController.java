package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ClubTotalSalaryController {

    Stage stage;

    @FXML
    private Label clubName;

    @FXML
    private Label salary;

    @FXML
    private Button back;

    @FXML
    void callBack(ActionEvent event) {
        stage.close();
    }

    public void setClubName(String name){
        clubName.setText(name);
    }

    public void setSalary(double totalSalary){
        salary.setText(totalSalary + " Dollars");
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

}
