package com.dam2.examenm08
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var filmDbHelper: FilmDBHelper
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(ListFragment())
        val bottomNav : BottomNavigationView = findViewById(R.id.bottom_navigation)
        filmDbHelper = FilmDBHelper(this)
        bottomNav.setOnItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.menu_films -> {
                    loadFragment(ListFragment())
                    true
                }

                R.id.menu_add_film -> {
                    loadFragment(AddFilmFragment())
                    true
                }

                R.id.menu_settings -> {
                    loadFragment(SettingsFragment())
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onDestroy() {
        filmDbHelper.close()
        super.onDestroy()
    }
}