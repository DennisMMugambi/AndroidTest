package com.rapptrlabs.androidtest.ui.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.rapptrlabs.androidtest.R
import com.rapptrlabs.androidtest.data.model.UiState
import com.rapptrlabs.androidtest.databinding.FragmentChatBinding
import com.rapptrlabs.androidtest.util.Dialog
import com.rapptrlabs.androidtest.util.LoadingContent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

// TODO: Make the UI look like it does in the mock-up. Allow for horizontal screen rotation.

// TODO: Retrieve the chat data from http://dev.rapptrlabs.com/Tests/scripts/chat_log.php
// TODO: Parse this chat data from JSON into ChatLogMessageModel and display it.

@AndroidEntryPoint
class ChatFragment : Fragment(R.layout.fragment_chat) {

    private lateinit var binding: FragmentChatBinding

    private lateinit var chatAdapter: ChatAdapter

    private val viewModel: ChatViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentChatBinding.bind(view)

        handleState()

    }

    private fun handleState() {
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    is UiState.Empty -> {

                    }

                    is UiState.Loading -> {

                        Dialog.show(requireContext(), LoadingContent(""))
                    }

                    is UiState.Success -> {

                        Dialog.dimiss()

                    }

                    is UiState.Error -> {

                    }

                    else -> {

                    }
                }
            }
        }


        lifecycleScope.launch {
            viewModel.thread.collect { thread ->

                thread.let {
                    chatAdapter = ChatAdapter(thread)

                    binding.recyclerView.apply {
                        adapter = chatAdapter
                        setHasFixedSize(true)
                        layoutManager =
                            LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.VERTICAL,
                                false
                            )
                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.resetStates()
    }
}