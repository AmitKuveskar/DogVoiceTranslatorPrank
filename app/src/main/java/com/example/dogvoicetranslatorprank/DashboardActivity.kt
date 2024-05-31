package com.example.dogvoicetranslatorprank

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class DashboardActivity : AppCompatActivity() {
    lateinit var ButtonDrawer: ImageButton
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var  Soundsbtn: androidx.appcompat.widget.AppCompatImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        drawerLayout = findViewById(R.id.drawerLayout)
        ButtonDrawer = findViewById(R.id.ButtonDrawer)
        navigationView = findViewById(R.id.navigationview)
        navigationView.itemIconTintList = null
        Soundsbtn = findViewById(R.id.sounds)

        Soundsbtn.setOnClickListener {
           val intent = Intent(this,BottomNavigationActivity::class.java)
            startActivity(intent)
        }

        ButtonDrawer.setOnClickListener {
            drawerLayout.open()
        }

        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.dogsound -> {
                    // Handle Dog Sounds item click
                    true
                }
                R.id.dogvoicetranslator -> {
                    // Handle Dog Voice Translator item click
                    true
                }
                R.id.training -> {
                    // Handle Training item click
                    true
                }
                R.id.fakecall -> {
                    // Handle Fake Call item click
                    true
                }
                R.id.whistle -> {
                    // Handle Whistle item click
                    true
                }
                R.id.privacypolicy -> {
                    // Handle Privacy Policy item click
                    true
                }
                R.id.termsandconditions -> {
                    // Handle Terms and Conditions item click
                    true
                }
                else -> false
            }

        }


    }
}