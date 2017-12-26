package myhelloworldapplication.com.forma203.appwhatsbest.Model;

import java.io.Serializable;


// TODO No underscores. PERIOD §!§!§! -> UserQuestions
public class User_Questions implements Serializable {

    private int id;
    private String refuser; // TODO lower camel case -> refUser
    private String refquestion;
    private Boolean refinal;

    public User_Questions() {
        this.id = id;
        this.refuser = refuser;
        this.refquestion = refquestion;
        this.refinal = refinal;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRefuser() {
        return refuser;
    }

    // TODO see, lack of proper camel case leads to semantic issues -> what is a Refuser ?
    public void setRefuser(String refuser) {
        this.refuser = refuser;
    }

    public String getRefquestion() {
        return refquestion;
    }

    public void setRefquestion(String refquestion) {
        this.refquestion = refquestion;
    }

    public Boolean getRefinal() {
        return refinal;
    }

    public void setRefinal(Boolean refinal) {
        this.refinal = refinal;
    }


}
