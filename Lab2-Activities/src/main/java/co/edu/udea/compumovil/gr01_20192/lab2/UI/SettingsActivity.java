package co.edu.udea.compumovil.gr01_20192.lab2.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import co.edu.udea.compumovil.gr01_20192.lab2.R;

public class SettingsActivity extends AppCompatActivity {

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.xml.preferences);
    }
}
