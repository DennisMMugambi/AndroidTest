package com.rapptrlabs.androidtest.ui.animation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.rapptrlabs.androidtest.R
import com.rapptrlabs.androidtest.databinding.FragmentAnimationBinding
import com.rapptrlabs.androidtest.util.DragTouchListener
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class AnimationFragment : Fragment(R.layout.fragment_animation) {

    private lateinit var binding: FragmentAnimationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAnimationBinding.bind(view)


        binding.logoImage.setOnTouchListener(DragTouchListener())


        binding.fadeInButton.setOnClickListener {
            animateLogoFadeInOut()
        }

    }

    private fun animateLogoFadeInOut() {
        val fadeOut = ObjectAnimator.ofFloat(binding.logoImage, "alpha", 1f, 0f).apply {
            duration = 1000 // 1 second duration
        }

        val fadeIn = ObjectAnimator.ofFloat(binding.logoImage, "alpha", 0f, 1f).apply {
            duration = 1000
        }

        val animatorSet = AnimatorSet()
        animatorSet.play(fadeIn).after(fadeOut)
        animatorSet.start()

        shakeAnimation(binding.logoImage)
    }

    private fun shakeAnimation(imageView: ImageView) {
        val animator = ObjectAnimator.ofFloat(imageView, "translationX", 0f, 25f, -25f, 25f, -25f, 15f, -15f, 6f, -6f, 0f)
        animator.duration = 500
        animator.start()
    }

}
