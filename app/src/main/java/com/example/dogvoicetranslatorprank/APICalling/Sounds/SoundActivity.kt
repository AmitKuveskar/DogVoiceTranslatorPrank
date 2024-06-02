package com.example.dogvoicetranslatorprank.APICalling.Sounds

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dogvoicetranslatorprank.BottomNavigationActivity
import com.example.dogvoicetranslatorprank.R

class SoundActivity : AppCompatActivity() {

    lateinit var backbtn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sound)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dropdownSpinner: Spinner = findViewById(R.id.dropdownSpinner)
        backbtn = findViewById(R.id.backbtn)
        backbtn.setOnClickListener {
            val intent = Intent(this@SoundActivity, BottomNavigationActivity::class.java)
            startActivity(intent)
        }

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.dropdown_array, // Array of dropdown items
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            dropdownSpinner.adapter = adapter
        }

    }
}