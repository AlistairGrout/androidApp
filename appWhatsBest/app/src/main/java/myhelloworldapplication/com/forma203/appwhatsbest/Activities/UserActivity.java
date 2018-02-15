package myhelloworldapplication.com.forma203.appwhatsbest.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import myhelloworldapplication.com.forma203.appwhatsbest.R;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Button gameBtn = findViewById(R.id.btn_game);
        Button returnBtn = findViewById(R.id.btn_return1);
        Button listBtn = findViewById(R.id.btn_list);
        returnBtn.setOnClickListener(this);
        gameBtn.setOnClickListener(this);
        listBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_game) {
            Intent toPlay = new Intent(UserActivity.this, GameActivity.class);
            startActivity(toPlay);
            Log.i("GameActivity", "Game");
        } else if (view.getId() == R.id.btn_return1) {
            Intent toReturn = new Intent(UserActivity.this, RealMainActivity.class);
            startActivity(toReturn);
        }else if (view.getId() == R.id.btn_list) {
            Intent toAdd = new Intent(UserActivity.this, ListActivity.class);
            startActivity(toAdd);
        }

    }
}
