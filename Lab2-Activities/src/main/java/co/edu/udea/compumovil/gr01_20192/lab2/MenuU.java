package co.edu.udea.compumovil.gr01_20192.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

import OpenHelper.PoiDB;

public class MenuU extends AppCompatActivity {

    //1gridview
    GridView gridView2;

    String[] sites = {"Atanasio","Arvi","Castillo"};//,"Explora","Pueblito","Zoologico"};
    String[] descs = {"Estadio y unidad deportiva","Parque ambiental","Museo historico"};
    String[] points = {"5","4","5"};
    int[] sitesImages = {R.drawable.atanasio,R.drawable.arvi,R.drawable.elcastillo};//,R.drawable.explora,R.drawable.pueblitopaisa,R.drawable.zoologico};
    //1gridview

    private Button buttonnew,buttons;

    GridView gridView;
    ArrayList<POI> list;
    POIAdapter adapter = null;

    public static PoiDB sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_u);

        //1 grisview
        gridView2 = findViewById(R.id.gridView2);
        CustomAdapter customAdapter = new CustomAdapter();
        gridView2.setAdapter(customAdapter);
        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Toast.makeText(getApplicationContext(),sites[i], Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),GridItem.class);
                intent.putExtra("name2",sites[i]);
                intent.putExtra("desc2",descs[i]);
                intent.putExtra("point2",points[i]);
                intent.putExtra("image2",sitesImages[i]);
                startActivity(intent);

            }
        });

    //1 gridview


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

        buttons = findViewById(R.id.buttonSettings);
        buttons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openActivitysettings();
            }
        });
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    public void openActivityAddSite(){
        Intent intent = new Intent(this, AddSite.class);
        startActivity(intent);
    }

    public void openActivitysettings(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }



    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return sitesImages.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.listpoi2,null);
            //getting view in row_data
            ImageView image2 = view1.findViewById(R.id.imagePOI2);
            TextView name2 = view1.findViewById(R.id.txtName2);
            TextView desc2 = view1.findViewById(R.id.txtDesc2);
            TextView point2 = view1.findViewById(R.id.txtPoint2);


            image2.setImageResource(sitesImages[i]);
            name2.setText(sites[i]);
            desc2.setText(descs[i]);
            point2.setText(points[i]);


            return view1;
        }
    }

    }

