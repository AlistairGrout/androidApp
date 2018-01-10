package myhelloworldapplication.com.forma203.appwhatsbest.Model;

import java.io.Serializable;

// TODO If you use Serializable to pass this through Activity / Fragment bundles, try Parcelable instead
public class User implements Serializable {
    private int id;
    private String email;
    private String password;

    public User(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password +
                '}';
    }


}


