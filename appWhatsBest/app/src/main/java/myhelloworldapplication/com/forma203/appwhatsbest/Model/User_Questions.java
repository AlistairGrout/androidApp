package myhelloworldapplication.com.forma203.appwhatsbest.Model;

import java.io.Serializable;


public class User_Questions implements Serializable
{

    private int id;
    private String refuser;
    private String refquestion;
    private Boolean refinal;

    public User_Questions()
    {
        this.id = id;
        this.refuser = refuser;
        this.refquestion = refquestion;
        this.refinal = refinal;

    }

    public int getId() {return id;}
    public void setId(int id){this.id = id;}

    public String getRefuser() {return refuser;}
    public void setRefuser(String refuser){this.refuser = refuser;}

    public String getRefquestion() {return refquestion;}
    public void setRefquestion(String refquestion){this.refquestion = refquestion;}

    public Boolean getRefinal() {return refinal;}
    public void setRefinal(Boolean refinal){this.refinal = refinal;}


}
