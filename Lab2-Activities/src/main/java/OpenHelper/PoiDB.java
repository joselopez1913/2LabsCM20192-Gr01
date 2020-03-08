package OpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

public class PoiDB extends SQLite_OpenHelper {

    public PoiDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql) {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(sql);
    }

    public void InsertPOI(String name, String desc, String point, byte[] image) {
        SQLiteDatabase db=getWritableDatabase();
        String sql= "INSERT INTO POI VALUES (Null,?,?,?,?)";

        SQLiteStatement statement=db.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,name);
        statement.bindString(2,desc);
        statement.bindString(3,point);
        statement.bindBlob(4,image);

        statement.executeInsert();
    }

    public Cursor getData(String sql){
        SQLiteDatabase db=getReadableDatabase();
        return db.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }

}

