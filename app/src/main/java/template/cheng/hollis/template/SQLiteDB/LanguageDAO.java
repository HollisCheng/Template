package template.cheng.hollis.template.SQLiteDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LanguageDAO {
    public static final String TABLE_Language = "Language";

    // Language Table - column names
    public static final String KEY_ID = "ID";
    public static final String KEY_Language = "Language";

    // UserData table Create Statements
    public static final String CREATE_TABLE_Language = "CREATE TABLE " + TABLE_Language
            + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_Language + " TEXT"
            + ");";

    private SQLiteDatabase db;

    public LanguageDAO(Context context) {
        db = DBHelper.getDatabase(context);
    }

    public void close() {
        db.close();
    }

    public Language insert(Language Language) {
        ContentValues values = new ContentValues();
        values.put(KEY_ID, Language.getID());
        values.put(KEY_Language, Language.getLanguage());

        db.insert(TABLE_Language, null, values);

        return Language;
    }

    // 修改參數指定的物件
    public boolean update(Language language) {
        ContentValues values = new ContentValues();
        values.put(KEY_ID, language.getID());
        values.put(KEY_Language, language.getLanguage());

        String where = KEY_ID + "=" + language.getID();

        return db.update(TABLE_Language, values, where, null) > 0;
    }

    // 刪除參數指定編號的資料
    public boolean delete(int id) {
        // 設定條件為編號，格式為「欄位名稱=資料」
        String where = KEY_ID + "=" + id;
        // 刪除指定編號資料並回傳刪除是否成功
        return db.delete(TABLE_Language, where, null) > 0;
    }

    // 讀取所有記事資料
    public List<Language> getAll() {
        List<Language> result = new ArrayList<>();
        Cursor cursor = db.query(
                TABLE_Language, null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }

        cursor.close();
        return result;
    }

    // 取得指定編號的資料物件
    public Language get(int id) {
        // 準備回傳結果用的物件
        Language item = null;
        // 使用編號為查詢條件
        String where = KEY_ID + "=" + id;
        // 執行查詢
        Cursor result = db.query(
                TABLE_Language, null, where, null, null, null, null, null);

        // 如果有查詢結果
        if (result.moveToFirst()) {
            // 讀取包裝一筆資料的物件
            item = getRecord(result);
        }

        // 關閉Cursor物件
        result.close();
        // 回傳結果
        return item;
    }

    // 把Cursor目前的資料包裝為物件
    public Language getRecord(Cursor cursor) {
        // 準備回傳結果用的物件
        Language result = new Language();

        result.setID(cursor.getInt(0));
        result.setLanguage(cursor.getString(1));

        // 回傳結果
        return result;
    }

    // 取得資料數量
    public int getCount() {
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_Language, null);

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }

        return result;
    }

    // 建立範例資料
    public void sample() {
        String UsingLanguage = Locale.getDefault().getLanguage();
        //Log.w("LDAO", "Lang=" + UsingLanguage);
        Language item;
        if (UsingLanguage.equals("zh")) {
            item = new Language(1, "zh");
        } else {
            item = new Language(1, "en");
        }

//        Language item = new Language("zh");

        insert(item);
    }
}
