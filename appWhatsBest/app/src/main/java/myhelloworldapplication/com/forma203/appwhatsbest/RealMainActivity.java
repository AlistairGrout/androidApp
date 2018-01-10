package myhelloworldapplication.com.forma203.appwhatsbest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
        // TODO Be careful there, if you click on account or log off directly, your intent won't be instantiated
        // TODO See method below, to keep it DRY (Don't Repeat Yourself)
//        switch (view.getId()) {
//            case R.id.btn_play:
//                Intent intent = new Intent(this, GameActivity.class);
//                startActivity(intent);
//                break;
//
//            case R.id.btn_account:
////                intent = new Intent(this, UserActivity.class);
////                startActivity(intent);
//                startActivityForClass(UserActivity.class);
//                break;
//
        // TODO Fragment can't be started with startActivity(), should used a FragmentManager
//            case R.id.btn_log_off:
////                intent = new Intent(this, StartFragment.class);
////                startActivity(intent);
//                startActivityForClass(StartFragment.class);
//                break;
//        }

        // TODO You could even do this : (I removed StartFragment right now because it crashes)
        Class clazz = null;
        switch (view.getId()) {
            case R.id.btn_play:
                clazz = GameActivity.class;
                break;

            case R.id.btn_account:
                clazz = UserActivity.class;
                break;
        }

        if (clazz != null) {
            startActivityForClass(clazz);
        }
    }

    // TODO See the annotation here, this gives additional info to the compiler to write defensive code @Nullable @NonNull, etc...
    private void startActivityForClass(@NonNull Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

}
