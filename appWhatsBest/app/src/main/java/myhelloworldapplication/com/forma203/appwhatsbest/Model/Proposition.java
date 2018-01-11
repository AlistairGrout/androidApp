package myhelloworldapplication.com.forma203.appwhatsbest.Model;

import java.io.Serializable;

public class Proposition implements Serializable
{

    private int id;
    private String choice1;
    private String choice2;

    public Proposition() {

    }

    public Proposition (int id, String choice1, String choice2){
        this.id = id;
        this.choice1 = choice1;
        this.choice2 = choice2;

    }

    public int getId() {return id;}
    public void setId(int id){this.id = id;}

    public String getChoice1() {return choice1;}
    public void setChoice1(String choice1){this.choice1 = choice1;}

    public String getChoice2() {return choice2;}
    public void setChoice2(String choice2){this.choice2 = choice2;}


    @Override
        public String toString() { return choice1; }

}



