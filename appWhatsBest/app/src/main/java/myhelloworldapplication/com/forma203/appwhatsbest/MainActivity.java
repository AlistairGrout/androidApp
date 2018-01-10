package myhelloworldapplication.com.forma203.appwhatsbest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

    //TODO problems with going back button. Must see how to make sure it doesn't leave the app
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        replaceFragment(new StartFragment());
    }

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, fragment)
                .commit();
    }

    // TODO Regioning could help you in terms of readability when you have multiple sources of overrides (callbacks)
    // TODO Select the code you'd like to region (ideally methods) and then press CTRL + ALT + T and then 'region ... endregion'

    // TODO Now, we know what do we implement here : methods of StartFragment.Callback - just a region, no documentation needed
    //region StartFragment.Callback
    @Override
    public void startLogin() {
        replaceFragment(new Login2Fragment());
    }

    @Override
    public void startRegister() {
        replaceFragment(new Register2Fragment());
    }

    @Override
    public void startAbout() {
        replaceFragment(new AboutFragment());
    }
    //endregion

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
    }

    @Override
    public void pressReset() {
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
