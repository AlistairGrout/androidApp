package myhelloworldapplication.com.forma203.appwhatsbest.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import myhelloworldapplication.com.forma203.appwhatsbest.Model.User;

public class UserDao {

    public static final String TABLE_NAME = "user_db";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    public static final String CREATE_REQUEST
            = String.format("CREATE TABLE %s (" +
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL)",

                    TABLE_NAME,
            COLUMN_ID,
            COLUMN_EMAIL,
            COLUMN_PASSWORD);
    public static final String DROP_REQUEST = String.format("DROP TABLE %s", TABLE_NAME);


    private DbHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public UserDao(Context context) {
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

    public long insert(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, user.getId());
        return db.insert(TABLE_NAME, null, values);

    }

    public long update(User user)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, user.getId());

        return db.update(
                TABLE_NAME, values,
                COLUMN_ID + " = ?",
                new String[] { String.valueOf(user.getId()) }
        );
    }

    public int delete(User user)
    {
        return db.delete(
                TABLE_NAME,
                COLUMN_ID + " = ?",
                new String[] { String.valueOf(user.getId())}
        );
    }

    public Cursor getUserCursorById(int id) {
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

    public User getUserById(int id) {
        Cursor c = getUserCursorById(id);
        if (c != null) {
            User user = cursorToUser(c);
            c.close();
            return user;
        } else {
            return null;
        }
    }

    public Cursor getUserCursor()
    {
        return db.query(TABLE_NAME, null, COLUMN_ID, new String[]{}, null, null, null);
    }

    public static User cursorToUser(Cursor c) {
        User user = new User();
        user.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
        return user;
    }





}
