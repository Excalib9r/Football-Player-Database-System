public class Club {
    private String name;
    private Player[] players;
    private int ind;

    public Club(){
        ind = 0;
        players = new Player[7];
    }
    public void set_clubName(String name){
        this.name = name;
    }
    public String get_clubName(){
        return this.name;
    }
    public void addPlayer(Player p){
        if(ind >= 7){
            System.out.println("Player limit reached in a Cub. Can't add more players");
        }
        else{
            players[ind] = p;
            ind++;
        }
    }
    public double getClubMaxSalary(){
        double max = 0;
        for(int i = 0; i < ind; i++){
            if(players[i].get_weekly_salary() >= max)
                max = players[i].get_weekly_salary();
        }
        return max;
    }
    public int getMaxAge(){
        int max = 0;
        for(int i = 0; i < ind; i++){
            if(players[i].get_age() >= max)
                max = players[i].get_age();
        }
        return max;
    }
    public double getMaxHeight(){
        double max = 0;
        for(int i = 0; i < ind; i++){
            if(players[i].get_height() >= max)
                max = players[i].get_height();
        }
        return max;
    }
    public void totalYearlySalary(){
        double total = 0;
        for(int i = 0; i < ind; i++){
            total += players[i].get_weekly_salary();
        }
        total *= 52;
        System.out.printf("Total salary for " + get_clubName() + " is: " + total);
        System.out.println();
    }

    public void playerWithMaxSalary(){
        double salary = getClubMaxSalary();
        for(int i = 0; i < ind; i++) {
            if(players[i].get_weekly_salary() == salary)
                players[i].showPlayerInfo();
        }
    }
    public void playerWithMaxAge(){
        int age = getMaxAge();
        for(int i = 0; i < ind; i++) {
            if(players[i].get_age() == age)
                players[i].showPlayerInfo();
        }
    }
    public void playerWithMaxHeight(){
        double height = getMaxHeight();
        for(int i = 0; i < ind; i++) {
            if(players[i].get_height() == height)
                players[i].showPlayerInfo();
        }
    }
    public boolean checkNumber(int number){
        for(int i = 0; i < ind; i++){
            if(players[i].get_number() == number)
                return true;
        }
        return false;
    }
    /*public boolean checkName(String name){
        for(int i = 0; i < ind; i++){
            if(players[i].get_name() == name)
                return true;
        }
        return false;
    }*/
}
