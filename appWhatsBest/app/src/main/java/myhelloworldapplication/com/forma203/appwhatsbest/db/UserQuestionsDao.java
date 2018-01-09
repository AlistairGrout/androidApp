package myhelloworldapplication.com.forma203.appwhatsbest.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import myhelloworldapplication.com.forma203.appwhatsbest.Model.UserQuestions;

public class UserQuestionsDao {
    public static final String TABLE_NAME = "userquestions_db";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USER = "refUser";
    public static final String COLUMN_QUESTION = "refQuestion";
    public static final String COLUMN_FINAL = "refFinal";

    public static final String CREATE_REQUEST
            = String.format("CREATE TABLE %s (" +
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT NOT NULL, " +
                    "%s INTEGER)",

            TABLE_NAME,
            COLUMN_ID,
            COLUMN_QUESTION,
            COLUMN_USER,
            COLUMN_FINAL);

    public static final String DROP_REQUEST = String.format("DROP TABLE %s", TABLE_NAME);

    private DbHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public UserQuestionsDao(Context context) {
        this.context = context;
    }

    public static UserQuestions cursorToUserQuestions(Cursor c) {
        UserQuestions userQuestions = new UserQuestions();
        userQuestions.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
        userQuestions.setRefUser(c.getString(c.getColumnIndex(COLUMN_USER)));
        userQuestions.setRefQuestion(c.getString(c.getColumnIndex(COLUMN_QUESTION)));
        userQuestions.setRefFinal(c.getInt(c.getColumnIndex(COLUMN_FINAL)) == 1);
        return userQuestions;
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

    public long insert(UserQuestions userQuestions) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, userQuestions.getId());
        values.put(COLUMN_USER, userQuestions.getRefUser());
        values.put(COLUMN_QUESTION, userQuestions.getRefQuestion());
        values.put(COLUMN_FINAL, userQuestions.getRefFinal() ? 1 : 0);

        return db.insert(TABLE_NAME, null, values);
    }

    public long update(UserQuestions userQuestions) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, userQuestions.getId());
        values.put(COLUMN_USER, userQuestions.getRefUser());
        values.put(COLUMN_QUESTION, userQuestions.getRefQuestion());
        values.put(COLUMN_FINAL, userQuestions.getRefFinal() ? 1 : 0);

        return db.update(
                TABLE_NAME, values,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(userQuestions.getId())}
        );
    }

    public int delete(UserQuestions userQuestions) {
        return db.delete(
                TABLE_NAME,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(userQuestions.getId())}
        );
    }

    public Cursor getPropositionCursorById(int id) {
        Cursor c = db.query(TABLE_NAME,
                null,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)},
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

    public UserQuestions getUserQuestionsById(int id) {
        Cursor c = getPropositionCursorById(id);
        if (c != null) {
            UserQuestions userQuestions = cursorToUserQuestions(c);
            c.close();
            return userQuestions;
        } else {
            return null;
        }
    }

    public Cursor getUserQuestionsCursor() {
        return db.query(TABLE_NAME, null, COLUMN_ID, new String[]{}, null, null, null);
    }

    public List<UserQuestions> getUserQuestions() {
        List<UserQuestions> userQuestions = new ArrayList<>();
        Cursor c = getUserQuestionsCursor();
        while (c.moveToNext()) {
            UserQuestions u = cursorToUserQuestions(c);
            userQuestions.add(u);
        }
        c.close();
        return userQuestions;
    }

}
