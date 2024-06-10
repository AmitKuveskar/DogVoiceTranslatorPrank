package com.example.dogvoicetranslatorprank.onBoarding.screens

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.navigation.fragment.findNavController
import com.example.dogvoicetranslatorprank.AppPreference
import com.example.dogvoicetranslatorprank.R
import java.util.Locale

class FifthScreen : Fragment() {
    private lateinit var radioButtonEnglish: RadioButton
    private lateinit var radioButtonHindi: RadioButton
    private lateinit var radioButtonSpanish: RadioButton
    private lateinit var radioButtonFrench: RadioButton
    private lateinit var radioButtonPortuguese: RadioButton
    private lateinit var radioButtonKorean: RadioButton
    private lateinit var radioButtonRussian: RadioButton
    private lateinit var Continuebtn: AppCompatImageButton
    lateinit var  appPreference: AppPreference
    private var lastCheckedRadioButton: RadioButton? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_fifth_screen, container, false)
        radioButtonEnglish = view.findViewById(R.id.radio_button_english)
        radioButtonHindi = view.findViewById(R.id.radio_button_hindi)
        radioButtonSpanish = view.findViewById(R.id.radio_button_spanish)
        radioButtonFrench = view.findViewById(R.id.radio_button_french)
        radioButtonPortuguese = view.findViewById(R.id.radio_button_portugese)
        radioButtonKorean = view.findViewById(R.id.radio_button_korean)
        radioButtonRussian = view.findViewById(R.id.radio_button_russian)
        Continuebtn = view.findViewById(R.id.continuebtn)



        Continuebtn.setOnClickListener {
            val selectedRadioButton = lastCheckedRadioButton
            if (selectedRadioButton != null) {
                findNavController().navigate(R.id.action_viewPagerFragment_to_dashboardActivity)
                onBoardingfinished()
            } else {
                Toast.makeText(requireContext(), "Please select a language", Toast.LENGTH_SHORT).show()
            }

        }


        setRadioButtonListeners()


       return view
    }


    private fun setRadioButtonListeners() {
        // Set click listener for each RadioButton
        radioButtonEnglish.setOnClickListener { onLanguageSelected(radioButtonEnglish) }
        radioButtonHindi.setOnClickListener { onLanguageSelected(radioButtonHindi) }
        Log.d("LanguageChange", "Language selected: ${radioButtonHindi.text}")
        radioButtonSpanish.setOnClickListener { onLanguageSelected(radioButtonSpanish) }
        radioButtonFrench.setOnClickListener { onLanguageSelected(radioButtonFrench) }
        radioButtonPortuguese.setOnClickListener { onLanguageSelected(radioButtonPortuguese) }
        radioButtonKorean.setOnClickListener { onLanguageSelected(radioButtonKorean) }
        radioButtonRussian.setOnClickListener { onLanguageSelected(radioButtonRussian) }
    }


    private fun onLanguageSelected(radioButton: RadioButton) {
        // Uncheck the last checked RadioButton
        lastCheckedRadioButton?.isChecked = false
        // Check the newly selected RadioButton
        radioButton.isChecked = true
        // Update the last checked RadioButton
        lastCheckedRadioButton = radioButton
        // Get the language code from the tag of the selected RadioButton
        val languageCode = radioButton.tag.toString()
        // Change the locale
        setLocale(languageCode)

    }



    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        requireActivity().baseContext.resources.updateConfiguration(
            config,
            requireActivity().baseContext.resources.displayMetrics
        )
        // Show toast message
        Toast.makeText(requireActivity(), "Language set to ${locale.displayLanguage}", Toast.LENGTH_SHORT).show()
    }

            private  fun onBoardingfinished(){
                val sharedPreferences = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putBoolean("Finished", true)
                editor.apply()
            }
}
