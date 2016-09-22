package template.cheng.hollis.template.SQLiteDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static SQLiteDatabase database;

    // Database Version
    public static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "Hollis.db";

    //    public DBHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
//        db.execSQL(UserDataDAO.CREATE_TABLE_UserData);
        db.execSQL(LanguageDAO.CREATE_TABLE_Language);
//        db.execSQL(FirstTimeDAO.CREATE_TABLE_FirstTime);
//        db.execSQL(OwnPropertiesDAO.CREATE_TABLE_OwnProperties);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
//        db.execSQL("DROP TABLE IF EXISTS " + UserDataDAO.CREATE_TABLE_UserData);
        db.execSQL("DROP TABLE IF EXISTS " + LanguageDAO.CREATE_TABLE_Language);
//        db.execSQL("DROP TABLE IF EXISTS " + FirstTimeDAO.CREATE_TABLE_FirstTime);
//        db.execSQL("DROP TABLE IF EXISTS " + OwnPropertiesDAO.CREATE_TABLE_OwnProperties);

        // create new tables
        onCreate(db);
    }

    // 需要資料庫的元件呼叫這個方法，這個方法在一般的應用都不需要修改
    public static SQLiteDatabase getDatabase(Context context) {
        if (database == null || !database.isOpen()) {
            database = new DBHelper(context, DATABASE_NAME,
                    null, DATABASE_VERSION).getWritableDatabase();
        }

        return database;
    }

}