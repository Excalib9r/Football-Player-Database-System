package server;

import sample.Club;
import sample.Player;
import util.LoginDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClientClubList implements Serializable {
    private Club club;
    private LoginDTO loginDTO;
    private List<Player> marketList;
    public ClientClubList(){
        marketList = new ArrayList<>();
        club = new Club();
        loginDTO = new LoginDTO();
    }
    public ClientClubList(LoginDTO loginDTO, Club club, List<Player> marketList){
        this.marketList = marketList;
        this.club = club;
        this.loginDTO = loginDTO;
    }
    public void setClub(Club club){
        this.club = club;
    }
    public Club getClub(){
        return this.club;
    }
    public void setLoginDTO(LoginDTO loginDTO){
        this.loginDTO = loginDTO;
    }
    public LoginDTO getLoginDTO(){
        return this.loginDTO;
    }
    public void setMarketList(List<Player> marketList){
        this.marketList = marketList;
    }
    public List<Player> getMarketList(){
        return this.marketList;
    }
}
