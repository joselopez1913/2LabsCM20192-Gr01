package co.edu.udea.compumovil.gr01_20192.lab2.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.edu.udea.compumovil.gr01_20192.lab2.DataBase.UserDB;
import co.edu.udea.compumovil.gr01_20192.lab2.Entities.User;
import co.edu.udea.compumovil.gr01_20192.lab2.R;

public class LoginU extends AppCompatActivity {

    private Button buttonback,buttons;
    Button btnIngresar;
    private UserDB UDB ;

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


}
