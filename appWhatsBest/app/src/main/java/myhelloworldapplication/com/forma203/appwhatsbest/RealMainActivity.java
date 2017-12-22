package myhelloworldapplication.com.forma203.appwhatsbest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RealMainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_main);

        Button btnAccount = findViewById(R.id.btn_account);
        Button btnPlay = findViewById(R.id.btn_play);
        Button btnLogOff = findViewById(R.id.btn_log_off);

        btnAccount.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        btnLogOff.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // TODO Should be a switch statement, more concise and clear
        if (view.getId() == R.id.btn_play) {
            Intent toPlay2 = new Intent(RealMainActivity.this, GameActivity.class);
            startActivity(toPlay2);
            Log.i("GameActivity", "Game");
        } else if (view.getId() == R.id.btn_account) {
            Intent toReturn = new Intent(RealMainActivity.this, UserActivity.class);
            startActivity(toReturn);
        } else if (view.getId() == R.id.btn_log_off) {
            Toast.makeText(RealMainActivity.this, "You logged off", Toast.LENGTH_SHORT).show();
            Intent toStartFrag = new Intent(RealMainActivity.this, MainActivity.class);
            startActivity(toStartFrag);

        }
    }
}
