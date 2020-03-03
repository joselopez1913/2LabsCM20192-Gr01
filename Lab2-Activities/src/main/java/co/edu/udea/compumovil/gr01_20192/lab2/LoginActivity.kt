package co.edu.udea.compumovil.gr01_20192.lab2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        registerButton.setOnClickListener{
            val intent: Intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
