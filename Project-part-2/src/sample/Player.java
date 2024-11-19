package sample;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private String country;
    private String club;
    private String position;
    private int age;
    private int number;
    private double height;
    private double weeklySalary;

    public Player(){};

    public Player(String name, String country, int age, double height, String club, String position, int number, double weeklySalary){
        this.name = name;
        this.country = country;
        this.age = age;
        this.height = height;
        this.club = club;
        this.position = position;
        this.number = number;
        this.weeklySalary = weeklySalary;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setCountry(String country){
        this.country = country;
    }
    public String getCountry(){
        return this.country;
    }
    public void setClub(String club){
        this.club = club;
    }
    public String getClub(){
        return this.club;
    }
    public void setPosition(String position){
        this.position = position;
    }
    public String getPosition(){
        return this.position;
    }
    public void setAge(int age){
        this.age = age;
    }
    public int getAge(){
        return this.age;
    }
    public void setNumber(int number){
        this.number = number;
    }
    public int getNumber(){
        return this.number;
    }
    public void setHeight(double height){
        this.height = height;
    }
    public double getHeight(){
        return this.height;
    }
    public void setWeeklySalary(double weekly_salary){
        this.weeklySalary = weekly_salary;
    }
    public double getWeeklySalary(){
        return this.weeklySalary;
    }
    public void showPlayerInfo(){
        System.out.println("Player name: " + name + ", " + "Country: " + country);
        System.out.println("Age: " + age + ", " + "Height: " + height);
        System.out.println("Club: " + club + ", " + "Position: " + position);
        System.out.println("Number: " + number + ", " + "Weekly salary: " + weeklySalary);
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
