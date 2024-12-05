package com.rapptrlabs.androidtest.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rapptrlabs.androidtest.R
import com.rapptrlabs.androidtest.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)

        navigateToDestinations()
    }

    private fun navigateToDestinations() {

        binding.loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
        }

        binding.chatButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_chatFragment)
        }

        binding.animationButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_animationFragment)
        }
    }
}