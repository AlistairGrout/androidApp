package myhelloworldapplication.com.forma203.appwhatsbest.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import myhelloworldapplication.com.forma203.appwhatsbest.Model.Proposition;
import myhelloworldapplication.com.forma203.appwhatsbest.R;

public class DetailsActivity extends AppCompatActivity {

    public static final String PROPOSITION = "PROPOSITION";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView tvFirstChoice = findViewById(R.id.tv_firstChoice);
        TextView tvSecondChoice = findViewById(R.id.tv_secondChoice);

        Proposition proposition = (Proposition) getIntent().getSerializableExtra(PROPOSITION);

        tvFirstChoice.setText(proposition.getFirstChoice());
        tvSecondChoice.setText(proposition.getSecondChoice());
    }
}
