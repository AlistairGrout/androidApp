package myhelloworldapplication.com.forma203.appwhatsbest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import myhelloworldapplication.com.forma203.appwhatsbest.Model.Proposition;

public class DetailsActivity extends AppCompatActivity {

    // TODO Now a static property to avoid typos (see ListActivity when used outside of this class)
    public static final String PROPOSITION = "PROPOSITION";

    // TODO 'could be local' warning
    private TextView tvChoice1;
    private TextView tvChoice2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tvChoice1 = findViewById(R.id.tv_choice1);
        tvChoice2 = findViewById(R.id.tv_choice2);

        Proposition proposition = (Proposition) getIntent().getSerializableExtra(PROPOSITION);

        tvChoice1.setText(proposition.getChoice1());
        tvChoice2.setText(proposition.getChoice2());
    }
}
