package com.example.dogvoicetranslatorprank.APICalling.Sounds

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Spinner
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
    private lateinit var loopButton: ImageView
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var Image: de.hdodenhof.circleimageview.CircleImageView
    private lateinit var seekBar: SeekBar
    private var isPlaying: Boolean = false
    private var isLooping: Boolean = false
    private var audioUrl: String? = null
    private var playbackDuration: Int = 0
    private val handler = Handler()
    private var startTime = 0L
    private var durationRunnable: Runnable? = null

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
        loopButton = findViewById(R.id.loop)

        // Find the Spinner in the layout
        val dropdownSpinner: Spinner = findViewById(R.id.dropdownSpinner)

        // Create an ArrayAdapter using the custom layout and the string array
        val Times = arrayOf("OFF", "5s", "10s", "15s")
        val arrayAdapter = ArrayAdapter(this@SoundActivity, android.R.layout.simple_spinner_dropdown_item, Times)
        dropdownSpinner.adapter = arrayAdapter

        // Set item selected listener for the dropdown spinner
        dropdownSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val newDuration = when (position) {
                    0 -> 0 // Off
                    1 -> 5000 // 5 seconds
                    2 -> 10000 // 10 seconds
                    3 -> 15000 // 15 seconds
                    else -> 0
                }
                if (newDuration != playbackDuration) {
                    playbackDuration = newDuration
                    applyPlaybackDuration()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No implementation needed
            }
        }

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
                setOnPreparedListener {
                    seekBar.max = duration
                    startAudio()
                }
                setOnCompletionListener { onAudioCompletion() }
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
                if (mediaPlayer.currentPosition >= mediaPlayer.duration) {
                    mediaPlayer.seekTo(0) // Reset the MediaPlayer if it has reached the end
                }
                startAudio()
            }
        }

        // Set click listener for the loop button
        loopButton.setOnClickListener {
            isLooping = !isLooping
            mediaPlayer.isLooping = isLooping
            loopButton.setImageResource(if (isLooping) R.drawable.infinityselected else R.drawable.infinity)
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

    private fun startAudio() {
        startTime = System.currentTimeMillis()
        mediaPlayer.start()
        isPlaying = true
        playButton.setImageResource(R.drawable.pause)

        if (playbackDuration > 0) {
            durationRunnable = Runnable {
                pauseAudio()
            }
            handler.postDelayed(durationRunnable!!, playbackDuration.toLong())
        }

        updateSeekBar()
    }

    private fun updateSeekBar() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (mediaPlayer.isPlaying) {
                    seekBar.progress = mediaPlayer.currentPosition
                    handler.postDelayed(this, 1000) // Update every second
                }
            }
        }, 1000)
    }

    private fun applyPlaybackDuration() {
        handler.removeCallbacksAndMessages(null) // Clear existing callbacks
        if (isPlaying) {
            val elapsedTime = System.currentTimeMillis() - startTime
            if (playbackDuration > 0 && playbackDuration - elapsedTime > 0) {
                durationRunnable = Runnable {
                    pauseAudio()
                }
                handler.postDelayed(durationRunnable!!, playbackDuration - elapsedTime)
            }
        }
    }

    private fun pauseAudio() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            isPlaying = false
            playButton.setImageResource(R.drawable.play)
            handler.removeCallbacksAndMessages(null) // Stop updating seek bar
        }
    }

    private fun releaseMediaPlayer() {
        if (this::mediaPlayer.isInitialized) {
            mediaPlayer.release() // Release MediaPlayer to free resources
        }
        handler.removeCallbacksAndMessages(null) // Remove all pending callbacks
    }

    private fun onAudioCompletion() {
        seekBar.progress = 0 // Reset the seek bar to the beginning
        mediaPlayer.seekTo(0) // Reset the MediaPlayer to the beginning
        isPlaying = false
        playButton.setImageResource(R.drawable.play)
        handler.removeCallbacksAndMessages(null) // Stop updating seek bar
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseMediaPlayer() // Release MediaPlayer when activity is destroyed
    }
}
