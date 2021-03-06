package myhelloworldapplication.com.forma203.appwhatsbest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import myhelloworldapplication.com.forma203.appwhatsbest.Fragments.AboutFragment;
import myhelloworldapplication.com.forma203.appwhatsbest.Fragments.Login2Fragment;
import myhelloworldapplication.com.forma203.appwhatsbest.Fragments.Register2Fragment;
import myhelloworldapplication.com.forma203.appwhatsbest.Fragments.StartFragment;

public class MainActivity extends AppCompatActivity implements
        Login2Fragment.Callback,
        Register2Fragment.Callback,
        StartFragment.Callback,
        AboutFragment.Callback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, new StartFragment())
                .commit();

        //        replaceFragment(new StartFragment());
    }

    //region TODO This should be refactored, not DRY (Don't Repeat Yourself)
    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, fragment)
                .commit();
    }

    @Override
    public void startLogin() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_container, new Login2Fragment());
        ft.addToBackStack(null);
        ft.commit();

        // TODO like this
//        replaceFragment(new Login2Fragment());
    }

    @Override
    public void startRegister() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_container, new Register2Fragment());
        ft.addToBackStack(null);
        ft.commit();

        // TODO like this
//        replaceFragment(new Register2Fragment());
    }

    @Override
    public void startAbout() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_container, new AboutFragment());
        ft.addToBackStack(null);
        ft.commit();

        // TODO like this
//        replaceFragment(new AboutFragment());
    }
    //endregion


    // TODO This is JavaDoc this is where you tell what your functions do.

    /**
     * Redirect user to RealMainActivity on button press.
     *
     * @param message  provided toast message as String
     * @param mail     unused, provided mail
     * @param password unused, provided password
     */
    @Override
    public void pressLogin(String message, String mail, String password) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent toPage = new Intent(MainActivity.this, RealMainActivity.class);
        startActivity(toPage);
        Log.i("MainActivity", "Press Login");
        // TODO Wrong comment placement, see JavaDoc above
        // Log l'user sur son profil utilisateur
    }

    /**
     * TODO Your documentation here
     */
    @Override
    public void pressReset() { //bouton sur le frag log TODO should be JavaDoc
        Intent toReturn = new Intent(MainActivity.this, MainActivity.class);
        startActivity(toReturn);
    }

    @Override
    public void pressSubmit(String message, String mail, String password, String passwordConfirm) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent toGoProfile = new Intent(MainActivity.this, RealMainActivity.class);
        startActivity(toGoProfile);
    }

    @Override
    public void registerError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void changeActivityUserProfile() {
        Intent toProfile = new Intent(MainActivity.this, RealMainActivity.class);
        startActivity(toProfile);
    }

    @Override
    public void resetActivity() {
        Intent toMain = new Intent(MainActivity.this, MainActivity.class);
        startActivity(toMain);
    }

    @Override
    public void loginError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void pressRet() {
        Intent retAgain = new Intent(MainActivity.this, MainActivity.class);
        startActivity(retAgain);
    }
}
