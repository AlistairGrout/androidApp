package myhelloworldapplication.com.forma203.appwhatsbest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import myhelloworldapplication.com.forma203.appwhatsbest.Model.Proposition;
import myhelloworldapplication.com.forma203.appwhatsbest.db.ThingsDao;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private Button check1;
    private Button check2;
    private TextView text1;
    private TextView text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // TODO This is OK since you will use that later
        text1 = (TextView) findViewById(R.id.txt_1);
        text2 = (TextView) findViewById(R.id.txt_2);

        check1 = findViewById(R.id.btn_choice1);
        check2 = findViewById(R.id.btn_choice2);
        check1.setOnClickListener(this);
        check2.setOnClickListener(this);

        showProposition();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_choice1:
            case R.id.btn_choice2:
                showProposition();
                break;
        }
        //showProposition();
    }

    private void showProposition() {
        ThingsDao thingsDao = new ThingsDao(this);
        thingsDao.openReadable();
        List<Proposition> propositions = thingsDao.getPropositions();

        int index = new Random().nextInt(propositions.size());
        Proposition proposition = propositions.get(index);
        text1.setText(proposition.getChoice1());
        text2.setText(proposition.getChoice2());
    }


}
