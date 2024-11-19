import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Collection {
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
            p.set_name(tokens[0]);
            p.set_country(tokens[1]);
            p.set_age(Integer.parseInt(tokens[2]));
            p.set_height(Double.parseDouble(tokens[3]));
            p.set_club(tokens[4]);
            p.set_position(tokens[5]);
            p.set_number(Integer.parseInt(tokens[6]));
            p.set_weekly_salary(Double.parseDouble(tokens[7]));
            players.add(p);
            addPlayerToClub(p.get_club(), p);
            addPlayerToCountry(p.get_country(), p);
        }
        br.close();
    }

    public void addPlayerToClub(String clubName, Player player){
        if(clubs.size() == 0){
            Club club = new Club();
            club.set_clubName(clubName);
            clubs.add(club);
            club.addPlayer(player);
        }
        else{
            if(!existClub(clubName)){
                Club club = new Club();
                club.set_clubName(clubName);
                clubs.add(club);
                club.addPlayer(player);
            }
            else{
               for(Club club : clubs){
                   if(club.get_clubName().equalsIgnoreCase(clubName)){
                       club.addPlayer(player);
                   }
               }
            }
        }
    }

    public boolean existClub(String clubName){
        for(Club club : clubs){
            if(club.get_clubName().equalsIgnoreCase(clubName))
                return true;
        }
        return false;
    }

    public void addPlayerToCountry(String countryName, Player player){
        if(countries.size() == 0){
            Country country = new Country();
            country.set_countryName(countryName);
            countries.add(country);
            country.addPlayer(player);
        }
        else{
            if(!existCountry(countryName)){
                Country country = new Country();
                country.set_countryName(countryName);
                countries.add(country);
                country.addPlayer(player);
            }
            else{
                for(Country country : countries){
                    if(country.get_CountryName().equalsIgnoreCase(countryName)){
                        country.addPlayer(player);
                    }
                }
            }
        }
    }

    public boolean existCountry(String countryName){
        for(Country country : countries){
            if(country.get_CountryName().equalsIgnoreCase(countryName))
                return true;
        }
        return false;
    }

    public void showPlayerInfo(String name){
        for(Player p : players){
            if(p.get_name().equalsIgnoreCase(name)){
                p.showPlayerInfo();
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("No such player with this name");
        }
        found = false;
    }

    public void showPlayerInfo(String country, String clubName){
        if(clubName.equalsIgnoreCase("Any")){
            for(Player p : players){
                if(p.get_country().equalsIgnoreCase(country)){
                    p.showPlayerInfo();
                    found = true;
                }
            }
        }
        else{
            for(Player p : players){
                if(p.get_country().equalsIgnoreCase(country)){
                    if(p.get_club().equalsIgnoreCase(clubName)){
                        p.showPlayerInfo();
                        found = true;
                }
            }
        }
        }
        if(!found){
            System.out.println("No such player with this country and club");
        }
        found = false;
    }

    public void showPlayerInfoWP(String position){
        for(Player p : players){
            if(p.get_position().equalsIgnoreCase(position)){
                p.showPlayerInfo();
                found = true;
            }
        }
        if(!found){
            System.out.println("No such player with this position");
        }
        found = false;
    }

    public void showPlayerInfo(double min, double max){
        for(Player p : players){
            if(p.get_weekly_salary() >= min && p.get_weekly_salary() <= max){
                p.showPlayerInfo();
                found = true;
            }
        }
        if(!found){
            System.out.println("No such player with this weekly salary range");
        }
        found = false;
    }

    public void countryWiseCount(){
        for(Country country : countries){
            System.out.println(country.get_CountryName() + ": " + country.get_playerCount() + " Player(s)");
        }
        System.out.println();
    }

    public void  clubMaxSalary(String clubName){
        for(Club club : clubs){
            if(club.get_clubName().equalsIgnoreCase(clubName)){
                club.playerWithMaxSalary();
                found = true;
            }
        }
        if(!found){
            System.out.println("No such club with this name");
        }
        found = false;
    }

    public void clubMaxAge(String clubName){
        for(Club club : clubs){
            if(club.get_clubName().equalsIgnoreCase(clubName)){
                club.playerWithMaxAge();
                found = true;
            }
        }
        if(!found){
            System.out.println("No such club with this name");
        }
        found = false;
    }

    public void clubMaxHeight(String clubName){
        for(Club club : clubs){
            if(club.get_clubName().equalsIgnoreCase(clubName)){
                club.playerWithMaxHeight();
                found = true;
            }
        }
        if(!found){
            System.out.println("No such club with this name");
        }
        found = false;
    }

    public void clubTotalYearlySalary(String clubName){
        for(Club club : clubs){
            if(club.get_clubName().equalsIgnoreCase(clubName)){
                club.totalYearlySalary();
                found = true;
            }
        }
        if(!found){
            System.out.println("No such club with this name");
        }
        found = false;
    }

    public void notExitThenAdd(Player p) {
        for (Player player : players) {
            if (player.get_name().equalsIgnoreCase(p.get_name())) {
                System.out.println("Can't add this player. There already exists a player with this name");
                found = true;
                break;
            }
        }
        if (!found) {
            for (Club club : clubs) {
                if (club.get_clubName().equalsIgnoreCase(p.get_club())) {
                    if (club.checkNumber(p.get_number())) {
                        System.out.println("Can't add this player. A club can't have more than one player with the same number");
                        found = true;
                        break;
                    }
                }
            }
        }
        if (!found) {
            players.add(p);
            addPlayerToClub(p.get_club(), p);
            addPlayerToCountry(p.get_country(), p);
            System.out.println("Added successfully");
        }
        found = false;
    }

    public /*static*/ void writeToFile() throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        for (Player p : players) {
            bw.write(p.get_name() + "," + p.get_country() + "," + p.get_age() + "," + p.get_height() + "," + p.get_club() + "," + p.get_position() + "," + p.get_number() + "," + p.get_weekly_salary());
            bw.write("\n");
        }
        bw.close();
    }
}
