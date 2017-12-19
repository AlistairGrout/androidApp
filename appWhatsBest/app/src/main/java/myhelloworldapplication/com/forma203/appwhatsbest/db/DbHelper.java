package myhelloworldapplication.com.forma203.appwhatsbest.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static myhelloworldapplication.com.forma203.appwhatsbest.db.ThingsDao.COLUMN_CHOIX;
import static myhelloworldapplication.com.forma203.appwhatsbest.db.ThingsDao.COLUMN_CHOIXBIS;

public class DbHelper extends SQLiteOpenHelper

{
    public static final String DB_NAME = "game_db";
    public static final int VERSION = 11;
    public DbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.i("DbHelper", "onCreate()");
        db.execSQL(ThingsDao.CREATE_REQUEST);
        db.execSQL(UserDao.CREATE_REQUEST);
        db.execSQL(UserQuestionsDao.CREATE_REQUEST);

        db.execSQL("INSERT INTO " + ThingsDao.TABLE_NAME + " (" + COLUMN_CHOIX + ", " + COLUMN_CHOIXBIS + ") " +
                "VALUES " +
                "('Perdre la vue', 'Perdre l''ouïe'), " +
                "('Plus jamais manger de fromage','Plus jamais savoir lire' ), " +
                "('Perdre les bras', 'Perdre les jambes' ), " +
                "('Beurre avant la confiture', 'Beurre après la confiture ' ), " +
                "('Version française','Version originale' ), " +
                "('Thé vert', 'Thé noir' ), " +
                "('Manger de la nourriture pour chien tous les jours', 'Devoir imiter un chien 6h par jour' ), " +
                "('Ski', 'Snowboard' ), " +
                "('Petit poney', 'Grande licorne' ), " +
                "('Brontosaurus', 'Tyrannosaurus' ), " +
                "('Le lait avant les céréales', 'Le lait après les céréales' ), " +
                "('Jupiler', 'Carapils' ), " +
                "('Chien', 'Chat' ), " +
                "('Voyager dans le passé','Voyager dans le futur' ), " +
                "('Manger du chat', 'Manger du chien'), " +
                "('Jour', 'Nuit'), " +
                "('Thé', 'Café'), " +
                "('Facebook', 'Twitter'), " +
                "('Vivre tout nu', 'Porter des vêtements'), " +
                "('Se couper une main', 'Se couper un pied'), " +
                "('Web', 'Mobile'), " +
                "('Devenir une licorne', 'Devenir une sirène')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        Log.i("DbHelper", "onUpgrade()");
        db.execSQL(ThingsDao.DROP_REQUEST);
        db.execSQL(UserDao.DROP_REQUEST);
        db.execSQL(UserQuestionsDao.DROP_REQUEST);
        onCreate(db);
    }


}
