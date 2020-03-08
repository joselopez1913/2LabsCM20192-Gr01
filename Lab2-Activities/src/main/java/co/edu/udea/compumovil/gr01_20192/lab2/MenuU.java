package co.edu.udea.compumovil.gr01_20192.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import OpenHelper.PoiDB;

public class MenuU extends AppCompatActivity {

    private Button buttonnew;

    GridView gridView;
    ArrayList<POI> list;
    POIAdapter adapter = null;

    public static PoiDB sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_u);

        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new POIAdapter(this, R.layout.listpoi, list);
        gridView.setAdapter(adapter);

        Cursor cursor = AddSite.sqLiteHelper.getData("SELECT * FROM POI");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String desc = cursor.getString(2);
            String point = cursor.getString(3);
            byte[] image = cursor.getBlob(4);
            list.add(new POI(name, desc, point, image,id));
        }
        adapter.notifyDataSetChanged();


        buttonnew = findViewById(R.id.buttonNew);
        buttonnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openActivityAddSite();
            }
        });
    }
    public void openActivityAddSite(){
        Intent intent = new Intent(this, AddSite.class);
        startActivity(intent);
    }
    }

