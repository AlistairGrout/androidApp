package myhelloworldapplication.com.forma203.appwhatsbest.Model;

import java.io.Serializable;

public class Proposition implements Serializable
{

    private int id;
    private String choix;
    private String choixbis;


    public Proposition()
    {
        this.id = id;
        this.choix = choix;
        this.choixbis = choixbis;

    }

    public int getId() {return id;}
    public void setId(int id){this.id = id;}

    public String getChoix() {return choix;}
    public void setChoix(String choix){this.choix = choix;}

    public String getChoixbis() {return choixbis;}
    public void setChoixbis(String choixbis){this.choixbis = choixbis;}





}
