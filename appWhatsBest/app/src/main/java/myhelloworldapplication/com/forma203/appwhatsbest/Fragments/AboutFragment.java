package myhelloworldapplication.com.forma203.appwhatsbest.Fragments;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import javax.security.auth.callback.Callback;

import myhelloworldapplication.com.forma203.appwhatsbest.R;

public class AboutFragment extends Fragment implements View.OnClickListener{


    private Callback callback;
    private Button retAgain;


    public interface Callback {
        void pressRet();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_about, container, false);
        retAgain = v.findViewById(R.id.btn_ret);
        retAgain.setOnClickListener(this);
        return v;
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
            case R.id.btn_ret:
                callback.pressRet();
                break;
        }

    }

}

