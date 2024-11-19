package sample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Country implements Serializable {
    private String name;
    private List<Player> players;
    private int playerCount;

    public Country(){
        playerCount = 0;
        players = new ArrayList();
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public int getPlayerCount(){
        return playerCount;
    }
    public void addPlayer(Player p){
        players.add(p);
        playerCount++;
    }
}
