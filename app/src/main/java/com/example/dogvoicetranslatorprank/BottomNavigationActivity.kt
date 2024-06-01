package com.example.dogvoicetranslatorprank

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.dogvoicetranslatorprank.Fragments.DogSoundsFragment
import com.example.dogvoicetranslatorprank.Fragments.FakeCallFragment
import com.example.dogvoicetranslatorprank.Fragments.TrainingFragment
import com.example.dogvoicetranslatorprank.Fragments.VoiceTranslatorFragment
import com.example.dogvoicetranslatorprank.Fragments.WhistleFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationActivity : AppCompatActivity() {
    lateinit var Bottom_Nav : BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        Bottom_Nav = findViewById(R.id.Bottom_Nav)
        Bottom_Nav.itemIconTintList = null

        Bottom_Nav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.dogsound ->{
                    replacefragment(DogSoundsFragment())
                    true
                }
                R.id.dogtraining ->{
                    replacefragment(VoiceTranslatorFragment())
                    true
                }
                R.id.training ->{
                    replacefragment(TrainingFragment())
                    true
                }
                R.id.fakecall ->{
                    replacefragment(FakeCallFragment())
                    true
                }
                R.id.whistle ->{
                    replacefragment(WhistleFragment())
                    true
                }
                else -> false
            }
        }

        replacefragment(DogSoundsFragment())
    }


    private fun replacefragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frame_container,fragment).commit() // using supportFragment we call fragments
    }
}