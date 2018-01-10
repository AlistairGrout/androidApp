package myhelloworldapplication.com.forma203.appwhatsbest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

// TODO I modified some things here, try to compare with the previous version
public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    //region Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        findViewById(R.id.btn_game).setOnClickListener(this);
        findViewById(R.id.btn_return1).setOnClickListener(this);
    }
    //endregion

    //region Private methods
    private void startActivityForClass(@NonNull Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
    //endregion

    //region OnClick
    @Override
    public void onClick(View view) {
        Class clazz = null;
        switch (view.getId()) {
            case R.id.btn_game:
                clazz = GameActivity.class;
                break;
            case R.id.btn_return1:
                clazz = RealMainActivity.class;
                break;
        }

        if (clazz != null) {
            startActivityForClass(clazz);
        }
    }
    //endregion
}
