package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Collection implements Serializable{
    private List <Player> players;
    private List <Club> clubs;
    private List <Country> countries;
    boolean found = false;
    private static final String INPUT_FILE_NAME = "players.txt";
    private static final String OUTPUT_FILE_NAME = "players.txt";

    public Collection() throws IOException {
        players = new ArrayList();
        clubs = new ArrayList();
        countries = new ArrayList();
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String[] tokens = line.split(",");
            Player p = new Player();
            p.setName(tokens[0]);
            p.setCountry(tokens[1]);
            p.setAge(Integer.parseInt(tokens[2]));
            p.setHeight(Double.parseDouble(tokens[3]));
            p.setClub(tokens[4]);
            p.setPosition(tokens[5]);
            p.setNumber(Integer.parseInt(tokens[6]));
            p.setWeeklySalary(Double.parseDouble(tokens[7]));
            players.add(p);
            addPlayerToClub(p.getClub(), p);
            addPlayerToCountry(p.getCountry(), p);
        }
        br.close();
    }

    public void addPlayerToClub(String clubName, Player player){
        if(clubs.size() == 0){
            Club club = new Club();
            club.setClubName(clubName);
            clubs.add(club);
            club.addPlayer(player);
        }
        else{
            if(!existClub(clubName)){
                Club club = new Club();
                club.setClubName(clubName);
                clubs.add(club);
                club.addPlayer(player);
            }
            else{
               for(Club club : clubs){
                   if(club.getClubName().equalsIgnoreCase(clubName)){
                       club.addPlayer(player);
                   }
               }
            }
        }
    }

    public boolean existClub(String clubName){
        for(Club club : clubs){
            if(club.getClubName().equalsIgnoreCase(clubName))
                return true;
        }
        return false;
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

    public Club getClubList(String clubName){
        for(Club club : clubs){
            if(club.getClubName().equalsIgnoreCase(clubName))
                return club;
        }
        return null;
    }

    public String notExitThenAdd(Player p) {
        for (Player player : players) {
            if (player.getName().equalsIgnoreCase(p.getName())) {
                return "Can't add this player. There already exists a player with this name";
            }
        }
        for (Club club : clubs) {
            if (club.getClubName().equalsIgnoreCase(p.getClub())) {
                if (club.checkNumber(p.getNumber())) {
                    return "Can't add this player. A club can't have more than one player with the same number";
                }
            }
        }
        if(!p.checkPosition(p.getPosition()))
            return "Can't add this player. The position of the player is invalid";
        players.add(p);
        addPlayerToClub(p.getClub(), p);
        addPlayerToCountry(p.getCountry(), p);
        return "Added successfully";
    }

    public /*static*/ void writeToFile() throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        for (Player p : players) {
            bw.write(p.getName() + "," + p.getCountry() + "," + p.getAge() + "," + p.getHeight() + "," + p.getClub() + "," + p.getPosition() + "," + p.getNumber() + "," + p.getWeeklySalary());
            bw.write("\n");
        }
        bw.close();
    }

    public List<Club> getClubs() {
        return clubs;
    }
}
