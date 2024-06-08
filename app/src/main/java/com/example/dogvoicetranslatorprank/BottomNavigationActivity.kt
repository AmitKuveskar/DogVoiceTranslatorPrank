package com.example.dogvoicetranslatorprank



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dogvoicetranslatorprank.Fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationActivity : AppCompatActivity() {
    lateinit var Bottom_Nav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        Bottom_Nav = findViewById(R.id.Bottom_Nav)
        Bottom_Nav.itemIconTintList = null

        // Handle bottom navigation item clicks
        Bottom_Nav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.dogsound -> {
                    replaceFragment(DogSoundsFragment())
                    true
                }
                R.id.dogtraining -> {
                    replaceFragment(VoiceTranslatorFragment())
                    true
                }
                R.id.training -> {
                    replaceFragment(TrainingFragment())
                    true
                }
                R.id.fakecall -> {
                    replaceFragment(FakeCallFragment())
                    true
                }
                R.id.whistle -> {
                    replaceFragment(WhistleFragment())
                    true
                }
                else -> false
            }
        }

        // Check if there's a specific fragment to load from the intent
        val targetFragment = intent.getStringExtra("TARGET_FRAGMENT")
        if (targetFragment != null) {
            when (targetFragment) {
                "WhistleFragment" -> {
                    replaceFragment(WhistleFragment())
                    Bottom_Nav.selectedItemId = R.id.whistle
                }
                "DogSoundsFragment" -> {
                    replaceFragment(DogSoundsFragment())
                    Bottom_Nav.selectedItemId = R.id.dogsound
                }
                "VoiceTranslatorFragment" -> {
                    replaceFragment(VoiceTranslatorFragment())
                    Bottom_Nav.selectedItemId = R.id.dogtraining
                }
                "TrainingFragment" -> {
                    replaceFragment(TrainingFragment())
                    Bottom_Nav.selectedItemId = R.id.training
                }
                "FakeCallFragment" -> {
                    replaceFragment(FakeCallFragment())
                    Bottom_Nav.selectedItemId = R.id.fakecall
                }
                else -> replaceFragment(DogSoundsFragment())
            }
        } else {
            // Default to DogSoundsFragment if no target is specified
            replaceFragment(DogSoundsFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .commit()
    }
}
