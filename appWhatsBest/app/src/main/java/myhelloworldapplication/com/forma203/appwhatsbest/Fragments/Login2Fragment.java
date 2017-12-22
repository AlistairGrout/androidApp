package myhelloworldapplication.com.forma203.appwhatsbest.Fragments;

import android.content.Context;
import android.os.Bundle;
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
    private Button login;
    private Button reset;
    private Button ret;

    public Login2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login2, container, false);
        login = (Button) v.findViewById(R.id.btn_login_submit);
        reset = (Button) v.findViewById(R.id.btn_login_reset);
        etMail = v.findViewById(R.id.et_login_email);
        etPassword = v.findViewById(R.id.et_login_pass);

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

        if (view.getId() == R.id.btn_login_submit) {
            String mail = etMail.getText().toString();
            String password = etPassword.getText().toString();
            Log.i("LoginActivity", mail + " : " + password);
            handleNext();
        } else if (view.getId() == R.id.btn_login_reset) {
            callback.resetActivity();
        }

    }

    private void handleNext() {

        String mail;
        String pass;
        mail = etMail.getText().toString().trim();
        pass = etPassword.getText().toString().trim();

        if (mail.equals("") || pass.equals("")) {
            this.callback.loginError("Please fill in the spaces");
        } else if (mail.equals("testmail@hotmail.com") || pass.equals("1234")) {
            callback.pressLogin("Welcome back !", mail, pass);
        }
    }

    // TODO Putting EVERYTHING in relatively edgy in terms of coupling, this should be refactored
    public interface Callback {
        void pressLogin(String message, String mail, String password);

        void pressReset();

        void changeActivityUserProfile();

        void resetActivity();

        void loginError(String message);
    }
}
