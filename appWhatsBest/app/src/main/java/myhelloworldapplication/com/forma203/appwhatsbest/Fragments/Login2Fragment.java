package myhelloworldapplication.com.forma203.appwhatsbest.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import myhelloworldapplication.com.forma203.appwhatsbest.R;

public class Login2Fragment extends Fragment implements View.OnClickListener {

    private Callback callback;
    private EditText etMail;
    private EditText etPassword;
    // TODO login and reset could become local variables (read your warnings :p)
    private Button login;
    private Button reset;
    private Button ret;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // TODO Don't forget that this casting is not necessary
        login = (Button) view.findViewById(R.id.btn_login_submit);
        reset = (Button) view.findViewById(R.id.btn_login_reset);
        etMail = view.findViewById(R.id.et_login_email);
        etPassword = view.findViewById(R.id.et_login_pass);

        login.setOnClickListener(this);
        reset.setOnClickListener(this);

        // TODO since you already have onClick() below, just put this one as well
//        reset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                etMail.setText("");
//                etPassword.setText("");
//            }
//        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.callback = (Callback) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public void onClick(View view) {
//        if (view.getId() == R.id.btn_login_submit) {
//            // TODO too much noise here, since your already get the mail and pass in handleNext(), log these in there as well
////            String mail = etMail.getText().toString();
////            String password = etPassword.getText().toString();
////            Log.i("LoginActivity", mail + " : " + password);
//            handleNext();
//        } else if (view.getId() == R.id.btn_login_reset) {
//            callback.resetActivity();
//        }

        // TODO Even though I like 'if ... else', switch is sometime more readable (even more in Kotlin, you'll see)
        switch (view.getId()) {
            case R.id.btn_login_submit:
                handleNext();
                break;
            case R.id.btn_login_reset:
                callback.resetActivity();
                break;
        }

    }

    private void handleNext() {
        // TODO no need split declaration adn assignment
        // ex: String mail = etMail.getText().toString().trim();
        String mail;
        String pass;
        mail = etMail.getText().toString().trim();
        pass = etPassword.getText().toString().trim();
//        Log.i("LoginActivity", mail + " : " + pass);
        // TODO even though you write more code, String.format() is mor flexible
        Log.i("LoginActivity", String.format("mail = %s, pass = %s", mail, pass));

        // TODO these Strings should be externalized in your strings.xml - waaaaaaay easier to maintain
        // TODO With Java 8 you have access to mail.isEmpty() - more concise, easier to read
        if (mail.equals("") || pass.equals("")) {
            this.callback.loginError("Please fill in the spaces");
        } else if (mail.equals("testmail@hotmail.com") || pass.equals("1234")) {
            callback.pressLogin("Welcome back !", mail, pass);
        }
    }

    // TODO Putting EVERYTHING in relatively edgy in terms of coupling, this should be refactored
    // TODO Callback is too generic could be : LoginPressManager, LoginClickListener, LoginFragmentHost, etc...
    public interface Callback {
        void pressLogin(String message, String mail, String password);

        void pressReset();

        void changeActivityUserProfile();

        void resetActivity();

        void loginError(String message);
    }
}
