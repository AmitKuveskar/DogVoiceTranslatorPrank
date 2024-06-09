package com.example.dogvoicetranslatorprank.APICalling.FakeCall

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.airbnb.lottie.LottieAnimationView
import com.example.dogvoicetranslatorprank.R

class IncomingCallActivity4 : AppCompatActivity() {

    private lateinit var acceptCallButton: com.airbnb.lottie.LottieAnimationView
    private lateinit var rejectCallButton: com.airbnb.lottie.LottieAnimationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_incoming_call4)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        acceptCallButton = findViewById(R.id.accept_call_button)
        rejectCallButton = findViewById(R.id.reject_call_button)
        val CallRingView = findViewById<LottieAnimationView>(R.id.accept_call_button)
        CallRingView.setAnimation(R.raw.callring)
        // Optional, as it's set in XML
        CallRingView.playAnimation()

        val RejectView = findViewById<LottieAnimationView>(R.id.reject_call_button)
        RejectView.setAnimation(R.raw.rejectcall)
        // Optional, as it's set in XML
        RejectView.playAnimation()
        RejectView.rotation = 130f



        acceptCallButton.setOnClickListener {
            val intent = Intent(this, FakeVideoCallActivity4::class.java)
            startActivity(intent)
            finish()
        }

        rejectCallButton.setOnClickListener {
            finish()
        }
    }

}

