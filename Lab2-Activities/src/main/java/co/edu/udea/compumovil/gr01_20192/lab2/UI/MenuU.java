package co.edu.udea.compumovil.gr01_20192.lab2.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import co.edu.udea.compumovil.gr01_20192.lab2.DataBase.PoiDB;
import co.edu.udea.compumovil.gr01_20192.lab2.Entities.Poi;
import co.edu.udea.compumovil.gr01_20192.lab2.R;

public class MenuU extends AppCompatActivity {

    private Button buttonnew,buttons;

    ListView lvPoi;
    private List<Poi> listpoi,lp;

    private PoiDB PDB,P2DB ;

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuu);

        //add POI of the DB to Listview
        PDB = PoiDB.getAppDatabase(getApplicationContext());
        listpoi = PDB.poiDao().getAll();
        lvPoi = (ListView) findViewById(R.id.LvPoi);

        AdapterPoi ap = new AdapterPoi(this);
        lvPoi.setAdapter(ap);
        //


        //2. LitView

        PDB = PoiDB.getAppDatabase(getApplicationContext());
        listpoi = PDB.poiDao().getAll();
        lvPoi = (ListView) findViewById(R.id.LvPoi);

        AdapterPoi adapterPoi = new AdapterPoi(this);
        lvPoi.setAdapter(adapterPoi);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityAddSite();
            }
        });

        //fragment with details of the sites

        P2DB=PoiDB.getAppDatabase(getApplicationContext());
        lp = P2DB.poiDao().getAll();
        listView=(ListView)findViewById(R.id.LvPoi);
        AdapterPoi aa = new AdapterPoi(this);
        listView.setAdapter(aa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),SiteDetail.class);

             //   intent.putExtra("imagenes",lp.get(position).getImage());
                intent.putExtra("site",lp.get(position).getNamep());
                intent.putExtra("desc",lp.get(position).getDescription());
                intent.putExtra("point",lp.get(position).getPoint());

                startActivity(intent);
            }
        });


        //




    }

    private void setSupportActionBar(Toolbar myToolbar) {
    }


    public void openActivityAddSite(){
        Intent intent = new Intent(this, AddSite.class);
        startActivity(intent);
    }

    public void openActivitysettings(){
        Intent intent = new Intent(this, SiteDetail.class);
        startActivity(intent);
    }

    class AdapterPoi extends ArrayAdapter<Poi>{

        AppCompatActivity appCompatActivity;

        public AdapterPoi(AppCompatActivity context){
            super(context,R.layout.listpoi,listpoi);
            appCompatActivity=context;
        }

        public View getView(int position, View convertviw, ViewGroup parent){
            LayoutInflater inflater=appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.listpoi,null);

            ImageView image3 =  (ImageView)item.findViewById(R.id.imagePOI);
            TextView name3 = item.findViewById(R.id.txtName);
            TextView desc3 = item.findViewById(R.id.txtDesc);
            TextView point3 = item.findViewById(R.id.txtPoint);

            Bitmap bitmap = BitmapFactory.decodeByteArray(listpoi.get(position).getImage(), 0, listpoi.get(position).getImage().length);
            image3.setImageBitmap(bitmap);
            name3.setText(listpoi.get(position).getNamep());
            desc3.setText(listpoi.get(position).getDescription());
            point3.setText(listpoi.get(position).getPoint());

            return item;
        }

    }

    }

