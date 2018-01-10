package myhelloworldapplication.com.forma203.appwhatsbest.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import myhelloworldapplication.com.forma203.appwhatsbest.R;

/**
 * TODO For navigation purposes, I think this should become an Activity
 * to understand why, use your app and then press 'About', 'Return', 'About', 'Return', etc a few times
 * and then press your phone's physical back button
 */
public class AboutFragment extends Fragment implements View.OnClickListener {

    private Callback callback;
//    private Button retAgain; TODO this field doesn't need to be a class property

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    // TODO let onCreateView() do the view creation and onViewCreated() the view manipulation : cleaner
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // TODO get the view and assign a click listener on it in one sentence
        view.findViewById(R.id.btn_ret).setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // TODO Since you use this fragment in MainActivity only, instanceof is not necessary
//        if (context instanceof Callback) {
        callback = (Callback) context;
//        }
        // TODO this is neat but not necessary, your app will crash if no callback is provided anyway
//        else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
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

    // TODO you don't need to write documentation when your naming is spot-on 'OnReturnPressListener' is self-explanatory
    public interface Callback {
        //    public interface OnReturnPressListener {
        void pressRet();
    }

}

