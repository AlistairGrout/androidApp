package myhelloworldapplication.com.forma203.appwhatsbest.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

import myhelloworldapplication.com.forma203.appwhatsbest.R;

public class Login2Fragment extends Fragment implements View.OnClickListener {


    private Callback callback;
    private EditText etMail;
    private EditText etPassword;
    private FirebaseAuth mAuth;
    private final String TAG = "failure log";
    private Context ctx;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_login2, container, false);
        Button login =  v.findViewById(R.id.btn_login_submit);
        Button reset =  v.findViewById(R.id.btn_login_reset);
        etMail = v.findViewById(R.id.et_login_email);
        etPassword = v.findViewById(R.id.et_login_pass);

        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etMail.setText("");
                etPassword.setText("");
            }
        });

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }


    //TODO problems with login. There's an error must put a breakpoint or Logcat here
    public void signIn() {
        mAuth.signInWithEmailAndPassword(etMail.getText().toString(), etPassword.getText().toString())
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            updateUI(null);
                        }
                    }
                });
        }

    //TODO use it (it's grey rip)
    public void getCurrentUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
        }
    }

    private void updateUI(FirebaseUser currentUser) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.callback = (Callback) getActivity();
        ctx = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_login_submit:
                signIn();
                break;
            case R.id.btn_login_reset:
                callback.resetActivity();
                break;
        }

    }

    //TODO use it or erase it but gotta do something with this
    /*private void handleNext() {

        String mail = etMail.getText().toString().trim();
        String pass = etPassword.getText().toString().trim();
        Log.i("LoginActivity", String.format("mail = %s, pass = %s", mail, pass));

        if (mail.equals("") || pass.equals("")) {
            this.callback.loginError(String.valueOf(R.string.empty_spaces));
        } else if (mail.equals("testmail@hotmail.com") || pass.equals("1234")) {
            callback.pressLogin(String.valueOf(R.string.user_logged));
        }
    }*/

    // TODO Putting EVERYTHING in relatively edgy in terms of coupling, this should be refactored
    public interface Callback {
        void pressLogin(String login);
        //String mail, String password

        void pressReset();

        void changeActivityUserProfile();

        void resetActivity();

        void loginError(String error);
    }
}
