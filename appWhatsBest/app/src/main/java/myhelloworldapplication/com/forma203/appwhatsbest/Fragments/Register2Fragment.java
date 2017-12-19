package myhelloworldapplication.com.forma203.appwhatsbest.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.security.auth.callback.Callback;

import myhelloworldapplication.com.forma203.appwhatsbest.MainActivity;
import myhelloworldapplication.com.forma203.appwhatsbest.R;

public class Register2Fragment extends Fragment implements View.OnClickListener
{

    private Callback callback;
    private EditText etMail;
    private EditText etPass;
    private EditText etPassConfirm;
    private Button btnRegister;

    public interface Callback
    {
        void pressSubmit(String message, String mail, String password, String passwordConfirm );
        void registerError(String message);

    }

    @Override
    public void onClick(View view)
    {
       if (view.getId() == R.id.btn_subreg)
        {
            String mail = etMail.getText().toString();
            String password = etPass.getText().toString();
            Log.i("RegisterActivity", mail + " : " + password);
            handleNext();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_register2, container, false);

        btnRegister = (Button) v.findViewById(R.id.btn_subreg);
        btnRegister.setOnClickListener(this);

        etMail = v.findViewById(R.id.register_email);
        etPass = v.findViewById(R.id.register_password);
        etPassConfirm = v.findViewById(R.id.register_check);

        return v;
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

    private void handleNext() {

        String mail = etMail.getText().toString().trim();
        String pass = etPass.getText().toString().trim();
        String passConfirm = etPassConfirm.getText().toString().trim();

        if (mail.equals("") || pass.equals("") || passConfirm.equals("")) {
            this.callback.registerError("Please fill in the spaces");
        } else {
            callback.pressSubmit("You registered on What's Best, welcome !", mail, pass, passConfirm);
        }
    }
}
