package com.example.dogvoicetranslatorprank.Fragments

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.dogvoicetranslatorprank.DashboardActivity
import com.example.dogvoicetranslatorprank.R
import java.io.IOException
import java.util.*

class VoiceTranslatorFragment : Fragment() {
    private lateinit var micIcon: ImageView
    private lateinit var recordIcon: ImageView
    lateinit var Homebtn: View
    private lateinit var recordingTimeTextView: TextView
    private lateinit var instructionTextView: TextView
    private var recorder: MediaRecorder? = null
    private var recordingTimeSeconds: Int = 0
    private var recordingTimer: Timer? = null
    private var isRecording: Boolean = false

    companion object {
        private const val LOG_TAG = "AudioRecordTest"
        private const val REQUEST_RECORD_AUDIO_PERMISSION = 200
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_voice_translator, container, false)

        micIcon = view.findViewById(R.id.micicon)
        recordIcon = view.findViewById(R.id.recordicon)
        recordingTimeTextView = view.findViewById(R.id.recordingTimeTextView)
        instructionTextView = view.findViewById(R.id.txthint)
        Homebtn = view.findViewById(R.id.home)
        Homebtn.setOnClickListener {
            val intent = Intent(requireActivity(), DashboardActivity::class.java)
            startActivity(intent)
        }

        micIcon.setOnClickListener {
            if (isRecording) {
                stopRecording()
                showCustomView()
                isRecording = false
                stopRecordingTimer()
                instructionTextView.visibility = View.VISIBLE
                recordingTimeTextView.visibility = View.GONE
                micIcon.visibility = View.VISIBLE
                recordIcon.visibility = View.GONE
            } else {
                if (ContextCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.RECORD_AUDIO
                    )
                    != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        requireActivity(),
                        arrayOf(Manifest.permission.RECORD_AUDIO), REQUEST_RECORD_AUDIO_PERMISSION
                    )
                } else {
                    startRecording()
                    startRecordingTimer()
                    isRecording = true
                    instructionTextView.visibility = View.GONE
                    micIcon.visibility = View.GONE
                    recordingTimeTextView.visibility = View.VISIBLE
                    recordIcon.visibility = View.VISIBLE
                }
            }
        }

        recordIcon.setOnClickListener {
            if (isRecording) {
                stopRecording()
                showCustomView()
                isRecording = false
                stopRecordingTimer()
                instructionTextView.visibility = View.VISIBLE
                recordingTimeTextView.visibility = View.GONE
                micIcon.visibility = View.VISIBLE
                recordIcon.visibility = View.GONE
            } else {
                if (ContextCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.RECORD_AUDIO
                    )
                    != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        requireActivity(),
                        arrayOf(Manifest.permission.RECORD_AUDIO), REQUEST_RECORD_AUDIO_PERMISSION
                    )
                } else {
                    startRecording()
                    startRecordingTimer()
                    isRecording = true
                    instructionTextView.visibility = View.GONE
                    micIcon.visibility = View.GONE
                    recordingTimeTextView.visibility = View.VISIBLE
                    recordIcon.visibility = View.VISIBLE
                }
            }
        }

        return view
    }

    private fun startRecording() {
        val fileName = "${requireContext().externalCacheDir?.absolutePath}/audiorecordtest.3gp"

        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            setOutputFile(fileName)

            try {
                prepare()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }

            start()
        }
    }

    private fun stopRecording() {
        recorder?.apply {
            try {
                stop()
                release()
            } catch (e: Exception) {
                Log.e(LOG_TAG, "Error stopping and releasing MediaRecorder: ${e.message}")
            }
        }
        recorder = null
    }

    private fun startRecordingTimer() {
        recordingTimeSeconds = 0
        recordingTimeTextView.visibility = View.VISIBLE
        recordingTimer = Timer()
        recordingTimer?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                activity?.runOnUiThread {
                    recordingTimeSeconds++
                    val minutes = recordingTimeSeconds / 60
                    val seconds = recordingTimeSeconds % 60
                    val timeString =
                        String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
                    recordingTimeTextView.text = timeString
                }
            }
        }, 0, 1000)
    }

    private fun stopRecordingTimer() {
        recordingTimer?.cancel()
        recordingTimeTextView.visibility = View.GONE
    }

    private fun showCustomView() {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.custom_popup, null)
        dialogBuilder.setView(dialogView)

        // Create and show the AlertDialog
        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }

}
