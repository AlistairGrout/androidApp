package myhelloworldapplication.com.forma203.appwhatsbest.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import myhelloworldapplication.com.forma203.appwhatsbest.Model.Proposition;
import myhelloworldapplication.com.forma203.appwhatsbest.UserActivity;


public class ThingsDao
{

    public static final String TABLE_NAME = "questions_db";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CHOIX = "choix1";
    public static final String COLUMN_CHOIXBIS = "choix2";


    public static final String CREATE_REQUEST
            = String.format("CREATE TABLE %s (" +
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL)",

            TABLE_NAME,
            COLUMN_ID,
            COLUMN_CHOIX,
            COLUMN_CHOIXBIS);

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
        values.put(COLUMN_CHOIX, proposition.getChoix());
        values.put(COLUMN_CHOIXBIS, proposition.getChoixbis());
        return db.insert(TABLE_NAME, null, values);
    }

    public long update(Proposition proposition)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CHOIX, proposition.getChoix());
        values.put(COLUMN_CHOIXBIS, proposition.getChoixbis());

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

    public List<Proposition> getPropositions() {

        List<Proposition> proposition = new ArrayList<>();
        Cursor c = getPropositionCursor();
        while (c.moveToNext()) {
            Proposition p = cursorToProposition(c);
            proposition.add(p);
        }
        c.close();
        return proposition;
    }

    private Cursor getPropositionCursor()
    {
        return db.query(TABLE_NAME, null, COLUMN_ID, new String[]{}, null, null, null);
    }

    public static Proposition cursorToProposition(Cursor c) {
        Proposition proposition = new Proposition();
        proposition.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
        proposition.setChoix(c.getString(c.getColumnIndex(COLUMN_CHOIX)));
        proposition.setChoixbis(c.getString(c.getColumnIndex(COLUMN_CHOIXBIS)));
        return proposition;
    }




    }
