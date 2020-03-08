package co.edu.udea.compumovil.gr01_20192.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuU extends AppCompatActivity {

    private Button buttonnew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_u);


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
