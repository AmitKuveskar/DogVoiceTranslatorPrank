package com.example.dogvoicetranslatorprank.APICalling.Sounds

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.dogvoicetranslatorprank.BottomNavigationActivity
import com.example.dogvoicetranslatorprank.R

class SoundActivity : AppCompatActivity() {

    private lateinit var backbtn: ImageView
    private lateinit var playButton: ImageView
    private lateinit var mediaPlayer: MediaPlayer
    private  lateinit var  Image :de.hdodenhof.circleimageview.CircleImageView
    private lateinit var seekBar: SeekBar
    private var isPlaying: Boolean = false
    private var audioUrl: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sound)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        backbtn = findViewById(R.id.backbtn)
        playButton = findViewById(R.id.playbtn)
        seekBar = findViewById(R.id.seekbar)
        Image = findViewById(R.id.mainImage)




        // Retrieve the image URL from the intent extras
        val imageUrl = intent.getStringExtra("post_image")

        // Use the image URL to display the image using Glide or any other image loading library
        Glide.with(this)
            .load(imageUrl)
            .into(Image)

        // Retrieve the audio URL from the intent extra
        audioUrl = intent.getStringExtra("audio_url")



        // Check if audio URL is not null or empty
        if (!audioUrl.isNullOrEmpty()) {
            // Create MediaPlayer instance with the audio URL
            mediaPlayer = MediaPlayer().apply {
                setDataSource(audioUrl)
                prepareAsync()
                setOnPreparedListener { startPlaying() }
                setOnCompletionListener { releaseMediaPlayer() }
            }
        } else {
            Toast.makeText(this, "Audio URL is empty", Toast.LENGTH_SHORT).show()
            finish() // Finish the activity if audio URL is empty
        }

        backbtn.setOnClickListener {
            releaseMediaPlayer()
            val intent = Intent(this@SoundActivity, BottomNavigationActivity::class.java)
            startActivity(intent)
        }

        // Set click listener for the play button
        playButton.setOnClickListener {
            if (isPlaying) {
                pauseAudio()
            } else {
                startAudio()
            }
        }

        // Set up SeekBar
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser && mediaPlayer.isPlaying) {
                    mediaPlayer.seekTo(progress)
                }
            }


            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // No implementation needed
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // No implementation needed
            }
        })
    }

    private fun startPlaying() {
        mediaPlayer.start()
        isPlaying = true
        playButton.setImageResource(R.drawable.pause)
        seekBar.max = mediaPlayer.duration

        // Update SeekBar progress as audio plays
        Thread {
            while (mediaPlayer.isPlaying) {
                try {
                    runOnUiThread {
                        seekBar.progress = mediaPlayer.currentPosition
                    }
                    Thread.sleep(1000) // Update every second
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    private fun startAudio() {
        mediaPlayer.start()
        isPlaying = true
        playButton.setImageResource(R.drawable.pause)
    }

    private fun pauseAudio() {
        mediaPlayer.pause()
        isPlaying = false
        playButton.setImageResource(R.drawable.play)
    }

    private fun releaseMediaPlayer() {
        mediaPlayer.release() // Release MediaPlayer to free resources
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseMediaPlayer() // Release MediaPlayer when activity is destroyed
    }
}
