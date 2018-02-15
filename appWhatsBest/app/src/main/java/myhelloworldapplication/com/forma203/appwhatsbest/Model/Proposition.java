package myhelloworldapplication.com.forma203.appwhatsbest.Model;

import java.io.Serializable;

public class Proposition implements Serializable
{

    private int id;
    private String firstChoice;
    private String secondChoice;

    public Proposition() {

    }

    public Proposition (int id, String firstChoice, String secondChoice){
        this.id = id;
        this.firstChoice = firstChoice;
        this.secondChoice = secondChoice;

    }

    public int getId() {return id;}
    public void setId(int id){this.id = id;}

    public String getFirstChoice() {return firstChoice;}
    public void setFirstChoice(String firstChoice){this.firstChoice = firstChoice;}

    public String getSecondChoice() {return secondChoice;}
    public void setSecondChoice(String secondChoice){this.secondChoice = secondChoice;}


    @Override
        public String toString() { return firstChoice; }

}



