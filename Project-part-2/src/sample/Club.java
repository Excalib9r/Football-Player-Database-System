package sample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Club implements Serializable {
    private String name;
    private List<Player> players;
    private List<Country> countries;

    public Club(){
        countries = new ArrayList<>();
        players = new ArrayList<>();
    }
    public void setClubName(String name){
        this.name = name;
    }
    public String getClubName(){
        return this.name;
    }
    public List<Player> getPlayers(){
        return players;
    }
    public void addPlayer(Player p){
        players.add(p);
    }
    public void removePlayer(Player p) {
        for(Player player : players){
            if(player.getName().equalsIgnoreCase(p.getName())){
                players.remove(player);
                break;
            }
        }
    }

    public List<Player> getPlayerInfo(String name){
        List<Player> plr = new ArrayList<>();
        for(Player player : players){
            if(player.getName().equalsIgnoreCase(name)){
                plr.add(player);
                break;
            }
        }
        return plr;
    }

    public List<Player> getPlayerInfoWP(String position){
        List<Player> plr = new ArrayList<>();
        for(Player player : players){
            if(player.getPosition().equalsIgnoreCase(position)){
                plr.add(player);
            }
        }
        return plr;
    }

    public List<Player>getPlayersInfo(double min, double max){
        List<Player> plr = new ArrayList<>();
        for(Player player : players){
            if(player.getWeeklySalary() >= min && player.getWeeklySalary() <= max){
                plr.add(player);
            }
        }
        return plr;
    }

    public List<Player> getCountryPlayer(String name){
        List<Player> plr = new ArrayList<>();
        for(Player player : players){
            if(player.getCountry().equalsIgnoreCase(name)){
                plr.add(player);
            }
        }
        return plr;
    }

    public List<Player> getClubMaxSalary(){
       List <Player> plr = new ArrayList<>();
        double max = getMaxSalary();
        for(Player player : players){
            if(player.getWeeklySalary() == max)
                plr.add(player);
        }
        return plr;
    }
    public List<Player> getClubMaxAge(){
        List<Player> plr = new ArrayList<>();
        int max = getMaxAge();
        for(Player player : players){
            if(player.getAge() == max)
                plr.add(player);
        }
        return plr;
    }
    public List<Player> getClubMaxHeight(){
        List<Player> plr = new ArrayList<>();
        double max = getMaxHeight();
        for(Player player : players){
            if(player.getHeight() == max)
                plr.add(player);
        }
       return plr;
    }
    public double totalYearlySalary(){
        double total = 0;
        for(Player player : players) {
            total += player.getWeeklySalary();
        }
        total *= 52;
        return total;
    }
    public boolean checkNumber(int number){
        for(Player player : players){
            if(player.getHeight() == number)
                return true;
        }
        return false;
    }
    public double getMaxSalary(){
        double max = -1;
        for(Player player : players){
            if(player.getWeeklySalary() >= max)
                max = player.getWeeklySalary();
        }
        return max;
    }
    public int getMaxAge(){
        int max = -1;
        for(Player player : players){
            if(player.getAge() >= max)
                max = player.getAge();
        }
        return max;
    }
    public double getMaxHeight(){
        double max = -1;
        for(Player player : players){
            if(player.getHeight() >= max)
                max = player.getHeight();
        }
        return max;
    }

    public Player searchPlayer(String playerName){
        for(Player player : players){
            if(player.getName().equalsIgnoreCase(playerName))
                return player;
        }
        return null;
    }

    public List<Country> countryWiseCount() {
        return countries;
    }

    public void callAdd(){
        for(Player player : players){
            addPlayerToCountry(player.getCountry(), player);
        }
    }

    public void addPlayerToCountry(String countryName, Player player){
        if(countries.size() == 0){
            Country country = new Country();
            country.setName(countryName);
            countries.add(country);
            country.addPlayer(player);
        }
        else{
            if(!existCountry(countryName)){
                Country country = new Country();
                country.setName(countryName);
                countries.add(country);
                country.addPlayer(player);
            }
            else{
                for(Country country : countries){
                    if(country.getName().equalsIgnoreCase(countryName)){
                        country.addPlayer(player);
                    }
                }
            }
        }
    }

    public boolean existCountry(String countryName){
        for(Country country : countries){
            if(country.getName().equalsIgnoreCase(countryName))
                return true;
        }
        return false;
    }

}
