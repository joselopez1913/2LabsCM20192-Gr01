package co.edu.udea.compumovil.gr01_20192.lab2.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import co.edu.udea.compumovil.gr01_20192.lab2.DataBase.PoiDB;
import co.edu.udea.compumovil.gr01_20192.lab2.Entities.Poi;
import co.edu.udea.compumovil.gr01_20192.lab2.R;

public class MenuU extends AppCompatActivity {

    private Button buttonnew,buttons;

    ListView lvPoi;
    private List<Poi> listpoi;

    private PoiDB PDB ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuu);

        //2. LitView

        PDB = PoiDB.getAppDatabase(getApplicationContext());
        listpoi = PDB.poiDao().getAll();
        lvPoi = (ListView) findViewById(R.id.LvPoi);

        AdapterPoi ap = new AdapterPoi(this);
        lvPoi.setAdapter(ap);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityAddSite();
            }
        });
    }

    private void setSupportActionBar(Toolbar myToolbar) {
    }


    public void openActivityAddSite(){
        Intent intent = new Intent(this, AddSite.class);
        startActivity(intent);
    }

    public void openActivitysettings(){
        Intent intent = new Intent(this, SettingsActivity.class);
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

