package myhelloworldapplication.com.forma203.appwhatsbest.Model;

import java.io.Serializable;



public class UserQuestions implements Serializable {

    private int id;
    private String refUser;
    private String refQuestion;
    private Boolean refFinal;

    public UserQuestions() {
        this.id = id;
        this.refUser = refUser;
        this.refQuestion = refQuestion;
        this.refFinal = refFinal;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRefUser() {
        return refUser;
    }


    public void setRefUser(String refUser) {
        this.refUser = refUser;
    }

    public String getRefQuestion() {
        return refQuestion;
    }

    public void setRefQuestion(String refQuestion) {
        this.refQuestion = refQuestion;
    }

    public Boolean getRefFinal() {
        return refFinal;
    }

    public void setRefFinal(Boolean refFinal) {
        this.refFinal = refFinal;
    }


}
