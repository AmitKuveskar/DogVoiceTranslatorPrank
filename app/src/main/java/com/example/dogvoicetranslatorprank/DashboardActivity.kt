package com.example.dogvoicetranslatorprank

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class DashboardActivity : AppCompatActivity() {
    lateinit var ButtonDrawer: ImageButton
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var  Soundsbtn: androidx.appcompat.widget.AppCompatImageButton
    lateinit var  Trainingbtn: ImageView
    lateinit var  Translatebtn: ImageView
    lateinit var  FakeCallbtn: ImageView
    lateinit var  Whistlebtn: ImageView

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
        Trainingbtn = findViewById(R.id.dogtraining)
        Translatebtn = findViewById(R.id.translatebtn)
        FakeCallbtn = findViewById(R.id.fakecallbtn)
        Whistlebtn = findViewById(R.id.whistlebtn)

        Trainingbtn.setOnClickListener {
            // Create an intent to navigate to BottomNavigationActivity
            val intent = Intent(this@DashboardActivity, BottomNavigationActivity::class.java)
            // Add an extra to specify the WhistleFragment target
            intent.putExtra("TARGET_FRAGMENT", "TrainingFragment")
            startActivity(intent)
        }

        Soundsbtn.setOnClickListener {
           val intent = Intent(this@DashboardActivity,BottomNavigationActivity::class.java)
            intent.putExtra("TARGET_FRAGMENT", "DogSoundsFragment")
            startActivity(intent)
        }

        Translatebtn.setOnClickListener {
            val intent = Intent(this@DashboardActivity,BottomNavigationActivity::class.java)
            intent.putExtra("TARGET_FRAGMENT", "VoiceTranslatorFragment")
            startActivity(intent)
        }

        FakeCallbtn.setOnClickListener {
            val intent = Intent(this@DashboardActivity,BottomNavigationActivity::class.java)
            intent.putExtra("TARGET_FRAGMENT", "FakeCallFragment")
            startActivity(intent)
        }

        Whistlebtn.setOnClickListener {
            val intent = Intent(this@DashboardActivity,BottomNavigationActivity::class.java)
            intent.putExtra("TARGET_FRAGMENT", "WhistleFragment")
            startActivity(intent)
        }




        ButtonDrawer.setOnClickListener {
            drawerLayout.open()
        }



        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.dogsound -> {
                    val intent = Intent(this@DashboardActivity, BottomNavigationActivity::class.java)
                    intent.putExtra("TARGET_FRAGMENT", "DogSoundFragment")
                    startActivity(intent)
                    true
                }
                R.id.dogvoicetranslator -> {
                    val intent = Intent(this@DashboardActivity, BottomNavigationActivity::class.java)
                    intent.putExtra("TARGET_FRAGMENT", "VoiceTranslatorFragment")
                    startActivity(intent)
                    true
                }
                R.id.training -> {
                    val intent = Intent(this@DashboardActivity, BottomNavigationActivity::class.java)
                    intent.putExtra("TARGET_FRAGMENT", "TrainingFragment")
                    startActivity(intent)
                    true
                }
                R.id.fakecall -> {
                    val intent = Intent(this@DashboardActivity, BottomNavigationActivity::class.java)
                    intent.putExtra("TARGET_FRAGMENT", "FakeCallFragment")
                    startActivity(intent)
                    true
                }
                R.id.whistle -> {
                    val intent = Intent(this@DashboardActivity, BottomNavigationActivity::class.java)
                    intent.putExtra("TARGET_FRAGMENT", "WhistleFragment")
                    startActivity(intent)
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