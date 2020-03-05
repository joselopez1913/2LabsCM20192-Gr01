package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLite_OpenHelper extends SQLiteOpenHelper {

    public SQLite_OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table users(_ID integer primary key autoincrement, " +
                "User text, Email text, Password text);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //abrir db
    public void Open(){
        this.getWritableDatabase();
    }
    //cerrar db
    public void Close(){
        this.close();
    }
    //insertar registros db users
    public void InsertReg(String us, String em, String pa){
        ContentValues valuess = new ContentValues();
        valuess.put("User",us);
        valuess.put("Email",em);
        valuess.put("Password",pa);
        this.getWritableDatabase().insert("users",null,valuess);
    }

    //Validador de usuarios
    public Cursor ConsultUSer(String us, String pas) throws SQLException{
        Cursor mcursor=null;
        mcursor=this.getReadableDatabase().query("users",new String[]{"_ID",
                "User","Email","Password"},"User like '"+us+"' " +
                "and Password like '"+pas+"'",null,null,null,null );
        return mcursor;
    }
}
