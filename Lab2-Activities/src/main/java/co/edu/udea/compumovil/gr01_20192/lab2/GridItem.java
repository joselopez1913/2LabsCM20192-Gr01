package co.edu.udea.compumovil.gr01_20192.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class GridItem extends AppCompatActivity {

    TextView gridData1,gridData2,gridData3 ;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_view_poi);

        imageView = findViewById(R.id.imagePOI2);
        gridData1 = findViewById(R.id.txtName2);
        gridData2 = findViewById(R.id.txtDesc2);
        gridData3 = findViewById(R.id.txtPoint2);

        Intent intent = getIntent();
        int receivedImage = intent.getIntExtra("image2",0);
        String receivedName1 =  intent.getStringExtra("name2");
        String receivedName2 =  intent.getStringExtra("desc2");
        String receivedName3 =  intent.getStringExtra("point2");

        imageView.setImageResource(receivedImage);
        gridData1.setText(receivedName1);
        gridData2.setText(receivedName2);
        gridData3.setText(receivedName3);

        //enable back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

