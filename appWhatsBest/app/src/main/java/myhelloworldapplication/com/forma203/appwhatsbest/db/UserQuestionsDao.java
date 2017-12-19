package myhelloworldapplication.com.forma203.appwhatsbest.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import myhelloworldapplication.com.forma203.appwhatsbest.Model.User_Questions;

public class UserQuestionsDao
{
    public static final String TABLE_NAME = "userquestions_db";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_REFUSER = "refuser";
    public static final String COLUMN_REFQUESTION = "refquestion";
    public static final String COLUMN_REFINAL= "refinal";

    public static final String CREATE_REQUEST
            = String.format("CREATE TABLE %s (" +
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT NOT NULL, " +
                    "%s INTEGER)",

            TABLE_NAME,
            COLUMN_ID,
            COLUMN_REFQUESTION,
            COLUMN_REFUSER,
            COLUMN_REFINAL);

    public static final String DROP_REQUEST = String.format("DROP TABLE %s", TABLE_NAME);

    private DbHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public UserQuestionsDao(Context context) {
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

    public long insert(User_Questions userQuestions) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, userQuestions.getId());
        values.put(COLUMN_REFUSER, userQuestions.getRefuser());
        values.put(COLUMN_REFQUESTION, userQuestions.getRefquestion());
        values.put(COLUMN_REFINAL, userQuestions.getRefinal() ? 1 : 0);

        return db.insert(TABLE_NAME, null, values);
    }

    public long update(User_Questions userQuestions)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, userQuestions.getId());
        values.put(COLUMN_REFUSER, userQuestions.getRefuser());
        values.put(COLUMN_REFQUESTION, userQuestions.getRefquestion());
        values.put(COLUMN_REFINAL, userQuestions.getRefinal() ? 1 : 0);

        return db.update(
                TABLE_NAME, values,
                COLUMN_ID + " = ?",
                new String[] { String.valueOf(userQuestions.getId()) }
        );
    }

    public int delete(User_Questions userQuestions)
    {
        return db.delete(
                TABLE_NAME,
                COLUMN_ID + " = ?",
                new String[] { String.valueOf(userQuestions.getId()) }
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

    public User_Questions getUserQuestionsById(int id) {
        Cursor c = getPropositionCursorById(id);
        if (c != null){
            User_Questions userQuestions = cursorToUserQuestions(c);
            c.close();
            return userQuestions;
        } else {
            return null;
        }
    }

    public Cursor getUserQuestionsCursor()
    {
        return db.query(TABLE_NAME, null, COLUMN_ID, new String[]{}, null, null, null);
    }

    public List<User_Questions> getUserQuestions()
    {
        List<User_Questions> userQuestions = new ArrayList<>();
        Cursor c = getUserQuestionsCursor();
        while (c.moveToNext()) {
            User_Questions u = cursorToUserQuestions(c);
            userQuestions.add(u);
        }
        c.close();
        return userQuestions;
    }

    public static User_Questions cursorToUserQuestions(Cursor c) {
        User_Questions userQuestions = new User_Questions();
        userQuestions.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
        userQuestions.setRefuser(c.getString(c.getColumnIndex(COLUMN_REFUSER)));
        userQuestions.setRefquestion(c.getString(c.getColumnIndex(COLUMN_REFQUESTION)));
        userQuestions.setRefinal(c.getInt(c.getColumnIndex(COLUMN_REFINAL)) == 1);
        return userQuestions;
    }

}
