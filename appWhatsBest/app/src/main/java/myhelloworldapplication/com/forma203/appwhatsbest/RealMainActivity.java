package myhelloworldapplication.com.forma203.appwhatsbest;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import javax.security.auth.callback.Callback;

import myhelloworldapplication.com.forma203.appwhatsbest.Fragments.StartFragment;

public class RealMainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_main);

        View[] buttons = {
                findViewById(R.id.btn_account),
                findViewById(R.id.btn_play),
                findViewById(R.id.btn_log_off)
        };

        for (View view : buttons) {
            view.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        // TODO Should be a switch statement, more concise and clear (partially done, log_off not working yet)
        switch (view.getId()) {
            case R.id.btn_play:
                Intent intent = new Intent(this,GameActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_account:
                intent = new Intent(this, UserActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_log_off:
                intent = new Intent(this, StartFragment.class);
                startActivity(intent);
                break;
        }
    }

}
