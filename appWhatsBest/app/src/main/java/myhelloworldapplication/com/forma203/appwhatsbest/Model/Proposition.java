package myhelloworldapplication.com.forma203.appwhatsbest.Model;

import java.io.Serializable;

// TODO If you use Serializable to pass this through Activity / Fragment bundles, try Parcelable instead
public class Proposition implements Serializable {

    private int id;
    private String choice1;
    private String choice2;


    public Proposition() {
        this.id = id;
        this.choice1 = choice1;
        this.choice2 = choice2;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChoice() {
        return choice1;
    }

    public void setChoice(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }


}
