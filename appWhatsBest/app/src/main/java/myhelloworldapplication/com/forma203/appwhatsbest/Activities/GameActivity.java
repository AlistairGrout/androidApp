package myhelloworldapplication.com.forma203.appwhatsbest.Activities;

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
import myhelloworldapplication.com.forma203.appwhatsbest.R;
import myhelloworldapplication.com.forma203.appwhatsbest.db.ThingsDao;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView firstText;
    private TextView secondText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // TODO This is OK since you will use that later
        firstText = findViewById(R.id.txt_first);
        secondText = findViewById(R.id.txt_second);

        Button firstChoice = findViewById(R.id.btn_firstChoice);
        Button secondChoice = findViewById(R.id.btn_secondChoice);
        firstChoice.setOnClickListener(this);
        secondChoice.setOnClickListener(this);

        showProposition();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_firstChoice:
            case R.id.btn_secondChoice:
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
        firstText.setText(proposition.getFirstChoice());
        secondText.setText(proposition.getSecondChoice());
    }


}
