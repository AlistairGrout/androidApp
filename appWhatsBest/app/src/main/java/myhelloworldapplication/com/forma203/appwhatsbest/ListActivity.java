package myhelloworldapplication.com.forma203.appwhatsbest;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import myhelloworldapplication.com.forma203.appwhatsbest.Model.Proposition;
import myhelloworldapplication.com.forma203.appwhatsbest.db.ThingsDao;

public class ListActivity extends AppCompatActivity
        implements View.OnClickListener,
        AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener,
        View.OnKeyListener {

    private Button btnAdd;
    private EditText etSearch;
    private ListView lvPropositions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        btnAdd = findViewById(R.id.btn_main_add);
        etSearch = findViewById(R.id.et_main_search);
        lvPropositions = findViewById(R.id.lv_main_proposition);

        btnAdd.setOnClickListener(this);
        etSearch.setOnKeyListener(this);
        lvPropositions.setOnItemClickListener(this);
        lvPropositions.setOnItemLongClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshListView();
    }

    // TODO this is the way to do it, you could close your DAO before instantiating your adapter, see comment
    protected void refreshListView() {
        ThingsDao thingsDao = new ThingsDao(this);
        thingsDao.openReadable();

//        List<Proposition> propositions = thingsDao.getPropositions();
//        thingsDao.close();
//
//        ArrayAdapter<Proposition> adapter = new ArrayAdapter<Proposition>(
//                this,
//                android.R.layout.simple_list_item_1,
//                propositions);

        ArrayAdapter<Proposition> adapter = new ArrayAdapter<Proposition>(
                this,
                android.R.layout.simple_list_item_1,
                thingsDao.getPropositions());

        thingsDao.close();

        lvPropositions.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Intent toAdd = new Intent(this, AddActivity.class);
        startActivity(toAdd);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Proposition clicked = (Proposition) parent.getItemAtPosition(position);
        Intent toDetails = new Intent(ListActivity.this, DetailsActivity.class);
        // TODO to avoid typos PROPOSITION is now a static property in DetailsActivity
        toDetails.putExtra(DetailsActivity.PROPOSITION, clicked);
        startActivity(toDetails);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        final Proposition clicked = (Proposition) parent.getItemAtPosition(position);

        new AlertDialog.Builder(this)
                .setTitle("What do you want to do ?")
                // TODO this is not a good way to do it. look @ setPositiveButton() / setNegativeButton()
                .setItems(R.array.items_menu, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which) {
                            case 0:
                                Intent toEdit = new Intent(ListActivity.this, AddActivity.class);
                                toEdit.putExtra("proposition", clicked);
                                startActivity(toEdit);
                                break;
                            case 1:
                                ThingsDao thingsDao = new ThingsDao(ListActivity.this);
                                thingsDao.openWritable();
                                thingsDao.delete(clicked);
                                thingsDao.close();
                                refreshListView();
                                break;
                        }
                    }
                })
                .show();
        return true;

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        // TODO This crashes the app, look into it
        // TODO Since you already do a SELECT * to populate your list

        String query = etSearch.getText().toString();
        ThingsDao thingsDao = new ThingsDao(this);
        thingsDao.openReadable();

        ArrayAdapter<Proposition> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                thingsDao.searchProposition(query));

        thingsDao.close();

        lvPropositions.setAdapter(adapter);
        return false;

    }
}
