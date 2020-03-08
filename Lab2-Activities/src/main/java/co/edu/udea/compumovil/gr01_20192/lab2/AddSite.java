package co.edu.udea.compumovil.gr01_20192.lab2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import OpenHelper.PoiDB;
import OpenHelper.SQLite_OpenHelper;

public class AddSite extends AppCompatActivity {

    EditText etname,etdesc,etpunt;
    Button btnchoose, btnadd,btnlist;
    ImageView imageView;

    public static PoiDB sqliteHelper;

    final int REQUEST_CODE_GALLERY=999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_site);


        init();

        sqliteHelper=new PoiDB(this,"POIDB.sqlite",null,1);

        sqliteHelper.queryData("CREATE TABLE IF NOT EXISTS POI (Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, desc VARCHAR, point VARCHAR, image BLOG)");

        btnchoose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(AddSite.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_GALLERY);
                            }
        });

        btnadd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try{
                    sqliteHelper.InsertPOI(
                            etname.getText().toString().trim(),
                            etdesc.getText().toString().trim(),
                            etpunt.getText().toString().trim(),
                            imageViewToByte(imageView)
                    );
                Toast.makeText(getApplicationContext(), "Imagen agregada", Toast.LENGTH_SHORT).show();
               etname.setText("");
               etdesc.setText("");
               etpunt.setText("");
               imageView.setImageResource(R.mipmap.ic_launcher);


                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });

        btnlist = findViewById(R.id.listButton);
        btnlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openActivityMenu();
            }
        });

    }

    private byte[] imageViewToByte(ImageView image){
        Bitmap bitmap=((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray=stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i,REQUEST_CODE_GALLERY);
            }
            else{
                Toast.makeText(getApplicationContext(), "No tienes permiso para elegir esta imagen",Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==REQUEST_CODE_GALLERY && resultCode==RESULT_OK && data !=null){
            Uri uri=data.getData();

            try{
                InputStream is=getContentResolver().openInputStream(uri);
                Bitmap bm= BitmapFactory.decodeStream(is);
                imageView.setImageBitmap(bm);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init(){
        etname = (EditText)findViewById(R.id.nameSite);
        etdesc = (EditText)findViewById(R.id.descripSite);
        etpunt = (EditText)findViewById(R.id.pointSite);
        btnchoose = (Button) findViewById(R.id.chooseButton);
        btnadd = (Button) findViewById(R.id.addButton);
        btnlist = (Button) findViewById(R.id.listButton);
        imageView=(ImageView) findViewById(R.id.imageView3);


    }

    public void openActivityMenu(){
        Intent intent = new Intent(this, MenuU.class);
        startActivity(intent);
    }

}
