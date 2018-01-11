package myhelloworldapplication.com.forma203.appwhatsbest.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import myhelloworldapplication.com.forma203.appwhatsbest.Model.Proposition;



public class ThingsDao
{

    public static final String TABLE_NAME = "questions_db";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CHOICE1 = "choice1";
    public static final String COLUMN_CHOICE2 = "choice2";


    public static final String CREATE_REQUEST
            = String.format("CREATE TABLE %s (" +
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL ) ",
            TABLE_NAME,
            COLUMN_ID,
            COLUMN_CHOICE1,
            COLUMN_CHOICE2);

    public static final String DROP_REQUEST = String.format("DROP TABLE %s", TABLE_NAME);

    private DbHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public ThingsDao(Context context) {
        this.context = context;
    }

    public void openWritable() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void openReadable() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase();
    }

    public void close() {
        db.close();
        dbHelper.close();
    }

    public long insert(Proposition proposition) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CHOICE1, proposition.getChoice1());
        values.put(COLUMN_CHOICE2, proposition.getChoice2());
        return db.insert(TABLE_NAME, null, values);
    }

    public long update(Proposition proposition)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CHOICE1, proposition.getChoice1());
        values.put(COLUMN_CHOICE2, proposition.getChoice2());

        return db.update(
            TABLE_NAME, values,
            COLUMN_ID + " = ?",
            new String[] { String.valueOf(proposition.getId()) }
    );
    }

    public int delete(Proposition proposition)
    {
        return db.delete(
                TABLE_NAME,
                COLUMN_ID + " =?",
                new String[] { String.valueOf(proposition.getId())}
        );
    }

    public Cursor getPropositionCursorById(int id) {
        Cursor c = db.query(TABLE_NAME,
                null,
                COLUMN_ID + " = ?",
                new String[] { String.valueOf(id) },
                null,
                null,
                null);

        if (c.getCount() > 0) {
            c.moveToFirst();
            return c;
        } else {
            return null;
        }
    }

    public Proposition getPropositionById(int id) {
        Cursor c = getPropositionCursorById(id);
        if (c != null) {
            Proposition proposition = cursorToProposition(c);
            c.close();
            return proposition;
        } else {
            return null;
        }
    }

    private Cursor getPropositionsCursor()
    {
        return db.query(TABLE_NAME, null, COLUMN_ID, new String[]{}, null, null, null);
    }

    public List<Proposition> getPropositions() {

        List<Proposition> propositions = new ArrayList<>();
        Cursor c = getPropositionsCursor();
        while (c.moveToNext()) {
            Proposition proposition = cursorToProposition(c);
            propositions.add(proposition);
        }
        c.close();
        return propositions;
    }


    public Cursor searchPropositionsCursor (String query) {
        return db.query(TABLE_NAME,
                null,
                COLUMN_CHOICE1 + "LIKE ?",
                new String[] { "%" + query + "%" },
                null,
                null,
                COLUMN_CHOICE2);
    }

    public List <Proposition> searchProposition(String query) {

        List<Proposition> propositions = new ArrayList<>();
        Cursor c = searchPropositionsCursor(query);
        while (c.moveToNext()) {
            Proposition proposition = cursorToProposition(c);
            propositions.add(proposition);
        }
        c.close();
        return propositions;
    }

    public static Proposition cursorToProposition(Cursor c) {
        Proposition proposition = new Proposition();
        proposition.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
        proposition.setChoice1(c.getString(c.getColumnIndex(COLUMN_CHOICE1)));
        proposition.setChoice2(c.getString(c.getColumnIndex(COLUMN_CHOICE2)));
        return proposition;
    }




    }
