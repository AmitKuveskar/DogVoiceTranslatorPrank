package com.example.dogvoicetranslatorprank.onBoarding.screens

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
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
//
//        radioButtonEnglish.setOnClickListener { setLocale("en") }
//        radioButtonHindi.setOnClickListener { setLocale("hi") }
//        radioButtonSpanish.setOnClickListener { setLocale("es") }
//        radioButtonFrench.setOnClickListener { setLocale("fr") }
//        radioButtonPortuguese.setOnClickListener { setLocale("pt") }
//        radioButtonKorean.setOnClickListener { setLocale("ko") }
//        radioButtonRussian.setOnClickListener { setLocale("ru") }
        setRadioButtonListeners()

       return view
    }

//    private fun setLocale(languageCode: String) {
//        val locale = Locale(languageCode)
//        Locale.setDefault(locale)
//        val config = Configuration()
//        config.setLocale(locale)
//        requireActivity().baseContext.resources.updateConfiguration(config, requireActivity().baseContext.resources.displayMetrics)
//
//        // Save language preference
//        val prefs: SharedPreferences = requireActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE)
//        val editor = prefs.edit()
//        editor.putString("My_Lang", languageCode)
//        editor.apply()
//
//        // Show toast message
//        Toast.makeText(requireActivity(), "Language set to ${locale.displayLanguage}", Toast.LENGTH_SHORT).show()
//
//        // Restart activity to apply the language change
//        requireActivity().recreate()
//    }
//
//    private fun loadLocale() {
//        val prefs: SharedPreferences = requireActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE)
//        val languageCode = prefs.getString("My_Lang", "")
//        if (languageCode != null) {
//            setLocale(languageCode)
//        }
//    }
//
//    override fun onResume() {
//        super.onResume()
//        loadLocale()
//    }
//}
//

    private fun setRadioButtonListeners() {
        // Set click listener for each RadioButton
        radioButtonEnglish.setOnClickListener { onLanguageSelected(radioButtonEnglish) }
        radioButtonHindi.setOnClickListener { onLanguageSelected(radioButtonHindi) }
        radioButtonSpanish.setOnClickListener { onLanguageSelected(radioButtonSpanish) }
        radioButtonFrench.setOnClickListener { onLanguageSelected(radioButtonFrench) }
        radioButtonPortuguese.setOnClickListener { onLanguageSelected(radioButtonPortuguese) }
        radioButtonKorean.setOnClickListener { onLanguageSelected(radioButtonKorean) }
        radioButtonRussian.setOnClickListener { onLanguageSelected(radioButtonRussian) }
    }

    private fun onLanguageSelected(radioButton: RadioButton) {
        // Unselect the last checked RadioButton if it exists
        lastCheckedRadioButton?.isChecked = false

        // Check the current RadioButton
        radioButton.isChecked = true

        // Update the last checked RadioButton
        lastCheckedRadioButton = radioButton

        // Get the tag of the RadioButton and check if it's not null
        val languageCode = radioButton.tag as? String
        if (languageCode != null) {
            // Set the locale based on the selected language code
            setLocale(languageCode)
        } else {
            // Handle the case where the tag is null
            Toast.makeText(requireActivity(), "Language code is null", Toast.LENGTH_SHORT).show()
        }
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

        // Save language preference
        val prefs: SharedPreferences =
            requireActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString("My_Lang", languageCode)
        editor.apply()

        // Show toast message
        Toast.makeText(requireActivity(), "Language set to ${locale.displayLanguage}", Toast.LENGTH_SHORT).show()

        // Restart activity to apply the language change
        requireActivity().recreate()
    }

    private fun loadLocale() {
        val prefs: SharedPreferences =
            requireActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val languageCode = prefs.getString("My_Lang", "")
        if (languageCode != null) {
            setLocale(languageCode)
        }
    }

    override fun onResume() {
        super.onResume()

    }
}
