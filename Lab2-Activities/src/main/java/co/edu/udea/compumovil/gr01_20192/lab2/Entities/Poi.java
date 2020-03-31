package co.edu.udea.compumovil.gr01_20192.lab2.Entities;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Poi")
public class Poi {

    @PrimaryKey(autoGenerate = true)
    public int pid;

    @ColumnInfo(name = "name")
    public String namep;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "point")
    public String point;

    @ColumnInfo(name = "image")
    public byte[] image;

    public Poi( String namep, String description, String point, byte[] image) {
        this.pid = pid;
        this.namep = namep;
        this.description = description;
        this.point = point;
        this.image = image;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getNamep() {
        return namep;
    }

    public void setNamep(String namep) {
        this.namep = namep;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
