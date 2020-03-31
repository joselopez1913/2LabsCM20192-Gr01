package co.edu.udea.compumovil.gr01_20192.lab2.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import co.edu.udea.compumovil.gr01_20192.lab2.R;

public class SiteDetail extends AppCompatActivity {

    TextView site, desc, point;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_view_poi);

        image=findViewById(R.id.imagePOI22);
        site=findViewById(R.id.txt11);
        desc=findViewById(R.id.txt22);
        point=findViewById(R.id.txt33);


        Intent i=getIntent();
      //  image.setImageResource(i.getIntExtra("imagenes",0));
        site.setText(i.getStringExtra("site"));
        desc.setText(i.getStringExtra("desc"));
        point.setText(i.getStringExtra("point"));
    }
}
