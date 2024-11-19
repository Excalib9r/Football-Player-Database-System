import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Collection c = new Collection();
        int n;
        boolean b1 = false;
        boolean b2 = false;

        Scanner scn = new Scanner(System.in);
        System.out.println();
        System.out.println("Main Menu:");
        System.out.println("(1) Search Players");
        System.out.println("(2) Search Clubs");
        System.out.println("(3) Add player");
        System.out.println("(4) Exit System");
        System.out.print("Enter an option: ");
        n = scn.nextInt();

        while(n != 4){
            if(b1)
                b2 = false;
            else if(n != 1 || n != 2 || n != 3)
                b2 = true;
            if(n == 1){
                int x;
                boolean b3 = true;
                System.out.println();
                System.out.println("Player Searching Options:");
                System.out.println("(1) By Player Name");
                System.out.println("(2) By Club and Country");
                System.out.println("(3) By Position");
                System.out.println("(4) By Salary Range");
                System.out.println("(5) Country-wise player count");
                System.out.println("(6) Back to Main Menu");
                System.out.print("Enter an option: ");
                x = scn.nextInt();
                scn.nextLine();
                while(x != 6){
                    if(n == 0)
                        b3 = true;

                    if(x == 1){
                        String name;
                        System.out.println();
                        System.out.println("Input a player name");
                        name = scn.nextLine();
                        c.showPlayerInfo(name);
                        x = -1;
                        b1 = true;
                        b3 = false;
                        n = -1;
                    }
                    else if(x == 2){
                        String country;
                        String clubName;
                        System.out.println();
                        System.out.println("Input a country name");
                        country = scn.nextLine();
                        System.out.println("Input a club name");
                        clubName = scn.nextLine();
                        c.showPlayerInfo(country, clubName);
                        x = -1;
                        b1 = true;
                        b3 = false;
                        n = -1;
                    }
                    else if(x == 3){
                        String position;
                        System.out.println();
                        System.out.println("Input a position");
                        position = scn.nextLine();
                        c.showPlayerInfoWP(position);
                        x = -1;
                        b1 = true;
                        b3 = false;
                        n = -1;
                    }
                    else if(x == 4){
                        double max;
                        double min;
                        System.out.println();
                        System.out.println("Input min and max salary as range");
                        System.out.print("Min: ");
                        min = scn.nextDouble();
                        System.out.print("Max: ");
                        max = scn.nextDouble();
                        c.showPlayerInfo(min, max);
                        x = -1;
                        b1 = true;
                        b3 = false;
                        n = -1;
                    }
                    else if(x == 5){
                        c.countryWiseCount();
                        x = -1;
                        b1 = true;
                        b3 = false;
                        n = -1;
                    }
                    else{
                        if(b3){
                            System.out.println("Please Input A Valid Number");
                            b3 = false;
                        }
                        System.out.println();
                        System.out.println("Player Searching Options:");
                        System.out.println("(1) By Player Name");
                        System.out.println("(2) By Club and Country");
                        System.out.println("(3) By Position");
                        System.out.println("(4) By Salary Range");
                        System.out.println("(5) Country-wise player count");
                        System.out.println("(6) Back to Main Menu");
                        System.out.print("Enter an option: ");
                        n = 0;
                        x = scn.nextInt();
                        scn.nextLine();
                    }
                }
                if(x == 6){
                    b1 = true;
                    n = -1;
                }
            }
            else if(n == 2){
                int x;
                boolean b4 = true;
                System.out.println();
                System.out.println("Club Searching Options:");
                System.out.println("(1) Player(s) with the maximum salary of a club");
                System.out.println("(2) Player(s) with the maximum age of a club");
                System.out.println("(3) Player(s) with the maximum height of a club");
                System.out.println("(4) Total yearly salary of a club");
                System.out.println("(5) Back to Main Menu");
                System.out.print("Enter an option: ");
                x = scn.nextInt();
                scn.nextLine();
                while(x != 5){
                    if(n == 0)
                        b4 = true;

                    if(x == 1){
                        String clubName;
                        System.out.println();
                        System.out.println("Input a club name");
                        clubName = scn.nextLine();
                        c.clubMaxSalary(clubName);
                        x = -1;
                        b1 = true;
                        b4 = false;
                        n = -1;
                    }
                    else if(x == 2){
                        String clubName;
                        System.out.println();
                        System.out.println("Input a club name");
                        clubName = scn.nextLine();
                        c.clubMaxAge(clubName);
                        x = -1;
                        b1 = true;
                        b4 = false;
                        n = -1;
                    }
                    else if(x == 3){
                        String clubName;
                        System.out.println();
                        System.out.println("Input a club name");
                        clubName = scn.nextLine();
                        c.clubMaxHeight(clubName);
                        x = -1;
                        b1 = true;
                        b4 = false;
                        n = -1;
                    }
                    else if(x == 4){
                        String clubName;
                        System.out.println();
                        System.out.println("Input a club name");
                        clubName = scn.nextLine();
                        c.clubTotalYearlySalary(clubName);
                        x = -1;
                        b1 = true;
                        b4 = false;
                        n = -1;
                    }
                    else{
                        if(b4){
                            System.out.println("Please Input A Valid Number");
                            b4 = false;
                        }
                        System.out.println();
                        System.out.println("Club Searching Options:");
                        System.out.println("(1) Player(s) with the maximum salary of a club");
                        System.out.println("(2) Player(s) with the maximum age of a club");
                        System.out.println("(3) Player(s) with the maximum height of a club");
                        System.out.println("(4) Total yearly salary of a club");
                        System.out.println("(5) Back to Main Menu");
                        System.out.print("Enter an option: ");
                        n = 0;
                        x = scn.nextInt();
                        scn.nextLine();
                    }
                }
                if(x == 5){
                    b1 = true;
                    n = -1;
                }
            }
            else if(n == 3){
                scn.nextLine();
                String name;
                String country;
                String club;
                String position;
                int age;
                int number;
                double height;
                double weekly_salary;
                System.out.println();
                System.out.println("Please input player information correctly");
                System.out.print("Player name: ");
                name = scn.nextLine();
                System.out.print("Country: ");
                country = scn.nextLine();
                System.out.print("Age : ");
                age = scn.nextInt();
                System.out.print("Height : ");
                height = scn.nextDouble();
                scn.nextLine();
                System.out.print("Club : ");
                club = scn.nextLine();
                System.out.print("Position : ");
                position = scn.nextLine();
                System.out.print("Number : ");
                number = scn.nextInt();
                System.out.print("Weekly salary : ");
                weekly_salary = scn.nextDouble();
                Player p = new Player(name,country,age,height,club,position,number,weekly_salary);
                if(!p.checkPosition(position))
                    System.out.println("Can't add this player. The position of the player is invalid");
                else
                    c.notExitThenAdd(p);
                b1 = true;
                n = -1;
            }
            else{
                if(b2){
                    System.out.println("Please Input A Valid Number");
                    b2 = false;
                }
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("(1) Search Players");
                System.out.println("(2) Search Clubs");
                System.out.println("(3) Add player");
                System.out.println("(4) Exit System");
                System.out.print("Enter an option: ");
                b1 = false;
                n = scn.nextInt();

            }
        }
        if(n == 4){
            c.writeToFile();
        }
    }
}
