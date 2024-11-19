public class Player {
    private String name;
    private String country;
    private String club;
    private String position;
    private int age;
    private int number;
    private double height;
    private double weekly_salary;

    public Player(){};
    public Player(String name, String country, int age, double height, String club, String position, int number, double weekly_salary){
        this.name = name;
        this.country = country;
        this.age = age;
        this.height = height;
        this.club = club;
        this.position = position;
        this.number = number;
        this.weekly_salary = weekly_salary;
    }
    public void set_name(String name){
        this.name = name;
    }
    public String get_name(){
        return this.name;
    }
    public void set_country(String country){
        this.country = country;
    }
    public String get_country(){
        return this.country;
    }
    public void set_club(String club){
        this.club = club;
    }
    public String get_club(){
        return this.club;
    }
    public void set_position(String position){
        this.position = position;
    }
    public String get_position(){
        return this.position;
    }
    public void set_age(int age){
        this.age = age;
    }
    public int get_age(){
        return this.age;
    }
    public void set_number(int number){
        this.number = number;
    }
    public int get_number(){
        return this.number;
    }
    public void set_height(double height){
        this.height = height;
    }
    public double get_height(){
        return this.height;
    }
    public void set_weekly_salary(double weekly_salary){
        this.weekly_salary = weekly_salary;
    }
    public double get_weekly_salary(){
        return this.weekly_salary;
    }
    public void showPlayerInfo(){
        System.out.println("Player name: " + name + ", " + "Country: " + country);
        System.out.println("Age: " + age + ", " + "Height: " + height);
        System.out.println("Club: " + club + ", " + "Position: " + position);
        System.out.println("Number: " + number + ", " + "Weekly salary: " + weekly_salary);
        System.out.println();
    }
    public boolean checkPosition(String position){
        if(position.equalsIgnoreCase("Goalkeeper"))
            return true;
        else if(position.equalsIgnoreCase("Defender"))
            return true;
        else if(position.equalsIgnoreCase("Midfielder"))
            return true;
        else if(position.equalsIgnoreCase("Forward"))
            return true;
        else
            return false;
    }
}
