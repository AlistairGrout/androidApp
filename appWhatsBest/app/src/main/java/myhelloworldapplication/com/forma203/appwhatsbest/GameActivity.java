package myhelloworldapplication.com.forma203.appwhatsbest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Random;

import myhelloworldapplication.com.forma203.appwhatsbest.Model.Proposition;
import myhelloworldapplication.com.forma203.appwhatsbest.db.ThingsDao;

public class GameActivity extends AppCompatActivity implements View.OnClickListener
{

    private Button check1;
    private Button check2;
    private Button goback;
    private TextView text1;
    private TextView text2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        check1 = (Button) findViewById(R.id.btn_choix1);
        check2 = (Button) findViewById(R.id.btn_choix2);
        goback = (Button) findViewById(R.id.btn_return);
        text1 = (TextView) findViewById(R.id.txt_1);
        text2 = (TextView) findViewById(R.id.txt_2);
        check1.setOnClickListener(this);
        check2.setOnClickListener(this);
        goback.setOnClickListener(this);

        afficherPropo();
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == R.id.btn_choix1)
        {
afficherPropo();
        }else if (view.getId() == R.id.btn_choix2)
        {
afficherPropo();
        }else if (view.getId() == R.id.btn_return)
        {
            Intent toProfile = new Intent (GameActivity.this, UserActivity.class);
            startActivity(toProfile);
        }

    }

    private void afficherPropo ()
    {
        ThingsDao thingsDao = new ThingsDao(this);
        thingsDao.openReadable();
        List<Proposition> propo = thingsDao.getPropositions();

        int index = new Random().nextInt(propo.size());
        Proposition proposition = propo.get(index);
        text1.setText(proposition.getChoix());
        text2.setText(proposition.getChoixbis());
    }
}
