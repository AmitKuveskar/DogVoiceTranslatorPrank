package com.example.dogvoicetranslatorprank.Fragments



import android.content.Intent
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.dogvoicetranslatorprank.DashboardActivity
import com.example.dogvoicetranslatorprank.R

class WhistleFragment : Fragment() {

    private lateinit var whistleButton: ImageButton
    private lateinit var seekBar: SeekBar
    private lateinit var frequencyTextView: TextView
    lateinit var Homebtn: ImageView
    private var whistleFrequency: Double = 20000.0 // Default frequency 20 kHz

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_whistle, container, false)

        Homebtn = view.findViewById(R.id.home)
        Homebtn.setOnClickListener {
            val intent = Intent(requireActivity(), DashboardActivity::class.java)
            startActivity(intent)
        }

        // Find UI elements
        whistleButton = view.findViewById(R.id.whistleButton)
        seekBar = view.findViewById(R.id.seekbar)
        frequencyTextView = view.findViewById(R.id.text)

        // Update TextView with initial frequency
        updateFrequencyTextView(whistleFrequency)

        // Set up the whistle button click listener
        whistleButton.setOnClickListener {
            playDogWhistle(whistleFrequency)
        }

        // Set up the seekbar listener to change frequency and update the TextView
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Map the seekbar progress to a frequency range, e.g., 20 kHz to 45 kHz
                whistleFrequency = 20000.0 + progress * 250.0
                updateFrequencyTextView(whistleFrequency)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Optional: handle start of touch
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Optional: handle end of touch
            }
        })

        return view
    }

    private fun updateFrequencyTextView(frequency: Double) {
        // Update the TextView to display the current frequency in kHz
        frequencyTextView.text = String.format("%.2f kHz", frequency / 1000)
    }

    private fun playDogWhistle(frequency: Double) {
        val durationMs = 1000 // 1 second, modify as needed

        val tone = generateTone(frequency, durationMs)
        val audioTrack = AudioTrack(
            AudioManager.STREAM_MUSIC,
            44100,
            AudioFormat.CHANNEL_OUT_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
            tone.size * 2,
            AudioTrack.MODE_STATIC
        )

        audioTrack.write(tone, 0, tone.size)
        audioTrack.play()

        audioTrack.setNotificationMarkerPosition(tone.size)
        audioTrack.setPlaybackPositionUpdateListener(object : AudioTrack.OnPlaybackPositionUpdateListener {
            override fun onMarkerReached(track: AudioTrack?) {
                track?.release()
            }

            override fun onPeriodicNotification(track: AudioTrack?) {
                // Do nothing
            }
        })
    }

    private fun generateTone(frequency: Double, durationMs: Int): ShortArray {
        val sampleRate = 44100
        val numSamples = durationMs * sampleRate / 1000
        val samples = ShortArray(numSamples)

        for (i in samples.indices) {
            val angle = 2.0 * Math.PI * i / (sampleRate / frequency)
            samples[i] = (Math.sin(angle) * Short.MAX_VALUE).toInt().toShort()
        }

        return samples
    }
}
