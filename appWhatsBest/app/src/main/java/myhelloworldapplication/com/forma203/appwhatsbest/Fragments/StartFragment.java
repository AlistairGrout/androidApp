package myhelloworldapplication.com.forma203.appwhatsbest.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import myhelloworldapplication.com.forma203.appwhatsbest.R;

public class StartFragment extends Fragment implements View.OnClickListener {

    // TODO ease of use / maintenance, this could be an activity (easier transitions)

    private Callback callback;
    // TODO Warning told you ! Should be local
    private Button btnLogin;
    private Button btnRegister;
    private Button btnAbout;

    public StartFragment() {
        // Required empty public constructor
        // TODO not really :p
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_start, container, false);

        // TODO this should move below, in onViewCreated (cleaner lifecycle)
        // TODO unnecessary cast with androidStudio 3.x
//        btnLogin = (Button) v.findViewById(R.id.btn_log);
        btnRegister = (Button) v.findViewById(R.id.btn_register);
        btnAbout = (Button) v.findViewById(R.id.btn_about);

        // TODO since all you do with those buttons is setting a listener, should be shorter
        v.findViewById(R.id.btn_log).setOnClickListener(this); // clear
        btnRegister.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Callback) {
            callback = (Callback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_log:
                callback.startLogin();
                break;

            case R.id.btn_register:
                callback.startRegister();
                break;

            case R.id.btn_about:
                callback.startAbout();
                break;
        }
    }

    public interface Callback {
        void startLogin();

        void startRegister();

        void startAbout();
    }


}
