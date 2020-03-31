package co.edu.udea.compumovil.gr01_20192.lab2.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import co.edu.udea.compumovil.gr01_20192.lab2.Entities.Poi;

@Dao
public interface PoiDao {

    @Query("SELECT * FROM Poi")
    List<Poi> getAll();

    @Insert
    void insert(Poi poi);

    @Update
    void update(Poi poi);

    @Delete
    void delete(Poi poi);
}

