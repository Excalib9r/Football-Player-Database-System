import java.util.ArrayList;
import java.util.List;

public class Country {
    private String name;
    private List<Player> players;
    private int playerCount;

    public Country(){
        playerCount = 0;
        players = new ArrayList();
    }
    public void set_countryName(String name){
        this.name = name;
    }
    public String get_CountryName(){
        return this.name;
    }
    public int get_playerCount(){
        return playerCount;
    }
    public void addPlayer(Player p){
        players.add(p);
        playerCount++;
    }
    /* showPlayersInfo(){
        System.out.println("Country name: " + get_CountryName());
        for(Player p : players)
            p.showPlayerInfo();
    }*/
}
