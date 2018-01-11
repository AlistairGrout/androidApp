package myhelloworldapplication.com.forma203.appwhatsbest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    private Button returnBtn;
    private Button gameBtn;
    private Button listBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        gameBtn = findViewById(R.id.btn_game);
        returnBtn = findViewById(R.id.btn_return1);
        listBtn = findViewById(R.id.btn_list);
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
