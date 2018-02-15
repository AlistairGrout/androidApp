package myhelloworldapplication.com.forma203.appwhatsbest.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import myhelloworldapplication.com.forma203.appwhatsbest.Fragments.Login2Fragment;
import myhelloworldapplication.com.forma203.appwhatsbest.Fragments.Register2Fragment;
import myhelloworldapplication.com.forma203.appwhatsbest.Fragments.StartFragment;
import myhelloworldapplication.com.forma203.appwhatsbest.R;

public class MainActivity extends AppCompatActivity implements
        Login2Fragment.Callback,
        Register2Fragment.Callback,
        StartFragment.Callback {

    FirebaseAuth mAuth;
//TODO problems with going back button. Must see how to make sure it doesn't leave the app
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
     replaceFragment(new StartFragment());
    }

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, fragment)
                .commit();
    }


    //region StartFragment.callback
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

        Intent toAbout = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(toAbout);
    }
    //endregion



    @Override
    public void pressLogin(String login) {
        Toast.makeText(this, this.getString(R.string.user_logged), Toast.LENGTH_SHORT).show();
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
    public void pressSubmit(String registered) {
        Toast.makeText(this, this.getString(R.string.user_registered), Toast.LENGTH_SHORT).show();
        Intent toGoProfile = new Intent(MainActivity.this, RealMainActivity.class);
        startActivity(toGoProfile);
    }

    //region Errors
    @Override
    public void loginError(String error) {
        Toast.makeText(this, this.getString(R.string.empty_spaces), Toast.LENGTH_SHORT).show();
        //String string = getString(R.string.empty_spaces);
    }

    @Override
    public void registerError(String error) {
        Toast.makeText(this, this.getString(R.string.empty_spaces), Toast.LENGTH_SHORT).show();
    }
    //endregion

    @Override
    public void resetActivity() {
        Intent toMain = new Intent(MainActivity.this, MainActivity.class);
        startActivity(toMain);
    }

    @Override
    public void changeActivityUserProfile() {
        Intent toProfile = new Intent(MainActivity.this, RealMainActivity.class);
        startActivity(toProfile);
    }

    @Override
    public void registerStep(String etMail, String etPassword) {
        mAuth.createUserWithEmailAndPassword(etMail, etPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("raf", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("raf", "createUserWithEmail:failure", task.getException());
                            updateUI(null);
                        }

                    }
                });
    }
    private void updateUI(FirebaseUser currentUser) {
    }





}
