package com.example.dogvoicetranslatorprank

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import java.util.logging.Handler

class SplashFragment : Fragment() {
    lateinit var DogIcon: ImageView
    lateinit var AppName: ImageView
    lateinit var animFadeIN : Animation
    lateinit var animBounce :Animation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        android.os.Handler().postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
        }, 3000)

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        DogIcon = view.findViewById(R.id.dogimg)
        AppName = view.findViewById(R.id.title)
        val dogWalkAnimationView = view.findViewById<LottieAnimationView>(R.id.dogwalk)
        dogWalkAnimationView.setAnimation(R.raw.dogwalk) // Optional, as it's set in XML
        dogWalkAnimationView.playAnimation() // Pl


        animFadeIN = AnimationUtils.loadAnimation(context,R.anim.fade_in)
        animBounce = AnimationUtils.loadAnimation(context,R.anim.bounce)

        AppName.startAnimation(animBounce)
        DogIcon.startAnimation(animFadeIN)

        return view
    }

}