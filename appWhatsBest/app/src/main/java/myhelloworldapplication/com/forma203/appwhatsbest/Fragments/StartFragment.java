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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_start, container, false);

        // TODO this should move below, in onViewCreated (cleaner lifecycle)
        Button btnLogin = v.findViewById(R.id.btn_log);
        Button btnRegister = v.findViewById(R.id.btn_register);
        Button btnAbout = v.findViewById(R.id.btn_about);

        btnLogin.setOnClickListener(this);
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
        callback = (Callback) getActivity();

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
