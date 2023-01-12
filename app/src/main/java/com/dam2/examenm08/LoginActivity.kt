package com.dam2.examenm08
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.util.DisplayMetrics
import android.widget.Toast
import java.util.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_ExamenM08)
        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)

        setAppLocale(sharedPreference.getString("lang", "ca")!!)

        setContentView(R.layout.activity_login)
        val usernameET = findViewById<EditText>(R.id.txtUsername)
        val passwordET = findViewById<EditText>(R.id.editTextNumberPassword)
        val loginBtn = findViewById<Button>(R.id.button)

//        val username = sharedPreference.getString("username", "")
//        val password = sharedPreference.getString("password", "")

//        if (username == usernameET.text.toString() && password == passwordET.text.toString()) {
//            val intent = Intent(applicationContext, MainActivity::class.java)
//            startActivity(intent)
//        }

        if (sharedPreference.contains("login")) {
            if(sharedPreference.getBoolean("login", false)==true){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

        loginBtn.setOnClickListener {
            if (usernameET.text.toString() == "user1" && passwordET.text.toString() == "123456") {
                val editor = sharedPreference.edit()
                editor.putString("username", usernameET.text.toString().trim())
                editor.putString("password", passwordET.text.toString().trim())
                editor.putBoolean("login", true)
                editor.apply()
                Toast.makeText(applicationContext, getString(R.string.credencials_ok), Toast.LENGTH_SHORT)
                    .show()
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, getString(R.string.credencials_f), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun setAppLocale(localeCode: String) {
        val resources: Resources = resources
        val dm: DisplayMetrics = resources.displayMetrics
        val config: Configuration = resources.configuration
        config.setLocale(Locale(localeCode.lowercase(Locale.getDefault())))

        resources.updateConfiguration(config, dm)
    }
}