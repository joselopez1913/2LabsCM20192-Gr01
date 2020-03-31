package co.edu.udea.compumovil.gr01_20192.lab2.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import co.edu.udea.compumovil.gr01_20192.lab2.DAO.PoiDao;
import co.edu.udea.compumovil.gr01_20192.lab2.Entities.Poi;

@Database(entities = {Poi.class}, version = 1)
public abstract class PoiDB extends RoomDatabase {
    public abstract PoiDao poiDao();

    private static PoiDB INSTANCE;

    public static PoiDB getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), PoiDB.class, "Poi").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}

