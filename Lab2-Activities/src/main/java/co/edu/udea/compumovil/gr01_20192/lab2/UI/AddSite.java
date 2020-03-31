package co.edu.udea.compumovil.gr01_20192.lab2.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.room.Room;

import android.Manifest;
import android.annotation.SuppressLint;
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
import java.nio.ByteBuffer;

import co.edu.udea.compumovil.gr01_20192.lab2.DataBase.PoiDB;
import co.edu.udea.compumovil.gr01_20192.lab2.Entities.Poi;
import co.edu.udea.compumovil.gr01_20192.lab2.R;

public class AddSite extends AppCompatActivity {

    EditText edtName, edtDesc,edtPoint;
    Button btnChoose, btnAdd, btnList;
    ImageView imageView;

    private PoiDB PDB ;

    final int REQUEST_CODE_GALLERY = 999;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_site);

        PDB= PoiDB.getAppDatabase(getApplicationContext());

        edtName = (EditText)findViewById(R.id.nameSite);
        edtDesc = (EditText)findViewById(R.id.descripSite);
        edtPoint= (EditText)findViewById(R.id.pointSite);
        btnChoose = (Button) findViewById(R.id.chooseButton);
        btnAdd = (Button) findViewById(R.id.addButton);
        btnList = (Button) findViewById(R.id.listButton);
        imageView=(ImageView) findViewById(R.id.imageView3);



        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        AddSite.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    //insertar a la DB
                    PDB.poiDao().insert(new Poi(edtName.getText().toString().trim(),edtDesc.getText().toString().trim(),edtPoint.getText().toString().trim(),imageViewToByte(imageView)));

                    Toast.makeText(getApplicationContext(), "Imagen agregada", Toast.LENGTH_SHORT).show();
                    edtName.setText("");
                    edtDesc.setText("");
                    edtPoint.setText("");
                    imageView.setImageResource(R.mipmap.ic_launcher);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        btnList = findViewById(R.id.listButton);
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openActivityMenu();
            }
        });

    }

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "No tienes permiso para acceder a galeria", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    public void openActivityMenu(){
        Intent intent = new Intent(this, MenuU.class);
        startActivity(intent);
    }



}
