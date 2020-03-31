package co.edu.udea.compumovil.gr01_20192.lab2.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

import co.edu.udea.compumovil.gr01_20192.lab2.DataBase.PoiDB;
import co.edu.udea.compumovil.gr01_20192.lab2.DataBase.UserDB;
import co.edu.udea.compumovil.gr01_20192.lab2.Entities.Poi;
import co.edu.udea.compumovil.gr01_20192.lab2.Entities.User;
import co.edu.udea.compumovil.gr01_20192.lab2.R;

public class LoginU extends AppCompatActivity {

    private Button buttonback,buttons;
    Button btnIngresar;
    private UserDB UDB ;
    private PoiDB PDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        //1. intent a regisger activity
        buttonback = findViewById(R.id.registerButton);
        buttonback.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v){
                 openActivityRegister();
             }
        });
        //1.


        //2.Validar login
        UDB= UserDB.getAppDatabase(getApplicationContext());
        btnIngresar=(Button)findViewById(R.id.loginButton);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                EditText txtuser=(EditText)findViewById(R.id.userText);
                EditText txtpass=(EditText)findViewById(R.id.passwordText);

                try {
                    User Userp =  UDB.userDao().consulterUser(txtuser.getText().toString(), txtpass.getText().toString());
                    if (Userp!=null){
                        PDB= PoiDB.getAppDatabase(getApplicationContext());
                        addSites();
                        Intent i2 = new Intent(getApplicationContext(), AddSite.class);
                        startActivity(i2);
                    }else{
                        Toast.makeText(getApplicationContext(),"Usuario o Contraseña Incorrectos"
                                ,Toast.LENGTH_LONG).show();
                    }
                    txtuser.setText("");
                    txtpass.setText("");
                    txtuser.findFocus();

                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        });

        //2.


    }

    //1.2 metodo intent
    public void openActivityRegister(){
        Intent intent = new Intent(this, RegisterU.class);
        startActivity(intent);
    }
    //1.2

    //Sitios quemados
    public void addSites(){

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.atanasio);
        ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
        bitmap1.compress(Bitmap.CompressFormat.PNG, 100, stream1);
        byte[] i1 = stream1.toByteArray();

        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.elcastillo);
        ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
        bitmap2.compress(Bitmap.CompressFormat.PNG, 100, stream2);
        byte[] i2 = stream2.toByteArray();

        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.arvi);
        ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
        bitmap3.compress(Bitmap.CompressFormat.PNG, 100, stream3);
        byte[] i3 = stream3.toByteArray();

        PDB.poiDao().insert(new Poi("Unidad Deportiva Atanasio Girardot","Unidad con acceso a Piscinas, canchas, estadio","5",i1));
        PDB.poiDao().insert(new Poi("Castillo san Felipe","Lugar con aspecto colonial destinado para eventos","3",i2));
        PDB.poiDao().insert(new Poi("Parque Arvi","EcoParque con acceso a zonas naturales","4",i3));
    }


}
