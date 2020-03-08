package co.edu.udea.compumovil.gr01_20192.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import OpenHelper.SQLite_OpenHelper;

public class RegisterU extends AppCompatActivity {

    private Button buttontoLogin;
    Button btnGrabarUsu;
    EditText txtUser,txtEmail,txtPassword;

    SQLite_OpenHelper helper=new SQLite_OpenHelper(this, "User", null,1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //1. Boton Atras Intent a pantalla de login

        buttontoLogin = findViewById(R.id.backRegisterButton);
        buttontoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openActivityLogin();
            }
        });
        //1.

        //2. registro usuarios en BD
        btnGrabarUsu=(Button)findViewById(R.id.bRegisterButton);
        txtUser=(EditText)findViewById(R.id.userRegisterText);
        txtEmail=(EditText)findViewById(R.id.emailRegisterText);
        txtPassword=(EditText)findViewById(R.id.passwordRegisterText);

        btnGrabarUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.Open();
                helper.InsertReg(String.valueOf(txtUser.getText()),
                        String.valueOf(txtEmail.getText()),
                        String.valueOf(txtPassword.getText()));
                helper.Close();

                Toast.makeText(getApplicationContext(), "Usuario Registrado"
                ,Toast.LENGTH_LONG).show();

                Intent i=new Intent(getApplicationContext(),LoginU.class);
                startActivity(i);
            }
        });
        //2.

    }

    //1.2
    public void openActivityLogin(){
        Intent intent = new Intent(this, LoginU.class);
        startActivity(intent);
    }
    //1.2

    //2.
}

