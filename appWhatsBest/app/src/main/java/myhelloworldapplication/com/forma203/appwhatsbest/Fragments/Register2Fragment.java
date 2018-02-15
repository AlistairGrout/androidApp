package myhelloworldapplication.com.forma203.appwhatsbest.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import myhelloworldapplication.com.forma203.appwhatsbest.R;

public class Register2Fragment extends Fragment implements View.OnClickListener
{

    private Callback callback;
    private EditText etMail;
    private EditText etPass;
    private EditText etPassConfirm;
    private FirebaseAuth mAuth;


    public interface Callback
    {
        void pressSubmit(String registered); //, String mail, String password, String passwordConfirm
        void registerError(String error);
        void registerStep(String etMail, String etPass);

    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.btn_subreg:
                handleNext();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_register2, container, false);

        Button btnRegister = v.findViewById(R.id.btn_subreg);
        btnRegister.setOnClickListener(this);

        etMail = v.findViewById(R.id.register_email);
        etPass = v.findViewById(R.id.register_password);
        etPassConfirm = v.findViewById(R.id.register_check);
        mAuth = FirebaseAuth.getInstance();

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
    }


    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        this.callback = (Callback) getActivity();
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        this.callback = null;
    }

    public void createAccount() {
        callback.registerStep(etMail.getText().toString(), etPass.getText().toString());
    }

    private void handleNext() {
        String mail = etMail.getText().toString().trim();
        String pass = etPass.getText().toString().trim();
        String passConfirm = etPassConfirm.getText().toString().trim();


        if (mail.equals("") || pass.equals("") || passConfirm.equals("")) {
            this.callback.registerError(String.valueOf(R.string.empty_spaces));
            return;
        } else {
            callback.pressSubmit(String.valueOf(R.string.user_registered));
            createAccount();
        }

    }
}
