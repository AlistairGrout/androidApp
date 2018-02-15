package myhelloworldapplication.com.forma203.appwhatsbest.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import myhelloworldapplication.com.forma203.appwhatsbest.Model.Proposition;
import myhelloworldapplication.com.forma203.appwhatsbest.R;
import myhelloworldapplication.com.forma203.appwhatsbest.db.ThingsDao;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etChoice1;
    private EditText etChoice2;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etChoice1 = findViewById(R.id.et_add_choice1);
        etChoice2 = findViewById(R.id.et_add_choice2);
        Button btnSubmit = findViewById(R.id.btn_submit_add);

        btnSubmit.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            Proposition proposition = (Proposition) extras.getSerializable("proposition");
            if (proposition != null) {
                this.id = proposition.getId();
                etChoice1.setText(proposition.getFirstChoice());
                etChoice2.setText(proposition.getSecondChoice());

            }
        }
    }

    @Override
    public void onClick(View v) {

        String choice1 = etChoice1.getText().toString().trim();
        String choice2 = etChoice2.getText().toString().trim();

        if (choice1.equals("") || choice2.equals("")) {
            Toast.makeText(this, "Text is required", Toast.LENGTH_SHORT).show();
        }else {
            Proposition proposition = new Proposition (id, choice1, choice2);

            ThingsDao thingsDao = new ThingsDao(this);
            thingsDao.openWritable();
            Toast.makeText(this, "Added!", Toast.LENGTH_SHORT).show();
            if(this.id == 0) {
                thingsDao.insert(proposition);
            }else {
                proposition.setId(this.id);
                thingsDao.update(proposition);
            }
            thingsDao.close();
            finish();

        }

    }
}
