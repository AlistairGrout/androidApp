package myhelloworldapplication.com.forma203.appwhatsbest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    private Button returnBtn;
    private Button gameBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        gameBtn = (Button) findViewById(R.id.btn_game);
        returnBtn = (Button) findViewById(R.id.btn_return1);
        returnBtn.setOnClickListener(this);
        gameBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.btn_game)
        {
            Intent toPlay = new Intent (UserActivity.this, GameActivity.class);
            startActivity(toPlay);
            Log.i("GameActivity", "Game");
        } else if (view.getId() == R.id.btn_return1){
            Intent toReturn = new Intent (UserActivity.this, RealMainActivity.class);
            startActivity(toReturn);
        }

    }
}
